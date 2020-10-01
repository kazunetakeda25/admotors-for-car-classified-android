<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * blogs Controller
 */
class Blogs extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'BLOGS' );
	}

	/**
	 * List down the registered users
	 */
	function index() {
		
		// no delete flag
		// no publish filter
		$conds['no_publish_filter'] = 1;

		// get rows count
		$this->data['rows_count'] = $this->Feed->count_all_by( $conds );

		// get blogs
		$this->data['blogs'] = $this->Feed->get_all_by( $conds , $this->pag['per_page'], $this->uri->segment( 4 ) );

		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {
		

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'blog_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )) );
		// no publish filter
		$conds['no_publish_filter'] = 1;

		// pagination
		$this->data['rows_count'] = $this->Feed->count_all_by( $conds );

		// search data

		$this->data['blogs'] = $this->Feed->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
		// load add list
		parent::search();
	}

	/**
	 * Create new one
	 */
	function add() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'blog_add' );

		// call the core add logic
		parent::add();
	}

	
	/**
	 * Saving Logic
	 * 1) upload image
	 * 2) save blog
	 * 3) save image
	 * 4) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {
		// start the transaction

		$this->db->trans_start();
		$logged_in_user = $this->ps_auth->get_user_info();
		
		/** 
		 * Insert blog Records 
		 */
		$data = array();

		// prepare blog title
		if ( $this->has_data( 'name' )) {
			$data['name'] = $this->get_data( 'name' );
		}
		

		// prepare blog description
		if ( $this->has_data( 'description' )) {
			$data['description'] = $this->get_data( 'description' );
		}

		
		$data['status'] = 1;
		
		// set timezone
		$data['added_user_id'] = $logged_in_user->user_id;

		if($id == "") {
			//save
			$data['added_date'] = date("Y-m-d H:i:s");
		} else {
			//edit
			unset($data['added_date']);
			$data['updated_date'] = date("Y-m-d H:i:s");
			$data['updated_user_id'] = $logged_in_user->user_id;
		}

		//save category
		if ( ! $this->Feed->save( $data, $id )) {
		// if there is an error in inserting user data,	

			// rollback the transaction
			$this->db->trans_rollback();

			// set error message
			$this->data['error'] = get_msg( 'err_model' );
			
			return;
		}

		
		/** 
		 * Check Transactions 
		 */

		// commit the transaction
		if ( ! $this->check_trans()) {
        	
			// set flash error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {

			if ( $id ) {
			// if user id is not false, show success_add message
				
				$this->set_flash_msg( 'success', get_msg( 'success_blog_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_blog_add' ));
			}
		}
	
		//get inserted product id
		$id = ( !$id )? $data['id']: $id ;
		
		if ( $this->has_data( 'gallery' )) {

		// if there is gallery, redirecti to gallery
			redirect( $this->module_site_url( 'gallery/' .$id ));
		}
		else {
		// redirect to list view
			redirect( $this->module_site_url() );
		}
	}

	/**
	 * Show Gallery
	 *
	 * @param      <type>  $id     The identifier
	 */
	function gallery( $id ) {
		// breadcrumb urls
		$edit_blog = get_msg('blog_edit');

		$this->data['action_title'] = array( 
			array( 'url' => 'edit/'. $id, 'label' => $edit_blog ), 
			array( 'label' => get_msg( 'blog_gallery' ))
		);
		
		$_SESSION['parent_id'] = $id;
		$_SESSION['type'] = 'blog';
    	    	
    	$this->load_gallery();
    }

	/**
 	* Update the existing one
	*/
	function edit( $id ) 
	{

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'blog_edit' );

		// load user
		$this->data['blog'] = $this->Feed->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );

	}

	/**
	 * Determines if valid input.
	 *
	 * @return     boolean  True if valid input, False otherwise.
	 */
	function is_valid_input( $id = 0 ) 
	{
		
		$rule = 'required|callback_is_valid_name['. $id  .']';

		$this->form_validation->set_rules( 'name', get_msg( 'title' ), $rule);
		
		if ( $this->form_validation->run() == FALSE ) {
		// if there is an error in validating,

			return false;
		}

		return true;
	}

	/**
	 * Determines if valid name.
	 *
	 * @param      <type>   $name  The  name
	 * @param      integer  $id     The  identifier
	 *
	 * @return     boolean  True if valid name, False otherwise.
	 */
	function is_valid_name( $name, $id = 0 )
	{		
		 $conds['name'] = $name;
			
		 	if( $id != "") {
				if ( strtolower( $this->Feed->get_one( $id )->name ) == strtolower( $name )) {
				// if the name is existing name for that user id,
					return true;
				} 
			} else {
				if ( $this->Feed->exists( ($conds ))) {
				// if the name is existed in the system,
					$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
					return false;
				}
			}
			return true;
	}


	/**
	 * Delete the record
	 * 1) delete blog
	 * 2) delete image from folder and table
	 * 3) check transactions
	 */
	function delete( $blog_id ) {

		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );

		// delete categories and images
		$enable_trigger = true; 
		
		// delete categories and images
		if ( !$this->ps_delete->delete_feed( $blog_id, $enable_trigger )) {

			// set error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));

			// rollback
			$this->trans_rollback();

			// redirect to list view
			redirect( $this->module_site_url());
		}
			
		/**
		 * Check Transcation Status
		 */
		if ( !$this->check_trans()) {

			$this->set_flash_msg( 'error', get_msg( 'err_model' ));	
		} else {
        	
			$this->set_flash_msg( 'success', get_msg( 'success_blog_delete' ));
		}
		
		redirect( $this->module_site_url());
	}


	/**
	 * Check blog name via ajax
	 *
	 * @param      boolean  $cat_id  The cat identifier
	 */
	function ajx_exists( $id = false )
	{
		// get blog name
		$name = $_REQUEST['name'];
		if ( $this->is_valid_name( $name, $id )) {
		// if the blog name is valid,
			echo "true";
		} else {
		// if invalid blog name,
			
			echo "false";
		}
	}

	/**
	 * Publish the record
	 *
	 * @param      integer  $feed_id  The blog identifier
	 */
	function ajx_publish( $feed_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$feed_data = array( 'status'=> 1 );
			
		// save data
		if ( $this->Feed->save( $feed_data, $feed_id )) {
			echo true;
		} else {
			echo false;
		}
	}
	
	/**
	 * Unpublish the records
	 *
	 * @param      integer  $feed_id  The category identifier
	 */
	function ajx_unpublish( $feed_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$feed_data = array( 'status'=> 0 );
			
		// save data
		if ( $this->Feed->save( $feed_data, $feed_id )) {
			echo true;
		} else {
			echo false;
		}
	}

	 

}