<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Transmissions Controller
 */

class Transmissions extends BE_Controller {
		/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'TRANSMISSIONS' );
	}

	/**
	 * List down the registered users
	 */
	function index() {

		// no publish filter
		$conds['no_publish_filter'] = 1;
	
		// get rows count
		$this->data['rows_count'] = $this->Transmission->count_all_by( $conds );
		
		// get transmissions
		$this->data['transmissions'] = $this->Transmission->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );
		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {
		
		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'trans_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )) );

		// pagination
		$this->data['rows_count'] = $this->Transmission->count_all_by( $conds );

		// search data
		$this->data['transmissions'] = $this->Transmission->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
		// load add list
		parent::search();
	}

		/**
	 * Create new one
	 */
	function add() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'trans_add' );

		// call the core add logic
		parent::add();
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save transmission
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {
		// start the transaction
		$this->db->trans_start();
		
		/** 
		 * Insert Transmission Records 
		 */
		$data = array();

		// prepare name
		if ( $this->has_data( 'name' )) {
			$data['name'] = $this->get_data( 'name' );
		}

		// save Transmission
		if ( ! $this->Transmission->save( $data, $id )) {
		// if there is an error in inserting user data,	

			// rollback the transaction
			$this->db->trans_rollback();

			// set error message
			$this->data['error'] = get_msg( 'err_model' );
			
			return;
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
				
				$this->set_flash_msg( 'success', get_msg( 'success_trans_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_trans_add' ));
			}
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
		 		// echo "bbbb";die;
				if ( strtolower( $this->Transmission->get_one( $id )->name ) == strtolower( $name )) {
				// if the name is existing name for that user id,
					return true;
				} else if ( $this->Transmission->exists( ($conds ))) {
				// if the name is existed in the system,
					$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
					return false;
				} 
			} else {
				// echo "aaaa";die;
				if ( $this->Transmission->exists( ($conds ))) {
				// if the name is existed in the system,
					$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
					return false;
				}
			}
			return true;
	}

	/**
	 * Update the existing one
	 */
	function edit( $id ) {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'trans_edit' );

		// load user
		$this->data['trans'] = $this->Transmission->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Delete all the news under transmission
	 *
	 * @param      integer  $trans_id  The transmission identifier
	 */
	function delete( $trans_id = 0 )
	{
		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );
		
		// delete categories and images
		$enable_trigger = true; 

		/** Note: enable trigger will delete news under transmission and all news related data */
		if ( !$this->ps_delete->delete_transmission( $trans_id, $enable_trigger )) {
		// if error in deleting transmission,

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
        	
			$this->set_flash_msg( 'success', get_msg( 'success_trans_delete' ));
		}
		
		redirect( $this->module_site_url());
	}

	/**
	 * Check transmission name via ajax
	 *
	 * @param      boolean  $cat_id  The cat identifier
	 */
	function ajx_exists( $id = false )
	{
		// get trans_name

		$name = $_REQUEST['name'];

		if ( $this->is_valid_name( $name, $id )) {

		// if the transmission name is valid,
			
			echo "true";
		} else {
		// if invalid transmission name,
			
			echo "false";
		}
	}

	/**
	 * Publish the record
	 *
	 * @param      integer  $trans_id  The Manufacturer identifier
	 */
	function ajx_publish( $trans_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$trans_data = array( 'status'=> 1 );
			
		// save data
		if ( $this->Transmission->save( $trans_data, $trans_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $manufacturer_id  The manufacturer identifier
	 */
	function ajx_unpublish( $trans_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$trans_data = array( 'status'=> 0 );
			
		// save data
		if ( $this->Transmission->save( $trans_data, $trans_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}

}