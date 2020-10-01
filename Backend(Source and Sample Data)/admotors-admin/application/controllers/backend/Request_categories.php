<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Requestcategories Controller
 */
class Request_categories extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'REQUEST_CATEGORIES' );
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
		$this->data['rows_count'] = $this->Request_category->count_all_by( $conds );
		
		// get Requestcategories
		$this->data['requestcategories'] = $this->Request_category->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );
		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {
		

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'cat_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )),
						'status' => $this->searchterm_handler( $this->input->post( 'status' )) );
		
		// no publish filter
		$conds['no_publish_filter'] = 1;

		// pagination
		$this->data['rows_count'] = $this->Request_category->count_all_by( $conds );

		// search data
		$this->data['requestcategories'] = $this->Request_category->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
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
		$this->data['action_title'] = get_msg( 'cat_edit' );

		// load user
		$this->data['requestcategory'] = $this->Request_category->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save Request_category
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {
		// start the transaction
		$this->db->trans_start();
		
		/** 
		 * Insert Request_category Records 
		 */
		$data = array();

		// prepare cat name
		if ( $this->has_data( 'request_cat_name' )) {
			$data['request_cat_name'] = $this->get_data( 'request_cat_name' );
		}


		// if 'is published' is checked,
		if ( $this->has_data( 'status' )) {
			
			$data['status'] = $this->get_data( 'status' );
		} 

		$cat = $this->Request_category->get_one( $id );

		if ( $this->get_data( 'status' ) == '1' ) {

		if ( $this->Request_category->delete_by( $data )) {

				$cat_data = array(
	        		"cat_name" => $cat->request_cat_name
	        	
	    		);
				
				$this->Category->save( $cat_data );

				
				//get img id for category
	    		$conds = array(
	    			"img_parent_id" => $cat->request_cat_id,
	    			"img_type" => "category"
	    		);

				$img = $this->Image->get_one_by( $conds );

				//print_r($img->img_id);

				// update 

				$img_data = array(
	        		"img_parent_id" => $cat_data['cat_id'],
	        		"img_type" => "category"
	    		);

				$this->Image->save( $img_data, $img->img_id );


				//get img id for category-icon
	    		$conds1 = array(
	    			"img_parent_id" => $cat->request_cat_id,
	    			"img_type" => "category-icon"
	    		);

				$img = $this->Image->get_one_by( $conds1 );

				$img_data = array(
	        		"img_parent_id" => $cat_data['cat_id'],
	        		"img_type" => "category-icon"
	    		);

	    		$this->Image->save( $img_data, $img->img_id );
		
			}

		} 

		//save Request_category
		if ( ! $this->Request_category->save( $data, $id )) {
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
				
				$this->set_flash_msg( 'success', get_msg( 'success_cat_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_cat_add' ));
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
		
		// $rule = 'required|callback_is_valid_name['. $id  .']';

		// $this->form_validation->set_rules( 'cat_name', get_msg( 'cat_name' ), $rule);

		// if ( $this->form_validation->run() == FALSE ) {
		// // if there is an error in validating,

		// 	return false;
		// }

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
	function is_valid_name( $name, $cat_id = 0 )
	{		

		 // $conds['cat_name'] = $name;

			
		 // 	if( $cat_id != "") {
		 // 		// echo "bbbb";die;
			// 	if ( strtolower( $this->Request_category->get_one( $id )->cat_name ) == strtolower( $name )) {
			// 	// if the name is existing name for that user id,
			// 		return true;
			// 	} 
			// } else {
			// 	// echo "aaaa";die;
			// 	if ( $this->Request_category->exists( ($conds ))) {
			// 	// if the name is existed in the system,
			// 		$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
			// 		return false;
			// 	}
			// }
			return true;
	}

	/**
	 * Check Request_category name via ajax
	 *
	 * @param      boolean  $cat_id  The cat identifier
	 */
	function ajx_exists( $request_cat_id = false )
	{
		// get Request_category name

		// $name = $_REQUEST['cat_name'];

		// if ( $this->is_valid_name( $name, $cat_id )) {

		// // if the Request_category name is valid,
			
		// 	echo "true";
		// } else {
		// // if invalid Request_category name,
			
		// 	echo "false";
		// }
	}

	
}