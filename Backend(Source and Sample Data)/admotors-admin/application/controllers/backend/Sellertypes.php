<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Sellertypes Controller
 */

class Sellertypes extends BE_Controller {
		/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'SELLER TYPES' );
	}

	/**
	 * List down the registered users
	 */
	function index() {

		// no publish filter
		$conds['no_publish_filter'] = 1;
	
		// get rows count
		$this->data['rows_count'] = $this->Sellertype->count_all_by( $conds );
		
		// get sellertypes
		$this->data['sellertypes'] = $this->Sellertype->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );
		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {
		
		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'seller_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )) );

		// pagination
		$this->data['rows_count'] = $this->Sellertype->count_all_by( $conds );

		// search data
		$this->data['sellertypes'] = $this->Sellertype->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
		// load add list
		parent::search();
	}

		/**
	 * Create new one
	 */
	function add() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'seller_add' );

		// call the core add logic
		parent::add();
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save fuel
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {
		// start the transaction
		$this->db->trans_start();
		
		/** 
		 * Insert Fuel Records 
		 */
		$data = array();

		// prepare seller_type
		if ( $this->has_data( 'seller_type' )) {
			$data['seller_type'] = $this->get_data( 'seller_type' );
		}

		// save fuel
		if ( ! $this->Sellertype->save( $data, $id )) {
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
				
				$this->set_flash_msg( 'success', get_msg( 'success_seller_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_seller_add' ));
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

		$this->form_validation->set_rules( 'seller_type', get_msg( 'seller_type' ), $rule);

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

		 $conds['seller_type'] = $name;

			
		 	if( $id != "") {
		 		// echo "bbbb";die;
				if ( strtolower( $this->Sellertype->get_one( $id )->seller_type ) == strtolower( $name )) {
				// if the name is existing name for that user id,
					return true;
				} else if ( $this->Sellertype->exists( ($conds ))) {
				// if the name is existed in the system,
					$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
					return false;
				} 
			} else {
				// echo "aaaa";die;
				if ( $this->Sellertype->exists( ($conds ))) {
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
		$this->data['action_title'] = get_msg( 'seller_edit' );

		// load user
		$this->data['seller'] = $this->Sellertype->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Delete all the news under fuel
	 *
	 * @param      integer  $fuel_id  The fuel identifier
	 */
	function delete( $seller_id = 0 )
	{
		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );
		
		// delete categories and images
		$enable_trigger = true; 

		/** Note: enable trigger will delete news under fuel and all news related data */
		if ( !$this->ps_delete->delete_seller( $seller_id, $enable_trigger )) {
		// if error in deleting fuel,

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
        	
			$this->set_flash_msg( 'success', get_msg( 'success_seller_delete' ));
		}
		
		redirect( $this->module_site_url());
	}

	/**
	 * Check seller name via ajax
	 *
	 * @param      boolean  $cat_id  The cat identifier
	 */
	function ajx_exists( $id = false )
	{
		// get seller_type

		$name = $_REQUEST['seller_type'];

		if ( $this->is_valid_name( $name, $id )) {

		// if the seller name is valid,
			
			echo "true";
		} else {
		// if invalid seller name,
			
			echo "false";
		}
	}

	/**
	 * Publish the record
	 *
	 * @param      integer  $seller_id  The Manufacturer identifier
	 */
	function ajx_publish( $seller_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$seller_data = array( 'status'=> 1 );
			
		// save data
		if ( $this->Sellertype->save( $seller_data, $seller_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $seller_id  The manufacturer identifier
	 */
	function ajx_unpublish( $seller_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$seller_data = array( 'status'=> 0 );
			
		// save data
		if ( $this->Sellertype->save( $seller_data, $seller_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}

}