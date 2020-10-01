<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for News
 */
class Images extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		parent::__construct( 'Image' );
		$this->load->library( 'PS_Image' );
	}

	function upload_post()
	{
		
		$platform_name = $this->post('platform_name');
		if ( !$platform_name ) {
			$this->custom_response( get_msg('Required Platform') ) ;
		}
		
		$user_id = $this->post('user_id');

		if($platform_name == "ios") {
			
			
			if ( !$user_id ) {
				$this->custom_response( get_msg('Required User ID') );
			}
			
			$uploaddir = 'uploads/';
			
			$path_parts = pathinfo( $_FILES['pic']['name'] );
			$filename = $path_parts['filename'] . date( 'YmdHis' ) .'.'. $path_parts['extension'];
			
			if (move_uploaded_file($_FILES['pic']['tmp_name'], $uploaddir . $filename)) {
			   $user_data = array( 'user_profile_photo' => $filename );
				   if ( $this->User->save( $user_data, $user_id ) ) {
					   	
					   	$user = $this->User->get_one( $user_id );

					   	$this->ps_adapter->convert_user( $user );
					   	
					   	$this->custom_response( $user );
				   } else {
					   	$this->error_response( get_msg('file_na') );
				   }
			   
			} else {
			   $this->error_response( get_msg('file_na') );
				
			}
			
		} else {
			
			$uploaddir = 'uploads/';
			
			$path_parts = pathinfo( $_FILES['file']['name'] );
			$filename = $path_parts['filename'] . date( 'YmdHis' ) .'.'. $path_parts['extension'];
			
			if (move_uploaded_file($_FILES['file']['tmp_name'], $uploaddir . $filename)) {
			   $user_data = array( 'user_profile_photo' => $filename );
				   if ( $this->User->save( $user_data, $user_id ) ) {

					   	$user = $this->User->get_one( $user_id );

					   	$this->ps_adapter->convert_user( $user );
					   	
					   	$this->custom_response( $user );

				   } else {
					   	$this->error_response( get_msg('file_na') );
				   }
			   
			} else {
			   $this->error_response( get_msg('file_na') );
				
			}
		}
		
	}

	function upload_item_post()
	{
		
		$item_id = $this->post('item_id');
		$files = $this->post('file');
		$img_id = $this->post('img_id');

			if ($img_id == "") {
				
				// upload images
				$upload_data = $this->ps_image->upload( $_FILES );
				foreach ( $upload_data as $upload ) {
				   	$item_img_data = array( 
					   	'img_parent_id'=> $item_id,
						'img_path' => $upload['file_name'],
						'img_type' => "item",
						'img_width'=> $upload['image_width'],
						'img_height'=> $upload['image_height']
				   	);
				}
			   if ( $this->Image->save( $item_img_data, $img_id ) ) {
			   		$conds['img_path'] = $item_img_data['img_path'];
			   		$img_id = $this->Image->get_one_by($conds)->img_id;
				   	$image = $this->Image->get_one( $img_id );

				   	$this->ps_adapter->convert_image( $image );
				   	
				   	$this->custom_response( $image );
			   } else {
				   	$this->error_response( get_msg('file_name') );
			   }
				
				   
			} else {
				
				// upload images
				$upload_data = $this->ps_image->upload( $_FILES );

				foreach ( $upload_data as $upload ) {
				   	$item_img_data = array( 
				   		'img_id' => $img_id,
					   	'img_parent_id'=> $item_id,
						'img_path' => $upload['file_name'],
						'img_width'=> $upload['image_width'],
						'img_height'=> $upload['image_height']
				   	);
				}
			   	if ( $this->Image->save( $item_img_data, $img_id ) ) {
				   	
				   	$image = $this->Image->get_one( $img_id );

				   	$this->ps_adapter->convert_image( $image );
				   	
				   	$this->custom_response( $image );
			   	} else {
				   	$this->error_response( get_msg('file_na') );
			   	}
			
			}
	}

	/** Chat image upload api */

	function chat_image_upload_post()
	{
		
		$sender_id = $this->post('sender_id');
		$type = $this->post('type');
		 $chat_data = array(

        	"item_id" => $this->post('item_id'), 
        	"buyer_user_id" => $this->post('buyer_user_id'), 
        	"seller_user_id" => $this->post('seller_user_id')

        );

		$chat_history_data = $this->Chat->get_one_by($chat_data);
		//////
		if($chat_history_data->id == "") {
			if ( $type == "to_buyer" ) {

				//prepare data for noti
		    	$user_ids[] = $this->post('buyer_user_id');
		    	$devices = $this->Noti->get_all_device_in($user_ids)->result();

		    	$device_ids = array();
		    	if ( count( $devices ) > 0 ) {
					foreach ( $devices as $device ) {
						$device_ids[] = $device->device_token;
					}
				}

				$user_id = $this->post('buyer_user_id');
	       		$user_name = $this->User->get_one($user_id)->user_name;

	       		$data['message'] = "Image!";
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

		    	$data['message'] = "Image!";
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

		        );

			}

			//sending noti
	    	$status = send_android_fcm_chat( $device_ids, $data );

	    	if ( !$sender_id ) {
				$this->custom_response( get_msg('Required Sender Id') ) ;
			}
			
			//$sender_id = $this->post('sender_id');

			
				
			$uploaddir = 'uploads/';
			
			$path_parts = pathinfo( $_FILES['file']['name'] );
			$filename = $path_parts['filename'] . date( 'YmdHis' ) .'.'. $path_parts['extension'];
			
			//print_r($filename); die;

			

			if (move_uploaded_file($_FILES['file']['tmp_name'], $uploaddir . $filename)) {
			   	
				$data = getimagesize($uploaddir . $filename);
				$width = $data[0];
				$height = $data[1];

			   	$img_data = array( 
			   		
			   		'img_parent_id' => $sender_id, 
			   		'img_type'      => "chat",
			   		'img_path'      => $filename,
			   		'img_width'     => $width,
			   		'img_height'    => $height 

			   	);

			   //	print_r($img_data); die;

			   if ( $this->Image->save( $img_data ) ) {

			   		//print_r($img_data['img_id']);

				   	$image = $this->Image->get_one( $img_data['img_id'] );

				   	//$this->ps_adapter->convert_image( $image );
				   	
				   	$this->custom_response( $image );

			   } else {
				   	$this->error_response( get_msg('file_na') );
			   }
			   
			} else {
			   $this->error_response( get_msg('file_na') );
				
			}
			


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

		    	$data['message'] = "Image!";
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

		    	$data['message'] = "Image!";
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

		        );

			}

			if( !$this->Chat->Save( $chat_data,$chat_history_data->id )) {

	    		$this->error_response( get_msg( 'err_accept_update' ));

	    	
	    	} else {

	    		//sending noti
	    		$status = send_android_fcm_chat( $device_ids, $data );

	    		if ( !$sender_id ) {
					$this->custom_response( get_msg('Required Sender Id') ) ;
				}
				
				//$sender_id = $this->post('sender_id');

				
					
				$uploaddir = 'uploads/';
				
				$path_parts = pathinfo( $_FILES['file']['name'] );
				$filename = $path_parts['filename'] . date( 'YmdHis' ) .'.'. $path_parts['extension'];
				
				//print_r($filename); die;

				

				if (move_uploaded_file($_FILES['file']['tmp_name'], $uploaddir . $filename)) {
				   	
					$data = getimagesize($uploaddir . $filename);
					$width = $data[0];
					$height = $data[1];

				   	$img_data = array( 
				   		
				   		'img_parent_id' => $sender_id, 
				   		'img_type'      => "chat",
				   		'img_path'      => $filename,
				   		'img_width'     => $width,
				   		'img_height'    => $height 

				   	);

				   //	print_r($img_data); die;

				   if ( $this->Image->save( $img_data ) ) {

				   		//print_r($img_data['img_id']);

					   	$image = $this->Image->get_one( $img_data['img_id'] );

					   	//$this->ps_adapter->convert_image( $image );
					   	
					   	$this->custom_response( $image );

				   } else {
					   	$this->error_response( get_msg('file_na') );
				   }
				   
				} else {
				   $this->error_response( get_msg('file_na') );
					
				}
		

	    	}


		}

		
	}
	
	/**
	 * Convert Object
	 */
	function convert_object( &$obj )
	{
		// call parent convert object
		parent::convert_object( $obj );

		// convert customize category object
		$this->ps_adapter->convert_image( $obj );
	}
}