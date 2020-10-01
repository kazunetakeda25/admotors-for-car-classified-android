<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * APPS Controller
 */
class APPS extends BE_Controller {
		/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'APPS' );
	}

	/**
	 * Load Api Entry Form
	 */
	function index( $id = "app1" ) {

		if ( $this->is_POST()) {
		// if the method is post

			// server side validation
			if ( $this->is_valid_input()) {

				// save user info
				$this->save( $id );
			}
		}

		// $this->data['api_constants'] = $this->api_constants;

		$this->data['action_title'] = "App Setting";

		//Get About Object
		$this->data['app'] = $this->App->get_one( $id );
		
		//Get Api Objects
		// $this->data['apis'] = $this->Api->get_all();

		$this->load_form( $this->data );
	}

	/**
	 * Saving Logic
	 * 1) save api data
	 * 2) check transaction status
	 *
	 * @param      boolean  $id  The api identifier
	 */
	function save( $id = false ) {
		// print_r($_POST); die;
		// start the transaction
		$this->db->trans_start();
		
		// prepare data for save
		$data = array();

		// api_ids
		if ( $this->has_data( 'app_id' )) {
			$data['app_id'] = $this->get_data( 'app_id' );
		}

		// app_home
		if ( $this->has_data( 'app_home' )) {
			$data['app_home'] = $this->get_data( 'app_home' );
		}

		// app_grid
		if ( $this->has_data( 'app_grid' )) {
			$data['app_grid'] = $this->get_data( 'app_grid' );
		}

		// app_detail
		if ( $this->has_data( 'app_detail' )) {
			$data['app_detail'] = $this->get_data( 'app_detail' );
		}


			// save app
			if ( ! $this->App->save( $data, $id )) {
			// if there is an error in inserting user data,	

				// rollback the transaction
				$this->db->trans_rollback();

				// set error message
				$this->data['error'] = get_msg( 'err_model' );
				
				return;
			}

		

		// commit the transaction
		if ( ! $this->check_trans()) {
        	
			// set flash error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {
			if ( $id ) {
			// if user id is not false, show success_add message
				
				$this->set_flash_msg( 'success', get_msg( 'success_api_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_api_add' ));
			}

		}

		redirect( $this->module_site_url() );
	}

    /**
	 * Determines if valid input.
	 *
	 * @return     boolean  True if valid input, False otherwise.
	 */
	function is_valid_input( $id = 0 ) {

		return true;
	}

}