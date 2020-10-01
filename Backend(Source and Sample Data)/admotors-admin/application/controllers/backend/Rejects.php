<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Reject Wallpapers Controller
 */
class Rejects extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'REJECTS' );
	}

	/**
	 * List down the reject wallpapers
	 */
	function index() {
		// no publish filter
		
		$conds['order_by'] = 1;
		$conds['order_by_field'] = "added_date";
		$conds['order_by_type'] = "desc";	

		// get rows count
		$this->data['rows_count'] = $this->Reject->count_all_by( $conds );

		// get reject wallpapers
		$rejects = $this->Reject->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );

		$this->data['rejects'] = $rejects;

		// load index logic
		parent::index();
	}


	/**
	 * Update the existing one
	 */
	function edit( $id ) {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'wallpaper_edit' );

		// load user
		$reject = $this->Reject->get_one( $id );

		$this->data['reject'] = $reject;

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save wallpaper
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {

		// start the transaction
		$this->db->trans_start();
		
		/** 
		 * Insert Wallpaper Records 
		 */
		$data = array();

		// prepare wallpaper_name
		if ( $this->has_data( 'wallpaper_name' )) {
			$data['wallpaper_name'] = $this->get_data( 'wallpaper_name' );
		}

		// cat_id
		if ( $this->has_data( 'cat_id' )) {
			$data['cat_id'] = $this->get_data( 'cat_id' );
		}

		// prepare wallpaper_search_tags
		if ( $this->has_data( 'wallpaper_search_tags' )) {
			$data['wallpaper_search_tags'] = $this->get_data( 'wallpaper_search_tags' );
		}

		// prepare types
		if ( $this->has_data( 'types' )) {
			$data['types'] = $this->get_data( 'types' );
		}

		// prepare point
		if ( $this->has_data( 'point' )) {
			$data['point'] = $this->get_data( 'point' );
		}

		// prepare point
		if ( $this->has_data( 'color_id' )) {
			$data['color_id'] = $this->get_data( 'color_id' );
		}
		
		// if 'is published' is checked,
		if ( $this->has_data( 'wallpaper_is_published' )) {
			
			$data['wallpaper_is_published'] = $this->get_data( 'wallpaper_is_published' );;
		} 

		// if 'is published' is checked,
		if ( $this->has_data( 'is_recommended' )) {
			
			$data['is_recommended'] = 1;
		} else {
			
			$data['is_recommended'] = 0;
		}

		// if 'is published' is checked,
		/*
		if ( $this->has_data( 'is_portrait' )) {
			
			$data['is_portrait'] = 1;
		} else {
			
			$data['is_portrait'] = 0;
		}

		// if 'is published' is checked,
		if ( $this->has_data( 'is_landscape' )) {
			
			$data['is_landscape'] = 1;
		} else {
			
			$data['is_landscape'] = 0;
		}

		// if 'is published' is checked,
		if ( $this->has_data( 'is_square' )) {
			
			$data['is_square'] = 1;
		} else {
			
			$data['is_square'] = 0;
		}
		*/

		if ( $this->has_data( 'modes' )) {
			$modes = $this->get_data('modes');
			if($modes == 1){
				$data['is_portrait'] = 1;
			} else if($modes == 2){
				$data['is_landscape'] = 1;
			} else {
				$data['is_square'] = 1;
			}
		}


		// save reject wallpaper
		if ( ! $this->Reject->save( $data, $id )) {
		// if there is an error in inserting user data,	

			// rollback the transaction
			$this->db->trans_rollback();

			// set error message
			$this->data['error'] = get_msg( 'err_model' );
			
			return;
		}


		//get inserted wallpaper id
		$id = ( !$id )? $data['wallpaper_id']: $id ;
		if($data['wallpaper_is_published']==1) {
			$user_id = $this->Wallpaper->get_one($id)->added_user_id;
			$upload_point=$this->About->get_one('abt1')->upload_point;
			$total_point = $this->User->get_one($user_id)->total_point;
			$user_point['total_point'] =$total_point + $upload_point;
			$this->User->save( $user_point, $user_id );
		}

		
		//// Start - Send Noti /////
		
		if($data['wallpaper_is_published'] == 1) {
			//approve so change status to publish (1)
			$message = get_msg( 'approve_message_1' ) . $data['wallpaper_name'] . get_msg( 'approve_message_2' );
		} else {
			//reject so change status to reject (3)
			$message = get_msg( 'reject_message_1' ) . $data['wallpaper_name'] . get_msg( 'reject_message_2' );
		}
		

		// if id is false, this is adding new record

		$error_msg = "";
		$success_device_log = "";

		$added_user_id = $this->Wallpaper->get_one($id)->added_user_id;
		$user_device_token = $this->User->get_one($added_user_id)->device_token;
		$user_name = $this->User->get_one($added_user_id)->user_name;
		
		//echo $user_device_token; die;

		if($user_device_token != "") {
			$devices[] = $user_device_token;
			
			$device_ids = array();
			if ( count( $devices ) > 0 ) {
				

				for($i=0; $i < count($devices); $i++) {
					$device_ids[] = $devices[0];
				}

			}


			$status = $this->send_android_fcm( $device_ids, array( "message" => $message ));
			
			


		}

		//// End - Send Noti /////



		/** 
		 * Check Transactions 
		 */

		// commit the transaction
		if ( ! $this->check_trans()) {
        	
			// set flash error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {

			if ( !$status ) {
				$error_msg .= get_msg( 'noti_sent_fail' );
				$this->set_flash_msg( 'error', get_msg( 'noti_sent_fail' ) );
			}


			if ( $status ) {
				$this->set_flash_msg( 'success', get_msg( 'noti_sent_success' ) . $user_name );
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

		$this->form_validation->set_rules( 'wallpaper_name', get_msg( 'wallpaper_name' ), $rule);

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
	function is_valid_name( $name, $wallpaper_id = 0 )
	{		

		$conds['wallpaper_name'] = $name;

		 	if( $wallpaper_id != "") {
		 		 //echo "bbbb";die;
				if ( strtolower( $this->Wallpaper->get_one( $id )->wallpaper_name ) == strtolower( $name )) {
				// if the name is existing name for that user id,
					return true;
				} 
			} else {
				 //echo "aaaa";die;
				if ( $this->Wallpaper->exists( ($conds ))) {
				// if the name is existed in the system,
					$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
					return false;
				}
			}
			return true;
	}

	
	/**
	 * Check wallpaper name via ajax
	 *
	 * @param      boolean  $wallpaper_id  The wallpaper identifier
	 */
	function ajx_exists( $wallpaper_id = false )
	{
		$name = $_REQUEST['wallpaper_name'];
		if ( $this->is_valid_name( $name, $wallpaper_id )) {

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
	 * @param      integer  $wallpaper_id  The wallpaper identifier
	 */
	function ajx_publish( $wallpaper_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$wallpaper_data = array( 'wallpaper_is_published'=> 1 );
			
		// save data
		if ( $this->Wallpaper->save( $wallpaper_data, $wallpaper_id )) {
			//Need to delete at history table because that wallpaper need to show again on app
			$data_delete['wallpaper_id'] = $wallpaper_id;
			$this->Wallpaper_delete->delete_by($data_delete);

			echo 'true';
		} else {
			echo 'false';
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $wallpaper_id  The wallpaper identifier
	 */
	function ajx_unpublish( $wallpaper_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$wallpaper_data = array( 'wallpaper_is_published'=> 0 );
			
		// save data
		if ( $this->Wallpaper->save( $wallpaper_data, $wallpaper_id )) {
			//Need to save at history table because that wallpaper no need to show on app
			$data_delete['wallpaper_id'] = $wallpaper_id;
			$this->Wallpaper_delete->save($data_delete);

			echo 'true';
		} else {
			echo 'false';
		}
	}
}