<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Main Controller for API classes
 */
class API_Controller extends REST_Controller
{
	// model to access database
	protected $model;

	// validation rule for new record
	protected $create_validation_rules;

	// validation rule for update record
	protected $update_validation_rules;

	// validation rule for delete record
	protected $delete_validation_rules;

	// is adding record?
	protected $is_add;

	// is updating record?
	protected $is_update;

	// is deleting record?
	protected $is_delete;

	// is get record using GET method?
	protected $is_get;

	// is search record using GET method?
	protected $is_search;

	// login user id API parameter key name
	protected $login_user_key;

	// login user id
	protected $login_user_id;

	// if API allowed zero login user id,
	protected $is_login_user_nullable;

	// default value to ignore user id
	protected $ignore_user_id;

	/**
	 * construct the parent 
	 */
	function __construct( $model, $is_login_user_nullable = false )
	{
		// header('Access-Control-Allow-Origin: *');
    	// header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");

		parent::__construct();

		// set the model object
		$this->model = $this->{$model};

		// load security library
		$this->load->library( 'PS_Security' );

		// load the adapter library
		$this->load->library( 'PS_Adapter' );
		
		// set the login user nullable
		$this->is_login_user_nullable = $is_login_user_nullable;

		// login user id key
		$this->login_user_key = "login_user_id";

		// default value to ignore user id for API
		$this->ignore_user_id = "nologinuser";

		if ( $this->is_logged_in()) {
		// if login user id is existed, pass the id to the adapter

			$this->login_user_id = $this->get_login_user_id();

			if ( !$this->User->is_exist( $this->login_user_id ) && !$this->is_login_user_nullable ) {
			// if login user id not existed in system,

				$this->error_response( get_msg( 'invalid_login_user_id' ));
			}

			$this->ps_adapter->set_login_user_id( $this->login_user_id );
		}

		// load the mail library
		$this->load->library( 'PS_Mail' );

		if ( ! $this->is_valid_api_key()) {
		// if invalid api key

			$this->response( array(
				'status' => 'error',
				'message' => get_msg( 'invalid_api_key' )
			), 404 );
		}

		// default validation rules
		$this->default_validation_rules();
	}

	/**
	 * Determines if logged in.
	 *
	 * @return     boolean  True if logged in, False otherwise.
	 */
	function is_logged_in()
	{
		// it is login user if the GET login_user_id is not null and default key
		// it is login user if the POST login_user_id is not null
		// it is login user if the PUT login_user_id is not null
		return ( $this->get( $this->login_user_key ) != null && $this->get( $this->login_user_key ) != $this->ignore_user_id ) ||
			( $this->post( $this->login_user_key ) != null ) ||
			( $this->put( $this->login_user_key ) != null ) ;
	}

	/**
	 * Gets the login user identifier.
	 */
	function get_login_user_id()
	{
		/**
		 * GET['login_user_id'] will create POST['user_id']
		 * POST['login_user_id'] will create POST['user_id'] and remove POST['login_user_id']
		 * PUT['login_user_id'] will create PUT['user_id'] and remove PUT['login_user_id']
		 */
		// if exist in get variable,
		if ( $this->get( $this->login_user_key ) != null) {

			// get user id
			$login_user_id = $this->get( $this->login_user_key );

			// replace user_id
			$this->_post_args['user_id'] = $this->get( $this->login_user_key );
			
			return $this->get( $this->login_user_key );
		}

		// if exist in post variable,
		if ( $this->post( $this->login_user_key ) != null) {

			// get user id
			$login_user_id = $this->post( $this->login_user_key );

			// replace user_id
			$this->_post_args['user_id'] = $this->post( $this->login_user_key );
			unset( $this->_post_args[ $this->login_user_key ] );
			
			return $login_user_id;
		}

		// if exist in put variable,
		if ( $this->put( $this->login_user_key ) != null) {

			// get user id
			$login_user_id = $this->put( $this->login_user_key );

			// replace user_id
			$this->_put_args['user_id'] = $this->put( $this->login_user_key );
			unset( $this->_put_args[ $this->login_user_key ] );
			
			return $login_user_id;
		}
	}

	/**
	 * Convert logged in user id to user_id
	 */
	function get_similar_key( $actual, $similar )
	{
		if ( empty( parent::post( $actual )) && empty( parent::put( $actual ))) {
		// if actual key is not existed in POST and PUT, return similar

			return $similar;
		}

		// else, just return normal key
		return $actual;
	}

