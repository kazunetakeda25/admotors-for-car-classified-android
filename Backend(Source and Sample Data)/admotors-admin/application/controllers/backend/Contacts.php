<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Categories Controller
 */
class Contacts extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'CONTACTS' );
	}

	/**
	 * List down conatct message
	 */
	function index() {

		// get rows count
		$this->data['rows_count'] = $this->Contact->count_all();

		// get comments
		$this->data['contacts'] = $this->Contact->get_all( $this->pag['per_page'], $this->uri->segment( 4 ) );

		// load index logic
		parent::index();
	}

	/**
	 * Delete the record
	 * 1) delete comment
	 * 2) check transactions
	 */
	function delete( $con_id ) {

		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );
		
		/**
		 * Delete comment
		 */
		if ( ! $this->Contact->delete( $con_id )) {
		// if there is an error in deleting news,
		
			// rollback
			$this->trans_rollback();

			// error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
			redirect( $this->module_site_url());
		}
			
		/**
		 * Check Transcation Status
		 */
		if ( !$this->check_trans()) {

			$this->set_flash_msg( 'error', get_msg( 'err_model' ));	
		} else {
        	
			$this->set_flash_msg( 'success', get_msg( 'success_contact_delete' ));
		}
		
		redirect( $this->module_site_url());
	}


	/**
	* View Comment Detail
	*/
	function detail($con_id)
	{
		$contact = $this->Contact->get_one( $con_id );
		$this->data['contact'] = $contact;

		$this->load_detail( $this->data );
	}

}