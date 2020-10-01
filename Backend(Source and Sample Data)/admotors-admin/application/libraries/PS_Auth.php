<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * PanaceaSoft Authentication
 */
class PS_Auth {

	// codeigniter instance
	protected $CI;

	// logged in user
	protected $logged_in_user;

	/**
	 * constrcuts required variables
	 */
	function __construct( )
	{
		// get CI instance
		$this->CI =& get_instance();

		// get logged in user info
		$this->logged_in_user = $this->get_user_info();
	}

	/**
	 * Validate the user and requested actions
	 * @return [type] [description]
	 */
	function validate( $module_name = false )
	{
		if ( ! $this->is_logged_in()) {
		// if there is no logged in user, return false
			
			return false;
		}

		if ( $module_name && ! $this->has_permission( $module_name )) {
		// if no permission for requrested module, return false 
			
			return false;
		}

		return true;
	}

	/**
	 * login by user info and keep in session
	 *
	 * @param      <type>   $user_email  The user email
	 * @param      <type>   $user_pass   The user pass
	 *
	 * @return     boolean  ( description_of_the_return_value )
	 */
	function login( $user_email, $user_pass )
	{
		// prep conditions
		$conds = array( 'user_email' => $user_email, 'user_password' => $user_pass, 'is_banned' => 0 );

		if ( ! $this->CI->User->exists( $conds )) {
		// if the user email and password is not existed, return false
			
			return false;
		}
		
		// get user info
		$user = $this->CI->User->get_one_by( $conds );

		// keep user info in session
		$this->CI->session->set_userdata( 'user_id', $user->user_id );
		$this->CI->session->set_userdata( 'role_id', $user->role_id );
		$this->CI->session->set_userdata( 'is_sys_admin', $user->user_is_sys_admin );
		
		return true;
	}

	/**
	 * logout the logged in user
	 *
	 * @return     boolean  ( description_of_the_return_value )
	 */
	function logout()
	{
		// keep user info in session
		$this->CI->session->unset_userdata( 'user_id' );
		$this->CI->session->unset_userdata( 'role_id' );
		$this->CI->session->unset_userdata( 'is_sys_admin' );

		return true;
	}

	/**
	 * Determines if logged in.
	 */
	function is_logged_in()
	{
		return $this->CI->session->userdata( 'user_id' ) != false;
	}

	/**
	 * Determines if it has permission.
	 *
	 * @param      <type>   $module_name  The module name
	 *
	 * @return     boolean  True if has permission, False otherwise.
	 */
	function has_permission( $module_name )
	{
		if ( ! $this->logged_in_user ) {
		// if there is no logged in user, return false

			return false;
		}

		if ( $this->is_system_admin()) {
		// system admin can access everywhere

			return true;
		}

		return $this->CI->User->has_permission( $module_name, $this->logged_in_user->user_id );
	}

	/**
	 * Determines if it has access.
	 *
	 * @param      <type>   $module_name  The module name
	 * @param      <type>   $action_id    The action identifier
	 *
	 * @return     boolean  True if has access, False otherwise.
	 */
	function has_access( $action_id )
	{
		if ( ! $this->logged_in_user ) {
		// if there is no logged in user, return false

			return false;
		}

		if ( $this->is_system_admin()) {
		// system admin can access everywhere

			return true;
		}

		return $this->CI->User->has_access( $action_id, $this->logged_in_user->role_id );
	}

	/**
	 * Gets the logged in user information
	 */
	function get_user_info()
	{
		if ( $this->is_logged_in()) {
		// if there is logged in user,
			
			return $this->CI->User->get_one( $this->CI->session->userdata( 'user_id' ));
		}

		return false;
	}

	/**
	 * Determines if system admin.
	 *
	 * @return     boolean  True if system admin, False otherwise.
	 */
	function is_system_admin()
	{
		return ( $this->CI->session->userdata( 'is_sys_admin' ) == 1 );
	}

	/**
	 * Determines if system user.
	 */
	function is_system_user() 
	{
		return ( $this->CI->session->userdata( 'role_id' ) != 4 );
	}

	/**
	 * Determines if backend user.
	 *
	 * @return     boolean  True if backend user, False otherwise.
	 */
	function is_backend_user()
	{
		return true;
	}

	/**
	 * Determines if frontend user.
	 *
	 * @return     boolean  True if frontend user, False otherwise.
	 */
	function is_frontend_user()
	{
		return ( $this->CI->session->userdata( 'role_id' ) == 4 );
	}
}