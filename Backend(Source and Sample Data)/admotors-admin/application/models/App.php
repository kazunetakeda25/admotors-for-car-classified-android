<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for api table
 */
class App extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_app', 'app_id', 'app' );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{
		// api_id condition
		if ( isset( $conds['app_id'] )) {
			
			$this->db->where( 'api_id', $conds['api_id'] );
		}

	}
}