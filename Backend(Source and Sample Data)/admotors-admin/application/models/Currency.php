<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for Currency table
 */
class Currency extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_items_currency', 'id', 'itm_curency' );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{
		// echo "fad";die;
		// default where clause
		if ( !isset( $conds['no_publish_filter'] )) {
			$this->db->where( 'status', 1 );
		}

		// id condition
		if ( isset( $conds['id'] )) {
			$this->db->where( 'id', $conds['id'] );
		}

		// currency_short_form condition
		if ( isset( $conds['currency_short_form'] )) {
			$this->db->where( 'currency_short_form', $conds['currency_short_form'] );
		}


		// currency_symbol condition
		if ( isset( $conds['currency_symbol'] )) {
			$this->db->where( 'currency_symbol', $conds['currency_symbol'] );
		}

		// searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->like( 'currency_short_form', $conds['searchterm'] );
		}

		// order_by
		if ( isset( $conds['order_by'] )) {

			$order_by_field = $conds['order_by_field'];
			$order_by_type = $conds['order_by_type'];

			$this->db->order_by( 'bs_items_currency.'.$order_by_field, $order_by_type );
		} else {

			$this->db->order_by( 'added_date' );
		}
	}
}