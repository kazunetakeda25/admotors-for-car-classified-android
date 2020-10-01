<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for Sellertypes
 */
class Sellertypes extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		parent::__construct( 'Sellertype' );
	}


	/**
	 * Convert Object
	 */
	function convert_object( &$obj )
	{
		// call parent convert object
		parent::convert_object( $obj );

	}

}