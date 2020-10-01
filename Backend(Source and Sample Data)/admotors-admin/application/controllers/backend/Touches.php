<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Touches Controller
 */
class Touches extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'TOUCHES' );
	}
		/**
	 * List down the registered users
	 */
	function index() {
		
		// no publish filter
		$conds['no_publish_filter'] = 1;
		$conds['order_by'] = 1;
		$conds['order_by_field'] = "touch_count";
		$conds['order_by_type'] = "desc";
	
		$this->data['touches'] = $this->Wallpaper->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );

		$this->data['rows_count'] = count($this->data['touches']);

		// load index logic
		parent::index();
	}

}