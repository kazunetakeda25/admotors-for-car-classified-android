<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Downloads Controller
 */
class Downloads extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'DOWNLOADS' );
	}
		/**
	 * List down the registered users
	 */
	function index() {
		
		// no publish filter
		$conds['no_publish_filter'] = 1;
		$conds['order_by'] = 1;
		$conds['order_by_field'] = "download_count";
		$conds['order_by_type'] = "desc";
		
		$this->data['downloads'] = $this->Wallpaper->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );

		$this->data['rows_count'] = count($this->data['downloads']);

		// load index logic
		parent::index();
	}

}