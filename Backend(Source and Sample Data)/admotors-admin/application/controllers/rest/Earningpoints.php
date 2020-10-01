<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for About
 */
class Earningpoints extends API_Controller
{
	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		// call the parent
		parent::__construct( 'Earningpoint' );		
	}

	/**
	 * Determines if valid input.
	 */
	function validation_rules()
	{
		// validation rules for create
		$this->create_validation_rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'earn_point',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'currrency_symbol',
	        	'rules' => 'required'
	        )
        );
	}
	
	/**
	 * Convert Object
	 */
	function convert_object( &$obj )
	{
		// call parent convert object
		parent::convert_object( $obj );
		
		//convert wallpaper object
		//$this->ps_adapter->convert_earning_point( $obj );
		$this->ps_adapter->convert_wallpaper( $obj );
	}


}