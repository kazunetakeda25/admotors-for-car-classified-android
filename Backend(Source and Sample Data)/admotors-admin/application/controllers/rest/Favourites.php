<?php
require_once( APPPATH .'libraries/REST_Controller.php' );

/**
 * REST API for Favourites
 */
class Favourites extends API_Controller
{

	/**
	 * Constructs Parent Constructor
	 */
	function __construct()
	{
		$is_login_user_nullable = false;

		// call the parent
		parent::__construct( 'Favourite', $is_login_user_nullable );

		// set the validation rules for create and update
		$this->validation_rules();
	}

	/**
	 * Determines if valid input.
	 */
	function validation_rules()
	{
		// validation rules for create
		$this->create_validation_rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required'
	        ),
	        array(
	        	'field' => 'user_id',
	        	'rules' => 'required'
	        )
        );

	}


	/**
	* When user press favourite button from app
	*/
	function press_post() 
	{
		
		// validation rules for create
		
		$rules = array(
			array(
	        	'field' => 'item_id',
	        	'rules' => 'required|callback_id_check[Item]'
	        ),
	        array(
	        	'field' => 'user_id',
	        	'rules' => 'required|callback_id_check[User]'
	        )
        );

		// validation
        if ( !$this->is_valid( $rules )) exit;

		$item_id = $this->post('item_id');
		$user_id = $this->post('user_id');
		
		// prep data
        $data = array( 'item_id' => $item_id, 'user_id' => $user_id );

		if ( $this->Favourite->exists( $data )) {

			if ( !$this->Favourite->delete_by( $data )) {
				$this->error_response( get_msg( 'err_model' ));
			} else {
				$conds_fav['item_id'] = $item_id;

			    $total_fav_count = $this->Favourite->count_all_by($conds_fav);

			    $item_data['favourite_count'] = $total_fav_count;
			    $this->Item->save($item_data, $item_id);
			}

		} else {
			
			if ( !$this->Favourite->save( $data )) {
				$this->error_response( get_msg( 'err_model' ));
			} else {
				$conds_fav['item_id'] = $item_id;

			    $total_fav_count = $this->Favourite->count_all_by($conds_fav);

			    $item_data['favourite_count'] = $total_fav_count;
			    $this->Item->save($item_data, $item_id);
			}

		}

		$obj = new stdClass;
		$obj->id = $item_id;
		$item = $this->Item->get_one( $obj->id );
		
		$item->login_user_id_post = $user_id;
		
		$this->ps_adapter->convert_item( $item );
		$this->custom_response( $item );
		

	}
}