<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for about table
 */
class Accept extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_accept_offer', 'id', 'acpt_' );
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

		// item_id condition
		if ( isset( $conds['item_id'] )) {
			$this->db->where( 'item_id', $conds['item_id'] );
		}

		// from_user_id condition
		if ( isset( $conds['from_user_id'] )) {
			$this->db->where( 'from_user_id', $conds['from_user_id'] );
		}

		// from_user_id condition
		if ( isset( $conds['to_user_id'] )) {
			$this->db->where( 'to_user_id', $conds['to_user_id'] );
		}

		
	}
}