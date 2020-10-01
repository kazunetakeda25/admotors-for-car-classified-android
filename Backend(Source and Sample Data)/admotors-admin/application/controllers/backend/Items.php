<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Items Controller
 */
class Items extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'CARS' );
	}

	/**
	 * List down the registered users
	 */
	function index() {

		$conds['no_publish_filter'] = 1;
		
		// get rows count
		$this->data['rows_count'] = $this->Item->count_all_by( $conds );

		// get categories
		$this->data['items'] = $this->Item->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );


		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'prd_search' );

		// condition with search term
		if($this->input->post('submit') != NULL ){

			if($this->input->post('searchterm') != "") {
				$conds['searchterm'] = $this->input->post('searchterm');
				$this->data['searchterm'] = $this->input->post('searchterm');
				$this->session->set_userdata(array("searchterm" => $this->input->post('searchterm')));
			} else {
				
				$this->session->set_userdata(array("searchterm" => NULL));
			}
			
			if($this->input->post('manufacturer_id') != ""  || $this->input->post('manufacturer_id') != '0') {
				$conds['manufacturer_id'] = $this->input->post('manufacturer_id');
				$this->data['manufacturer_id'] = $this->input->post('manufacturer_id');
				$this->data['selected_manufacturer_id'] = $this->input->post('manufacturer_id');
				$this->session->set_userdata(array("manufacturer_id" => $this->input->post('manufacturer_id')));
				$this->session->set_userdata(array("selected_manufacturer_id" => $this->input->post('manufacturer_id')));
			} else {
				$this->session->set_userdata(array("manufacturer_id" => NULL ));
			}

			if($this->input->post('model_id') != ""  || $this->input->post('model_id') != '0') {
				$conds['model_id'] = $this->input->post('model_id');
				$this->data['model_id'] = $this->input->post('model_id');
				$this->session->set_userdata(array("model_id" => $this->input->post('model_id')));
			} else {
				$this->session->set_userdata(array("model_id" => NULL ));
			}

			if($this->input->post('item_price_type_id') != ""  || $this->input->post('item_price_type_id') != '0') {
				$conds['item_price_type_id'] = $this->input->post('item_price_type_id');
				$this->data['item_price_type_id'] = $this->input->post('item_price_type_id');
				
				$this->session->set_userdata(array("item_price_type_id" => $this->input->post('item_price_type_id')));
				
			} else {
				$this->session->set_userdata(array("item_price_type_id" => NULL ));
			}

			if($this->input->post('item_type_id') != ""  || $this->input->post('item_type_id') != '0') {
				$conds['item_type_id'] = $this->input->post('item_type_id');
				$this->data['item_type_id'] = $this->input->post('item_type_id');
				
				$this->session->set_userdata(array("item_type_id" => $this->input->post('item_type_id')));
				
			} else {
				$this->session->set_userdata(array("item_type_id" => NULL ));
			}

			if($this->input->post('item_currency_id') != ""  || $this->input->post('item_currency_id') != '0') {
				$conds['item_currency_id'] = $this->input->post('item_currency_id');
				$this->data['item_currency_id'] = $this->input->post('item_currency_id');
				
				$this->session->set_userdata(array("item_currency_id" => $this->input->post('item_currency_id')));
				
			} else {
				$this->session->set_userdata(array("item_currency_id" => NULL ));
			}

			if($this->input->post('color_id') != ""  || $this->input->post('color_id') != '0') {
				$conds['color_id'] = $this->input->post('color_id');
				$this->data['color_id'] = $this->input->post('color_id');
				
				$this->session->set_userdata(array("color_id" => $this->input->post('color_id')));
				
			} else {
				$this->session->set_userdata(array("color_id" => NULL ));
			}

			if($this->input->post('fuel_type_id') != ""  || $this->input->post('fuel_type_id') != '0') {
				$conds['fuel_type_id'] = $this->input->post('fuel_type_id');
				$this->data['fuel_type_id'] = $this->input->post('fuel_type_id');
				
				$this->session->set_userdata(array("fuel_type_id" => $this->input->post('fuel_type_id')));
				
			} else {
				$this->session->set_userdata(array("fuel_type_id" => NULL ));
			}

			if($this->input->post('build_type_id') != ""  || $this->input->post('build_type_id') != '0') {
				$conds['build_type_id'] = $this->input->post('build_type_id');
				$this->data['build_type_id'] = $this->input->post('build_type_id');
				
				$this->session->set_userdata(array("build_type_id" => $this->input->post('build_type_id')));
				
			} else {
				$this->session->set_userdata(array("build_type_id" => NULL ));
			}

			if($this->input->post('seller_type_id') != ""  || $this->input->post('seller_type_id') != '0') {
				$conds['seller_type_id'] = $this->input->post('seller_type_id');
				$this->data['seller_type_id'] = $this->input->post('seller_type_id');
				
				$this->session->set_userdata(array("seller_type_id" => $this->input->post('seller_type_id')));
				
			} else {
				$this->session->set_userdata(array("seller_type_id" => NULL ));
			}

			if($this->input->post('transmission_id') != ""  || $this->input->post('transmission_id') != '0') {
				$conds['transmission_id'] = $this->input->post('transmission_id');
				$this->data['transmission_id'] = $this->input->post('transmission_id');
				
				$this->session->set_userdata(array("transmission_id" => $this->input->post('transmission_id')));
				
			} else {
				$this->session->set_userdata(array("transmission_id" => NULL ));
			}

			if($this->input->post('status') != "0") {
				
				$conds['status'] = $this->input->post('status');
				$this->data['status'] = $this->input->post('status');
				$this->session->set_userdata(array("status" => $this->input->post('status')));
			
			} else {
				$this->session->set_userdata(array("status" => NULL ));
			}



		} else {
			//read from session value
			if($this->session->userdata('searchterm') != NULL){
				$conds['searchterm'] = $this->session->userdata('searchterm');
				$this->data['searchterm'] = $this->session->userdata('searchterm');
			}

			if($this->session->userdata('manufacturer_id') != NULL){
				$conds['manufacturer_id'] = $this->session->userdata('manufacturer_id');
				$this->data['manufacturer_id'] = $this->session->userdata('manufacturer_id');
				$this->data['selected_manufacturer_id'] = $this->session->userdata('manufacturer_id');
			}

			if($this->session->userdata('model_id') != NULL){
				$conds['model_id'] = $this->session->userdata('model_id');
				$this->data['model_id'] = $this->session->userdata('model_id');
				$this->data['selected_manufacturer_id'] = $this->session->userdata('cat_id');
			}

			if($this->session->userdata('item_price_type_id') != NULL){
				$conds['item_price_type_id'] = $this->session->userdata('item_price_type_id');
				$this->data['item_price_type_id'] = $this->session->userdata('item_price_type_id');
			}

			if($this->session->userdata('item_type_id') != NULL){
				$conds['item_type_id'] = $this->session->userdata('item_type_id');
				$this->data['item_type_id'] = $this->session->userdata('item_type_id');
			}

			if($this->session->userdata('item_currency_id') != NULL){
				$conds['item_currency_id'] = $this->session->userdata('item_currency_id');
				$this->data['item_currency_id'] = $this->session->userdata('item_currency_id');
			}

			if($this->session->userdata('color_id') != NULL){
				$conds['color_id'] = $this->session->userdata('color_id');
				$this->data['color_id'] = $this->session->userdata('color_id');
			}

			if($this->session->userdata('fuel_type_id') != NULL){
				$conds['fuel_type_id'] = $this->session->userdata('fuel_type_id');
				$this->data['fuel_type_id'] = $this->session->userdata('fuel_type_id');
			}

			if($this->session->userdata('build_type_id') != NULL){
				$conds['build_type_id'] = $this->session->userdata('build_type_id');
				$this->data['build_type_id'] = $this->session->userdata('build_type_id');
			}

			if($this->session->userdata('seller_type_id') != NULL){
				$conds['seller_type_id'] = $this->session->userdata('seller_type_id');
				$this->data['seller_type_id'] = $this->session->userdata('seller_type_id');
			}

			if($this->session->userdata('transmission_id') != NULL){
				$conds['transmission_id'] = $this->session->userdata('transmission_id');
				$this->data['transmission_id'] = $this->session->userdata('transmission_id');
			}
		
			if($this->session->userdata('status') != 0){
				$conds['status'] = $this->session->userdata('status');
				$this->data['status'] = $this->session->userdata('status');
			}
			

		}

		if ($conds['status'] == "Select Status") {
			$conds['status'] = "1";
		}


		// pagination
		$this->data['rows_count'] = $this->Item->count_all_by( $conds );

		// search data
		$this->data['items'] = $this->Item->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );

		// load add list
		parent::search();
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save category
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {
		
			$logged_in_user = $this->ps_auth->get_user_info();

			// Item id
		   	if ( $this->has_data( 'id' )) {
				$data['id'] = $this->get_data( 'id' );

			}

		   	// Manufacturer id
		   	if ( $this->has_data( 'manufacturer_id' )) {
				$data['manufacturer_id'] = $this->get_data( 'manufacturer_id' );
			}

			// Model id
		   	if ( $this->has_data( 'model_id' )) {
				$data['model_id'] = $this->get_data( 'model_id' );
			}

			// Color id
		   	if ( $this->has_data( 'color_id' )) {
				$data['color_id'] = $this->get_data( 'color_id' );
			}

			// Fuel id
		   	if ( $this->has_data( 'fuel_type_id' )) {
				$data['fuel_type_id'] = $this->get_data( 'fuel_type_id' );
			}

			// build_type_id
		   	if ( $this->has_data( 'build_type_id' )) {
				$data['build_type_id'] = $this->get_data( 'build_type_id' );
			}

			// seller_type_id
		   	if ( $this->has_data( 'seller_type_id' )) {
				$data['seller_type_id'] = $this->get_data( 'seller_type_id' );
			}

			// transmission_id
		   	if ( $this->has_data( 'transmission_id' )) {
				$data['transmission_id'] = $this->get_data( 'transmission_id' );
			}

			// Type id
		   	if ( $this->has_data( 'item_type_id' )) {
				$data['item_type_id'] = $this->get_data( 'item_type_id' );
			}

			// Price id
		   	if ( $this->has_data( 'item_price_type_id' )) {
				$data['item_price_type_id'] = $this->get_data( 'item_price_type_id' );
			}

			// Currency id
		   	if ( $this->has_data( 'item_currency_id' )) {
				$data['item_currency_id'] = $this->get_data( 'item_currency_id' );
			}

			// location id
		   	if ( $this->has_data( 'item_location_id' )) {
				$data['item_location_id'] = $this->get_data( 'item_location_id' );
			}

			//title
		   	if ( $this->has_data( 'title' )) {
				$data['title'] = $this->get_data( 'title' );
			}

			//condition of item
		   	if ( $this->has_data( 'condition_of_item_id' )) {
				$data['condition_of_item_id'] = $this->get_data( 'condition_of_item_id' );
			}

			// description
		   	if ( $this->has_data( 'description' )) {
				$data['description'] = $this->get_data( 'description' );
			}

			// highlight_info
		   	if ( $this->has_data( 'highlight_info' )) {
				$data['highlight_info'] = $this->get_data( 'highlight_info' );
			}

			// price
		   	if ( $this->has_data( 'price' )) {
				$data['price'] = $this->get_data( 'price' );
			}

			// price
		   	if ( $this->has_data( 'plate_number' )) {
				$data['plate_number'] = $this->get_data( 'plate_number' );
			}

			// engine_power
		   	if ( $this->has_data( 'engine_power' )) {
				$data['engine_power'] = $this->get_data( 'engine_power' );
			}

			// steering_position
		   	if ( $this->has_data( 'steering_position' )) {
				$data['steering_position'] = $this->get_data( 'steering_position' );
			}

			// no_of_owner
		   	if ( $this->has_data( 'no_of_owner' )) {
				$data['no_of_owner'] = $this->get_data( 'no_of_owner' );
			}

			// trim_name
		   	if ( $this->has_data( 'trim_name' )) {
				$data['trim_name'] = $this->get_data( 'trim_name' );
			}

			// vehicle_id
		   	if ( $this->has_data( 'vehicle_id' )) {
				$data['vehicle_id'] = $this->get_data( 'vehicle_id' );
			}

			// price_unit
		   	if ( $this->has_data( 'price_unit' )) {
				$data['price_unit'] = $this->get_data( 'price_unit' );
			}

			// year
		   	if ( $this->has_data( 'year' )) {
				$data['year'] = $this->get_data( 'year' );
			}

			// max_passengers
		   	if ( $this->has_data( 'max_passengers' )) {
				$data['max_passengers'] = $this->get_data( 'max_passengers' );
			}

			// no_of_doors
		   	if ( $this->has_data( 'no_of_doors' )) {
				$data['no_of_doors'] = $this->get_data( 'no_of_doors' );
			}

			// mileage
		   	if ( $this->has_data( 'mileage' )) {
				$data['mileage'] = $this->get_data( 'mileage' );
			}

			// license_expiration_date
		   	if ( $this->has_data( 'license_expiration_date' )) {
				$data['license_expiration_date'] = $this->get_data( 'license_expiration_date' );
			}

			// price
		   	if ( $this->has_data( 'price' )) {
				$data['price'] = $this->get_data( 'price' );
			}

			// address
		   	if ( $this->has_data( 'address' )) {
				$data['address'] = $this->get_data( 'address' );
			}

			// prepare Item lat
			if ( $this->has_data( 'lat' )) {
				$data['lat'] = $this->get_data( 'lat' );
			}

			// prepare Item lng
			if ( $this->has_data( 'lng' )) {
				$data['lng'] = $this->get_data( 'lng' );
			}

			// if 'is_sold_out' is checked,
			if ( $this->has_data( 'is_sold_out' )) {
				$data['is_sold_out'] = 1;
			} else {
				$data['is_sold_out'] = 0;
			}

			// if 'business_mode' is checked,
			if ( $this->has_data( 'business_mode' )) {
				$data['business_mode'] = 1;
			} else {
				$data['business_mode'] = 0;
			}

			// if 'licence_status' is checked,
			if ( $this->has_data( 'licence_status' )) {
				$data['licence_status'] = 1;
			} else {
				$data['licence_status'] = 0;
			}

			// set timezone

			if($id == "") {
				//save
				$data['added_date'] = date("Y-m-d H:i:s");
				$data['added_user_id'] = $logged_in_user->user_id;

			} else {
				//edit
				unset($data['added_date']);
				$data['updated_date'] = date("Y-m-d H:i:s");
				$data['updated_user_id'] = $logged_in_user->user_id;
			}

			//save item
			if ( ! $this->Item->save( $data, $id )) {
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

	//get all subcategories when select category

	function get_all_model( $manufacturer_id )
    {
    	$conds['manufacturer_id'] = $manufacturer_id;
    	
    	$models = $this->Model->get_all_by($conds);
		echo json_encode($models->result());
    }


    /**
	 * Show Gallery
	 *
	 * @param      <type>  $id     The identifier
	 */
	function gallery( $id ) {
		// breadcrumb urls
		$edit_item = get_msg('prd_edit');

		$this->data['action_title'] = array( 
			array( 'url' => 'edit/'. $id, 'label' => $edit_item ), 
			array( 'label' => get_msg( 'item_gallery' ))
		);
		
		$_SESSION['parent_id'] = $id;
		$_SESSION['type'] = 'item';
    	    	
    	$this->load_gallery();
    }


	/**
 	* Update the existing one
	*/
	function edit( $id ) 
	{
		
		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'prd_edit' );

		// load user
		$this->data['item'] = $this->Item->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );

	}

	/**
	 * Determines if valid input.
	 *
	 * @return     boolean  True if valid input, False otherwise.
	 */
	function is_valid_input( $id = 0 ) 
	{
		
		// $rule = 'required|callback_is_valid_name['. $id  .']';

		// $this->form_validation->set_rules( 'name', get_msg( 'name' ), $rule);
		
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
	function is_valid_name( $name, $id = 0 )
	{		
		 // $conds['name'] = $name;
		
			// if ( strtolower( $this->Item->get_one( $id )->name ) == strtolower( $name )) {
			// // if the name is existing name for that user id,
			// 	return true;
			// } else if ( $this->Item->exists( ($conds ))) {
			// // if the name is existed in the system,
			// 	$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
			// 	return false;
			// }
			return true;
	}


	/**
	 * Delete the record
	 * 1) delete Item
	 * 2) delete image from folder and table
	 * 3) check transactions
	 */
	function delete( $id ) 
	{
		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );

		// delete categories and images
		$enable_trigger = true; 
		
		// delete categories and images
		//if ( !$this->ps_delete->delete_product( $id, $enable_trigger )) {
		$type = "item";

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
        	
			$this->set_flash_msg( 'success', get_msg( 'success_prd_delete' ));
		}
		
		redirect( $this->module_site_url());
	}


	/**
	 * Check Item name via ajax
	 *
	 * @param      boolean  $Item_id  The cat identifier
	 */
	function ajx_exists( $id = false )
	{
		
		// // get Item name
		// $name = $_REQUEST['name'];
		
		// if ( $this->is_valid_name( $name, $id )) {
		// // if the Item name is valid,
			
		// 	echo "true";
		// } else {
		// // if invalid Item name,
			
		// 	echo "false";
		// }
	}

	/**
	 * Publish the record
	 *
	 * @param      integer  $prd_id  The Item identifier
	 */
	function ajx_publish( $item_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$prd_data = array( 'status'=> 1 );
			
		// save data
		if ( $this->Item->save( $prd_data, $item_id )) {
			//Need to delete at history table because that wallpaper need to show again on app
			$data_delete['item_id'] = $item_id;
			$this->Item_delete->delete_by($data_delete);
			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $prd_id  The category identifier
	 */
	function ajx_unpublish( $item_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$prd_data = array( 'status'=> 0 );
			
		// save data
		if ( $this->Item->save( $prd_data, $item_id )) {

			//Need to save at history table because that wallpaper no need to show on app
			$data_delete['item_id'] = $item_id;
			$this->Item_delete->save($data_delete);
			echo 'true';
		} else {
			echo 'false';
		}
	}

 }