	/**
	 * Override Get variables
	 *
	 * @param      <type>  $key    The key
	 */
	function get( $key = NULL, $xss_clean = true )
	{
		return $this->ps_security->clean_input( parent::get( $key, $xss_clean ));
	}

	/**
	 * Override Post variables
	 *
	 * @param      <type>  $key    The key
	 */
	function post( $key = NULL, $xss_clean = true )
	{
		if ( $key == 'user_id' ) {
		// if key is user_id and user_id is not in variable, get the similar key

			$key = $this->get_similar_key( 'user_id', $this->login_user_key );
		}

		return $this->ps_security->clean_input( parent::post( $key, $xss_clean ));
	}

	/**
	 * Override Put variables
	 *
	 * @param      <type>  $key    The key
	 */
	function put( $key = NULL, $xss_clean = true )
	{
		if ( $key == 'user_id' ) {
		// if key is user_id and user_id is not in variable, get the similar key
			
			$key = $this->get_similar_key( 'user_id', $this->login_user_key );
		}

		return $this->ps_security->clean_input( parent::put( $key, $xss_clean ));
	}

	/**
	 * Determines if valid api key.
	 *
	 * @return     boolean  True if valid api key, False otherwise.
	 */
	function is_valid_api_key()
	{	
		$client_api_key = $this->get( 'api_key' );
		
		if ( $client_api_key == NULL ) {
		// if API key is null, return false;

			return false;
		}
		$conds['key'] = $client_api_key;

		$api_key = $this->Api_key->get_all_by( $conds)->result();
		$server_api_key = $api_key[0]->key;

		if ( $client_api_key != $server_api_key ) {
		// if API key is different with server api key, return false;

			return false;
		}

		return true;
	}

	/**
	 * Convert Object
	 */
	function convert_object( &$obj ) 
	{
		// convert added_date date string
		if ( isset( $obj->added_date )) {
			
			// added_date timestamp string
			$obj->added_date_str = ago( $obj->added_date );
		}
	}

	/**
	 * Gets the default photo.
	 *
	 * @param      <type>  $id     The identifier
	 * @param      <type>  $type   The type
	 */
	function get_default_photo( $id, $type )
	{
		$default_photo = "";

		// get all images
		$img = $this->Image->get_all_by( array( 'img_parent_id' => $id, 'img_type' => $type ))->result();

		if ( count( $img ) > 0 ) {
		// if there are images for news,
			
			$default_photo = $img[0];
		} else {
		// if no image, return empty object

			$default_photo = $this->Image->get_empty_object();
		}

		return $default_photo;
	}

	/**
	 * Response Error
	 *
	 * @param      <type>  $msg    The message
	 */
	function error_response( $msg )
	{
		$this->response( array(
			'status' => 'error',
			'message' => $msg
		), 404 );
	}

	/**
	 * Response Success
	 *
	 * @param      <type>  $msg    The message
	 */
	function success_response( $msg )
	{
		$this->response( array(
			'status' => 'success',
			'message' => $msg
		));
	}

	/**
	 * Custome Response return 404 if not data found
	 *
	 * @param      <type>  $data   The data
	 */
	function custom_response( $data, $require_convert = true )
	{
		if ( empty( $data )) {
		// if there is no data, return error

			$this->error_response( get_msg( 'no_record' ) );

		} else if ( $require_convert ) {
		// if there is data, return the list

			if ( is_array( $data )) {
			// if the data is array

				foreach ( $data as $obj ) {

					// convert object for each obj
					$this->convert_object( $obj );
				}
			} else {

				$this->convert_object( $data );
			}
		}

		$data = $this->ps_security->clean_output( $data );

		$this->response( $data );
	}

	/**
	 * Default Validation Rules
	 */
	function default_validation_rules()
	{
		// default rules
		$rules = array(
			array(
				'field' => $this->model->primary_key,
				'rules' => 'required|callback_id_check'
			)
		);

		// set to update validation rules
		$this->update_validation_rules = $rules;

		// set to delete_validation_rules
		$this->delete_validation_rules = $rules;
	}

