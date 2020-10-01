<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Permission Model for Permission Table
 */
class Permission extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() {
		parent::__construct( 'core_permissions', false, false );
	}
}