<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Frontend Controller which extends PS main Controller
 * 1) Loading Template
 */
class FE_Controller extends PS_Controller {
   	
	/**
	 * constructs required variables
	 * 1) template path
	 * 2) base url
	 * 3) site url
	 *
	 * @param      <type>  $auth_level   The auth level
	 * @param      <type>  $module_name  The module name
	 */
	function __construct( $auth_level, $module_name )
	{
		parent::__construct( $auth_level, $module_name );
		
		// template path
		$this->template_path = $this->config->item( 'fe_view_path' );

		// base url & site url
		$fe_url = $this->config->item( 'fe_url' );

		if ( !empty( $fe_url )) {
		// if fe controller path is not empty,
			
			$this->module_url = $fe_url .'/'. $this->module_url;
		}

		// load meta data
		$this->load_metadata();

		// load widget library
		$this->load->library( 'PS_Widget' );
		$this->ps_widget->set_template_path( $this->template_path );
	}

	/**
	 * returns site url for controller
	 *
	 * @param      boolean  $path   The path
	 *
	 * @return     <type>   ( description_of_the_return_value )
	 */
	function module_site_url( $path = false )
	{
		if ( $path ) {
		// if the path is exists,
			
			return site_url( $path );
		}

		return site_url();
	}
}