	/**
	 * Id Checking
	 *
	 * @param      <type>  $id     The identifier
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function id_check( $id, $model_name = false )
    {
    	$tmp_model = $this->model;

    	if ( $model_name != false) {
    		$tmp_model = $this->{$model_name};
    	}

        if ( !$tmp_model->is_exist( $id )) {
        
            $this->form_validation->set_message('id_check', 'Invalid {field}');
            return false;
        }

        return true;
    }

	/**
	 * { function_description }
	 *
	 * @param      <type>   $conds  The conds
	 *
	 * @return     boolean  ( description_of_the_return_value )
	 */
	function is_valid( $rules )
	{
		if ( empty( $rules )) {
		// if rules is empty, no checking is required
			
			return true;
		}

		// GET data
		$user_data = array_merge( $this->get(), $this->post(), $this->put() );

		$this->form_validation->set_data( $user_data );
		$this->form_validation->set_error_delimiters('', '');
		$this->form_validation->set_rules( $rules );

		if ( $this->form_validation->run() == FALSE ) {
		// if there is an error in validating,

			$errors = $this->form_validation->error_array();

			if ( count( $errors ) == 1 ) {
			// if error count is 1, remove '\n'

				$this->error_response( trim(validation_errors()) );
			}

			$this->error_response( validation_errors());
		}

		return true;
	}

	/**
	 * Returns default condition like default order by
	 * @return array custom_condition_array
	 */
	function default_conds()
	{
		return array();
	}

	/**
	 * Get all or Get One
	 */
	function get_get()
	{
		// add flag for default query
		$this->is_get = true;

		// get id
		$id = $this->get( 'id' );

		if ( $id ) {
			
			// if 'id' is existed, get one record only
			$data = $this->model->get_one( $id );

			if ( isset( $data->is_empty_object )) {
			// if the id is not existed in the return object, the object is empty
				
				$data = array();
			}

			$this->custom_response( $data );
		}

		// get limit & offset
		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );

		// get search criteria
		$default_conds = $this->default_conds();
		$user_conds = $this->get();
		$conds = array_merge( $default_conds, $user_conds );

		if ( $limit ) {
			unset( $conds['limit']);
		}

		if ( $offset ) {
			unset( $conds['offset']);
		}

