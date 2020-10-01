<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * PanaceaSoft Database Trigger
 */
class PS_Delete {

	// codeigniter instance
	protected $CI;

	/**
	 * Constructor
	 */
	function __construct()
	{
		// get CI instance
		$this->CI =& get_instance();

		// load image library
		$this->CI->load->library( 'PS_Image' );
	}

	/**
	 * Delete the category and image under the category
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_manufacturer( $manufacturer_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Manufacturer->delete( $manufacturer_id )) {
		// if there is an error in deleting category,
			
			return false;
		}

		// prepare condition
		$conds = array( 'img_type' => 'manufacturer', 'img_parent_id' => $manufacturer_id );

		if ( $this->CI->delete_images_by( $conds )) {
			$conds = array( 'img_type' => 'manufacturer-icon', 'img_parent_id' => $manufacturer_id );

			if ( !$this->CI->delete_images_by( $conds )) {
			// if error in deleting image, 

				return false;
			}
		}

		if ( $enable_trigger ) {
		// if execute_trigger is enable, trigger to delete wallpaper related data
			if ( ! $this->delete_manufacturer_trigger( $manufacturer_id )) {
			// if error in deleteing wallpaper and wallpaper related data

				return false;
			}
		}

		return true;
	}

	function delete_banner( $ban_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Banner->delete( $ban_id )) {
		// if there is an error in deleting category,
			
			return false;
		}

		// prepare condition
		$conds = array( 'img_type' => 'banner', 'img_parent_id' => $ban_id );

		if ( ! $this->CI->delete_images_by( $conds )) {
		
			return false;

		}

		return true;
	}

		/**
	 * Delete the wallpaper and image under the wallpaper
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_item( $id, $enable_trigger = false )
	{
		if ( ! $this->CI->Item->delete( $id )) {
		// if there is an error in deleting wallpaper,
			
			return false;
		}  
		
		// prepare condition
		$conds = array( 'img_type' => 'item', 'img_parent_id' => $id );

		if ( !$this->CI->delete_images_by( $conds )) {
		// if error in deleting image, 

			return false;
		}
	

		if ( $enable_trigger ) {
		// if execute_trigger is enable, trigger to delete wallpaper related data

			if ( !$this->delete_item_trigger( $id )) {
			// if error in deleting wallpaper related data,

				return false;
			}
			
		}

		return true;
	}
	

	/**
	 * Delete the color and image under the color
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_color( $color_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Color->delete( $color_id )) {
		// if there is an error in deleting color,
			
			return false;
		}

		return true;
	}

	/**
	 * Delete the fuel type and image under the fuel type
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_fuel( $fuel_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Fueltype->delete( $fuel_id )) {
		// if there is an error in deleting fuel type,
			
			return false;
		}

		return true;
	}

	/**
	 * Delete the seller type and image under the seller type
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_seller( $seller_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Sellertype->delete( $seller_id )) {
		// if there is an error in deleting seller type,
			
			return false;
		}

		return true;
	}

	/**
	 * Delete the transmission and image under the transmission
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_transmission( $trans_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Transmission->delete( $trans_id )) {
		// if there is an error in deleting transmission,
			
			return false;
		}

		return true;
	}


	/**
	 * Delete the build type and image under the build type
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_buildtype( $build_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Buildtype->delete( $build_id )) {
		// if there is an error in deleting build type,
			
			return false;
		}

		return true;
	}

	/**
	 * Delete the category and image under the category
	 *
	 * @param      <type>  $id     The identifier
	 */
	// when user delete from backend all the related table delete


