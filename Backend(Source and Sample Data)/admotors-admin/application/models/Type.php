<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for api table
 */
class Type extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_type', 'typ_id', 'ty' );
	}

	
}