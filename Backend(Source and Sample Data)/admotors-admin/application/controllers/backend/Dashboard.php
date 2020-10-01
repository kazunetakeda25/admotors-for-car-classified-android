<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Dashboard Controller
 */
class Dashboard extends BE_Controller {

	/**
	 * set required variable and libraries
	 */
	function __construct() {
		parent::__construct( ROLE_CONTROL, 'DASHBOARD' );
	}

	/**
	 * Home page for the dashbaord controller
	 */
	function index() {
		
		$this->load_template( 'dashboard' );
	}

	function exports()
	{
		// Load the DB utility class
		$this->load->dbutil();
		
		// Backup your entire database and assign it to a variable
		$export = $this->dbutil->backup();
		
		// Load the download helper and send the file to your desktop
		$this->load->helper('download');
		force_download('ps_wallpapers.zip', $export);
	}
}