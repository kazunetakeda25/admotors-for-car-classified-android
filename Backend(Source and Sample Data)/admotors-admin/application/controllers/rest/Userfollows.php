<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for News
 */
class Userfollows extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		parent::__construct( 'Userfollow' );
	}

	/**
	 * Default Query for API
	 * @return [type] [description]
	 */
	function default_conds()
	{
		//print_r($this->post());die;
		$conds = array();

		if ( $this->is_get ) {
		// if is get record using GET method

		}

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

			if($this->post('user_id') != "") {
				$conds['user_id']   = $this->post('user_id');
			}

			if($this->post('id') != "") {
				$conds['id']   = $this->post('id');
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

		// convert customize item object
		$this->ps_adapter->convert_user_follow( $obj );

	}


    /**
	 * Search API
	 */
	function search_post()
	{	
		// add flag for default query
		$this->is_search = true;

		// add default conds
		$default_conds = $this->default_conds();
		$user_conds = $this->get();
		$conds = array_merge( $default_conds, $user_conds );
	
		//get follower and following
		if ( $conds['return_types'] == "follower" ) {
			$conds1['followed_user_id'] = $conds['user_id'];
		}

		if ( $conds['return_types'] == "following" ) {
			$conds1['user_id'] = $conds['user_id'];
		}
		//print_r($conds1);die;

		$userfollow_data = $this->Userfollow->get_all_by($conds1)->result();
		//print_r($userfollow_data);die;
		
		if ( count( $userfollow_data ) > 0 ) {
			if ($conds['return_types'] != "") {
				
				if ( $conds['return_types'] == "follower" ) {

					foreach ($userfollow_data as $userfollow) {

				  		$result .= "'".$userfollow->user_id ."'" .",";

				  		//print_r($result . "##");die;
				  
					}

				}

				if ( $conds['return_types'] == "following" ) {
					foreach ($userfollow_data as $userfollow) {

				  		$result .= "'".$userfollow->followed_user_id ."'" .",";
				  
					}
				}

				$limit = $this->get( 'limit' );
				$offset = $this->get( 'offset' );

				$follower_and_following_user = rtrim($result,",");

				$conds['follower_and_following_user'] = $follower_and_following_user;

				$obj = $this->User->get_all_follower_by_user($conds, $limit, $offset)->result();
				$followed_user_id = $conds['user_id'];
				
				$this->ps_adapter->convert_follow_user_list( $obj, $followed_user_id );
				$this->custom_response( $obj );
			} 

			else  {
				if ($conds['id'] != "") {

					$user_id = $conds['id'];
					$obj = $this->User->get_one($user_id);
					$obj->following_user_id = $conds['user_id'];
					$obj->followed_user_id = $user_id;
					
					$this->ps_adapter->convert_user( $obj );
					$this->custom_response( $obj );
				
					$limit = $this->get( 'limit' );
					$offset = $this->get( 'offset' );

					if ( !empty( $limit ) && !empty( $offset )) {
					// if limit & offset is not empty
						
						$data = $this->User->get_all_by( $conds, $limit, $offset )->result();
					} else if ( !empty( $limit )) {
					// if limit is not empty

						$data = $this->User->get_all_by( $conds, $limit )->result();
					} else {
					// if both are empty
						
						$data = $this->User->get_all_by( $conds )->result();
					}
					$this->ps_adapter->convert_follow_user_list( $data );

					}
					
					$this->custom_response( $data );

				} 

		} else if ($conds['id'] != "") {
			$user_id = $conds['id'];
			$obj = $this->User->get_one($user_id);
			$obj->following_user_id = $conds['user_id'];
			$obj->followed_user_id = $user_id;
			
			$this->ps_adapter->convert_user( $obj );
			$this->custom_response( $obj );
		
			$limit = $this->get( 'limit' );
			$offset = $this->get( 'offset' );

			if ( !empty( $limit ) && !empty( $offset )) {
			// if limit & offset is not empty
				
				$data = $this->User->get_all_by( $conds, $limit, $offset )->result();
			} else if ( !empty( $limit )) {
			// if limit is not empty

				$data = $this->User->get_all_by( $conds, $limit )->result();
			} else {
			// if both are empty
				
				$data = $this->User->get_all_by( $conds )->result();
			}
			$this->ps_adapter->convert_follow_user_list( $data );
			$this->custom_response( $data );

		} else {
			$this->error_response( get_msg( 'no_record' ) );

		}
	}

}