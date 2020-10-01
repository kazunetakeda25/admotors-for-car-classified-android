<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * PanaceaSoft Authentication
 */
class PS_Adapter {

	// codeigniter instance
	protected $CI;

	// login user
	protected $login_user_id;

	/**
	 * Constructor
	 */
	function __construct()
	{
		// get CI instance
		$this->CI =& get_instance();
	}

	/**
	 * Sets the login user.
	 */
	function set_login_user_id( $user_id )
	{
		$this->login_user_id = $user_id;
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
		$img = $this->CI->Image->get_all_by( array( 'img_parent_id' => $id, 'img_type' => $type ))->result();

		if ( count( $img ) > 0 ) {
		// if there are images for wallpaper,
			
			$default_photo = $img[0];
		} else {
		// if no image, return empty object

			$default_photo = $this->CI->Image->get_empty_object();
		}

		return $default_photo;
	}
	

	/**
	 * Customize wallpaper object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_image( &$obj )
	{

	}

	/**
	 * Customize manufacturer object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_manufacturer( &$obj )
	{
		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'manufacturer' );

		//set default icon
		$obj->default_icon = $this->get_default_photo( $obj->id, 'manufacturer-icon' );
	}

	/**
	 * Customize category object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_banners( &$obj )
	{
		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'banner' );
	}


	/**
	 * Customize category object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_item( &$obj )
	{
		// photo count
		$obj->photo_count = $this->CI->Image->count_all_by(array('img_parent_id' => $obj->id));
		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'item' );

		// Manufacturer object
		if ( isset( $obj->manufacturer_id )) {

			$tmp_manu = $this->CI->Manufacturer->get_one( $obj->manufacturer_id );

			$this->convert_manufacturer( $tmp_manu );

			$obj->manufacturer = $tmp_manu;
		}

		// Model Object
		if ( isset( $obj->model_id )) {

			$tmp_model = $this->CI->Model->get_one( $obj->model_id );

			$this->convert_models( $tmp_model );

			$obj->model = $tmp_model;
		}

		// Itemtype Object
		if ( isset( $obj->item_type_id )) {

			$tmp_item_type = $this->CI->Itemtype->get_one( $obj->item_type_id );
			$obj->item_type = $tmp_item_type;
		}

		// Itemprice Object
		if ( isset( $obj->item_price_type_id )) {

			$tmp_item_price_type = $this->CI->Pricetype->get_one( $obj->item_price_type_id );
			$obj->item_price_type = $tmp_item_price_type;
		}

		// Itemcurrency Object
		if ( isset( $obj->item_currency_id )) {

			$tmp_item_currency = $this->CI->Currency->get_one( $obj->item_currency_id );
			$obj->item_currency = $tmp_item_currency;
		}

		// Itemlocation Object
		if ( isset( $obj->item_location_id )) {

			$tmp_item_location= $this->CI->Itemlocation->get_one( $obj->item_location_id );
			$obj->item_location = $tmp_item_location;
		}

		// condition of item id Object
		if ( isset( $obj->condition_of_item_id )) {

			$tmp_condition_item= $this->CI->Condition->get_one( $obj->condition_of_item_id );
			$obj->condition_of_item = $tmp_condition_item;
		}

		// color id Object
		if ( isset( $obj->color_id )) {

			$tmp_color = $this->CI->Color->get_one( $obj->color_id );
			$obj->color = $tmp_color;
		}

		// fuel type id Object
		if ( isset( $obj->fuel_type_id )) {

			$tmp_fueltype = $this->CI->Fueltype->get_one( $obj->fuel_type_id );
			$obj->fuel_type = $tmp_fueltype;
		}

		// build type id Object
		if ( isset( $obj->build_type_id )) {

			$tmp_buildtype = $this->CI->Buildtype->get_one( $obj->build_type_id );
			$obj->build_type = $tmp_buildtype;
		}

		// seller type id Object
		if ( isset( $obj->seller_type_id )) {

			$tmp_sellertype = $this->CI->Sellertype->get_one( $obj->seller_type_id );
			$obj->seller_type = $tmp_sellertype;
		}

		// seller type id Object
		if ( isset( $obj->transmission_id )) {

			$tmp_trans = $this->CI->Transmission->get_one( $obj->transmission_id );
			$obj->transmission = $tmp_trans;
		}

		//print_r($obj->added_user_id . "$$");die;
		// User Object
		if ( isset( $obj->added_user_id )) {
			$tmp_item_user = $this->CI->User->get_one( $obj->added_user_id );

			$this->convert_user( $tmp_item_user );
			$obj->user = $tmp_item_user;
		}

		//Need to check for Like and Favourite
		$obj->is_favourited = 0;
		$obj->is_owner = 0;

		if($this->get_login_user_id() != "") {
			//echo "1";
			//Need to check for Fav
			$conds['item_id'] = $obj->id;
			$conds['user_id']    = $this->get_login_user_id();
			$login_user_id = $conds['user_id'];


			if($obj->added_user_id == $login_user_id) {
				$obj->is_owner = 1;
			} else {
				$obj->is_owner = 0;
			}

			$fav_id = $this->CI->Favourite->get_one_by($conds)->id;
			$obj->is_favourited = 0;
			if($fav_id != "") {
				$obj->is_favourited = 1;
			} else {
				$obj->is_favourited = 0;
			}

		} else if($obj->login_user_id_post != "") {
			//echo "2";
			$conds['item_id'] 	= $obj->id;
			$conds['user_id']    = $obj->login_user_id_post;
			$login_user_id = $conds['user_id'];

			// checking for like product by user

			$obj->is_owner = 0;
			if($obj->added_user_id == $login_user_id) {
				$obj->is_owner = 1;
			} else {
				$obj->is_owner = 0;
			}
			
			$fav_id = $this->CI->Favourite->get_one_by($conds)->id;
			$obj->is_favourited = 0;
			if($fav_id != "") {
				$obj->is_favourited = 1;
			} else {
				$obj->is_favourited = 0;
			}

		}
		//die;

		//print_r($obj->added_user_id);die;
		//unset($obj->login_user_id_post);

		$obj->is_favourited = $obj->is_favourited;
		$obj->is_owner = $obj->is_owner;

	}

	/**
	 * Customize chatitem object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_chatitem( &$obj )
	{
			
		// photo count
		$obj->photo_count = $this->CI->Image->count_all_by(array('img_parent_id' => $obj->id));
		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'item' );

		// manufacturer object
		if ( isset( $obj->manufacturer_id )) {
			$tmp_manu = $this->CI->Manufacturer->get_one( $obj->manufacturer_id );

			$this->convert_manufacturer( $tmp_manu );

			$obj->manufacturer = $tmp_manu;
		}

		// Model Object
		if ( isset( $obj->model_id )) {
			$tmp_model = $this->CI->Model->get_one( $obj->model_id );

			$this->convert_model( $tmp_model );

			$obj->model = $tmp_model;
		}

		// Itemtype Object
		if ( isset( $obj->item_type_id )) {
			$tmp_item_type = $this->CI->Itemtype->get_one( $obj->item_type_id );

			$obj->item_type = $tmp_item_type;
		}

		// Itemprice Object
		if ( isset( $obj->item_price_type_id )) {
			$tmp_item_price_type = $this->CI->Pricetype->get_one( $obj->item_price_type_id );

			$obj->item_price_type = $tmp_item_price_type;
		}

		// Itemcurrency Object
		if ( isset( $obj->item_currency_id )) {
			$tmp_item_currency = $this->CI->Currency->get_one( $obj->item_currency_id );

			$obj->item_currency = $tmp_item_currency;
		}

		// Itemlocation Object
		if ( isset( $obj->item_location_id )) {
			$tmp_item_location= $this->CI->Itemlocation->get_one( $obj->item_location_id );

			$obj->item_location = $tmp_item_location;
		}

		// condition of item id Object
		if ( isset( $obj->condition_of_item_id )) {
			$tmp_condition_item= $this->CI->Condition->get_one( $obj->condition_of_item_id );

			$obj->condition_of_item = $tmp_condition_item;
		}

		// Chat User Object
		if ( isset( $obj->chat_user_id )) {
			$conds['chat_user_id'] = $obj->chat_user_id;

			$tmp_item_user = $this->CI->User->get_all_in_user($conds)->result();
			//print_r($tmp_item_user);die;

			$this->convert_user( $tmp_item_user );

			$obj->chat_user = $tmp_item_user;
		}


		//Need to check for Like and Favourite
		$obj->is_favourited = 0;
		$obj->is_owner = 0;
		
		if($this->get_login_user_id() != "") {
			//Need to check for Fav
			$conds['item_id'] = $obj->id;
			$conds['user_id'] = $this->get_login_user_id();
			$login_user_id    = $conds['user_id'];
		
			// checking for item by user
			if($obj->added_user_id == $login_user_id) {
				$obj->is_owner = 1;
			} else {
				$obj->is_owner = 0;
			}


			$fav_id = $this->CI->Favourite->get_one_by($conds)->fav_id;

			$obj->is_favourited = 0;
			if($fav_id != "") {
				$obj->is_favourited = 1;
			} else {
				$obj->is_favourited = 0;
			}

		} else if($obj->login_user_id_post != "") {
			$conds['item_id'] = $obj->id;
			$conds['user_id'] = $obj->login_user_id_post;
			$login_user_id    = $conds['user_id'];

			// checking for like product by user
			$obj->is_owner = 0;
			if($obj->added_user_id == $login_user_id) {
				$obj->is_owner = 1;
			} else {
				$obj->is_owner = 0;
			}

			$fav_id = $this->CI->Favourite->get_one_by($conds)->id;
			$obj->is_favourited = 0;
			if($fav_id != "") {
				$obj->is_favourited = 1;
			} else {
				$obj->is_favourited = 0;
			}

		}

		
		unset($obj->login_user_id_post);

		$obj->is_favourited = $obj->is_favourited;
		$obj->is_owner = $obj->is_owner;

	}


	/**
	 * Sets the login user.
	 */
	function get_login_user_id()
	{
		return $this->login_user_id;
	}


	
	/**
	 * Customize follower user object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_user_follow( &$obj )
	{
		// user object
		if ( isset( $obj->followed_user_id )) {
			$tmp_user = $this->CI->User->get_one( $obj->followed_user_id );

			$this->convert_user( $tmp_user );

			$obj->user_followed = $tmp_user;
		}
	}



	/**
	 * Customize about object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_about( &$obj )
	{
		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->about_id, 'about' );

	}

	/**
	 * Customize user object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_user( &$obj , $need_return = false)
	{
		
		$conds['added_user_id'] = $obj->user_id;
		//print_r($obj->user_id);die;
		$obj->rating_count = $this->CI->Rate->count_all_by(array('to_user_id' => $obj->user_id));

        //is follower or not

		$obj->is_followed = 0;
		
		 if($obj->followed_user_id != "") {
			
			$conds1['user_id']    = $obj->following_user_id;
			$conds1['followed_user_id'] = $obj->followed_user_id;
			
			// checking follower
			
			$followed_user_id = $this->CI->Userfollow->get_one_by($conds1)->id;
			//print_r($followed_user_id);die;
			$obj->is_followed = 0;
			if($followed_user_id != "") {
				$obj->is_followed = 1;
			} else {
				$obj->is_followed = 0;
			}

		}
		

		
		unset($obj->following_user_id);
		unset($obj->followed_user_id);

		$obj->is_followed = $obj->is_followed;

        //rating details 
		
		$total_rating_count = 0;
		$total_rating_value = 0;

		$five_star_count = 0;
		$five_star_percent = 0;

		$four_star_count = 0;
		$four_star_percent = 0;

		$three_star_count = 0;
		$three_star_percent = 0;

		$two_star_count = 0;
		$two_star_percent = 0;

		$one_star_count = 0;
		$one_star_percent = 0;


		

		//Rating Total how much ratings for this product
		$conds_rating['to_user_id'] = $obj->user_id;
		$total_rating_count = $this->CI->Rate->count_all_by($conds_rating);
		$sum_rating_value = $this->CI->Rate->sum_all_by($conds_rating)->result()[0]->rating;


		//Rating Value such as 3.5, 4.3 and etc
		if($total_rating_count > 0) {
			$total_rating_value = number_format((float) ($sum_rating_value  / $total_rating_count), 1, '.', '');
		} else {
			$total_rating_value = 0;
		}

		//For 5 Stars rating
		$conds_five_star_rating['rating'] = 5;
		$conds_five_star_rating['to_user_id'] = $obj->user_id;
		$five_star_count = $this->CI->Rate->count_all_by($conds_five_star_rating);
		if($total_rating_count > 0) {
			$five_star_percent = number_format((float) ((100 / $total_rating_count) * $five_star_count), 1, '.', '');
		} else {
			$five_star_percent = 0;
		}

		//For 4 Stars rating
		$conds_four_star_rating['rating'] = 4;
		$conds_four_star_rating['to_user_id'] = $obj->user_id;
		$four_star_count = $this->CI->Rate->count_all_by($conds_four_star_rating);
		if($total_rating_count > 0) {
			$four_star_percent = number_format((float) ((100 / $total_rating_count) * $four_star_count), 1, '.', '');
		} else {
			$four_star_percent = 0;
		}


		//For 3 Stars rating
		$conds_three_star_rating['rating'] = 3;
		$conds_three_star_rating['to_user_id'] = $obj->user_id;
		$three_star_count = $this->CI->Rate->count_all_by($conds_three_star_rating);
		if($total_rating_count > 0) {
			$three_star_percent = number_format((float) ((100 / $total_rating_count) * $three_star_count), 1, '.', '');
		} else {
			$three_star_percent = 0;
		}


		//For 2 Stars rating
		$conds_two_star_rating['rating'] = 2;
		$conds_two_star_rating['to_user_id'] = $obj->user_id;
		$two_star_count = $this->CI->Rate->count_all_by($conds_two_star_rating);

		if($total_rating_count > 0) {
			$two_star_percent = number_format((float) ((100 / $total_rating_count) * $two_star_count), 1, '.', '');
		} else {
			$two_star_percent = 0;
		}

		//For 1 Stars rating
		$conds_one_star_rating['rating'] = 1;
		$conds_one_star_rating['to_user_id'] = $obj->user_id;
		$one_star_count = $this->CI->Rate->count_all_by($conds_one_star_rating);

		if($total_rating_count > 0) {
		$one_star_percent = number_format((float) ((100 / $total_rating_count) * $one_star_count), 1, '.', '');
		} else {
			$one_star_percent = 0;
		}


		$rating_std = new stdClass();
		@$rating_std->five_star_count = $five_star_count; 
		$rating_std->five_star_percent = $five_star_percent;

		$rating_std->four_star_count = $four_star_count;
		$rating_std->four_star_percent = $four_star_percent;

		$rating_std->three_star_count = $three_star_count;
		$rating_std->three_star_percent = $three_star_percent;

		$rating_std->two_star_count = $two_star_count;
		$rating_std->two_star_percent = $two_star_percent;

		$rating_std->one_star_count = $one_star_count;
		$rating_std->one_star_percent = $one_star_percent;

		//print_r($total_rating_count . "##" . $total_rating_value);die;

		$rating_std->total_rating_count = $total_rating_count;
		$rating_std->total_rating_value = $total_rating_value;


		$obj->rating_details = $rating_std;

		if($need_return)
		{
			return $obj;
		} 

	}

	/**
	 * Customize noti object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_noti_message( &$obj )
	{
		
		
		if($this->get_login_user_id() != "") {
			$noti_user_data = array(
	        	"noti_id" => $obj->id,
	        	"user_id" => $this->get_login_user_id()
	    	);
			if ( !$this->CI->Notireaduser->exists( $noti_user_data )) {
				$obj->is_read = 0;
			} else {
				$obj->is_read = 1;
			}
		} 
		


		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'noti' );
	}

	/**
	 * Customize noti object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_noti( &$obj )
	{
		
		
		if($this->get_login_user_id() != "") {
			$noti_user_data = array(
	        	"noti_id" => $obj->id,
	        	"user_id" => $this->get_login_user_id()
	    	);
			if ( !$this->CI->Notireaduser->exists( $noti_user_data )) {
				$obj->is_read = 0;
			} else {
				$obj->is_read = 1;
			}
		} 
		


		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'noti' );
	}

	/**
	 * Customize request category object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_request_category( &$obj )
	{
		
	}

	/**
	 * Customize request category object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_models( &$obj )
	{
		
		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'model' );

		//set default icon
		$obj->default_icon = $this->get_default_photo( $obj->id, 'model_icon' );

		if ( isset( $obj->manufacturer_id )) {
			$tmp_manu = $this->CI->Manufacturer->get_one( $obj->manufacturer_id );

			$this->convert_manufacturer( $tmp_manu );

			$obj->manufacturer = $tmp_manu;
		}
	}

	/*
	 * Customize feed object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_feed( &$obj )
	{
		// set default photo
		$obj->default_photo = $this->get_default_photo( $obj->id, 'blog' );

	}

	/**
	 * Customize tag object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_rating( &$obj )
	{
		// set user object

		if ( is_array( $obj )) {
			
			for ($i=0; $i < count($obj) ; $i++) { 
				if ( isset( $obj[$i]->from_user_id )) {

					$tmp_from_user_id = $this->CI->User->get_one( $obj[$i]->from_user_id );
					//print_r($tmp_from_user_id);die;
					$this->convert_user( $tmp_from_user_id );
					//print_r($a);die;
					$obj[$i]->from_user = $tmp_from_user_id;
				}


				// set user object
				if ( isset( $obj[$i]->to_user_id )) {
					$tmp_to_user_id = $this->CI->User->get_one( $obj[$i]->to_user_id );

					$this->convert_user( $tmp_to_user_id );

					$obj[$i]->to_user = $tmp_to_user_id;
				}
			}
			

		}else {

			if ( isset( $obj->from_user_id )) {

					$tmp_from_user_id = $this->CI->User->get_one( $obj->from_user_id );
					//print_r($tmp_from_user_id);die;
					$this->convert_user( $tmp_from_user_id );
					//print_r($a);die;
					$obj->from_user = $tmp_from_user_id;
				}


				// set user object
				if ( isset( $obj->to_user_id )) {
					$tmp_to_user_id = $this->CI->User->get_one( $obj->to_user_id );

					$this->convert_user( $tmp_to_user_id );

					$obj->to_user = $tmp_to_user_id;
				}

		}
	}

	/**
	 * Customize chatitem object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_chathistory( &$obj )
	{
		if ( is_array( $obj )) {
			for ($i=0; $i < count($obj) ; $i++) { 

				if ( $obj[$i]->nego_price == '0') {
					$obj[$i]->is_offer = 0;
					$obj[$i]->offer_amount = 0;
					
				} else {
					$obj[$i]->is_offer = 1;
					$obj[$i]->offer_amount = $obj[$i]->nego_price;

				}
				// item object
				if ( isset( $obj[$i]->item_id )) {
					$tmp_item = $this->CI->Item->get_one( $obj[$i]->item_id );

					$this->convert_item( $tmp_item );

					$obj[$i]->item = $tmp_item;
				}

				//set users objects
				if ( isset( $obj[$i]->buyer_user_id )) {

					$tmp_buyer_user_id = $this->CI->User->get_one( $obj[$i]->buyer_user_id );
					$this->convert_user( $tmp_buyer_user_id );
					$obj[$i]->buyer = $tmp_buyer_user_id;
				}

				if ( isset( $obj[$i]->seller_user_id )) {
					$tmp_seller_user_id = $this->CI->User->get_one( $obj[$i]->seller_user_id );

					$this->convert_user( $tmp_seller_user_id );

					$obj[$i]->seller = $tmp_seller_user_id;
				}
			}
		} else {


			if ( $obj->nego_price == '0') {
				$obj->is_offer = 0;
				$obj->offer_amount = 0;
				
			} else {
				$obj->is_offer = 1;
				$obj->offer_amount = $obj->nego_price;

			}

			if ( isset( $obj->item_id )) {
				$tmp_item = $this->CI->Item->get_one( $obj->item_id );

				$this->convert_item( $tmp_item );

				$obj->item = $tmp_item;
			}

			// set users objects
			
			if ( isset( $obj->buyer_user_id )) {

				$tmp_buyer_user_id = $this->CI->User->get_one( $obj->buyer_user_id );
				$this->convert_user( $tmp_buyer_user_id );
				$obj->buyer = $tmp_buyer_user_id;
			}


			if ( isset( $obj->seller_user_id )) {
				$tmp_seller_user_id = $this->CI->User->get_one( $obj->seller_user_id );

				$this->convert_user( $tmp_seller_user_id );

				$obj->seller = $tmp_seller_user_id;
			}

		}	
	}


	/**
	 * Customize follwing user object
	 *
	 * @param      <type>  $obj    The object
	 */
	function convert_follow_user( &$obj )
	{
		// user object
		if ( isset( $obj->user_id )) {
			$tmp_user = $this->CI->User->get_one( $obj->user_id );

			$this->convert_user( $tmp_user );

			$obj->user_followed = $tmp_user;
		}
	}

