<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Read More
 *
 * @param      string  $string   string
 * @param      integer  $limit   character limit
 *
 * @return     string   ( description_of_the_return_value )
 */
if ( !function_exists( 'read_more' )) 
{
	function read_more( $string, $limit )
	{
		$string = strip_tags($string);
		
		if (strlen($string) > $limit) {
		
		    // truncate string
		    $stringCut = substr($string, 0, $limit);
		
		    // make sure it ends in a word so assassinate doesn't become ass...
		    $string = substr($stringCut, 0, strrpos($stringCut, ' ')).'...'; 
		}
		return $string;
	}
}

/**
 * transform 'added date' display
 *
 * @param      integer  $time   The time
 *
 * @return     string   ( description_of_the_return_value )
 */
if ( ! function_exists( 'ago' ))
{
	function ago( $time )
	{
		if ( empty( $time )) return "Today";

		// get ci instance
		$CI =& get_instance();
		
		$time = mysql_to_unix( $time );
		$now = $CI->db->query('SELECT NOW( ) as now')->row()->now;
		$now = mysql_to_unix( $now );

		$periods = array("second", "minute", "hour", "day", "week", "month", "year", "decade");
		$lengths = array("60","60","24","7","4.35","12","10");

		$difference = $now - $time;
		$tense = "ago";

		for ($j = 0; $difference >= $lengths[$j] && $j < count($lengths)-1; $j++) {
			$difference /= $lengths[$j];
		}

		$difference = round($difference);

		if ($difference != 1) {
			$periods[$j].= "s";
		}

		if ($difference==0) {
			return "Just Now";
		} else {
			return "$difference $periods[$j] ago";
		}
	}
}

/**
 * return the message
 *
 * @param      <type>  $key    The key
 */
if ( ! function_exists( 'get_msg' ))
{
	function get_msg( $key )
	{
		// get ci instance
		$CI =& get_instance();

		// load the language
		$message = $CI->lang->line( $key );

		if ( empty( $message )) {
		// if message is empty, return the key
			return $key;
		}

		// return the message
		return $message;
	}
}

/**
 * Show the flash message
 */
if ( ! function_exists( 'flash_msg')) 
{
	function flash_msg()
	{
		// get ci instance
		$CI =& get_instance();

		$CI->load->view( 'common/flash_msg' );
	}
}

/**
 * Shows the analytic.
 */
if ( ! function_exists( 'show_analytic' ))
{
	function show_analytic()
	{
		// get ci instance
		$CI =& get_instance();

		$CI->load->view( 'ps/analytic' );
	}
}

/**
 * Shows the ads.
 */
if ( ! function_exists( 'show_ads' ))
{
	function show_ads()
	{
		// get ci instance
		$CI =& get_instance();

		$CI->load->view( 'ps/ads' );
	}
}

/**
 * Shows the breadcrumb.
 *
 * @param      <type>  $urls   The urls
 */
if ( ! function_exists( 'show_breadcrumb' )) 
{
	function show_breadcrumb( $urls = array() )
	{
		// get ci instance
		$CI =& get_instance();

		$template_path = $CI->config->item( 'be_view_path' );

		// load breadcrumb
		$CI->load->view( $template_path .'/partials/breadcrumb', array( 'urls' => $urls )); 
	}
}

/**
 * Shows the data.
 *
 * @param      <type>  $string  The string
 */
if ( ! function_exists( 'show_data' )) 
{
	function show_data( $string )
	{
		// get ci instance
		$CI =& get_instance();
		$CI->load->library( 'PS_Security' );

		return $CI->ps_security->clean_output( $string );
	}
}

/**
 * Determines if view exists.
 *
 * @param      <type>   $path   The path
 *
 * @return     boolean  True if view exists, False otherwise.
 */
if ( ! function_exists( 'is_view_exists' )) 
{
	function is_view_exists( $path )
	{
		return file_exists( APPPATH .'views/'. $path .'.php' );
	}
}

/**
 * Gets the dummy photo.
 *
 * @return     <type>  The dummy photo.
 */
if ( ! function_exists( 'get_dummy_photo' )) 
{
	function get_dummy_photo()
	{
		return "default_news.jpeg";
	}
}