	function delete_user( $user_id ) {


    	$conds_user['user_id'] = $user_id;
		$conds_from_user['from_user_id'] = $user_id;
		$conds_to_user['to_user_id'] = $user_id;
		$conds_added_user['added_user_id'] = $user_id;
		$conds_followed_user['followed_user_id'] = $user_id;
		$conds_buyer_user['buyer_user_id'] = $user_id;
		$conds_seller_user['seller_user_id'] = $user_id;


		//delete User
		if ( !$this->CI->User->delete_by( $conds_user )) {

			return false;
		}

		// delete Rating
		$this->CI->Rate->delete_by( $conds_from_user );
		$this->CI->Rate->delete_by( $conds_to_user );

		// delete push notification users
		if ( !$this->CI->Notireaduser->delete_by( $conds_user )) {

			return false;
		}

		// delete push notification tokens
		if ( !$this->CI->Noti->delete_by( $conds_user )) {

			return false;
		}

		// delete items and others related with item

		$items_data 	= $this->CI->Item->get_one_by($conds_added_user);

		$item_data['item_id'] = $items_data->id;
        $img_data['img_parent_id'] = $items_data->id;

        $this->CI->Chat->delete_by( $item_data );
        $this->CI->Favourite->delete_by( $item_data );
        $this->CI->Itemreport->delete_by( $item_data );
        $this->CI->Touch->delete_by( $item_data );
        $this->CI->Image->delete_by( $img_data );

		if ( !$this->CI->Item->delete_by( $conds_added_user )) {

			return false;
		}

		//delete follows

		$following_user		= $this->CI->Userfollow->get_all_by( $conds_user )->result();
		$follower_user		= $this->CI->Userfollow->get_all_by( $conds_followed_user )->result();

		foreach ($following_user as $following) {

			$conds_follower['user_id'] = $following->followed_user_id;

			$follower_user_data = $this->CI->User->get_one_by( $conds_follower );
			
			$follower_count = $follower_user_data->follower_count;

			$user_data = array(
			 	"follower_count" => $follower_count - 1
			 );

			 $this->CI->User->save($user_data,$follower_user_data->user_id);
			

		}


		foreach ($follower_user as $follower) {

			$conds_follow['user_id'] = $follower->user_id;

			$following_user_data = $this->CI->User->get_one_by( $conds_follow );

			$following_count = $following_user_data->following_count;

			$user_data = array(
			 	"following_count" => $following_count - 1
			 );

			 $this->CI->User->save($user_data,$following_user_data->user_id);

		}
		
		$this->CI->Userfollow->delete_by( $conds_user );
		$this->CI->Userfollow->delete_by( $conds_followed_user );
		

		// delete Favourite
		if ( !$this->CI->Favourite->delete_by( $conds_user )) {

			return false;
		}

		// delete Chat History

		$this->CI->Chat->delete_by( $conds_buyer_user );
		$this->CI->Chat->delete_by( $conds_seller_user ); 

		return true;
			

	}

	/**
	 * Trigger to delete wallpaper and related data when category is deleted
	 * delete wallpaper
	 * delete wallpaper images
	 */
	function delete_manufacturer_trigger( $manufacturer_id )
	{
		
		return true;
	}

	/**
	 * Trigger to delete item and related data when category is deleted
	 * delete item
	 * delete item images
	 */
	function delete_item_trigger( $item_id )
	{
		$conds_item['item_id'] = $item_id;
		$conds_id['id'] = $item_id;

		// delete Item
		if ( !$this->CI->Item->delete_by( $conds_id )) {

			return false;
		}

		
		// delete chat history
		if ( !$this->CI->Chat->delete_by( $conds_item )) {

			return false;
		}

		// delete favourite
		if ( !$this->CI->Favourite->delete_by( $conds_item )) {

			return false;
		}

		// delete item reports
		if ( !$this->CI->Itemreport->delete_by( $conds_item )) {

			return false;
		}

		// delete touches
		if ( !$this->CI->Touch->delete_by( $conds_item )) {

			return false;
		}

		return true;

	}

	/**
	 * Delete history for API
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_history( $type_id, $type_name, $enable_trigger = false )
	{		

		
		if( $type_name == "model") {


			if ( ! $this->CI->Model->delete( $type_id )) {
			// if there is an error in deleting product,
				
				return false;
			} else {
				//product is successfully deleted so need to save in log table
				$data_delete['type_id']   = $type_id;
				$data_delete['type_name'] = $type_name;

				$this->CI->Delete_history->save($data_delete);
			}

		} else if ( $type_name == "item" ) {

			if ( ! $this->CI->Item->delete( $type_id )) {
			// if there is an error in deleting product,
				
				return false;
			} else {
				//product is successfully deleted so need to save in log table
				$data_delete['type_id']   = $type_id;
				$data_delete['type_name'] = $type_name;

				$this->CI->Delete_history->save($data_delete);
			}
		}


		// prepare condition
		if($type_name == "model") {
		
			$conds = array( 'img_type' => 'model', 'img_parent_id' => $type_id );
			if ( $this->CI->delete_images_by( $conds )) {
			$conds = array( 'img_type' => 'model-icon', 'img_parent_id' => $type_id );
			// if error in deleting image,
				if ( !$this->CI->delete_images_by( $conds )) {
				
				// if error in deleting image, 

					return false;
				
				} 
			}
		
		} else if($type_name == "item") {

			$conds = array( 'img_type' => 'item', 'img_parent_id' => $type_id );
			if ( !$this->CI->Image->delete_by( $conds )) {
				return false;
			}

		}

		
		if ( $enable_trigger ) {
		// if execute_trigger is enable, trigger to delete wallpaper related data
			if( $type_name == "model" ) {
				if ( !$this->delete_model_trigger( $type_id )) {
				// if error in deleting wallpaper related data,

					return false;
				}
			} else if( $type_name == "item" ) {

					if ( !$this->delete_item_trigger( $type_id )) {
					// if error in deleting wallpaper related data,
						return false;
					}

				}
				
			}

		return true;
	}

	/**
	 * Delete Image by id and type
	 *
	 * @param      <type>  $conds  The conds
	 */
	function delete_images_by( $conds )
	{
		// get all images
		$images = $this->CI->Image->get_all_by( $conds );

		if ( !empty( $images )) {
		// if images are not empty,

			foreach ( $images->result() as $img ) {
			// loop and delete each image

				if ( ! $this->CI->ps_image->delete_images( $img->img_path ) ) {
				// if there is an error in deleting images

					return false;
				}
			}
		}

		if ( ! $this->CI->Image->delete_by( $conds )) {
		// if error in deleting from database,

			return false;
		}

		return true;
	}

