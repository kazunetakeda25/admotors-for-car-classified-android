<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for Colors
 */
class Colors extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		parent::__construct( 'Color' );
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