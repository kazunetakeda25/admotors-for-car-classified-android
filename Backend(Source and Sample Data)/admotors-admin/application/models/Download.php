<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for touch table
 */
class Download extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_downloads', 'id', 'dload' );
	}


	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{
		// echo "jjjj";die;
		// touch_id condition
		if ( isset( $conds['id'] )) {
			$this->db->where( 'id', $conds['id'] );
		}

		// wallpaper_id condition
		if ( isset( $conds['user_id'] )) {
			$this->db->where( 'user_id', $conds['user_id'] );
		}

		// wallpaper_id condition
		if ( isset( $conds['wallpaper_id'] )) {
			$this->db->where( 'wallpaper_id', $conds['wallpaper_id'] );
		}

		
	}
}