<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Item Currency Controller
 */
class Item_currency extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'ITEM_CURRENCY' );
	}

	/**
	 * List down the registered users
	 */
	function index() {
		
		// no publish filter
		$conds['no_publish_filter'] = 1;
		$conds['order_by'] = 1;
		$conds['order_by_field'] = "added_date";
		$conds['order_by_type'] = "desc";
		// get rows count
		$this->data['rows_count'] = $this->Currency->count_all_by( $conds );
		
		// get currencies
		$this->data['currencies'] = $this->Currency->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );
		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {
		

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'currency_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )) );
		// no publish filter
		$conds['no_publish_filter'] = 1;
		$conds['order_by'] = 1;
		$conds['order_by_field'] = "added_date";
		$conds['order_by_type'] = "desc";


		// pagination
		$this->data['rows_count'] = $this->Currency->count_all_by( $conds );

		// search data
		$this->data['currencies'] = $this->Currency->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
		// load add list
		parent::search();
	}

	/**
	 * Create new one
	 */
	function add() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'cat_add' );

		// call the core add logic
		parent::add();
	}

	/**
	 * Update the existing one
	 */
	function edit( $id ) {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'currency_edit' );

		// load user
		$this->data['currency'] = $this->Currency->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save Currency
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {
		// start the transaction
		$this->db->trans_start();
		
		/** 
		 * Insert Currency Records 
		 */
		$data = array();

		// prepare currency_short_form
		if ( $this->has_data( 'currency_short_form' )) {
			$data['currency_short_form'] = $this->get_data( 'currency_short_form' );
		}

		// prepare currency_symbol
		if ( $this->has_data( 'currency_symbol' )) {
			$data['currency_symbol'] = $this->get_data( 'currency_symbol' );
		}

		$data['status'] = 1;

		// save Currency
		if ( ! $this->Currency->save( $data, $id )) {
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
				
				$this->set_flash_msg( 'success', get_msg( 'success_currency_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_currency_add' ));
			}
		}

		redirect( $this->module_site_url());
	}

	/**
	 * Delete the record
	 * 1) delete Currency
	 * 2) delete image from folder and table
	 * 3) check transactions
	 */
	function delete( $currency_id ) {

		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );
		
		// delete currencies and images
		if ( !$this->ps_delete->delete_currency( $currency_id )) {

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
        	
			$this->set_flash_msg( 'success', get_msg( 'success_currency_delete' ));
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

		$this->form_validation->set_rules( 'currency_short_form', get_msg( 'currency_short_form' ), $rule);

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
		 $conds['currency_short_form'] = $name;

		 	if ( strtolower( $this->Currency->get_one( $id )->currency_short_form ) == strtolower( $name )) {
			// if the name is existing name for that user id,
				return true;
			} else if ( $this->Currency->exists( ($conds ))) {
			// if the name is existed in the system,
				$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
				return false;
			}
			return true;
	}

	/**
	 * Check category name via ajax
	 *
	 * @param      boolean  $cat_id  The cat identifier
	 */
	function ajx_exists( $id = false )
	{
		// get category name

		$name = $_REQUEST['currency_short_form'];

		if ( $this->is_valid_name( $name, $id )) {

		// if the category name is valid,
			
			echo "true";
		} else {
		// if invalid category name,
			
			echo "false";
		}
	}
	/**
	 * Publish the record
	 *
	 * @param      integer  $Currency_id  The Currency identifier
	 */
	function ajx_publish( $currency_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$currency_data = array( 'status'=> 1 );
			
		// save data
		if ( $this->Currency->save( $currency_data, $currency_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $Currency_id  The Currency identifier
	 */
	function ajx_unpublish( $currency_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$currency_data = array( 'status'=> 0 );
		// save data
		if ( $this->Currency->save( $currency_data, $currency_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}
}