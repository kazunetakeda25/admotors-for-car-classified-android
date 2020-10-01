<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Likes Controller
 */
class Apis extends BE_Controller {

	private $api_constants;
	
	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'APIS' );

		$this->api_constants = array(
			'GET_ALL_MANUFACTURERS' => array( 'id' => 'Category Id', 'name' => 'Category Name', 'added_date' => 'Added Date' ),
			'GET_ALL_WALLPAPERS' => array( 'wallpaper_id' => 'Wallpaper Id', 'wallpaper_name' => 'Wallpaper Name', 'added_date' => 'Added Date'),
			'SEARCH_WALLPAPERS' => array( 'wallpaper_id' => 'Wallpaper Id', 'wallpaper_name' => 'Wallpaper Name', 'added_date' => 'Added Date' ),
			'GET_WALLPAPER_BY_CAT' => array( 'wallpaper_id' => 'Wallpaper Id', 'wallpaper_name' => 'Wallpaper Name', 'added_date' => 'Added Date' )
		);
	}

	/**
	 * Load Api Entry Form
	 */
	function index() {

		if ( $this->is_POST()) {
		// if the method is post

			// server side validation
			if ( $this->is_valid_input()) {

				// save user info
				$this->save();
			}
		}

		$this->data['api_constants'] = $this->api_constants;

		$this->data['action_title'] = "Api Setting";
		
		//Get Api Objects
		$this->data['apis'] = $this->Api->get_all();

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

		// start the transaction
		$this->db->trans_start();
		
		// prepare data for save
		$data = array();

		// api_ids
		if ( $this->has_data( 'api_id' )) {
			$api_ids = $this->get_data( 'api_id' );
		}

		// order_by_field
		if ( $this->has_data( 'order_by_field' )) {
			$order_by_field = $this->get_data( 'order_by_field' );
		}

		// order_by_type
		if ( $this->has_data( 'order_by_type' )) {
			$order_by_type = $this->get_data( 'order_by_type' );
		}

		$index = 0;
		if ( !empty( $api_ids )) foreach ( $api_ids as $api_id ) {

			$data = array(
				'order_by_field' => $order_by_field[ $index ],
				'order_by_type' => $order_by_type[ $index ]
			);

			// save category
			if ( ! $this->Api->save( $data, $api_id )) {
			// if there is an error in inserting user data,	

				// rollback the transaction
				$this->db->trans_rollback();

				// set error message
				$this->data['error'] = get_msg( 'err_model' );
				
				return;
			}

			$index++;
		}


		// commit the transaction
		if ( ! $this->check_trans()) {
        	
			// set flash error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {

			$this->set_flash_msg( 'success', get_msg( 'success_api_edit' ));
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