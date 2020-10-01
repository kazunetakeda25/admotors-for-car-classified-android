<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Item Itemreport Controller
 */
class Item_reports extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'ITEM_report' );
	}

	/**
	 * List down the registered users
	 */
	function index() {
		
		// no publish filter
		$conds['no_publish_filter'] = 1;
		
		// get rows count
		$this->data['rows_count'] = $this->Itemreport->count_all_by( $conds );
		
		// get Item reports
		$this->data['reports'] = $this->Itemreport->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );
		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {
		

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'report_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )) );
		// no publish filter
		$conds['no_publish_filter'] = 1;
		$conds['order_by'] = 1;
		$conds['order_by_field'] = "added_date";
		$conds['order_by_report'] = "desc";


		// pagination
		$this->data['rows_count'] = $this->Itemreport->count_all_by( $conds );

		// search data
		$this->data['reports'] = $this->Itemreport->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
		// load add list
		parent::search();
	}

	/**
	 * Create new one
	 */
	function add() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'report_add' );

		// call the core add logic
		parent::add();
	}

	/**
	 * Update the existing one
	 */
	function edit( $id ) {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'report_edit' );

		// load user
		$report = $this->Itemreport->get_one( $id );

		$this->data['item_id'] = $report->item_id;

		$item_id = $this->data['item_id'];

		$this->data['item'] = $this->Item->get_one( $item_id );
		//print_r($this->data['item']);die;

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save Itemreport
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	 
	function save( $id = false ) {
		//echo "2";die;
		
			$logged_in_user = $this->ps_auth->get_user_info();

			$report = $this->Itemreport->get_one( $id );

			$item_id = $report->item_id;

			$user_id = $report->reported_user_id;
			//print_r($user_id);die;

			$conds['item_id'] = $item_id;

			$conds['user_id'] = $user_id;


			if( isset($item_id) && isset($user_id) ){
				$this->Itemreport->delete_by( $conds );
			}

			$data['status'] = 2;

			//print_r($data);die;
			//save item
			if ( ! $this->Item->save( $data, $item_id )) {
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
					
					$this->set_flash_msg( 'success', get_msg( 'success_prd_edit' ));
				} else {
				// if user id is false, show success_edit message

					$this->set_flash_msg( 'success', get_msg( 'success_prd_add' ));
				}
			}


		// Item Id Checking 
		if ( $this->has_data( 'gallery' )) {
		// if there is gallery, redirecti to gallery
			redirect( $this->module_site_url( 'gallery/' .$id ));
		}
		else {
		// redirect to list view
			redirect( $this->module_site_url() );
		}
	}

	
	
	/**
	 * Determines if valid input.
	 *
	 * @return     boolean  True if valid input, False otherwise.
	 */
	function is_valid_input( $id = 0 ) {

		return true;
	}

	/**
	 * Determines if valid name.
	 *
	 * @param      <report>   $name  The  name
	 * @param      integer  $id     The  identifier
	 *
	 * @return     boolean  True if valid name, False otherwise.
	 */
	function is_valid_name( $name, $id = 0 )
	{		
		return true;
	}

	
}