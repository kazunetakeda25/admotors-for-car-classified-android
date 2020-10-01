<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Banners Controller
 */
class Banners extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'HOME BANNER' );
	}

	/**
	 * List down the registered users
	 */
	function index() {
	
		// no publish filter
		$conds['no_publish_filter'] = 1;
		// get rows count
		$this->data['rows_count'] = $this->Banner->count_all_by( $conds );
		
		// get banners
		$this->data['banners'] = $this->Banner->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );
		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {
		

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'ban_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )) );

		// no publish filter
		$conds['no_publish_filter'] = 1;

		// pagination
		$this->data['rows_count'] = $this->Banner->count_all_by( $conds );

		// search data
		$this->data['banners'] = $this->Banner->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
		// load add list
		parent::search();
	}

	/**
	 * Create new one
	 */
	function add() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'ban_add' );

		// call the core add logic
		parent::add();
	}

	/**
	 * Update the existing one
	 */
	function edit( $id ) {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'ban_edit' );

		// load user
		$this->data['ban'] = $this->Banner->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save Banner
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {
		// start the transaction
		$this->db->trans_start();
		
		/** 
		 * Insert Banner Records 
		 */
		$data = array();

		// prepare name
		if ( $this->has_data( 'name' )) {
			$data['name'] = $this->get_data( 'name' );
		}


		// save Banner
		if ( ! $this->Banner->save( $data, $id )) {
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
			if ( ! $this->insert_icon_images( $_FILES, 'banner', $data['id'], "cover" )) {
				// if error in saving image

				// commit the transaction
				$this->db->trans_rollback();
				
				return;
			}
		
		}

		/** 
		 * Check Transactions 
		 */

		// commit the transaction
		if ( ! $this->check_trans()) {
        	
			// set flash error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {

			if ( $id ) {
			// if user id is not false, show success_add message
				
				$this->set_flash_msg( 'success', get_msg( 'success_ban_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_ban_add' ));
			}
		}

		redirect( $this->module_site_url());
	}

	/**
	 * Delete the record
	 * 1) delete Banner
	 * 2) delete image from folder and table
	 * 3) check transactions
	 */
	function delete( $ban_id ) {

		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );
		
		// delete banner and images
		if ( !$this->ps_delete->delete_banner( $ban_id )) {

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
        	
			$this->set_flash_msg( 'success', get_msg( 'success_ban_delete' ));
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

		$this->form_validation->set_rules( 'name', get_msg( 'name' ), $rule);

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

		 	if( $id != "") {
		 	
				if ( strtolower( $this->Banner->get_one( $id )->name ) == strtolower( $name )) {
				// if the name is existing name for that user id,
					return true;
				} else if ( $this->Banner->exists( ($conds ))) {
				// if the name is existed in the system,
					$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
					return false;
				}
			} else {
				// echo "aaaa";die;
				if ( $this->Banner->exists( ($conds ))) {
				// if the name is existed in the system,
					$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
					return false;
				}
			}
			return true;
	}

	/**
	 * Check Banner name via ajax
	 *
	 * @param      boolean  $cat_id  The cat identifier
	 */
	function ajx_exists( $id = false )
	{
		// get Banner name
		$name = $_REQUEST['name'];

		if ( $this->is_valid_name( $name, $id )) {

		// if the Banner name is valid,
			
			echo "true";
		} else {
		// if invalid Banner name,
			
			echo "false";
		}
	}

	/**
	 * Publish the record
	 *
	 * @param      integer  $manufacturer_id  The Banner identifier
	 */
	function ajx_publish( $ban_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$ban_data = array( 'status'=> 1 );
			
		// save data
		if ( $this->Banner->save( $ban_data, $ban_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $ban_id  The banner identifier
	 */
	function ajx_unpublish( $ban_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$ban_data = array( 'status'=> 0 );
			
		// save data
		if ( $this->Banner->save( $ban_data, $ban_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
}