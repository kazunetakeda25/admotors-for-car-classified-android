<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for about table
 */
class Fueltype extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_items_fuel_types', 'id', 'fuel' );
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

		// fuel_name condition
		if ( isset( $conds['fuel_name'] )) {
			$this->db->where( 'fuel_name', $conds['fuel_name'] );
		}

		// searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->like( 'fuel_name', $conds['searchterm'] );
		}

		$this->db->order_by( 'added_date' );
	}
}