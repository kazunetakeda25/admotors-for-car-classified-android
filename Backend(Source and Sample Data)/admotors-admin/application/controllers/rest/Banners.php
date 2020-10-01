<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for Banners
 */
class Banners extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		parent::__construct( 'Banner' );
	}


	/**
	 * Convert Object
	 */
	function convert_object( &$obj )
	{
		// call parent convert object
		parent::convert_object( $obj );

		// convert customize item object
		$this->ps_adapter->convert_banners( $obj );

	}

}