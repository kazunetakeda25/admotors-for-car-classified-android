<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Build type class for about table
 */
class Buildtype extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_items_build_types', 'id', 'color' );
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

		// car_type condition
		if ( isset( $conds['car_type'] )) {
			$this->db->where( 'car_type', $conds['car_type'] );
		}

		// searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->like( 'car_type', $conds['searchterm'] );
		}

		$this->db->order_by( 'added_date' );
	}
}