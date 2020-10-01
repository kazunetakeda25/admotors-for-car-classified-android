<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Likes Controller
 */

class App_settings extends BE_Controller {

		/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'APPSETTINGS' );
	}

	/**
	 * Load About Entry Form
	 */

	function index( $id = "app1" ) {

		if ( $this->is_POST()) {
		// if the method is post

			// server side validation
			//if ( $this->is_valid_input()) {

				// save user info
				$this->save( $id );
			//}
		}

		$this->data['action_title'] = "App Setting";
		
		//Get About Object
		$this->data['app'] = $this->App_setting->get_one( $id );

		$this->load_form($this->data);

	}

	/**
	 * Update the existing one
	 */
	function edit( $id = "app1") {


		// load user
		$this->data['app'] = $this->App_setting->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Saving Logic
	 * 1) save about data
	 * 2) check transaction status
	 *
	 * @param      boolean  $id  The about identifier
	 */
	function save( $id = false ) {

		
		// prepare data for save
		$data = array();

		// id
		if ( $this->has_data( 'id' )) {
			$data['id'] = $this->get_data( 'id' );
		}

		// lat
		if ( $this->has_data( 'lat' )) {
			$data['lat'] = $this->get_data( 'lat' );
		}

		// lng
		if ( $this->has_data( 'lng' )) {
			$data['lng'] = $this->get_data( 'lng' );
		}

		//print_r($data);die;
		if ( ! $this->App_setting->save( $data, $id )) {
		// if there is an error in inserting user data,	

			// set error message
			$this->data['error'] = get_msg( 'err_model' );
			
			return;
		} 


	}
}