	function delete_model_trigger( $model_id )
	{
		// get all product and delete the wallpaper under the subcategory
		$items = $this->CI->Item->get_all_by( array( 'model_id' => $model_id, 'no_publish_filter' => 1 ))->result();
		if ( !empty( $items )) {
		// if the wallpaper list not empty
			
			// loop all the wallpaper
			foreach ( $items as $item ) {
				// delete wallpaper and images
				$enable_trigger = true;

				if ( !$this->delete_item( $item->id, $enable_trigger )) {
				// if error in deleting wallpaper,

					return false;
				} 
			}
		}
		return true;
	}

	/**
	 * Delete the currency record
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_currency( $currency_id )
	{		
		if ( ! $this->CI->Currency->delete( $currency_id )) {
		// if there is an error in deleting currency,
			
			return false;
		}


		return true;
	}

	/**
	 * Delete the price record
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_price( $price_id )
	{		
		if ( ! $this->CI->Pricetype->delete( $price_id )) {
		// if there is an error in deleting currency,
			
			return false;
		}


		return true;
	}

	/**
	 * Delete the type record
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_type( $type_id )
	{		
		if ( ! $this->CI->Itemtype->delete( $type_id )) {
		// if there is an error in deleting currency,
			
			return false;
		}


		return true;
	}

	/**
	 * Delete the type record
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_location( $location_id )
	{		
		if ( ! $this->CI->Itemlocation->delete( $location_id )) {
		// if there is an error in deleting currency,
			
			return false;
		}


		return true;
	}

	/**
	 * Delete the Discount and image under the Product
	 *
	 * @param      <type>  $id     The identifier
	*/
	function delete_feed( $feed_id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Feed->delete( $feed_id )) {
		// if there is an error in deleting Product,
			
			return false;
		}

		// prepare condition
		$conds = array( 'img_type' => 'feed', 'img_parent_id' => $feed_id );

		if ( !$this->CI->delete_images_by( $conds )) {
			
			return false;
			
		}
		return true;
	}

	/**
	* Delete All Chat History
	*/

	function delete_chat_history()
	{

		if ( ! $this->CI->Chat->delete_all()) {
			return false;
		}

		return true;

	}

	/**
	 * Delete the notification and image under the notification
	 *
	 * @param      <type>  $id     The identifier
	 */
	function delete_noti( $id, $enable_trigger = false )
	{		
		if ( ! $this->CI->Noti_message->delete( $id )) {
		// if there is an error in deleting notification,
			
			return false;
		}

		// prepare condition
		$conds = array( 'img_type' => 'noti', 'img_parent_id' => $id );

		if ( !$this->CI->delete_images_by( $conds )) {
		// if error in deleting image, 

			return false;
		}

		if ( $enable_trigger ) {
		// if execute_trigger is enable, trigger to delete wallpaper related data

			if ( ! $this->delete_noti_trigger( $id )) {
			// if error in deleteing wallpaper and wallpaper related data

				return false;
			}
		}

		return true;
	}

	// delete condition

	
	function delete_condition( $condition_id )
	{		
		if ( ! $this->CI->Condition->delete( $condition_id )) {
		// if there is an error in deleting condition,
			
			return false;
		}


		return true;
	}

	


}