<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for Users
 */
class Users extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		parent::__construct( 'User' );
	}	

	/**
	 * Default Query for API
	 * @return [type] [description]
	 */
	function default_conds()
	{
		$conds = array();

		if ( $this->is_search ) {
			if($this->post('user_name') != "") {
				$conds['user_name']   = $this->post('user_name');
			}

			if($this->post('overall_rating') != "") {
				$conds['overall_rating']   = $this->post('overall_rating');
			}

			if($this->post('return_types') != "") {
				$conds['return_types']   = $this->post('return_types');
			}

			if($this->post('login_user_id') != "") {
				$conds['added_user_id']   = $this->post('login_user_id');
			}
		}

		return $conds;

	}

	/**
	 * Convert Object
	 */
	function convert_object( &$obj )
	{
		// call parent convert object
		parent::convert_object( $obj );

		// convert customize category object
		$this->ps_adapter->convert_user( $obj );
	}
	
	/**
	 * Users Registration
	 */
	function add_post()
	{
		// validation rules for user register
		$rules = array(
			array(
	        	'field' => 'user_name',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'user_email',
	        	'rules' => 'required|valid_email|callback_email_check'
	        ),
	        array(
	        	'field' => 'user_password',
	        	'rules' => 'required'
	        )

        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $code = generate_random_string(5);

        $user_data = array(
        	
        	"user_name" => $this->post('user_name'), 
        	"user_email" => $this->post('user_email'), 
        	'user_password' => md5($this->post('user_password')),
        	"device_token" => $this->post('device_token'),
        	"code" =>  $code,
        	"email_verify" => 1,
        	"status" => 2 //Need to verified status

        );

        $conds['user_email'] = $user_data['user_email'];
        $conds['status'] = 2;
       	$user_infos = $this->User->user_exists($conds)->result();

       	if (empty($user_infos)) {

       		if ( !$this->User->save($user_data)) {

        	$this->error_response( get_msg( 'err_user_register' ));

        	} else {

        	$noti_data = array(

					"user_id" => $user_data['user_id'],
					"device_token" => $user_data['device_token']
				);

	        	if ( $this->Noti->exists( $noti_data )) {
		        // if the noti data is already existed, return success
		        	$this->success_response( get_msg( 'token_already_exist '));
		        } else {
		        	$this->Noti->save( $noti_data, $push_noti_token_id );
		        }


	        	$subject = "New User Account Registration";
				

	        	if ( !send_user_register_email( $user_data['user_id'], $subject )) {

					$this->error_response( get_msg( 'user_register_success_but_email_not_send' ));
				
				} 
        	}

       	} else {

       		//$this->error_response( get_msg( 'need_to_verify' ));
       		$this->custom_response($this->User->get_one($user_infos[0]->user_id));

       	}

        
        $this->custom_response($this->User->get_one($user_data["user_id"]));

	}


	/**
	 * Email Checking
	 *
	 * @param      <type>  $email     The identifier
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function email_check( $email )
    {
        if ( $this->User->exists( array( 'user_email' => $email ))) {
        	
            $this->form_validation->set_message('email_check', 'Email Exist');
            return false;
        } 

        return true;
    }

    /**
	 * Users Login
	 */
	function login_post()
	{
		// validation rules for user register
		$rules = array(
			
	        array(
	        	'field' => 'user_email',
	        	'rules' => 'required|valid_email'
	        ),
	        array(
	        	'field' => 'user_password',
	        	'rules' => 'required'
	        ),
	         array(
	        	'field' => 'device_token',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;
        
        if ( $this->User->exists( array( 'user_email' => $this->post( 'user_email' ), 'user_password' => $this->post( 'user_password' ), 'device_token' => $this->post( 'device_token' )))) {

        //if ( $this->User->exists( array( 'user_email' => $this->post( 'user_email' ), 'user_password' => $this->post( 'user_password' )))) {

        	$email = $this->post( 'user_email' );
	        $conds['user_email'] = $email;
	        $is_banned = $this->User->get_one_by($conds)->is_banned;

	        if ( $is_banned == '1') {
	        	$this->error_response( get_msg( 'err_user_banned' ));
	        } else {
	        	$user = $this->User->get_one_by(array("user_email" => $this->post('user_email')));

        		$noti_data = array(

					"user_id" => $user->user_id,
					"device_token" => $this->post( 'device_token' )
				);
		        
        		if ( !$this->Noti->exists( $noti_data )) {
		        	$this->Noti->save( $noti_data, $push_noti_token_id );
		        } 

		        $this->custom_response($user);

	        }
        
            
        } else {

        	$this->error_response( get_msg( 'err_user_not_exist' ));

        }

	}

	/**
	* User Reset Password
	*/
	function reset_post()
	{
		// validation rules for user register
		$rules = array(
	        array(
	        	'field' => 'user_email',
	        	'rules' => 'required|valid_email'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $user_info = $this->User->get_one_by( array( "user_email" => $this->post( 'user_email' )));

        if ( isset( $user_info->is_empty_object )) {
        // if user info is empty,
        	
        	$this->error_response( get_msg( 'err_user_not_exist' ));
        }

        // generate code
        $code = md5(time().'teamps');

        // insert to reset
        $data = array(
			'user_id' => $user_info->user_id,
			'code' => $code
		);

		if ( !$this->ResetCode->save( $data )) {
		// if error in inserting,

			$this->error_response( get_msg( 'err_model' ));
		}

		// Send email with reset code
		$to = $user_info->user_email;
	    $subject = 'Password Reset';
		$msg = "<p>Hi,". $user_info->user_name ."</p>".
					"<p>Please click the following link to reset your password<br/>".
					"<a href='". site_url( $this->config->item( 'reset_url') .'/'. $code ) ."'>Reset Password</a></p>".
					"<p>Best Regards,<br/>". $this->config->item('sender_name') ."</p>";

		// send email from admin
		if ( ! $this->ps_mail->send_from_admin( $to, $subject, $msg ) ) {

			$this->error_response( get_msg( 'err_email_not_send' ));
		}
		
		$this->success_response( get_msg( 'success_email_sent' ));
	}

	/**
	* User Profile Update
	*/

	function profile_update_post()
	{

		// validation rules for user register
		$rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'user_name',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'user_email',
	        	'rules' => 'required|valid_email'
	        ),
	        array(
	        	'field' => 'user_phone',
	        	'rules' => 'required'
	        ),
	       
	        array(
	        	'field' => 'user_about_me',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        
        $user_data = array(
        	"user_name"     => $this->post('user_name'), 
        	"user_email"    => $this->post('user_email'), 
        	"user_phone"    => $this->post('user_phone'),
        	"user_address"  => $this->post('user_address'),
        	"city"			=> $this->post('city'),
        	"user_about_me" => $this->post('user_about_me'),
        	"device_token" => $this->post('device_token')
        );
        // print_r($user_data);die;

        if ( !$this->User->save($user_data, $this->post('user_id'))) {

        	$this->error_response( get_msg( 'err_user_update' ));
        }

        $this->success_response( get_msg( 'success_profile_update' ));

	}

	/**
	* User Profile Update
	*/
	function password_update_put()
	{

		// validation rules for user register
		$rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required|callback_id_check[User]'
	        ),
	        array(
	        	'field' => 'user_password',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $user_data = array(
        	"user_password"     => md5($this->put('user_password'))
        );

        if ( !$this->User->save($user_data, $this->put('user_id'))) {
        	$this->error_response( get_msg( 'err_user_password_update' )); 
        }

        $this->success_response( get_msg( 'success_profile_update' ));

	}


	/**
	* User Verified Code
	*/
	function verify_post()
	{

		// validation rules for user register
		$rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required|callback_id_check[User]'
	        ),
	        array(
	        	'field' => 'code',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $user_verify_data = array(
        	"code"     => $this->post('code'),
        	"user_id"  => $this->post('user_id')
        );


        $user_id = $this->User->get_one_by($user_verify_data)->user_id;

        if($user_id  == $this->post('user_id')) {
        	$user_data = array(
	        	"code"    => " ",
	        	"status"  => 1
        	);
        	$this->User->save($user_data,$user_id);
        	$this->custom_response($this->User->get_one($user_id));

        } else {

        	$this->error_response( get_msg( 'invalid_code' )); 

        }

        

	}

	/**
	 * Users Request Code
	 */
	function request_code_post()
	{
		// validation rules for user register
		$rules = array(
	        array(
	        	'field' => 'user_email',
	        	'rules' => 'required'
	        )

        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        	if (!$this->User->exists( array( 'user_email' => $this->post( 'user_email' )))) {

        		$this->error_response( get_msg( 'err_user_not_exist' ));

        	} else {
        		
        		$email = $this->post( 'user_email' );
		        $conds['user_email'] = $email;
		        
		        $user_id = $this->User->get_one_by($conds)->user_id;

		        $code = $this->User->get_one_by($conds)->code;

		        if($code == " " ) {

		        	$resend_code = generate_random_string(5);
		        	$user_data_code = array(
			        	"code"    => $resend_code
		        	);
		        	$this->User->save($user_data_code,$user_id);

		        } 

	        
		        $user_data['user_id'] = $user_id;

        		$subject = "Verification Code has been sent";

	        	if ( !send_user_register_email( $user_data['user_id'], $subject )) {

					$this->error_response( get_msg( 'user_register_success_but_email_not_send' ));
				
				}
					
				$this->success_response( get_msg( 'success_email_sent' ));

				
        	}

       
    }

    /**
	 * Users Registration with Facebook
	 */
	function register_post()
	{
		$rules = array(
			array(
	        	'field' => 'user_name',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'facebook_id',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        //Need to check facebook_id is aleady exist or not?
        if ( !$this->User->exists( array( 'facebook_id' => $this->post( 'facebook_id' ) ))) {

            //User not yet exist 
        	$fb_id = $this->post( 'facebook_id' ) ;
			$url = "https://graph.facebook.com/$fb_id/picture?width=350&height=500";
		  	$data = file_get_contents($url);
		  	
		  	
		  	$dir = "uploads/";
			$img = md5(time()).'.jpg';
		  	$ch = curl_init($url);
			$fp = fopen( 'uploads/'. $img, 'wb' );
			curl_setopt($ch, CURLOPT_FILE, $fp);
			curl_setopt($ch, CURLOPT_HEADER, 0);
			curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
			curl_exec($ch);
			curl_close($ch);
			fclose($fp);


			////

			$user_data = array(
	        	"user_name" 	=> $this->post('user_name'), 
	        	'user_email'    => $this->post('user_email'), 
	        	"facebook_id" 	=> $this->post('facebook_id'),
	        	"user_profile_photo" => $img,
	        	"device_token" => $this->post('device_token'),
	        	"facebook_verify" => 1,
	        	"status" 	=> 1, 
		        "code"    => ' ',
		        "user_password" => ' '
        	);


        	$user_email = $user_data['user_email'];
        	//print_r($user_email);die;

        	if (!empty($user_email)) {
        		//email exists
        		$conds_email['user_email'] = $user_email;
        		$user_infos = $this->User->get_one_user_email($conds_email)->result();
				$user_id = $user_infos[0]->user_id;
        		
        	} 
			
        	if ( $user_id != "") {
				//user email alerady exist

				$this->User->save($user_data,$user_id);

				$noti_data = array(

					"user_id" => $user_data['user_id'],
					"device_token" => $user_data['device_token']
				);
		        
        		if ( !$this->Noti->exists( $noti_data )) {
		        	$this->Noti->save( $noti_data, $push_noti_token_id );
		        }
				
			} else {
				//user email not exist

				if ( !$this->User->save($user_data)) {
        			$this->error_response( get_msg( 'err_user_register' ));
        		}

        		$noti_data = array(

					"user_id" => $user_data['user_id'],
					"device_token" => $user_data['device_token']
				);
		        
        		if ( !$this->Noti->exists( $noti_data )) {
		        	$this->Noti->save( $noti_data, $push_noti_token_id );
		        }

        		$this->custom_response($this->User->get_one($user_data['user_id']));

			}

        	$this->custom_response($this->User->get_one($user_infos[0]->user_id));

        } else {

        	//User already exist in DB
        	$conds['facebook_id'] = $this->post( 'facebook_id' );
        	$conds1['facebook_id'] = $this->post( 'facebook_id' );
        	$user_profile_photo = $this->User->get_one_by($conds['facebook_id'])->user_profile_photo;

        	//Delete existing image 
        	@unlink('./uploads/'.$user_profile_photo);
			
			//Download again
			$fb_id = $this->post( 'facebook_id' ) ;
			$url = "https://graph.facebook.com/$fb_id/picture?width=350&height=500";
		  	$data = file_get_contents($url);
		  	
		  	
		  	$dir = "uploads/";
			$img = md5(time()).'.jpg';
		  	$ch = curl_init($url);
			$fp = fopen( 'uploads/'. $img, 'wb' );
			curl_setopt($ch, CURLOPT_FILE, $fp);
			curl_setopt($ch, CURLOPT_HEADER, 0);
			curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
			curl_exec($ch);
			curl_close($ch);
			fclose($fp);

			$user_data = array(
				'user_name'    	=> $this->post('user_name'), 
				'user_email'    => $this->post('user_email'),
				'user_profile_photo' => $img,
				'device_token'  => $this->post('device_token')
			);

			$users_data = $this->User->get_one_by($conds1);
			$user_id = $users_data->user_id;

			$conds['facebook_id'] = $this->post( 'facebook_id' );
			$user_datas = $this->User->get_one_by($conds);
			$user_id = $user_datas->user_id;

			if ( $user_datas->is_banned == 1 ) {

				$this->error_response( get_msg( 'err_user_banned' ));
			} else {

				if ( !$this->User->save($user_data,$user_id)) {
        		$this->error_response( get_msg( 'err_user_register' ));
        		}

			}

        	$this->custom_response($this->User->get_one($user_datas->user_id));

        }

	}

	/**
	* User Profile Update
	*/
	function password_update_post()
	{

		// validation rules for user register
		$rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required|callback_id_check[User]'
	        ),
	        array(
	        	'field' => 'user_password',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $user_data = array(
        	"user_password"     => md5($this->post('user_password'))
        );

        if ( !$this->User->save($user_data, $this->post('user_id'))) {
        	$this->error_response( get_msg( 'err_user_password_update' )); 
        }

        $this->success_response( get_msg( 'success_profile_update' ));

	}

	/**
	* Trigger to delete user related data when user is deleted
	* delete user related data
	*/

	function user_delete_post( ) {

		// validation rules for user register
		$rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required'
	        )
	    );   
	    
	    // exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $id = $this->post('user_id');

        $conds['user_id'] = $id;

        // check user id

        $user_data = $this->User->get_one_by($conds);

        //print_r($user_data);die;


        if ( $user_data->user_id == "" ) {

        	$this->error_response( get_msg( 'invalid_user_id' ));

        } else {

        	$conds_user['user_id'] = $id;
			$conds_from_user['from_user_id'] = $id;
			$conds_to_user['to_user_id'] = $id;
			$conds_added_user['added_user_id'] = $id;
			$conds_followed_user['followed_user_id'] = $id;
			$conds_buyer_user['buyer_user_id'] = $id;
			$conds_seller_user['seller_user_id'] = $id;

			//delete User
			if ( !$this->User->delete_by( $conds_user )) {

				return false;
			}

			// delete Rating
			$this->Rate->delete_by( $conds_from_user );
			$this->Rate->delete_by( $conds_to_user );

			// delete push notification users
			if ( !$this->Notireaduser->delete_by( $conds_user )) {

				return false;
			}

			// delete push notification tokens
			if ( !$this->Noti->delete_by( $conds_user )) {

				return false;
			}

			// delete items and others related table

			$items_data 	= $this->Item->get_one_by($conds_added_user);

			$item_data['item_id'] = $items_data->id;
	        $img_data['img_parent_id'] = $items_data->id;

	        $this->Chat->delete_by( $item_data );
	        $this->Favourite->delete_by( $item_data );
	        $this->Itemreport->delete_by( $item_data );
	        $this->Touch->delete_by( $item_data );
	        $this->Image->delete_by( $img_data );

			if ( !$this->Item->delete_by( $conds_added_user )) {

				return false;
			}

			//delete follows

			$following_user		= $this->Userfollow->get_all_by( $conds_user )->result();
			$follower_user		= $this->Userfollow->get_all_by( $conds_followed_user )->result();

			foreach ($following_user as $following) {

				$conds_follower['user_id'] = $following->followed_user_id;

				$follower_user_data = $this->User->get_one_by( $conds_follower );
				
				$follower_count = $follower_user_data->follower_count;

				$user_data = array(
				 	"follower_count" => $follower_count - 1
				 );

				 $this->User->save($user_data,$follower_user_data->user_id);
				

			}


			foreach ($follower_user as $follower) {

				$conds_follow['user_id'] = $follower->user_id;

				$following_user_data = $this->User->get_one_by( $conds_follow );

				$following_count = $following_user_data->following_count;

				$user_data = array(
				 	"following_count" => $following_count - 1
				 );

				 $this->User->save($user_data,$following_user_data->user_id);

			}
			
			$this->Userfollow->delete_by( $conds_user );
			$this->Userfollow->delete_by( $conds_followed_user );
			

			// delete Favourite
			if ( !$this->Favourite->delete_by( $conds_user )) {

				return false;
			}

			// delete Chat History

			$this->Chat->delete_by( $conds_buyer_user );
			$this->Chat->delete_by( $conds_seller_user ); 
			
			$this->success_response( get_msg( 'success_delete' ));

        }


	}


	/**
	* Return User Unread Count For Chat Notification and Blog
	*/

	function unread_count_post( ) {

		$rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'device_token',
	        	'rules' => 'required'
	        )
        );

        // exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        $blog_noti_unread_count = 0;

        //get all noti_id by user_id and device_token from bs_push_notification_users table

        $conds['user_id'] 	   = $this->post('user_id');
        $conds['device_token'] = $this->post('device_token');

        $blog_notis = $this->Notireaduser->get_all_by($conds)->result();

        

        foreach ( $blog_notis as $blog_noti ) {

			$id .= "'" .$blog_noti->noti_id . "',";
		
		}

		

		if ($id == "") {

		 	$obj = $this->Noti_message->get_all()->result();
		 	$blog_noti_unread_count = count($obj);

		} else {

			$result = rtrim($id,',');
			
			$conds_blog_noti['$id'] = $result;

			

			$obj = $this->Noti_message->get_all_not_in_noti($conds_blog_noti)->result();
			


			$blog_noti_unread_count = count($obj);
		}


		//For buyer_unread_count

        $buyer_unread_count = 0;

        $conds_chat['buyer_user_id'] = $this->post('user_id');

        $chats_buyer_unread_records = $this->Chat->get_all_by($conds_chat)->result();


        foreach ( $chats_buyer_unread_records as $chats_buyer_unread_record ) {

			$buyer_unread_count += $chats_buyer_unread_record->buyer_unread_count;
		
		}

        //For seller_unread_count

        $seller_unread_count = 0;

        $conds_chat_seller['seller_user_id'] = $this->post('user_id');

       
        $chats_seller_unread_records = $this->Chat->get_all_by($conds_chat_seller)->result();

        foreach ( $chats_seller_unread_records as $chats_seller_unread_record ) {

			$seller_unread_count += $chats_seller_unread_record->seller_unread_count;


		
		}


		$count_object = new stdClass; 
		$count_object->blog_noti_unread_count  = $blog_noti_unread_count; 
		$count_object->buyer_unread_count      = $buyer_unread_count; 
		$count_object->seller_unread_count     = $seller_unread_count; 
		

		$final_data = $this->ps_security->clean_output( $count_object );


		$this->response( $final_data );


	}

	/**
	 * Users Registration with Google
	*/
	function google_register_post()
	{
		$rules = array(
			array(
	        	'field' => 'user_name',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'google_id',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        //Need to check google_id is aleady exist or not?
        if ( !$this->User->exists( 
        	array( 
        		'google_id' => $this->post( 'google_id' ) 
        		))) {
        
            //User not yet exist 
        	$gg_id = $this->post( 'google_id' ) ;
			$url = $this->post('profile_photo_url');

			if ($url !="") {

				$data = file_get_contents($url);
			  	$dir = "uploads/";
				$img = md5(time()).'.jpg';
			  	$ch = curl_init($url);
				$fp = fopen( 'uploads/'. $img, 'wb' );
				curl_setopt($ch, CURLOPT_FILE, $fp);
				curl_setopt($ch, CURLOPT_HEADER, 0);
				curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
				curl_exec($ch);
				curl_close($ch);
				fclose($fp);

				$user_data = array(
		        	"user_name" 	=> $this->post('user_name'), 
		        	'user_email'    => $this->post('user_email'), 
		        	"google_id" 	=> $this->post('google_id'),
		        	"user_profile_photo" => $img,
		        	"device_token" => $this->post('device_token'),
		        	"google_verify" => 1,
		        	"status" 	=> 1, 
			        "code"   => ' ',
			        "user_password" => ' '
	        	);

			} else{

					$user_data = array(
		        	"user_name" 	=> $this->post('user_name'), 
		        	'user_email'    => $this->post('user_email'), 
		        	"google_id" 	=> $this->post('google_id'),
		        	"device_token" => $this->post('device_token'),
		        	"google_verify" => 1,
		        	"status" 	=> 1, 
			        "code"   => ' ',
			        "user_password" => ' '
        		);
			}

        	$conds_email['user_email'] = $user_data['user_email'];
			$user_infos = $this->User->get_one_user_email($conds_email)->result();
			$user_id = $user_infos[0]->user_id;

			if ( $user_id != "") {
				//user email alerady exist

				$this->User->save($user_data,$user_id);

				$noti_data = array(

					"user_id" => $user_data['user_id'],
					"device_token" => $user_data['device_token']
				);

        		if ( !$this->Noti->exists( $noti_data )) {
		        	$this->Noti->save( $noti_data, $push_noti_token_id );
		        } 
				
			} else {
				//user email not exist

				if ( !$this->User->save($user_data)) {
        		$this->error_response( get_msg( 'err_user_register' ));
        		}

        		$noti_data = array(

					"user_id" => $user_data['user_id'],
					"device_token" => $user_data['device_token']
				);

        		if ( !$this->Noti->exists( $noti_data )) {
		        	$this->Noti->save( $noti_data, $push_noti_token_id );
		        } 

        		$this->custom_response($this->User->get_one($user_data['user_id']));

			}

        	$this->custom_response($this->User->get_one($user_infos[0]->user_id));

        } else {

        	//User already exist in DB
        	$conds['google_id'] = $this->post( 'google_id' );
        	$user_profile_photo = $this->User->get_one_by($conds['google_id'])->user_profile_photo;

        	//Delete existing image 
        	@unlink('./uploads/'.$user_profile_photo);
			
			//Download again
			$gg_id = $this->post( 'google_id' ) ;
			$url = $this->post('profile_photo_url');
		  	
			if($url != "") {
			  	$data = file_get_contents($url);
			  	
			  	
			  	$dir = "uploads/";
				$img = md5(time()).'.jpg';
			  	$ch = curl_init($url);
				$fp = fopen( 'uploads/'. $img, 'wb' );
				curl_setopt($ch, CURLOPT_FILE, $fp);
				curl_setopt($ch, CURLOPT_HEADER, 0);
				curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
				curl_exec($ch);
				curl_close($ch);
				fclose($fp);

				$user_data = array(
					'user_name'    	=> $this->post('user_name'), 
					'user_email'    => $this->post('user_email'), 
					'user_profile_photo' => $img,	
				);
			} else {

				$user_data = array(
					'user_name'    	=> $this->post('user_name'), 
					'user_email'    => $this->post('user_email')
				);
			}
			$conds['google_id'] = $this->post( 'google_id' );
			$user_datas = $this->User->get_one_by($conds);
			$user_id = $user_datas->user_id;

			if ( $user_datas->is_banned == 1 ) {

				$this->error_response( get_msg( 'err_user_banned' ));
			} else {

				if ( !$this->User->save($user_data,$user_id)) {
	        		$this->error_response( get_msg( 'err_user_register' ));
	        	}

			}

        	$this->custom_response($this->User->get_one($user_datas->user_id));

        }


	}

	/**
	 * Users Registration with Phone
	*/
	function phone_register_post()
	{
		$rules = array(
			array(
	        	'field' => 'user_name',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'phone_id',
	        	'rules' => 'required'
	        )
        );

		// exit if there is an error in validation,
        if ( !$this->is_valid( $rules )) exit;

        //Need to check phone_id is aleady exist or not?
        if ( !$this->User->exists( 
        	//new
        	array( 
        		'phone_id' => $this->post( 'phone_id' ) 
        		))) {
        
           

			$user_data = array(
	        	"user_name" 	=> $this->post('user_name'), 
	        	'user_phone'    => $this->post('user_phone'), 
	        	"phone_id" 	   => $this->post('phone_id'),
	        	"device_token" => $this->post('device_token'),
	        	"phone_verify" => 1
        	);

        	if ( !$this->User->save($user_data)) {
        		$this->error_response( get_msg( 'err_user_register' ));
        	}


        	$noti_data = array(

					"user_id" => $user_data['user_id'],
					"device_token" => $user_data['device_token']
				);
		        
        		if ( !$this->Noti->exists( $noti_data )) {
		        	$this->Noti->save( $noti_data, $push_noti_token_id );
		        } 

        	$this->custom_response($this->User->get_one($user_data["user_id"]));

        } else {
        	//update
        	//User already exist in DB
			$user_data = array(
				'user_name'    	=> $this->post('user_name'), 
				'user_phone'    => $this->post('user_phone')
			);

			$conds['phone_id'] = $this->post( 'phone_id' );
			$user_datas = $this->User->get_one_by($conds);
			$user_id = $user_datas->user_id;

			if ( $user_datas->is_banned == 1 ) {

				$this->error_response( get_msg( 'err_user_banned' ));
			} else {

				if ( !$this->User->save($user_data,$user_id)) {
	        		$this->error_response( get_msg( 'err_user_register' ));
	        	}

			}

        	$this->custom_response($this->User->get_one($user_datas->user_id));

        }

	}
	

}