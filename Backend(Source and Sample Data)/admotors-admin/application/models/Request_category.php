<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for category table
 */
class Request_category extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_categories_request', 'request_cat_id', 'cat' );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{
		// default where clause
		if ( !isset( $conds['no_publish_filter'] )) {
			$this->db->where( 'status', 1 );
		}

		// request_cat_name condition
		if ( isset( $conds['request_cat_name'] )) {
			$this->db->where( 'request_cat_name', $conds['request_cat_name'] );
		}

		//status condition

		// category id condition
		if ( isset( $conds['status'] )) {
			if ($conds['status'] != "" ) {
				if ($conds['status'] != 0) {
					$this->db->where( 'status', $conds['status'] );	
				}
				
			}
		}

		// searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->like( 'request_cat_name', $conds['searchterm'] );
		}

		// order_by
		if ( isset( $conds['order_by'] )) {

			$order_by_field = $conds['order_by_field'];
			$order_by_type = $conds['order_by_type'];

			$this->db->order_by( 'bs_categories_request.'.$order_by_field, $order_by_type );
		} else {

			$this->db->order_by( 'added_date' );
		}
	}
}