		if ( count( $conds ) == 0 ) {
		// if 'id' is not existed, get all	
		
			if ( !empty( $limit ) && !empty( $offset )) {
			// if limit & offset is not empty
				
				$data = $this->model->get_all( $limit, $offset )->result();
			} else if ( !empty( $limit )) {
			// if limit is not empty
				
				$data = $this->model->get_all( $limit )->result();
			} else {
			// if both are empty

				$data = $this->model->get_all()->result();
			}

			$this->custom_response( $data );
		} else {

			if ( !empty( $limit ) && !empty( $offset )) {
			// if limit & offset is not empty

				$data = $this->model->get_all_by( $conds, $limit, $offset )->result();
			} else if ( !empty( $limit )) {
			// if limit is not empty

				$data = $this->model->get_all_by( $conds, $limit )->result();
			} else {
			// if both are empty

				$data = $this->model->get_all_by( $conds )->result();
			}

			$this->custom_response( $data );
		}
	}

	/**
	 * Get all or Get One
	 */
	function get_favourite_get()
	{
		
		// add flag for default query
		$this->is_get = true;

		// get limit & offset
		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );

		// get search criteria
		$default_conds = $this->default_conds();
		$user_conds = $this->get();
		$conds = array_merge( $default_conds, $user_conds );
		$conds['user_id'] = $this->get_login_user_id();

		if ( $limit ) {
			unset( $conds['limit']);
		}

		if ( $offset ) {
			unset( $conds['offset']);
		}
		
		if ( !empty( $limit ) && !empty( $offset )) {
		// if limit & offset is not empty
			$data = $this->model->get_item_favourite( $conds, $limit, $offset )->result();
		} else if ( !empty( $limit )) {
		// if limit is not empty

			$data = $this->model->get_item_favourite( $conds, $limit )->result();
		} else {
		// if both are empty
			$data = $this->model->get_item_favourite( $conds )->result();
		}

		$this->custom_response( $data );
	}

	/**
	 * Get all or Get One
	 */
	function get_user_follow_get()
	{
		// add flag for default query
		$this->is_get = true;

		// get limit & offset
		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );

		// get search criteria
		$default_conds = $this->default_conds();
		$user_conds = $this->get();
		$conds = array_merge( $default_conds, $user_conds );
		$conds['user_id'] = $this->get( 'login_user_id' );

		$userfollow_data = $this->Userfollow->get_all_by($conds)->result();
		//print_r(count( $userfollow_data ));die;

		if ( count( $userfollow_data ) > 0 ) {
			foreach ($userfollow_data as $userfollow) {
		  	$result .= "'".$userfollow->followed_user_id ."'" .",";
		  
		}

		if ( !empty( $limit ) && !empty( $offset )) {
		// if limit & offset is not empty
			//$data = $this->model->get_wallpaper_delete_by_userid( $conds, $limit, $offset )->result();
			$data = $this->model->get_all_by( $conds, $limit, $offset )->result();
		} else if ( !empty( $limit )) {
		// if limit is not empty

			//$data = $this->model->get_wallpaper_delete_by_userid( $conds, $limit )->result();
			$data = $this->model->get_all_by( $conds, $limit )->result();
		} else {
		// if both are empty
			//$data = $this->model->get_wallpaper_delete_by_userid( $conds )->result();
			$data = $this->model->get_all_by( $conds )->result();
		}


		$followuser = rtrim($result,",");

		$conds['followuser'] = $followuser;

		$obj = $this->User->get_all_follower_by_user($conds, $limit, $offset)->result();

		if($obj == "") {
			$this->error_response( get_msg( 'no_record' ) );
		} else {
			$this->ps_adapter->convert_follow_user_list( $obj );
			$this->custom_response( $obj );
		}
		
		
		} else {
			$this->error_response( get_msg( 'no_record' ) );

		}
		
	}

	/**
	 * Get all or Get One
	 */
	function get_download_get()
	{
		// add flag for default query
		$this->is_get = true;

		// get limit & offset
		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );

		// get search criteria
		$default_conds = $this->default_conds();
		$user_conds = $this->get();
		$conds = array_merge( $default_conds, $user_conds );
		$conds['user_id'] = $this->get( 'login_user_id' );
		if ( $limit ) {
			unset( $conds['limit']);
		}

		if ( $offset ) {
			unset( $conds['offset']);
		}
		
		if ( !empty( $limit ) && !empty( $offset )) {
		// if limit & offset is not empty
			// echo "adfad";die;
			$data = $this->model->get_download_by_userid( $conds, $limit, $offset )->result();
		} else if ( !empty( $limit )) {
		// if limit is not empty

			$data = $this->model->get_download_by_userid( $conds, $limit )->result();
		} else {
		// if both are empty
			$data = $this->model->get_download_by_userid( $conds )->result();
		}

		$this->custom_response( $data );
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
		// check empty condition
		$final_conds = array();
		foreach( $conds as $key => $value ) {
			if ( !empty( $value )) {
				$final_conds[$key] = $value;
			}
		}
		$conds = $final_conds;

		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );


		if ( !empty( $limit ) && !empty( $offset )) {
			// if limit & offset is not empty
			$data = $this->model->get_all_by( $conds, $limit, $offset )->result();


		} else if ( !empty( $limit )) {
			// if limit is not empty
			$data = $this->model->get_all_by( $conds, $limit )->result();

		} else {
			// if both are empty
			$data = $this->model->get_all_by( $conds )->result();

		}


		$this->custom_response( $data );
	}

	/**
	 * Adds a post.
	 */
	function add_post()
	{
		// set the add flag for custom response
		$this->is_add = true;

		if ( !$this->is_valid( $this->create_validation_rules )) {
		// if there is an error in validation,
			
			return;
		}

		// get the post data
		$data = $this->post();

		if ( !$this->model->save( $data )) {
			$this->error_response( get_msg( 'err_model' ));
		}

		// response the inserted object	
		$obj = $this->model->get_one( $data[$this->model->primary_key] );

		$this->custom_response( $obj );
	}

	/**
	 * Adds a post.
	 */
	function add_follow_post() 
	{
		
		// validation rules for create
		
		$rules = array(
			array(
	        	'field' => 'user_id',
	        	'rules' => 'required|callback_id_check[User]'
	        ),
	        array(
	        	'field' => 'followed_user_id',
	        	'rules' => 'required|callback_id_check[User]'
	        )
        );

		// validation
        if ( !$this->is_valid( $rules )) exit;

		$following_user_id = $this->post('user_id'); //Mary
		$followed_user_id = $this->post('followed_user_id');//Admin
		
		// prep data
        $data = array( 'user_id' => $following_user_id, 'followed_user_id' => $followed_user_id );


		if ( $this->Userfollow->exists( $data )) {
			
			if ( !$this->Userfollow->delete_by( $data )) {
				$this->error_response( get_msg( 'err_model' ));
			} else {

			   	$conds_following['user_id'] = $following_user_id;
				$conds_followed['followed_user_id'] = $following_user_id;
				$conds_followed1['followed_user_id'] = $followed_user_id;

				//for user_id
				$total_follow_count = $this->Userfollow->count_all_by($conds_followed);
				$total_following_count = $this->Userfollow->count_all_by($conds_following);

				$user_data['follower_count'] = $total_follow_count;
				$user_data['following_count'] = $total_following_count;
				$user_id = $this->post('user_id');
				$this->User->save($user_data, $user_id);
				
				//for followed user_id
				$following_user['follower_count'] = $this->Userfollow->count_all_by($conds_followed1);
				$this->User->save($following_user, $followed_user_id);

			}

		} else {

			if ( !$this->Userfollow->save( $data )) {
				$this->error_response( get_msg( 'err_model' ));
			} else {
				$conds_following['user_id'] = $following_user_id;
				$conds_followed['followed_user_id'] = $following_user_id;
				$conds_followed1['followed_user_id'] = $followed_user_id;

				//for user_id
				$total_follow_count = $this->Userfollow->count_all_by($conds_followed);
				$total_following_count = $this->Userfollow->count_all_by($conds_following);

				$user_data['follower_count'] = $total_follow_count;
				$user_data['following_count'] = $total_following_count;
				$user_id = $this->post('user_id');
				$this->User->save($user_data, $user_id);

				//for followed user_id
				$following_user['follower_count'] = $this->Userfollow->count_all_by($conds_followed1);
				$this->User->save($following_user, $followed_user_id);

			}

		}

		$obj = new stdClass;
		$obj->user_id = $followed_user_id;
		$user = $this->User->get_one( $obj->user_id );
		
		$user->followed_user_id = $followed_user_id;
		$user->following_user_id = $following_user_id;
		$this->ps_adapter->convert_user( $user );
		$this->custom_response( $user );
		

	}


	/**
	 * Adds a post.
	 */
	function update_put()
	{
		// set the add flag for custom response
		$this->is_update = true;

		if ( !$this->is_valid( $this->update_validation_rules )) {
		// if there is an error in validation,
			
			return;
		}

		// get the post data
		$data = $this->put();

		// get id
		$id = $this->get( $this->model->primary_key );

		if ( !$this->model->save( $data, $id )) {
		// error in saving, 
			
			$this->error_response( get_msg( 'err_model' ));
		}

		// response the inserted object	
		$obj = $this->model->get_one( $id );

		$this->custom_response( $obj );
	}

	/**
	 * Delete the record
	 */
	function delete_delete()
	{
		// set the add flag for custom response
		$this->is_delete = true;

		if ( !$this->is_valid( $this->delete_validation_rules )) {
		// if there is an error in validation,
			
			return;
		}

		// get id
		$id = $this->get( $this->model->primary_key );

		if ( !$this->model->delete( $id )) {
		// error in saving, 
			
			$this->error_response( get_msg( 'err_model' ));
		}

		$this->success_response( get_msg( 'success_delete' ));
	}

	/**
	 * Claim Point From User
	 */
	function claim_point_post()
	{	
		$user_id = $this->post('user_id');
		$point = $this->post('point');

		if($user_id != "") {

			$user = $this->model->get_one($this->post('user_id'));

			//Get Existing Point
			$existing_total_point = $user->total_point;

			//Add Point to User
			$data['total_point'] = $existing_total_point + $point;

			//Point Update
			$this->model->save($data, $user_id);

			$obj = $this->model->get_one( $user_id );

			$this->custom_response( $obj );

		}
	}

	/**
	 * Custome Response return 404 if not data found
	 *
	 * @param      <type>  $data   The data
	 */
	function custom_response_noti( $data, $require_convert = true )
	{	
		if ( empty( $data )) {
		// if there is no data, return error

			$this->error_response( get_msg( 'no_record' ) );

		} else if ( $require_convert ) {
		// if there is data, return the list
			if ( is_array( $data )) {
			// if the data is array
				foreach ( $data as $obj ) {
					// convert object for each obj
					if($this->get_login_user_id() != "") {
						$noti_user_data = array(
				        	"noti_id" => $obj->id,
				        	"user_id" => $this->get_login_user_id(),
				        	"device_token" => $this->post('device_token')
				    	);
						if ( !$this->Notireaduser->exists( $noti_user_data )) {
							$obj->is_read = 88;
						} else {
							$obj->is_read = 100;
						}
					} 

					$this->convert_object( $obj );
				}
			} else {
				if($this->get_login_user_id() != "") {
					$noti_user_data = array(
			        	"noti_id" => $data->id,
			        	"user_id" => $this->get_login_user_id(),
			        	"device_token" => $this->post('device_token')
			    	);
					if ( !$this->Notireaduser->exists( $noti_user_data )) {
						$data->is_read = 99;
					} else {
						$data->is_read = 100;
					}
				} 

				$this->convert_object( $data );
			}
		}
		$data = $this->ps_security->clean_output( $data );

		

		$this->response( $data );
	}


	/**
	* Download Item 
	*/
	function touch_item_post()
	{

		$user_id = $this->post('user_id');
		$item_id = $this->post('item_id');

		if($user_id != "") {

			$data['user_id'] 		= $user_id ;
			$data['item_id']   = $item_id ;

			$obj = $this->Item->get_one( $item_id  );
			$user_obj = $this->User->get_one( $user_id  );


			//if($user_obj->user_id != "") {

				if($obj->id != "") {

					if($this->Touch->save($data)) {
						
						//Need to update download_count at item table 
						$conds['item_id'] = $item_id;
						
						//Get Downlaod Count from Download Table
						$item_touch_count = $this->Touch->count_all_by($conds);

						//Update at Item Table
						$item_id = $conds['item_id'];
						$data_item['touch_count'] = $item_touch_count;
						$this->Item->save($data_item, $item_id);

						$this->success_response( get_msg( 'success_touch_count'));


					} else {
						$this->error_response( get_msg( 'err_model' ));
					}

				} else {
					$this->error_response( get_msg( 'invalid_item' ));
				}
			// } else {
			// 	$this->error_response( get_msg( 'invalid_user' ));
			// }


		} else {
			$this->error_response( get_msg( 'user_id_required' ));
		}

	}


	/**
  	* Get Delete History By Date Range.
  	*/
	function get_delete_history_post()
	{
	  	

		$start = $this->post('start_date');
		$end   = $this->post('end_date');
		$user_id = $this->post('user_id');
		  
		$conds['start_date'] = $start;
		$conds['end_date']   = $end;

		$conds['order_by'] = 1;
		$conds['order_by_field'] = "type_name";
		$conds['order_by_type'] = "desc";


		//$deleted_his_ids = $this->Delete_history->get_all_history_by($conds)->result();
		$deleted_his_ids = $this->Delete_history->get_all_by($conds)->result();

		$this->custom_response_history( $deleted_his_ids, $user_id, false );

	}

	/**
	 * Custome Response return 404 if not data found
	 *
	 * @param      <type>  $data   The data
	 */
	function custom_response_history( $data, $user_id, $require_convert = true )
	{

		$version_object = new stdClass; 
		$version_object->version_no           = $this->Version->get_one("1")->version_no; 
		$version_object->version_force_update = $this->Version->get_one("1")->version_force_update;
		$version_object->version_title        = $this->Version->get_one("1")->version_title;
		$version_object->version_message      = $this->Version->get_one("1")->version_message;
		$version_object->version_need_clear_data      = $this->Version->get_one("1")->version_need_clear_data;
		$app_object->lat = $this->App_setting->get_one('app1')->lat;
		$app_object->lng = $this->App_setting->get_one('app1')->lng;
		$user_object->is_banned = $this->User->get_one($user_id)->is_banned;

		if ($user_object->is_banned == "") {
			$user_object->is_banned = 0;
		}
		
		$final_data->version = $version_object;
		$final_data->app_setting = $app_object;
		$final_data->user_info = $user_object;
		$final_data->delete_history = $data;
		

		$final_data = $this->ps_security->clean_output( $final_data );


		$this->response( $final_data );
	}

	/**
	 * Adds wallpaper a post.
	 */
	function add_cat_post()
	{
		// set the add flag for custom response
		$this->is_add = true;

		if ( !$this->is_valid( $this->create_validation_rules )) {
		// if there is an error in validation,
			return;
		}

		// get the post data
		$data = $this->post();
		
		$request_cat_id = $this->post('request_cat_id');

		if( $request_cat_id ) {
			//Need to get existing status 
			$data['status'] = $this->Requestcategory->get_one( $request_cat_id )->status;
		
		} else {
			//Default status is pending
			$data['status'] = "1";

		}

		// $user_id = $this->post('added_user_id');
		// $device_token = $this->post('device_token');
		
		// if($device_token){
		// 	$user_data['device_token'] = $device_token;
		// 	$this->User->save( $user_data, $user_id );
		// }
		
		// unset($data['device_token']);
			
		if($request_cat_id) {
			$data['updated_date'] = date("Y-m-d H:i:s");
			if (! $this->Request_category->save( $data, $request_cat_id )) {
				$this->error_response( get_msg( 'err_model' ));
			} 
		} else {
			if ( !$this->Request_category->save( $data )) {
				$this->error_response( get_msg( 'err_model' ));
			}

		}

		// response the inserted object	
		$obj = $this->model->get_one( $data[$this->model->primary_key] );
		$this->ps_adapter->convert_request_category( $obj );
		$this->custom_response( $obj );
	}

	/**
	 * Get all or Get One
	 */
	function get_item_by_followuser_get()
	{

		// add flag for default query
		$this->is_get = true;

		// get limit & offset
		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );

		// get search criteria
		$default_conds = $this->default_conds();
		$user_conds = $this->get();
		$conds = array_merge( $default_conds, $user_conds );
		$conds['user_id'] = $this->get( 'login_user_id' );

		$userfollow_data = $this->Userfollow->get_all_by($conds)->result();
		//print_r(count( $userfollow_data ));die;

		if ( count( $userfollow_data ) > 0 ) {
			foreach ($userfollow_data as $userfollow) {
		  	$result .= "'".$userfollow->followed_user_id ."'" .",";
		  
		}

		if ( !empty( $limit ) && !empty( $offset )) {
			// if limit & offset is not empty
			//$data = $this->model->get_wallpaper_delete_by_userid( $conds, $limit, $offset )->result();
			$data = $this->model->get_all_by( $conds, $limit, $offset )->result();
		} else if ( !empty( $limit )) {
			// if limit is not empty

			//$data = $this->model->get_wallpaper_delete_by_userid( $conds, $limit )->result();
			$data = $this->model->get_all_by( $conds, $limit )->result();
		} else {
			// if both are empty
			//$data = $this->model->get_wallpaper_delete_by_userid( $conds )->result();
			$data = $this->model->get_all_by( $conds )->result();
		}


		$followuser = rtrim($result,",");
		
		$conds['followuser'] = $followuser;

		$item_list = $this->Item->get_all_item_by_followuser($conds, $limit, $offset)->result();
		
		if($item_list == "") {
			$this->error_response( get_msg( 'no_record' ) );
		} else {
			$this->ps_adapter->convert_item( $item_list );
			$this->custom_response( $item_list );
		}
		

		} else {
			$this->error_response( get_msg( 'no_record' ) );

		}
		

		
	}

	/**
	 * Adds a post.
	 */
	function add_accept_offer_post()
	{
		// set the add flag for custom response
		$this->is_add = true;

		if ( !$this->is_valid( $this->create_validation_rules )) {
		// if there is an error in validation,
			
			return;
		}

		// get the post data
		$data = $this->post();
		
		if ( $this->model->exists( $data ) ) {

			//existing accept user
			$this->error_response( get_msg( 'already_accept_user' ));
			
		} else {

			if ( $this->model->save( $data )) {
				// response the inserted object	
				$obj = $this->model->get_one( $data[$this->model->primary_key] );

				if ( $obj->item_id != " ") {
					$id = $obj->item_id;
					$item_data = array(
		        		"is_sold_out" => 1
		        	
		    		);

					$this->Item->save( $item_data, $id );

					$item_data = $this->Item->get_one($id);
					$this->ps_adapter->convert_item($item_data);
					$this->custom_response( $item_data );
				}
			
 			}
		
		}
	}

	/**
	 * Adds a post.
	 */
	function add_rating_post()
	{
		// set the add flag for custom response
		$this->is_add = true;

		if ( !$this->is_valid( $this->create_validation_rules )) {
		// if there is an error in validation,
			
			return;
		}

		// get the post data
		$data = $this->post();
		$from_user_id = $data['from_user_id'];
		$to_user_id = $data['to_user_id'];
		
		$conds['from_user_id'] = $from_user_id;
		$conds['to_user_id'] = $to_user_id;
		//print_r($conds);die;
		
		$id = $this->model->get_one_by($conds)->id;

		$rating = $data['rating'];
		if ( $id ) {

			$this->model->save( $data, $id );

			// response the inserted object	
			$obj = $this->model->get_one( $id );
		} else {
			$this->model->save( $data );

			// response the inserted object	
			$obj = $this->model->get_one( $data[$this->model->primary_key] );
		}

		//Need to update rating value at product
		$conds_rating['to_user_id'] = $obj->to_user_id;

		$total_rating_count = $this->Rate->count_all_by($conds_rating);
		$sum_rating_value = $this->Rate->sum_all_by($conds_rating)->result()[0]->rating;

		if($total_rating_count > 0) {
			$total_rating_value = number_format((float) ($sum_rating_value  / $total_rating_count), 1, '.', '');
		} else {
			$total_rating_value = 0;
		}

		$user_data['overall_rating'] = $total_rating_value;
		$this->User->save($user_data, $obj->to_user_id);

		
		//$obj_item = $this->Product->get_one( $obj->product_id );
		$obj_rating = $this->Rate->get_one( $obj->id );

		$this->ps_adapter->convert_rating( $obj_rating);
		$this->custom_response( $obj_rating );
	}

	// get rating list by user id

	function rating_user_post()
	{
		$this->is_add = true;

		if ( !$this->is_valid( $this->create_validation_rules )) {
		// if there is an error in validation,
			
			return;
		}

		// get the post data
		$data = $this->post();
		$user_id = $data['user_id'];
		$conds['to_user_id'] = $user_id;

		$users = $this->Rate->get_all_by($conds)->result();
		//print_r($users);die;
		
		foreach ($users as $user) {
			$to_user_id = $user->to_user_id;
			$id .= "'" . $user->id . "',";
		}

		$result = rtrim($id,',');
		//print_r($result);die;

		if ($user_id == $to_user_id) {

			$conds1['id'] = $result;
			//print_r($conds1['id']);die;

			$obj = $this->Rate->get_all_in_rating( $conds1 )->result();
			//print_r($obj);die;

			$this->ps_adapter->convert_rating( $obj );

			$this->custom_response( $obj );
		

		}
		
	}

	/**
	 * Get all or Get One
	 */
	function get_following_user_get()
	{
		// add flag for default query
		$this->is_get = true;

		// get limit & offset
		$limit = $this->get( 'limit' );
		$offset = $this->get( 'offset' );

		// get search criteria
		$default_conds = $this->default_conds();
		$user_conds = $this->get();
		$conds = array_merge( $default_conds, $user_conds );
		$conds['followed_user_id'] = $this->get( 'login_user_id' );

		$userfollow_data = $this->Userfollow->get_all_by($conds)->result();
		//print_r( $userfollow_data );die;

		if ( count( $userfollow_data ) > 0 ) {
			foreach ($userfollow_data as $userfollow) {
		  	$result .= "'".$userfollow->user_id ."'" .",";
		  
		}

		if ( !empty( $limit ) && !empty( $offset )) {
		// if limit & offset is not empty
			//$data = $this->model->get_wallpaper_delete_by_userid( $conds, $limit, $offset )->result();
			$data = $this->model->get_all_by( $conds, $limit, $offset )->result();
		} else if ( !empty( $limit )) {
		// if limit is not empty

			//$data = $this->model->get_wallpaper_delete_by_userid( $conds, $limit )->result();
			$data = $this->model->get_all_by( $conds, $limit )->result();
		} else {
		// if both are empty
			//$data = $this->model->get_wallpaper_delete_by_userid( $conds )->result();
			$data = $this->model->get_all_by( $conds )->result();
		}


		$followuser = rtrim($result,",");

		$conds['followuser'] = $followuser;

		$obj = $this->User->get_all_follower_by_user($conds, $limit, $offset)->result();
		
		$this->ps_adapter->convert_follow_user_list( $obj );
		$this->custom_response( $obj );

		} else {
			$this->error_response( get_msg( 'no_record' ) );

		}
	}

	


	
}
