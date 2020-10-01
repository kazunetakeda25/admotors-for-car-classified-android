<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for News
 */
class Chat_items extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		parent::__construct( 'Item' );
	}

	/**
	 * Default Query for API
	 * @return [type] [description]
	 */
	function default_conds()
	{
		$conds = array();

		if ( $this->is_get ) {
		// if is get record using GET method

			// get default setting for GET_ALL_CATEGORIES
			//$setting = $this->Api->get_one_by( array( 'api_constant' => GET_ALL_CATEGORIES ));

			$conds['order_by'] = 1;
			$conds['order_by_field'] = $setting->order_by_field;
			$conds['order_by_type'] = $setting->order_by_type;
		}

		return $conds;
	}

	/**
	 * Get buyer and seller list
	 */
	function get_buyer_seller_list_post()
	{
		// add flag for default query
		$this->is_get = true;

		// get the post data
		$user_id = $this->post('user_id');
		$return_type 	 = $this->post('return_type');

		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );

			// get limit & offset

			if ( $return_type == "buyer") {
				//$conds['buyer_user_id'] = $user_id;
				
				//pph modified @ 22 June 2019
				$conds['seller_user_id'] = $user_id;

				if ( !empty( $limit ) && !empty( $offset )) {
			// if limit & offset is not empty
				
				$chats = $this->Chat->get_all_by($conds,$limit, $offset)->result();
			} else if ( !empty( $limit )) {
			// if limit is not empty

				
				$chats = $this->Chat->get_all_by($conds, $limit )->result();
			} else {
			// if both are empty
				
				$chats = $this->Chat->get_all_by($conds)->result();
			}


			foreach ( $chats as $chat ) {

				$id .= "'" .$chat->id . "',";
			
			}

			if ($id == "") {
				$this->error_response( get_msg( 'no_record' ) );
			} else {

				$result = rtrim($id,',');
				$conds['$id'] = $result;

				$obj = $this->Chat->get_all_in_chat($conds)->result();
				$this->ps_adapter->convert_chathistory( $obj );
				$this->custom_response( $obj );

			}

			

		} else if ( $return_type == "seller") {

			//$conds['seller_user_id'] = $user_id;
			//pph modified @ 22 June 2019
			$conds['buyer_user_id'] = $user_id;
				
				if ( !empty( $limit ) && !empty( $offset )) {
			// if limit & offset is not empty
				
				$chats = $this->Chat->get_all_by($conds,$limit, $offset)->result();
			} else if ( !empty( $limit )) {
			// if limit is not empty

				
				$chats = $this->Chat->get_all_by($conds, $limit )->result();
			} else {
			// if both are empty
				
				$chats = $this->Chat->get_all_by($conds)->result();
			}
			
			foreach ( $chats as $chat ) {

				$id .= "'" .$chat->id . "',";
			
			}

			if ($id == "") {
				$this->error_response( get_msg( 'no_record' ) );
			} else {

				$result = rtrim($id,',');
				$conds['$id'] = $result;

				$obj = $this->Chat->get_all_in_chat($conds)->result();
				$this->ps_adapter->convert_chathistory( $obj );
				$this->custom_response( $obj );

			}

		}

	}


	/**
	 * Convert Object
	 */
	function convert_object( &$obj )
	{

		// call parent convert object
		parent::convert_object( $obj );

		// convert customize item object
		$this->ps_adapter->convert_chatitem( $obj );
	}

}