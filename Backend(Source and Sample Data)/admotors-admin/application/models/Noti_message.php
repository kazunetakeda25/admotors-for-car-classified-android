<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for about table
 */
class Noti_message extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_push_notification_messages', 'id', 'noti' );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{
		// id condition
		if ( isset( $conds['id'] )) {
			$this->db->where( 'id', $conds['id'] );
		}

		// message condition
		if ( isset( $conds['message'] )) {
			$this->db->where( 'message', $conds['message'] );
		}

		//searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->where( 'message', $conds['searchterm'] );
		}

		// description condition
		if ( isset( $conds['description'] )) {
			$this->db->where( 'description', $conds['description'] );
		}

		// added_user_id condition
		if ( isset( $conds['added_user_id'] )) {
			$this->db->where( 'added_user_id', $conds['added_user_id'] );
		}


		$this->db->order_by( 'added_date', 'desc' );
		
	}

	
}