<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for Notification
 */
class Chats extends API_Controller
{
	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		// call the parent
		parent::__construct( 'Chat' );

	}

	/**
	 * Add Chat History
	 */
	function add_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        )

        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;
        $type = $this->post('type');

        $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

        $chat_history_data = $this->Chat->get_one_by($chat_data);


        if($chat_history_data->id == "") {

        	if ( $type == "to_buyer" ) {

		    	$buyer_unread_count = $chat_history_data->buyer_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"buyer_unread_count" => $buyer_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s")

		        );

		    	} elseif ( $type == "to_seller" ) {

		    	$seller_unread_count = $chat_history_data->seller_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"seller_unread_count" => $seller_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s")

		        );

		    	}

	        if ( !$this->Chat->save($chat_data)) {
	        	
	        	$this->error_response( get_msg( 'err_chat_history_save' ));
	        
	        } else {

	        	$obj = $this->Chat->get_one_by($chat_data);
				$this->ps_adapter->convert_chathistory( $obj );
				$this->custom_response( $obj );

	        }

	    } else {

	    	if ( $type == "to_buyer" ) {

		    	$buyer_unread_count = $chat_history_data->buyer_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"buyer_unread_count" => $buyer_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s")

		        );

		    	} elseif ( $type == "to_seller" ) {

		    	$seller_unread_count = $chat_history_data->seller_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"seller_unread_count" => $seller_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s")

		        );

		    	}
	    	

	    	if ( $this->Chat->save($chat_data,$chat_history_data->id)) {
	        	
	        	$obj = $this->Chat->get_one_by($chat_data);
				$this->ps_adapter->convert_chathistory( $obj );
				$this->custom_response( $obj );
	        
	        }

	    }


	}


	/**
	 * Update Price 
	 */
	function update_price_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'nego_price',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $type = $this->post('type');

        $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

        $chat_history_data = $this->Chat->get_one_by($chat_data);


        if($chat_history_data->id == "") {

        	if ( $type == "to_buyer" ) {

        		//prepare data for noti
		    	$user_ids[] = $this->post('buyer_user_id');


	        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
	        	//print_r($devices);die;	

				$device_ids = array();
				if ( count( $devices ) > 0 ) {
					foreach ( $devices as $device ) {
						$device_ids[] = $device->device_token;
					}
				}


				$user_id = $this->post('buyer_user_id');
	       		$user_name = $this->User->get_one($user_id)->user_name;

		    	$price = $this->post('nego_price');
	       		if ( $price == 0) {
		    		$data['message'] = "Offer Rejected!";
		    	} else {
		    		$data['message'] = "Make Offer!";
		    	}
		    	$data['buyer_user_id'] = $this->post('buyer_user_id');
		    	$data['seller_user_id'] = $this->post('seller_user_id');
		    	$data['sender_name'] = $user_name;
		    	$data['item_id'] = $this->post('item_id');

        		
		    	$buyer_unread_count = $chat_history_data->buyer_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"buyer_unread_count" => $buyer_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s"),
		        	"nego_price" => $this->post('nego_price')

		        );


		       

	    	} elseif ( $type == "to_seller" ) {

	    		//prepare data for noti
		    	$user_ids[] = $this->post('seller_user_id');


	        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
	        	//print_r($devices);die;	

				$device_ids = array();
				if ( count( $devices ) > 0 ) {
					foreach ( $devices as $device ) {
						$device_ids[] = $device->device_token;
					}
				}


				$user_id = $this->post('seller_user_id');
	       		$user_name = $this->User->get_one($user_id)->user_name;

		    	$price = $this->post('nego_price');
	       		if ( $price == 0) {
		    		$data['message'] = "Offer Rejected!";
		    	} else {
		    		$data['message'] = "Make Offer!";
		    	}
		    	$data['buyer_user_id'] = $this->post('buyer_user_id');
		    	$data['seller_user_id'] = $this->post('seller_user_id');
		    	$data['sender_name'] = $user_name;
		    	$data['item_id'] = $this->post('item_id');

		    	$seller_unread_count = $chat_history_data->seller_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"seller_unread_count" => $seller_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s"),
		        	"nego_price" => $this->post('nego_price')

		        );

	    	}

	       	//sending noti
	    	$status = send_android_fcm_chat( $device_ids, $data );

	        $this->Chat->save($chat_data);	
	        $obj = $this->Chat->get_one_by($chat_data);
			$this->ps_adapter->convert_chathistory( $obj );
			$this->custom_response( $obj );


	    } else {

		    	if ( $type == "to_buyer" ) {


		    	//prepare data for noti
		    	$user_ids[] = $this->post('buyer_user_id');


	        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
	        	//print_r($devices);die;	

				$device_ids = array();
				if ( count( $devices ) > 0 ) {
					foreach ( $devices as $device ) {
						$device_ids[] = $device->device_token;
					}
				}


				$user_id = $this->post('buyer_user_id');
	       		$user_name = $this->User->get_one($user_id)->user_name;

		    	$price = $this->post('nego_price');
	       		if ( $price == 0) {
		    		$data['message'] = "Offer Rejected!";
		    	} else {
		    		$data['message'] = "Make Offer!";
		    	}
		    	$data['buyer_user_id'] = $this->post('buyer_user_id');
		    	$data['seller_user_id'] = $this->post('seller_user_id');
		    	$data['sender_name'] = $user_name;
		    	$data['item_id'] = $this->post('item_id');


		    	$buyer_unread_count = $chat_history_data->buyer_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"buyer_unread_count" => $buyer_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s"),
		        	"nego_price" => $this->post('nego_price')

		        );

		    	} elseif ( $type == "to_seller" ) {


		    	$user_ids[] = $this->post('seller_user_id');

	        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
	        


				$device_ids = array();
				if ( count( $devices ) > 0 ) {
					foreach ( $devices as $device ) {
						$device_ids[] = $device->device_token;
					}
				}


				$user_id = $this->post('seller_user_id');
	       		$user_name = $this->User->get_one($user_id)->user_name;

		    	$price = $this->post('nego_price');
	       		if ( $price == 0) {
		    		$data['message'] = "Offer Rejected!";
		    	} else {
		    		$data['message'] = "Make Offer!";
		    	}
		    	$data['buyer_user_id'] = $this->post('buyer_user_id');
		    	$data['seller_user_id'] = $this->post('seller_user_id');
		    	$data['sender_name'] = $user_name;
		    	$data['item_id'] = $this->post('item_id');

		    	$seller_unread_count = $chat_history_data->seller_unread_count;
		    	
		    	$chat_data = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"seller_unread_count" => $seller_unread_count + 1,
		        	"added_date" => date("Y-m-d H:i:s"),
		        	"nego_price" => $this->post('nego_price')

		        );

		    	}

	    	

	    	if( !$this->Chat->Save( $chat_data,$chat_history_data->id )) {

	    		$this->error_response( get_msg( 'err_price_update' ));

	    	
	    	} else {

	    		//sending noti
	    		$status = send_android_fcm_chat( $device_ids, $data );

	    		$obj = $this->Chat->get_one_by($chat_data);
				$this->ps_adapter->convert_chathistory( $obj );
				$this->custom_response( $obj );
	    	}

	    }
	}

	/**
	 * Update count
	 */
	function reset_count_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'type',
	        	'rules' => 'required'
	        )
        );


		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

        $chat_history_data = $this->Chat->get_one_by($chat_data);


        if($chat_history_data->id == "") {
	        	
	        $this->error_response( get_msg( 'err_chat_history_not_exist' ));


	    } else {
	    	
	    	if($this->post('type') == "to_seller") {

		    	$chat_data_update = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"seller_unread_count" => 0

		        );

		    } else if($this->post('type') == "to_buyer") {

		    	$chat_data_update = array(

		        	"item_id" => $this->post('item_id'), 
		        	"buyer_user_id" => $this->post('buyer_user_id'), 
		        	"seller_user_id" => $this->post('seller_user_id'),
		        	"buyer_unread_count" => 0

		        );
		    }

	    	if( !$this->Chat->Save( $chat_data_update,$chat_history_data->id )) {

	    		$this->error_response( get_msg( 'err_count_update' ));

	    	
	    	} else {

	    		$obj = $this->Chat->get_one_by($chat_data);
				$this->ps_adapter->convert_chathistory( $obj );
				$this->custom_response( $obj );

	    	}


	    }


	}

    /* Update accept or not
    */


    function update_accept_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'nego_price',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;
        $type = $this->post('type');

        $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

        $chat_history_data = $this->Chat->get_one_by($chat_data);
        //print_r($chat_history_data);die;

        // is_accept checking by seller_id and item_id
        $accept_checking_data = array(

        	"item_id" => $this->post('item_id'), 
        	"seller_user_id" => $this->post('seller_user_id'),

        );

        //print_r($accept_checking_data);die;	
        $accept_checking_result = $this->Chat->get_all_by($accept_checking_data)->result();
        //print_r($accept_checking_result);die;


        $accept_result_flag = 0;

        foreach ($accept_checking_result as $rst) {
	    		
    		if ($rst->is_accept == 1) {
    			$accept_result_flag = 1;
    			break;
    		}
    		

    	}

    	if( $accept_result_flag == 1 ) {
    		$this->error_response( get_msg( 'err_accept_offer' ));
    	} else {


	        if($chat_history_data->id == "") {

	        	if ( $type == "to_buyer" ) {

	        		//prepare data for noti
			    	$user_ids[] = $this->post('buyer_user_id');


		        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
		        	//print_r($devices);die;	

					$device_ids = array();
					if ( count( $devices ) > 0 ) {
						foreach ( $devices as $device ) {
							$device_ids[] = $device->device_token;
						}
					}


					$user_id = $this->post('buyer_user_id');
		       		$user_name = $this->User->get_one($user_id)->user_name;
		       		$price = $this->post('nego_price');
		       		
			    	$data['message'] = "Offer Accepted!";
			    	$data['buyer_user_id'] = $this->post('buyer_user_id');
			    	$data['seller_user_id'] = $this->post('seller_user_id');
			    	$data['sender_name'] = $user_name;
			    	$data['item_id'] = $this->post('item_id');

			    	$buyer_unread_count = $chat_history_data->buyer_unread_count;
			    	
			    	$chat_data = array(

			        	"item_id" => $this->post('item_id'), 
			        	"buyer_user_id" => $this->post('buyer_user_id'), 
			        	"seller_user_id" => $this->post('seller_user_id'),
			        	"buyer_unread_count" => $buyer_unread_count + 1,
			        	"added_date" => date("Y-m-d H:i:s"),
			        	"nego_price" => $this->post('nego_price'),
			        	"is_accept" => 1

			        );

			    	} elseif ( $type == "to_seller" ) {

			    	//prepare data for noti
			    	$user_ids[] = $this->post('seller_user_id');


		        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
		        	//print_r($devices);die;	

					$device_ids = array();
					if ( count( $devices ) > 0 ) {
						foreach ( $devices as $device ) {
							$device_ids[] = $device->device_token;
						}
					}


					$user_id = $this->post('seller_user_id');
		       		$user_name = $this->User->get_one($user_id)->user_name;

			    	$data['message'] = "Offer Accepted!";
			    	$data['buyer_user_id'] = $this->post('buyer_user_id');
			    	$data['seller_user_id'] = $this->post('seller_user_id');
			    	$data['sender_name'] = $user_name;
			    	$data['item_id'] = $this->post('item_id');	

			    	$seller_unread_count = $chat_history_data->seller_unread_count;
			    	
			    	$chat_data = array(

			        	"item_id" => $this->post('item_id'), 
			        	"buyer_user_id" => $this->post('buyer_user_id'), 
			        	"seller_user_id" => $this->post('seller_user_id'),
			        	"seller_unread_count" => $seller_unread_count + 1,
			        	"added_date" => date("Y-m-d H:i:s"),
			        	"nego_price" => $this->post('nego_price'),
			        	"is_accept" => 1


			        );

			    	}

			    //sending noti
		    	$status = send_android_fcm_chat( $device_ids, $data );	

		        $this->Chat->save($chat_data);	
		        $obj = $this->Chat->get_one_by($chat_data);
				$this->ps_adapter->convert_chathistory( $obj );
				$this->custom_response( $obj );


		    } else {


		    	//print_r($chat_history_data->is_accept);die;

		    	$conds_chat['seller_user_id'] = $chat_history_data->seller_user_id;
		    	$conds_chat['item_id'] = $chat_history_data->item_id;

		    	$chats = $this->Chat->get_all_by($conds_chat)->result();

		    	//print_r($chats);die;

		    	$accept_flag = 0;	

		    	foreach ($chats as $chat) {
		    		
		    		if ($chat->is_accept == 1) {
		    			$accept_flag = 1;
		    			break;
		    		}
		    		

		    	}
		    	
		    	if( $accept_flag == 1 ) {

		    		$this->error_response( get_msg( 'err_accept_offer' ));
		    	}

		    	else {

		    		if ( $type == "to_buyer" ) {

		    		//prepare data for noti
			    	$user_ids[] = $this->post('buyer_user_id');


		        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
		        	//print_r($devices);die;	

					$device_ids = array();
					if ( count( $devices ) > 0 ) {
						foreach ( $devices as $device ) {
							$device_ids[] = $device->device_token;
						}
					}


					$user_id = $this->post('buyer_user_id');
		       		$user_name = $this->User->get_one($user_id)->user_name;
		       		
		       		$data['message'] = "Offer Accepted!";
			    	$data['buyer_user_id'] = $this->post('buyer_user_id');
			    	$data['seller_user_id'] = $this->post('seller_user_id');
			    	$data['sender_name'] = $user_name;
			    	$data['item_id'] = $this->post('item_id');

			    	$buyer_unread_count = $chat_history_data->buyer_unread_count;


			    	$chat_data = array(

			        	"item_id" => $this->post('item_id'), 
			        	"buyer_user_id" => $this->post('buyer_user_id'), 
			        	"seller_user_id" => $this->post('seller_user_id'),
			        	"buyer_unread_count" => $buyer_unread_count + 1,
			        	"added_date" => date("Y-m-d H:i:s"),
			        	"nego_price" => $this->post('nego_price'),
			        	"is_accept"	 => 1	

			        );


			    	} elseif ( $type == "to_seller" ) {

			    	//prepare data for noti
			    	$user_ids[] = $this->post('seller_user_id');


		        	$devices = $this->Noti->get_all_device_in($user_ids)->result();
		        	//print_r($devices);die;	

					$device_ids = array();
					if ( count( $devices ) > 0 ) {
						foreach ( $devices as $device ) {
							$device_ids[] = $device->device_token;
						}
					}


					$user_id = $this->post('seller_user_id');
		       		$user_name = $this->User->get_one($user_id)->user_name;

			    	$data['message'] = "Offer Accepted!";
			    	$data['buyer_user_id'] = $this->post('buyer_user_id');
			    	$data['seller_user_id'] = $this->post('seller_user_id');
			    	$data['sender_name'] = $user_name;
			    	$data['item_id'] = $this->post('item_id');	

			    	$seller_unread_count = $chat_history_data->seller_unread_count;
			    	
			    	$chat_data = array(

			        	"item_id" => $this->post('item_id'), 
			        	"buyer_user_id" => $this->post('buyer_user_id'), 
			        	"seller_user_id" => $this->post('seller_user_id'),
			        	"seller_unread_count" => $seller_unread_count + 1,
			        	"added_date" => date("Y-m-d H:i:s"),
			        	"nego_price" => $this->post('nego_price'),
			        	"is_accept"	 => 1

			        );

			    	}
		    	}

		    	if( !$this->Chat->Save( $chat_data,$chat_history_data->id )) {

		    		$this->error_response( get_msg( 'err_accept_update' ));

		    	
		    	} else {

		    		//sending noti
		    		$status = send_android_fcm_chat( $device_ids, $data );

		    		$obj = $this->Chat->get_one_by($chat_data);
					$this->ps_adapter->convert_chathistory( $obj );
					$this->custom_response( $obj );

		    	}
		    }

	    }
	    die;	

	}

    /**
	 * Update Price 
	 */
	function item_sold_out_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        )
        );


		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;
        $item_id = $this->post('item_id');
        $buyer_user_id = $this->post('buyer_user_id');
        $seller_user_id = $this->post('seller_user_id');
        $item_sold_out = array(

        	"is_sold_out" => 1, 

        );

        $this->Item->save($item_sold_out,$item_id);
        $conds['item_id'] = $item_id;
        $conds['buyer_user_id'] = $buyer_user_id;
        $conds['seller_user_id'] = $seller_user_id;
        
        $obj = $this->Chat->get_one_by($conds);

        $this->ps_adapter->convert_chathistory( $obj );
        $this->custom_response($obj);
    }


    /**
	 * Reset is_accept 
	 */
	function reset_accept_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        )
        );


		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

        $chat_history_data = $this->Chat->get_one_by($chat_data);


        if($chat_history_data->id == "") {
	        	
	        $this->error_response( get_msg( 'err_chat_history_not_exist' ));


	    } else {
	    	
	    	$chat_data = array(

	        	"item_id" => $this->post('item_id'), 
	        	"buyer_user_id" => $this->post('buyer_user_id'), 
	        	"seller_user_id" => $this->post('seller_user_id'),
	        	"is_accept" => 0

	        );

	    	if( !$this->Chat->Save( $chat_data,$chat_history_data->id )) {

	    		$this->error_response( get_msg( 'err_accept_update' ));

	    	
	    	} else {

	    		$this->success_response( get_msg( 'accept_reset_success' ));


	    	}


	    }


	}

	/**
	 * Delete All Chat History
	 */
	function delete_chat_history_post()
	{
		
		// delete categories and images
		if ( !$this->Chat->delete_all()) {

			// set error message
			$this->error_response( get_msg( 'error_delete_chat_history' ));
			// rollback

			
		}
		
		$this->success_response( get_msg( 'success_delete_chat_history' ));
		
	}

	/**
	 * Reset Soldout
	 */

	function reset_sold_out_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        )
        );


		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

        $chat_history_data = $this->Chat->get_one_by($chat_data);


        if($chat_history_data->id == "") {
	        	
	        $this->error_response( get_msg( 'err_chat_history_not_exist' ));


	    } else {
	    	
	    	$chat_data = array(

	        	"item_id" => $this->post('item_id'), 
	        	"buyer_user_id" => $this->post('buyer_user_id'), 
	        	"seller_user_id" => $this->post('seller_user_id'),
	        	"is_accept" => 0

	        );

	    	if( !$this->Chat->Save( $chat_data,$chat_history_data->id )) {

	    		$this->error_response( get_msg( 'err_accept_update' ));

	    	
	    	} else {

	    		$item_data = array(
	    			"is_sold_out" => 0
	    		);

	    		if( !$this->Item->Save( $item_data, $this->post('item_id') )) {

		    		$this->error_response( get_msg( 'err_soldout_reset' ));

		    	} else {

		    		$this->success_response( get_msg( 'soldout_reset_success' ));

		    	}


	    	}


	    }

	}


	/**
	 * get chat history
	 */

	function get_chat_history_post()
	{
		// validation rules for chat history
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'buyer_user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'seller_user_id',
	        	'rules' => 'required'
	        )
        );


		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

        $obj = $this->Chat->get_one_by($chat_data);

        $this->ps_adapter->convert_chathistory( $obj );
		$this->custom_response( $obj );

    }
    
}