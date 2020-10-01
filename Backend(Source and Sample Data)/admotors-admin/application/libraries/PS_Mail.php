<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * PanaceaSoft Authentication
 */
class PS_Mail {

	// codeigniter instance
	protected $CI;

	/**
	 * Load CI Instances
	 */
	function __construct()
	{
		// get CI instance
		$this->CI =& get_instance();

		// load mail library
		$this->CI->load->library( 'email', array(
       		'mailtype'  => 'html',
        	'newline'   => '\r\n'
		));
	}

	/**
	 * Sends a from admin.
	 *
	 * @param      <type>  $to       { parameter_description }
	 * @param      <type>  $subject  The subject
	 * @param      <type>  $msg      The message
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function send_from_admin( $to, $subject, $msg ) 
	{
		// get system admin email
		$from = $this->CI->config->item( 'sender_email' );

		// get system admin name
		$from_name = $this->CI->config->item( 'sender_name' );

		// send email
		return $this->send( $to, $subject, $msg, $from, $from_name );
	}

	/**
	 * Send Email
	 *
	 * @param      <type>   $from       The from
	 * @param      <type>   $to         { parameter_description }
	 * @param      <type>   $subject    The subject
	 * @param      <type>   $msg        The message
	 * @param      boolean  $from_name  The from name
	 *
	 * @return     <type>   ( description_of_the_return_value )
	 */
	function send( $to, $subject, $msg, $from, $from_name = false )
	{
		// Sender Information
		$this->CI->email->from( $from, $from_name );
		
		// Receiver Information
		$this->CI->email->to( $to ); 

		// Subject
		$this->CI->email->subject( $subject );

		// msg
		$this->CI->email->message( $msg );	

		// Send Email
		return $this->CI->email->send();
	}
}