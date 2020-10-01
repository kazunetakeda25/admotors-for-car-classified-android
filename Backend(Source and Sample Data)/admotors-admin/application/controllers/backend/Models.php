<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Models Controller
 */
class Models extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'MODELS' );
	}

	/**
	 * List down the registered users
	 */
	function index() 
	{
		// no publish filter
		$conds['no_publish_filter'] = 1;

		// get rows count
		$this->data['rows_count'] = $this->Model->count_all_by( $conds );
		// get model
		$this->data['models'] = $this->Model->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );

		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() 
	{

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'model_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )),
						'manufacturer_id' => $this->searchterm_handler( $this->input->post('manufacturer_id')) );
		
		// pagination
		$this->data['rows_count'] = $this->Model->count_all_by( $conds );

		// search data
		$this->data['models'] = $this->Model->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );

		// load add list
		parent::search();
	}

	/**
	 * Create new one
	 */
	function add() 
	{
		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'model_add' );

		// call the core add logic
		parent::add();
		
	}

	/**
 	* Update the existing one
	*/
	function edit( $id ) 
	{
		
		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'model_edit' );

		// load user
		$this->data['model'] = $this->Model->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
		
	}


	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save model
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) 
	{

		// start the transaction
		$this->db->trans_start();
		$logged_in_user = $this->ps_auth->get_user_info();
		
		/** 
		 * Insert Model Records 
		 */
		$data = array();

	    // Model id
	    if ( $this->has_data( 'manufacturer_id' )) {
			$data['manufacturer_id'] = $this->get_data( 'manufacturer_id' );

		}
		// prepare name
		if ( $this->has_data( 'name' )) {
			$data['name'] = $this->get_data( 'name' );

		}

		//Default Status is Publish 
		$data['status'] = 1;

		// set timezone
		$data['added_user_id'] = $logged_in_user->user_id;

		if($id == "") {
			//save
			$data['added_date'] = date("Y-m-d H:i:s");
		} else {
			//edit
			unset($data['added_date']);
			$data['updated_date'] = date("Y-m-d H:i:s");
			$data['updated_user_id'] = $logged_in_user->user_id;
		}

		// save model
		if ( ! $this->Model->save( $data, $id )) {

		// if there is an error in inserting user data,	

			// rollback the transaction
			$this->db->trans_rollback();

			// set error message
			$this->data['error'] = get_msg( 'err_model' );
			
			return;
		}

		/** 
		 * Upload Image Records 
		 */
		
		if ( !$id ) {
			if ( ! $this->insert_images_icon_and_cover( $_FILES, 'model', $data['id'], "cover" )) {
				// if error in saving image

					// commit the transaction
					$this->db->trans_rollback();
					
					return;
				}
			if ( ! $this->insert_images_icon_and_cover( $_FILES, 'model_icon', $data['id'], "icon" )) {
				// if error in saving image

					// commit the transaction
					$this->db->trans_rollback();
					
					return;
				}	
		}
		
		
		// commit the transaction
		if ( ! $this->check_trans()) {
        	
			// set flash error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {

			if ( $id ) {
			// if user id is not false, show success_add message
				
				$this->set_flash_msg( 'success', get_msg( 'success_model_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_model_add' ));
			}
		}

		redirect( $this->module_site_url());
	}
	
/**
	 * Delete the record
	 * 1) delete model
	 * 2) delete image from folder and table
	 * 3) check transactions
	 */
	function delete( $id = 0 ) {

		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );

		// delete model and images
		$enable_trigger = true; 
		
		// delete model and images
		$type = "model";
	
		if ( !$this->ps_delete->delete_history( $id, $type, $enable_trigger )) {

			// set error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));

			// rollback
			$this->trans_rollback();

			// redirect to list view
			redirect( $this->module_site_url());
		}
			
		/**
		 * Check Transcation Status
		 */
		if ( !$this->check_trans()) {

			$this->set_flash_msg( 'error', get_msg( 'err_model' ));	
		} else {
        	
			$this->set_flash_msg( 'success', get_msg( 'success_model_delete' ));
		}
		
		redirect( $this->module_site_url());
	}


	/**
	 * Determines if valid input.
	 *
	 * @return     boolean  True if valid input, False otherwise.
	 */
	function is_valid_input( $id = 0 ) {
		
		$rule = 'required|callback_is_valid_name['. $id  .']';

		$this->form_validation->set_rules( 'name', get_msg( 'model_name' ), $rule);

		if ( $this->form_validation->run() == FALSE ) {
		// if there is an error in validating,

			return false;
		}

		return true;
	}

	/**
	 * Determines if valid name.
	 *
	 * @param      <type>   $name  The  name
	 * @param      integer  $id     The  identifier
	 *
	 * @return     boolean  True if valid name, False otherwise.
	 */
	function is_valid_name( $name, $id = 0 )
	{		
		$conds['name'] = $name;

		if ( html_entity_decode(strtolower( $this->Model->get_one( $id )->name )) == htmlentities(strtolower( $name ))) {
		// if the name is existing name for that user id,
			return true;
		} else if ( $this->Model->exists( ($conds ))) {
		// if the name is existed in the system,
			$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
			return false;
		}

		return true;
	}

	/**model
	 * Check model name via ajax
	 *
	 * @param      boolean  $model_id  The model identifier
	 */
	function ajx_exists( $model_id = false )
	{
		

		// get model name
		$name = $_REQUEST['name'];

		if ( $this->is_valid_name( $name, $model_id )) {
		// if the model name is valid,
			
			echo "true";
		} else {
		// if invalid model name,
			
			echo "false";
		}

		
	}


	/**
	 * Publish the record
	 *
	 * @param      integer  $model_id  The model identifier
	 */
	function ajx_publish( $model_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$model_data = array( 'status'=> 1 );
			
		// save data
		if ( $this->Model->save( $model_data, $model_id )) {
			echo true;
		} else {
			echo false;
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $model_id  The model identifier
	 */
	function ajx_unpublish( $model_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$model_data = array( 'status'=> 0 );
			
		// save data
		if ( $this->Model->save( $model_data, $model_id )) {
			echo true;
		} else {
			echo false;
		}
	}

}