<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for about table
 */
class Noti extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_push_notification_tokens', 'push_noti_token_id', 'noti_tkn_' );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{

		// push_noti_token_id condition
		if ( isset( $conds['push_noti_token_id'] )) {
			$this->db->where( 'push_noti_token_id', $conds['push_noti_token_id'] );
		}

		// device_token condition
		if ( isset( $conds['device_token'] )) {
			$this->db->where( 'device_token', $conds['device_token'] );
		}

		// platform_name condition
		if ( isset( $conds['platform_name'] )) {
			$this->db->where( 'platform_name', $conds['platform_name'] );
		}

		// user_id condition
		if ( isset( $conds['user_id'] )) {
			$this->db->where( 'user_id', $conds['user_id'] );
		}
		
		$this->db->order_by( 'added_date', 'desc' );
		
	}
}