<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Earning Point Controller
 */
class Earningpoints extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'EARNINGPOINTS' );
	}
		/**
	 * List down the registered users
	 */
	function index() {
		
		// no publish filter
		$conds['no_publish_filter'] = 1;
		$conds['order_by'] = 1;
		$conds['order_by_field'] = "added_date";
		$conds['order_by_type'] = "desc";
		// get rows count
		$this->data['rows_count'] = $this->Earningpoint->count_all_by( $conds );
		
		$this->data['earnpoints'] = $this->Earningpoint->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );
		// load index logic
		parent::index();
	}

}