	function convert_follow_user_list( &$obj, $followed_user_id )
	{

		if ( is_array( $obj )) {

			for ($i=0; $i < count($obj) ; $i++) { 

				$obj[$i]->is_followed = 0;
			
				 if($followed_user_id != "") {
					
					// $conds1['user_id']    = $following_user_id;
					// $conds1['followed_user_id'] = $obj[$i]->user_id;
					$conds1['user_id']    = $obj[$i]->user_id;
					$conds1['followed_user_id'] = $followed_user_id;
					// checking follower
					
					$followed_user_id = $this->CI->Userfollow->get_one_by($conds1)->id;
					//print_r($followed_user_id);die;
					$obj[$i]->is_followed = 0;
					if($followed_user_id != "") {
						$obj[$i]->is_followed = 1;
					} else {
						$obj[$i]->is_followed = 0;
					}

				}
				
				unset($followed_user_id);

				$obj[$i]->is_followed = $obj[$i]->is_followed;

				if ( isset( $obj[$i]->user_id )) {

					$obj[$i]->rating_count = $this->CI->Rate->count_all_by(array('to_user_id' => $obj[$i]->user_id));

						//rating details 
					$total_rating_count = 0;
					$total_rating_value = 0;

					$five_star_count = 0;
					$five_star_percent = 0;

					$four_star_count = 0;
					$four_star_percent = 0;

					$three_star_count = 0;
					$three_star_percent = 0;

					$two_star_count = 0;
					$two_star_percent = 0;

					$one_star_count = 0;
					$one_star_percent = 0;

					//Rating Total how much ratings for this product
					$conds_rating['to_user_id'] = $obj[$i]->user_id;
					
					$total_rating_count = $this->CI->Rate->count_all_by($conds_rating);
					$sum_rating_value = $this->CI->Rate->sum_all_by($conds_rating)->result()[0]->rating;


					//Rating Value such as 3.5, 4.3 and etc
					if($total_rating_count > 0) {
						$total_rating_value = number_format((float) ($sum_rating_value  / $total_rating_count), 1, '.', '');
					} else {
						$total_rating_value = 0;
					}

					//For 5 Stars rating

					$conds_five_star_rating['rating'] = 5;
					$conds_five_star_rating['to_user_id'] = $obj->id;
					$five_star_count = $this->CI->Rate->count_all_by($conds_five_star_rating);
					if($total_rating_count > 0) {
						$five_star_percent = number_format((float) ((100 / $total_rating_count) * $five_star_count), 1, '.', '');
					} else {
						$five_star_percent = 0;
					}

					//For 4 Stars rating
					$conds_four_star_rating['rating'] = 4;
					$conds_four_star_rating['to_user_id'] = $obj->id;
					$four_star_count = $this->CI->Rate->count_all_by($conds_four_star_rating);
					if($total_rating_count > 0) {
						$four_star_percent = number_format((float) ((100 / $total_rating_count) * $four_star_count), 1, '.', '');
					} else {
						$four_star_percent = 0;
					}


					//For 3 Stars rating
					$conds_three_star_rating['rating'] = 3;
					$conds_three_star_rating['to_user_id'] = $obj->id;
					$three_star_count = $this->CI->Rate->count_all_by($conds_three_star_rating);
					if($total_rating_count > 0) {
						$three_star_percent = number_format((float) ((100 / $total_rating_count) * $three_star_count), 1, '.', '');
					} else {
						$three_star_percent = 0;
					}


					//For 2 Stars rating
					$conds_two_star_rating['rating'] = 2;
					$conds_two_star_rating['to_user_id'] = $obj->id;
					$two_star_count = $this->CI->Rate->count_all_by($conds_two_star_rating);

					if($total_rating_count > 0) {
						$two_star_percent = number_format((float) ((100 / $total_rating_count) * $two_star_count), 1, '.', '');
					} else {
						$two_star_percent = 0;
					}

					//For 1 Stars rating
					$conds_one_star_rating['rating'] = 1;
					$conds_one_star_rating['to_user_id'] = $obj->id;
					$one_star_count = $this->CI->Rate->count_all_by($conds_one_star_rating);

					if($total_rating_count > 0) {
					$one_star_percent = number_format((float) ((100 / $total_rating_count) * $one_star_count), 1, '.', '');
					} else {
						$one_star_percent = 0;
					}


					$rating_std = new stdClass();
					@$rating_std->five_star_count = $five_star_count; 
					$rating_std->five_star_percent = $five_star_percent;

					$rating_std->four_star_count = $four_star_count;
					$rating_std->four_star_percent = $four_star_percent;

					$rating_std->three_star_count = $three_star_count;
					$rating_std->three_star_percent = $three_star_percent;

					$rating_std->two_star_count = $two_star_count;
					$rating_std->two_star_percent = $two_star_percent;

					$rating_std->one_star_count = $one_star_count;
					$rating_std->one_star_percent = $one_star_percent;

					$rating_std->total_rating_count = $total_rating_count;
					$rating_std->total_rating_value = $total_rating_value;

					$obj[$i]->rating_details = $rating_std;	
					
				}

			
			}

		} else {
			$obj->is_followed = 0;
			
			 if($followed_user_id != "") {
				
				// $conds1['user_id']    = $following_user_id;
				// $conds1['followed_user_id'] = $obj[$i]->user_id;
				$conds1['user_id']    = $obj[$i]->user_id;
				$conds1['followed_user_id'] = $followed_user_id;
				// checking follower
				
				$followed_user_id = $this->CI->Userfollow->get_one_by($conds1)->id;
				//print_r($followed_user_id);die;
				$obj->is_followed = 0;
				if($followed_user_id != "") {
					$obj->is_followed = 1;
				} else {
					$obj->is_followed = 0;
				}

			}
			
			unset($followed_user_id);

			$obj->is_followed = $obj->is_followed;
			$obj->rating_count = $this->CI->Rate->count_all_by(array('to_user_id' => $obj->user_id));
			//rating details 
			$total_rating_count = 0;
			$total_rating_value = 0;

			$five_star_count = 0;
			$five_star_percent = 0;

			$four_star_count = 0;
			$four_star_percent = 0;

			$three_star_count = 0;
			$three_star_percent = 0;

			$two_star_count = 0;
			$two_star_percent = 0;

			$one_star_count = 0;
			$one_star_percent = 0;

			//Rating Total how much ratings for this product
			$conds_rating['to_user_id'] = $obj->to_user_id;
			$total_rating_count = $this->CI->Rate->count_all_by($conds_rating);
			$sum_rating_value = $this->CI->Rate->sum_all_by($conds_rating)->result()[0]->rating;

			//Rating Value such as 3.5, 4.3 and etc
			if($total_rating_count > 0) {
				$total_rating_value = number_format((float) ($sum_rating_value  / $total_rating_count), 1, '.', '');
			} else {
				$total_rating_value = 0;
			}

			//For 5 Stars rating

			$conds_five_star_rating['rating'] = 5;
			$conds_five_star_rating['to_user_id'] = $obj->id;
			$five_star_count = $this->CI->Rate->count_all_by($conds_five_star_rating);
			if($total_rating_count > 0) {
				$five_star_percent = number_format((float) ((100 / $total_rating_count) * $five_star_count), 1, '.', '');
			} else {
				$five_star_percent = 0;
			}

			//For 4 Stars rating
			$conds_four_star_rating['rating'] = 4;
			$conds_four_star_rating['to_user_id'] = $obj->id;
			$four_star_count = $this->CI->Rate->count_all_by($conds_four_star_rating);
			if($total_rating_count > 0) {
				$four_star_percent = number_format((float) ((100 / $total_rating_count) * $four_star_count), 1, '.', '');
			} else {
				$four_star_percent = 0;
			}


			//For 3 Stars rating
			$conds_three_star_rating['rating'] = 3;
			$conds_three_star_rating['to_user_id'] = $obj->id;
			$three_star_count = $this->CI->Rate->count_all_by($conds_three_star_rating);
			if($total_rating_count > 0) {
				$three_star_percent = number_format((float) ((100 / $total_rating_count) * $three_star_count), 1, '.', '');
			} else {
				$three_star_percent = 0;
			}


			//For 2 Stars rating
			$conds_two_star_rating['rating'] = 2;
			$conds_two_star_rating['to_user_id'] = $obj->id;
			$two_star_count = $this->CI->Rate->count_all_by($conds_two_star_rating);

			if($total_rating_count > 0) {
				$two_star_percent = number_format((float) ((100 / $total_rating_count) * $two_star_count), 1, '.', '');
			} else {
				$two_star_percent = 0;
			}

			//For 1 Stars rating
			$conds_one_star_rating['rating'] = 1;
			$conds_one_star_rating['to_user_id'] = $obj->id;
			$one_star_count = $this->CI->Rate->count_all_by($conds_one_star_rating);

			if($total_rating_count > 0) {
			$one_star_percent = number_format((float) ((100 / $total_rating_count) * $one_star_count), 1, '.', '');
			} else {
				$one_star_percent = 0;
			}


			$rating_std = new stdClass();
			@$rating_std->five_star_count = $five_star_count; 
			$rating_std->five_star_percent = $five_star_percent;

			$rating_std->four_star_count = $four_star_count;
			$rating_std->four_star_percent = $four_star_percent;

			$rating_std->three_star_count = $three_star_count;
			$rating_std->three_star_percent = $three_star_percent;

			$rating_std->two_star_count = $two_star_count;
			$rating_std->two_star_percent = $two_star_percent;

			$rating_std->one_star_count = $one_star_count;
			$rating_std->one_star_percent = $one_star_percent;

			$rating_std->total_rating_count = $total_rating_count;
			$rating_std->total_rating_value = $total_rating_value;


			$obj[$i]->rating_details = $rating_std;	

		}


	}
			

}