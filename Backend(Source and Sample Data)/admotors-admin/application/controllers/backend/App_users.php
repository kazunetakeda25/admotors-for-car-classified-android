<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Users crontroller for BE_USERS table
 */
class App_users extends BE_Controller {

	/**
	 * Constructs required variables
	 */
	function __construct() {
		parent::__construct( MODULE_CONTROL, 'USERS' );
	}

	/**
	 * List down the registered users
	 */
	function index() {

		//system users filter
		//$conds = array( 'system_role_id' => 4 );
		$conds['role_id'] = 4;
		// get rows count
		$this->data['rows_count'] = $this->User->count_all($conds);

		// get users
		$this->data['users'] = $this->User->get_all_by($conds, $this->pag['per_page'], $this->uri->segment( 4 ) );

		// load index logic
		parent::index();
	}

	/**
	 * Searches for the first match in system users
	 */
	function search() {

		// breadcrumb urls
		$data['action_title'] = get_msg( 'user_search' );

		// handle search term
		$searchterm = $this->searchterm_handler( $this->input->post( 'searchterm' ));
		
		// condition
		$conds = array( 'searchterm' => $searchterm );

		$this->data['rows_count'] = $this->User->count_all_by( $conds );

		$this->data['users'] = $this->User->get_all_by( $conds, $this->pag['per_page'], $this->uri->segment( 4 ));
		
		parent::search();
	}

	/**
	 * Create the user
	 */
	function add() {

		// breadcrumb
		$this->data['action_title'] = get_msg( 'user_add' );

		// call add logic
		parent::add();
	}

	/**
	 * Update the user
	 */
	function edit( $user_id ) {

		// breadcrumb
		$this->data['action_title'] = get_msg( 'user_edit' );

		// load user
		$this->data['user'] = $this->User->get_one( $user_id );

		// call update logic
		parent::edit( $user_id );
	}

	/**
	 * Delete the user
	 */
	function delete() {

		// check access
		$this->check_access( DEL );

	}

	/**
	 * Saving User Info logic
	 *
	 * @param      boolean  $user_id  The user identifier
	 */
	function save( $user_id = false ) {
		
		// prepare user object and permission objects
		$user_data = array();

		// save username
		if ( $this->has_data( 'user_name' )) {
			$user_data['user_name'] = $this->get_data( 'user_name' );
		}

		// save user email
		if( $this->has_data( 'user_email' )) {
			$user_data['user_email'] = $this->get_data( 'user_email' );
		}

		// save password if exists or not empty
		if ( $this->has_data( 'user_password' ) 
			&& !empty( $this->get_data( 'user_password' ))) {
			$user_data['user_password'] = md5( $this->get_data( 'user_password' ));
		}

		// save role id
		if( $this->has_data( 'role_id' )) {
			$user_data['role_id'] = $this->get_data( 'role_id' );
		}

		$permissions = ( $this->get_data( 'permissions' ) != false )? $this->get_data( 'permissions' ): array();

		// save data
		if ( ! $this->User->save_user( $user_data, $permissions, $user_id )) {
		// if there is an error in inserting user data,	

			$this->set_flash_msg( 'error', get_msg( 'err_model' ));
		} else {
		// if no eror in inserting

			if ( $user_id ) {
			// if user id is not false, show success_add message
				
				$this->set_flash_msg( 'success', get_msg( 'success_user_edit' ));
			} else {
			// if user id is false, show success_edit message

				$this->set_flash_msg( 'success', get_msg( 'success_user_add' ));
			}
		}

		redirect( $this->module_site_url());
	}

	/**
	 * Determines if valid input.
	 *
	 * @return     boolean  True if valid input, False otherwise.
	 */
	function is_valid_input( $user_id = 0 ) {
		
		$email_rule = 'required|valid_email|callback_is_valid_email['. $user_id  .']';
		$rule = 'required';

		$this->form_validation->set_rules( 'user_email', get_msg( 'user_email' ), $email_rule);
		$this->form_validation->set_rules( 'user_name', get_msg( 'user_name' ), $rule );
		
		$user = $this->User->get_one( $user_id );

		if ( !$user->user_is_sys_admin ) {
		// if updated user is not system admin,
			
			$this->form_validation->set_rules( 'permissions[]', get_msg( 'allowed_modules'), $rule );
		}
		
		if ( $user_id === 0 ) {
		// password is required if new user
			
			$this->form_validation->set_rules( 'user_password', get_msg( 'user_password' ), $rule );
			$this->form_validation->set_rules( 'conf_password', get_msg( 'conf_password' ), $rule .'|matches[user_password]' );
		}

		if ( $this->form_validation->run() == FALSE ) {
		// if there is an error in validating,

			return false;
		}

		return true;
	}

	/**
	 * Determines if valid email.
	 *
	 * @param      <type>   $email  The user email
	 * @param      integer  $user_id     The user identifier
	 *
	 * @return     boolean  True if valid email, False otherwise.
	 */
	function is_valid_email( $email, $user_id = 0 )
	{		

		if ( strtolower( $this->User->get_one( $user_id )->user_email ) == strtolower( $email )) {
		// if the email is existing email for that user id,
			
			return true;
		} else if ( $this->User->exists( array( 'user_email' => $_REQUEST['user_email'] ))) {
		// if the email is existed in the system,

			$this->form_validation->set_message('is_valid_email', get_msg( 'err_dup_email' ));
			return false;
		}

		return true;
	}

	/**
	 * Ajax Exists
	 *
	 * @param      <type>  $user_id  The user identifier
	 */
	function ajx_exists( $user_id = null )
	{
		$user_email = $_REQUEST['user_email'];
		
		if ( $this->is_valid_email( $user_email, $user_id )) {
		// if the user email is valid,
			
			echo "true";
		} else {
		// if the user email is invalid,

			echo "false";
		}
	}
}