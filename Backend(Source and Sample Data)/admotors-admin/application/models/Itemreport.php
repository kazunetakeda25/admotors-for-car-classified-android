<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for Itemreport table
 */
class Itemreport extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_items_report', 'id', 'itm_report' );
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

		// reported_user_id
		if ( isset( $conds['reported_user_id'] )) {
			$this->db->like( 'reported_user_id', $conds['reported_user_id'] );
		}
		
		$this->db->order_by( 'added_date', 'desc' );
		
	}
}