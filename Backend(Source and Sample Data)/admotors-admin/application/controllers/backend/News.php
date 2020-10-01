<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * News Controller
 */
class News extends BE_Controller {

	/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'NEWS' );
	}

	/**
	 * List down the registered users
	 */
	function index() {

		// no publish filter
		$conds['no_publish_filter'] = 1;

		// get rows count
		$this->data['rows_count'] = $this->NewsModel->count_all_by( $conds );

		// get news
		$this->data['news_list'] = $this->NewsModel->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );

		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match.
	 */
	function search() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'news_search' );
		
		// condition with search term
		$conds = array( 'searchterm' => $this->searchterm_handler( $this->input->post( 'searchterm' )) );
		$conds['no_publish_filter'] = 1;

		// pagination
		$this->data['rows_count'] = $this->NewsModel->count_all_by( $conds );

		// search data
		$this->data['news_list'] = $this->NewsModel->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ) );
		
		// load add list
		parent::search();
	}

	/**
	 * Create new one
	 */
	function add() {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'news_add' );

		// call the core add logic
		parent::add();
	}

	/**
	 * Update the existing one
	 */
	function edit( $id ) {

		// breadcrumb urls
		$this->data['action_title'] = get_msg( 'news_edit' );

		// load user
		$this->data['news'] = $this->NewsModel->get_one( $id );

		// call the parent edit logic
		parent::edit( $id );
	}

	/**
	 * Saving Logic
	 * 1) save news
	 * 2) check transaction status
	 *
	 * @param      boolean  $id  The user identifier
	 */
	function save( $id = false ) {

		// start the transaction
		$this->db->trans_start();
		
		// prepare cat name
		$data['user_id'] = $this->ps_auth->get_user_info()->user_id;

		// news_title
		if ( $this->has_data( 'news_title' )) {
			$data['news_title'] = $this->get_data( 'news_title' );
		}

		// news_desc
		if ( $this->has_data( 'news_desc' )) {
			$data['news_desc'] = $this->get_data( 'news_desc' );
		}

		// news_search_tags
		if ( $this->has_data( 'news_search_tags' )) {
			$data['news_search_tags'] = $this->get_data( 'news_search_tags' );
		}

		// seo_title
		if ( $this->has_data( 'seo_title' )) {
			$data['seo_title'] = $this->get_data( 'seo_title' );
		}

		// seo_desc
		if ( $this->has_data( 'seo_desc' )) {
			$data['seo_desc'] = $this->get_data( 'seo_desc' );
		}

		// seo_keywords
		if ( $this->has_data( 'seo_keywords' )) {
			$data['seo_keywords'] = $this->get_data( 'seo_keywords' );
		}

		// cat_id
		if ( $this->has_data( 'cat_id' )) {
			$data['cat_id'] = $this->get_data( 'cat_id' );
		}

		// if 'is published' is checked,
		if ( $this->has_data( 'news_is_published' )) {
			$data['news_is_published'] = 1;
		} else {
			$data['news_is_published'] = 0;
		}

		// if 'is editor pick' is checked,
		if ( $this->has_data( 'news_is_editor_pick' )) {
			$data['news_is_editor_pick'] = 1;
		} else {
			$data['news_is_editor_pick'] = 0;
		}

		// save category
		if ( ! $this->NewsModel->save( $data, $id )) {
		// if there is an error in inserting user data,	

			// rollback the transaction
			$this->db->trans_rollback();

			// set error message
			$this->data['error'] = get_msg( 'err_model' );
			
			return;
		}

		$id = ( !$id )? $data['news_id']: $id ;

		// Save Youtube URLs
		if ( !$this->save_youtubes( $id )) {

			// rollback the transaction
			$this->db->trans_rollback();

			// set error message
			$this->data['error'] = get_msg( 'err_model' );
			
			return;
		}

		// commit the transaction
		if ( ! $this->check_trans()) {
        	
			// set flash error message
			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {

			if ( $id ) {
			// if user id is not false, show success_add message
				
				$this->set_flash_msg( 'success', get_msg( 'success_news_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_news_add' ));
			}
		}

		if ( $this->has_data( 'gallery' )) {
		// if there is gallery, redirecti to gallery
			
			redirect( $this->module_site_url( 'gallery/'. $id));
		} else {
		// redirect to list view

			redirect( $this->module_site_url() );
		}
	}

	/**
	 * Saves youtubes.
	 *
	 * @param      <type>  $id     The identifier
	 */
	function save_youtubes( $id )
	{
		/** 
		 * Insert Youtube URLs 
		 */
		if ( $this->has_data( 'youtube_url' )) {

			$youtubes = $this->get_data( 'youtube_url' );

			if ( is_array( $youtubes )) {

				// delete existing youtube urls
				if ( !$this->NewsYoutube->delete_by( array( 'news_id' => $id ))) {

					return false;
				}

				// add new youtube urls
				foreach ( $youtubes as $youtube ) {

					if ( !empty( $youtube )) {
						$youtube_data = array( 'news_id' => $id, 'news_youtube_url' => $youtube );

						if ( ! $this->NewsYoutube->save( $youtube_data )) {
						// if error in saving image
							
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	/**
	 * Determines if valid input.
	 *
	 * @return     boolean  True if valid input, False otherwise.
	 */
	function is_valid_input( $id = 0 ) {
		
		$this->form_validation->set_rules( 'news_title', get_msg( 'news_title' ), 'required' );
		$this->form_validation->set_rules( 'cat_id', get_msg( 'cat_id' ), 'required');

		if ( $this->form_validation->run() == FALSE ) {
		// if there is an error in validating,

			return false;
		}

		return true;
	}

	/**
	 * Delete the record
	 * 1) delete news
	 * 2) check transactions
	 */
	function delete( $news_id ) {

		// start the transaction
		$this->db->trans_start();

		// check access
		$this->check_access( DEL );
		
		// enable trigger to delete all news related data
		$enable_trigger = true;

		if ( ! $this->ps_delete->delete_news( $news_id, $enable_trigger )) {
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
        	
			$this->set_flash_msg( 'success', get_msg( 'success_news_delete' ));
		}
		
		redirect( $this->module_site_url());
	}

	/**
	 * Show Gallery
	 *
	 * @param      <type>  $id     The identifier
	 */
	function gallery( $id ) {
		
		// breadcrumb urls
		$this->data['action_title'] = array( 
			array( 'url' => 'edit/'. $id, 'label' => 'Edit News' ), 
			array( 'label' => get_msg( 'news_gallery' ))
		);
		
		$_SESSION['parent_id'] = $id;
		$_SESSION['type'] = 'news';
    	    	
    	$this->load_gallery();
    }

    /**
	 * Publish the record
	 *
	 * @param      integer  $news_id  The news identifier
	 */
	function ajx_publish( $news_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$news_data = array( 'news_is_published'=> 1 );
			
		// save data
		if ( $this->NewsModel->save( $news_data, $news_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}

	/**
	 * Unpublish the record
	 *
	 * @param      integer  $news_id  The news identifier
	 */
	function ajx_unpublish( $news_id = 0 )
	{
		// check access
		$this->check_access( PUBLISH );
		
		// prepare data
		$news_data = array( 'news_is_published'=> 0 );
			
		// save data
		if ( $this->NewsModel->save( $news_data, $news_id )) {
			echo 'true';
		} else {
			echo 'false';
		}
	}

	/**
	 * Check news name via ajax
	 *
	 * @param      boolean  $news_id  The news identifier
	 */
	function ajx_exists( $news_id = false )
	{
		// get category name
		$news_title = $_REQUEST['news_title'];

		if ( $this->is_valid_name( $news_title, $news_id )) {
		// if the news name is valid,
			
			echo "true";
		} else {
		// if invalid category name,
			
			echo "false";
		}
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

		if ( strtolower( $this->NewsModel->get_one( $id )->news_title ) == strtolower( $name )) {
		// if the name is existing name for that user id,

			return true;
		} else if ( $this->NewsModel->exists( array( 'news_title' => $name ))) {
		// if the name is existed in the system,

			$this->form_validation->set_message('is_valid_name', get_msg( 'err_dup_name' ));
			return false;
		}

		return true;
	}
}