/**
 * Gets the configuration.
 *
 * @param      <type>  $key    The key
 *
 * @return     <type>  The configuration.
 */
if ( ! function_exists( 'get_app_config' )) 
{
	function get_app_config( $key )
	{
		// get ci instance
		$CI =& get_instance();

		$CI->load->model( 'About' );
		$abt = $CI->About->get_one( 'abt1' );

		if ( isset( $abt->{$key} )) {
			return $abt->{$key};
		}

		return false;
	}
}

/**
 * Image URL Path
 *
 * @param      <type>  $path   The path
 *
 * @return     <type>  ( description_of_the_return_value )
 */
if ( ! function_exists( 'img_url' ))
{
	function img_url( $path = false )
	{
		return base_url( '/uploads/'. $path );
	}
}

/**
 * Gets the default photo.
 *
 * @param      <type>  $id     The identifier
 * @param      <type>  $type   The type
 */
if ( ! function_exists( 'get_default_photo' ))
{
	function get_default_photo( $id, $type )
	{
		$default_photo = "";

		// get ci instance
		$CI =& get_instance();

		// get all images
		$img = $CI->Image->get_all_by( array( 'img_parent_id' => $id, 'img_type' => $type ))->result();

		if ( count( $img ) > 0 ) {
		// if there are images for news,
			
			$default_photo = $img[0];
		} else {
		// if no image, return empty object

			$default_photo = $CI->Image->get_empty_object();
		}

		return $default_photo;
	}
}


/**
 * Gets the generate_random_string
 *
 * @param      <type>  $id     The identifier
 * @param      <type>  $type   The type
 */
if ( ! function_exists( 'generate_random_string' ))
{
	function generate_random_string($length = 5) {
	    $characters = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	    $charactersLength = strlen($characters);
	    $randomString = '';
	    for ($i = 0; $i < $length; $i++) {
	        $randomString .= $characters[rand(0, $charactersLength - 1)];
	    }
	    return $randomString;
	}
}


/**
* Sending Message From FCM For Android
*/

if ( ! function_exists( 'send_android_fcm_chat' ))
{
	function send_android_fcm_chat( $registatoin_ids, $data) 
    {

    	// get ci instance
		$CI =& get_instance();
		
    	//print_r($registatoin_ids); die;
    	// print_r($data); die;
    	$message = $data['message'];
		$buyer_id = $data['buyer_user_id'];
		$seller_id = $data['seller_user_id'];
		$sender_name = $data['sender_name'];
		$item_id = $data['item_id'];
		$sender_profle_photo = $data['sender_profle_photo'];
    	//Google cloud messaging GCM-API url
    	$url = 'https://fcm.googleapis.com/fcm/send';
    	
    	$fields = array(
    	    'registration_ids' => $registatoin_ids,
    	    'data' => array(
    	    	'message' => $message,
    	    	'buyer_id' => $buyer_id,
    	    	'seller_id' => $seller_id,
    	    	'item_id' => $item_id,
    	    	'sender_name' => $sender_name,
    	    	'sender_profle_photo' => $sender_profle_photo,
    	    	'action' => "abc"
    	    )

    	);

    	

    	// Update your Google Cloud Messaging API Key
    	//define("GOOGLE_API_KEY", "AIzaSyAzKBPuzGuR0nlvY0AxPrXsEMBuRUxO4WE");
    	define("GOOGLE_API_KEY", $CI->config->item( 'fcm_api_key' ));
    	//define("GOOGLE_API_KEY", $this->config->item( 'fcm_api_key' ));  	
    	
    	//print_r(GOOGLE_API_KEY); die;
    	//print_r($fields); die;
    	$headers = array(
    	    'Authorization: key=' . GOOGLE_API_KEY,
    	    'Content-Type: application/json'
    	);
    	$ch = curl_init();
    	curl_setopt($ch, CURLOPT_URL, $url);
    	curl_setopt($ch, CURLOPT_POST, true);
    	curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    	curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);	
    	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    	curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
    	$result = curl_exec($ch);				
    	if ($result === FALSE) {
    	    die('Curl failed: ' . curl_error($ch));
    	}
    	curl_close($ch);

    	return $result;
    }
}



