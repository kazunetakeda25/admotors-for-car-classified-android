<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for Userfollow table
 */
class Userfollow extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_follows', 'id', 'itm_f' );
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

		// user_id condition
		if ( isset( $conds['user_id'] )) {
			$this->db->where( 'user_id', $conds['user_id'] );
		}

		// followed_user_id
		if ( isset( $conds['followed_user_id'] )) {
			$this->db->like( 'followed_user_id', $conds['followed_user_id'] );
		}

		

		$this->db->order_by( 'added_date' );
		
	}
}