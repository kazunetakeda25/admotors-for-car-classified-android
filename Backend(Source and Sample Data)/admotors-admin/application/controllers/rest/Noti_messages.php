<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for Notification
 */
class Noti_messages extends API_Controller
{
	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		// call the parent
		parent::__construct( 'Noti' );

	}

	function all_notis_post() 
	{
		
		$limit = $this->get( 'limit' );
   		$offset = $this->get( 'offset' );

		$noti_obj = $this->Noti_message->get_all($limit,$offset)->result();

		foreach ($noti_obj as $nt)
		{
			$noti_user_data = array(
	        	"noti_id" 		=> $nt->id,
	        	"user_id" 		=> $this->post('user_id'),
	        	"device_token"  => $this->post('device_token')

	    	);

	    	if ( $this->Notireaduser->exists( $noti_user_data )) {
	    		$nt->is_read = 1;
	    	} else {
	    		$nt->is_read = 0;
	    	}

	    	
		}

    	$this->custom_response_noti( $noti_obj );

	}

	/**
	 * Convert Object
	 */
	function convert_object( &$obj )
	{

		// call parent convert object
		parent::convert_object( $obj );

		// convert customize category object
		$noti_user_data = array(
        	"noti_id" => $obj->id,
        	"user_id" => $this->post('user_id'),
        	"device_token"  => $this->post('device_token')
    	);

    	if ( !$this->Notireaduser->exists( $noti_user_data )) {
    		
    		$obj->is_read = 0;
    	} else {
    		
    		$obj->is_read = 1;
    	}

		// convert customize item object
		$this->ps_adapter->convert_noti_message( $obj );
	}
}