<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Sellertype class for about table
 */
class Sellertype extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_items_seller_types', 'id', 'sel' );
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

		// seller_type condition
		if ( isset( $conds['seller_type'] )) {
			$this->db->where( 'seller_type', $conds['seller_type'] );
		}

		// searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->like( 'seller_type', $conds['searchterm'] );
		}

		$this->db->order_by( 'added_date' );
	}
}