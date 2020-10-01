<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Empty Class
 */
class My_Model {}

/**
 * PanaceaSoft Base Model
 */
class PS_Model extends CI_Model {
	
	// name of the database table
	protected $table_name;

	// name of the ID field
	public $primary_key;

	// name of the key prefix
	protected $key_prefix;

	/**
	 * constructs required data
	 */
	function __construct( $table_name, $primary_key = false, $key_prefix = false )
	{
		parent::__construct();

		// set the table name
		$this->table_name = $table_name;
		$this->primary_key = $primary_key;
		$this->key_prefix = $key_prefix;
	}

	/**
	 * Empty class to be extended
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array()) {

	}

	/**
	 * Generate the TeamPS Unique Key
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function generate_key()
	{
		return $this->key_prefix . md5( $this->key_prefix . microtime() . uniqid() . 'teamps' );
	}

    /**
     * Determines if exist.
     *
     * @param      <type>   $id     The identifier
     *
     * @return     boolean  True if exist, False otherwise.
     */
    function is_exist( $id ) {
    	
    	// from table
    	$this->db->from( $this->table_name );

    	// where clause
		$this->db->where( $this->primary_key, $id );
		
		// get query
		$query = $this->db->get();

		// return the result
		return ($query->num_rows()==1);
    }

    /**
     * Save the data if id is not existed
     *
     * @param      <type>   $data   The data
     * @param      boolean  $id     The identifier
     */
	function save( &$data, $id = false ) {
		
		if ( !$id ) {
		// if id is not false and id is not yet existed,
			if ( !empty( $this->primary_key ) && !empty( $this->key_prefix )) {
			// if the primary key and key prefix is existed,
			
				// generate the unique key
				$data[ $this->primary_key ] = $this->generate_key();
			}

			// insert the data as new record
			return $this->db->insert( $this->table_name, $data );

			// print_r($this->db->last_query());die;
		} else {
			// else
			// where clause
			$this->db->where( $this->primary_key, $id);

			// update the data
			return $this->db->update($this->table_name,$data);
			//print_r($this->db->last_query());die;
		}
	}

	/**
	 * Returns all the records
	 *
	 * @param      boolean  $limit   The limit
	 * @param      boolean  $offset  The offset
	 */
	function get_all( $limit = false, $offset = false ) {

		// where clause
		$this->custom_conds();

		// from table
		$this->db->from($this->table_name);


		if ( $limit ) {
		// if there is limit, set the limit
			
			$this->db->limit($limit);
		}
		
		if ( $offset ) {
		// if there is offset, set the offset,
			
			$this->db->offset($offset);
		}
		return $this->db->get();
		//print_r($this->db->last_query());die;
	}

	/**
	 * Returns the total count
	 */
	function count_all() {
		// from table
		$this->db->from( $this->table_name );

		// where clause
		$this->custom_conds();

		// return the count all results
		return $this->db->count_all_results();
		// print_r($this->db->last_query());die;
	}

	/**
	 * Return the info by Id
	 *
	 * @param      <type>  $id     The identifier
	 */
	function get_one( $id ) {
		// query the record
		$query = $this->db->get_where( $this->table_name, array( $this->primary_key => $id ));
		if ( $query->num_rows() == 1 ) {
		// if there is one row, return the record
			
			return $query->row();
		} else {
		// if there is no row or more than one, return the empty object
			
			return $this->get_empty_object( $this->table_name );
		}
	}

	/**
	 * Returns the multiple Info by Id
	 *
	 * @param      array  $ids    The identifiers
	 */
	function get_multi_info( $ids = array()) {
		
		// from table
		$this->db->from( $this->table_name );

		// where clause
		$this->db->where_in( $this->primary_key, $ids );

		// returns
		return $this->db->get();
	}

	/**
	 * Delete the records by Id
	 *
	 * @param      <type>  $id     The identifier
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function delete( $id )
	{
		// where clause
		$this->db->where( $this->primary_key, $id );

		// delete the record
		return $this->db->delete( $this->table_name );
 	}

 	/**
 	 * Delete the records by ids
 	 *
 	 * @param      array   $ids    The identifiers
 	 *
 	 * @return     <type>  ( description_of_the_return_value )
 	 */
 	function delete_list( $ids = array()) {
 		
 		// where clause
		$this->db->where_in( $this->primary_key, $id );

		// delete the record
		return $this->db->delete( $this->table_name );
 	}

	/**
	 * returns the object with the properties of the table
	 *
	 * @return     stdClass  The empty object.
	 */
    function get_empty_object()
    {   
        $obj = new stdClass();
        
        $fields = $this->db->list_fields( $this->table_name );
        foreach ( $fields as $field ) {
            $obj->$field = '';
        }
        $obj->is_empty_object = true;
        return $obj;
    }

   	/**
   	 * Execute The query
   	 *
   	 * @param      <type>   $sql     The sql
   	 * @param      <type>   $params  The parameters
   	 *
   	 * @return     boolean  ( description_of_the_return_value )
   	 */
	function exec_sql( $sql, $params = false )
	{
		if ( $params ) {
		// if the parameter is not false

			// bind the parameter and run the query
			return $this->db->query( $sql, $params );	
		}

		// if there is no parameter,
		return $this->db->query( $sql );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function conditions( $conds = array())
	{
		// if condition is empty, return true
		if ( empty( $conds )) return true;
	}

	/**
	 * Check if the key is existed,
	 *
	 * @param      array   $conds  The conds
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function exists( $conds = array()) {

		// where clause
		$this->custom_conds( $conds );
		
		// from table
		$this->db->from( $this->table_name );

		// get query
		$query = $this->db->get();

		// return the result
		return ($query->num_rows() == 1);
		// print_r($this->db->last_query());die;
	}

	/**
	 * Gets all by the conditions
	 *
	 * @param      array    $conds   The conds
	 * @param      boolean  $limit   The limit
	 * @param      boolean  $offset  The offset
	 *
	 * @return     <type>   All by.
	 */
	function get_all_by( $conds = array(), $limit = false, $offset = false ) {

		if($conds['lat'] != "" && $conds['lng'] != "") {
			$this->db->select('*,( 3959
		      * acos( cos( radians('. $conds['lat'] .') )
		              * cos(  radians( lat )   )
		              * cos(  radians( lng ) - radians('. $conds['lng'] .') )
		            + sin( radians('. $conds['lat'] .') )
		              * sin( radians( lat ) )
		            )
		    ) as distance');

		    if ($conds['miles'] == "") {
		    	$conds['miles'] = 0;
		    	$this->db->having('distance < ' .  $conds['miles'] );
		    } else {
		    	$this->db->having('distance < ' .  $conds['miles'] );

		    }

		   
		}

		// where clause
		$this->custom_conds( $conds );

		// from table
		$this->db->from( $this->table_name );

		if ( $limit ) {
		// if there is limit, set the limit
			
			$this->db->limit($limit);
		}
		
		if ( $offset ) {
		// if there is offset, set the offset,
			
			$this->db->offset($offset);
		}
		
	 	return $this->db->get();
		// print_r($this->db->last_query());die;
	
	}

	
	/**
	 * Counts the number of all by the conditions
	 *
	 * @param      array   $conds  The conds
	 *
	 * @return     <type>  Number of all by.
	 */
	function count_all_by( $conds = array()) {

		// where clause
		$this->custom_conds( $conds );
		
		// from table
		$this->db->from( $this->table_name );

		// return the count all results
		return $this->db->count_all_results();
		 //print_r($this->db->last_query());die;
	}

	/**
	 * Sum the number of all by the conditions
	 *
	 * @param      array   $conds  The conds
	 *
	 * @return     <type>  Number of all by.
	 */
	function sum_all_by( $conds = array()) {
		
		// where clause
		$this->custom_conds( $conds );
		
		$this->db->select_sum('rating');
		// from table
		$this->db->from( $this->table_name );

		// return the count all results
		//return $this->db->count_all_results();
		return $this->db->get();
	}

	/**
	 * Gets the information by.
	 *
	 * @param      array   $conds  The conds
	 *
	 * @return     <type>  The information by.
	 */
	function get_one_by( $conds = array()) {
		
		// where clause
		$this->custom_conds( $conds );
		// query the record
		$query = $this->db->get( $this->table_name );

		if ( $query->num_rows() == 1 ) {
		// if there is one row, return the record
			return $query->row();
		} else {
		// if there is no row or more than one, return the empty object
			 return $this->get_empty_object( $this->table_name );
			
		}

	}


	/**
	 * Delete the records by condition
	 *
	 * @param      array   $conds  The conds
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function delete_by( $conds = array() )
	{
		// where clause
		$this->custom_conds( $conds );

		// delete the record
		return $this->db->delete( $this->table_name );
		 //print_r($this->db->last_query());die;
 	}

 	/**
	 * Delete the records
	 */
	function delete_all()
	{
		// where clause
		//$this->custom_conds( $conds );
		// delete the records
		//$this->db->delete( $this->table_name );
		//print_r($this->db->last_query());die;

		return $this->db->query('Delete from bs_chat_history');
 	}



 	/**
	 * Delete the records by condition
	 *
	 * @param      array   $conds  The conds
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function get_rating_count( $conds = array() )
	{

		$this->db->select(' sum(rating) as t_count'); 
		$this->db->from('bs_rating');

		if(isset($conds['wallpaper_id'])) {

			if ($conds['wallpaper_id'] != "" || $conds['wallpaper_id'] != 0) {
					
					$this->db->where( 'wallpaper_id', $conds['wallpaper_id'] );	

			}

		}

	
	 	return $this->db->get();
		// print_r($this->db->last_query());die;
 	}

 

 	function get_total_earning_point($conds = array()) {

 		$this->db->select(' sum(earn_point) as total'); 
		$this->db->from('bs_earning_points');

		if(isset($conds['user_id'])) {

			if ($conds['user_id'] != "" || $conds['user_id'] != 0) {
					
					$this->db->where( 'user_id', $conds['user_id'] );	

			}

		}

	 	return $this->db->get();
		// print_r($this->db->last_query());die;
 	}


 	/** get popular item */

 	function get_item_count( $conds = array() )
	{
		$this->db->select('bs_items.*, count(bs_touches.touch_id) as t_count,bs_items.title'); 
		$this->db->from('bs_touches');
		$this->db->join('bs_items', 'bs_touches.item_id = bs_items.id');
	
		$this->db->limit(5);

		if(isset($conds['item_id'])) {

			if ($conds['item_id'] != "" || $conds['item_id'] != 0) {
					
					$this->db->where( 'item_id', $conds['item_id'] );	

			}

		}
		$this->db->group_by("bs_touches.item_id");
		$this->db->order_by("count(DISTINCT bs_touches.touch_id)", "DESC");
		return $this->db->get();
	
 	}

 	/** get reported item */

 	function get_item_report($conds = array())
	{
		//print_r("asdfasd" .$conds);die;
		$this->db->select('bs_items_report.*');    
  		$this->db->from('bs_items_report');
  		$this->db->where('month(added_date)',$conds['added_date']);

		return $this->db->get();
		 // print_r($this->db->last_query());die;
	}

 /**
  * Gets all by the conditions
  *
  * @param      array    $conds   The conds
  * @param      boolean  $limit   The limit
  * @param      boolean  $offset  The offset
  *
  * @return     <type>   All by.
  */
	function get_all_device_in( $conds = array(), $limit = false, $offset = false ) {

	  // where clause
	  $this->db->where_in('user_id', $conds);

	  // from table
	  $this->db->from( $this->table_name );

	  if ( $limit ) {
	  // if there is limit, set the limit
	   
	   $this->db->limit($limit);
	  }
	  
	  if ( $offset ) {
	  // if there is offset, set the offset,
	   
	   $this->db->offset($offset);
	  }
	  
	  return $this->db->get();

	 }

	 
 	/**
	Returns Item list by followuser
	*/
	function get_all_item_by_followuser( $conds = array() , $limit = false, $offset = false)
	{
		//print_r($limit . "$offset");die;
		$sql = "SELECT *  FROM bs_items WHERE  added_user_id in (". $conds['followuser'] ." ) LIMIT $limit OFFSET $offset";
		//print_r($sql);die;
		$query = $this->db->query($sql);

		return $query;
		  //print_r($this->db->last_query());die;
		
	}

	/**
	Returns popular Items count
	*/
	function count_item_by($conds = array()){
		$this->custom_conds();
		//where clause
		$this->db->select('bs_items.*, count(bs_touches.item_id) as t_count');    
  		$this->db->from('bs_items');
  		$this->db->join('bs_touches', 'bs_items.id = bs_touches.item_id');

		if ( isset( $conds['cat_id'] )) {
			if ($conds['cat_id'] != "" ) {
				if ($conds['cat_id'] != '0') {
					$this->db->where( 'bs_items.cat_id', $conds['cat_id'] );	
				} 
				
			}
		}

		//  sub category id condition 
		if ( isset( $conds['sub_cat_id'] )) {
			if ($conds['sub_cat_id'] != "" ) {
				if ($conds['sub_cat_id'] != '0') {
					$this->db->where( 'bs_items.sub_cat_id', $conds['sub_cat_id'] );
				}
				
			}
			
		}

		//  item type id condition 
		if ( isset( $conds['item_type_id'] )) {
			if ($conds['item_type_id'] != "" ) {
				if ($conds['item_type_id'] != '0') {
					$this->db->where( 'bs_items.item_type_id', $conds['item_type_id'] );
				}
				
			}
			
		}

		//  item price type id condition 
		if ( isset( $conds['item_price_type_id'] )) {
			if ($conds['item_price_type_id'] != "" ) {
				if ($conds['item_price_type_id'] != '0') {
					$this->db->where( 'bs_items.item_price_type_id', $conds['item_price_type_id'] );
				}
				
			}
			
		}

		//  item currency id condition 
		if ( isset( $conds['item_currency_id'] )) {
			if ($conds['item_currency_id'] != "" ) {
				if ($conds['item_currency_id'] != '0') {
					$this->db->where( 'bs_items.item_currency_id', $conds['item_currency_id'] );
				}
				
			}
			
		}

		//  status condition 
		if ( isset( $conds['status'] )) {
			if ($conds['status'] != "" ) {
				if ($conds['status'] != '0') {
					$this->db->where( 'bs_items.status', $conds['status'] );
				}
				
			}
			
		}

		if ( isset( $conds['searchterm'] ) || isset( $conds['date'] )) {
			$dates = $conds['date'];

			if ($dates != "") {
				$vardate = explode('-',$dates,2);

				$temp_mindate = $vardate[0];
				$temp_maxdate = $vardate[1];		

				$temp_startdate = new DateTime($temp_mindate);
				$mindate = $temp_startdate->format('Y-m-d');

				$temp_enddate = new DateTime($temp_maxdate);
				$maxdate = $temp_enddate->format('Y-m-d');
			} else {
				$mindate = "";
			 	$maxdate = "";
			}
			
			if ($conds['searchterm'] == "" && $mindate != "" && $maxdate != "") {
				//got 2dates
				if ($mindate == $maxdate ) {

					$this->db->where("bs_touches.added_date BETWEEN DATE('".$mindate."') AND DATE('". $maxdate."' + INTERVAL 1 DAY)");

				} else {

					$today_date = date('Y-m-d');
					if($today_date == $maxdate) {
						$current_time = date('H:i:s');
						$maxdate = $maxdate . " ". $current_time;
					}

					$this->db->where( 'date(bs_touches.added_date) >=', $mindate );
   					$this->db->where( 'date(bs_touches.added_date) <=', $maxdate );

				}
				$this->db->like( '(title', $conds['searchterm'] );
				$this->db->or_like( 'title)', $conds['searchterm'] );
			} else if ($conds['searchterm'] != "" && $mindate != "" && $maxdate != "") {
				//got name and 2dates
				if ($mindate == $maxdate ) {

					$this->db->where("bs_touches.added_date BETWEEN DATE('".$mindate."') AND DATE('". $maxdate."' + INTERVAL 1 DAY)");

				} else {

					$today_date = date('Y-m-d');
					if($today_date == $maxdate) {
						$current_time = date('H:i:s');
						$maxdate = $maxdate . " ". $current_time;
					}

					$this->db->where( 'date(bs_touches.added_date) >=', $mindate );
   					$this->db->where( 'date(bs_touches.added_date) <=', $maxdate );

				}
				$this->db->group_start();
				$this->db->like( 'title', $conds['searchterm'] );
				$this->db->or_like( 'title', $conds['searchterm'] );
				$this->db->group_end();
			} else {
				//only name 
				$this->db->group_start();
				$this->db->like( 'title', $conds['searchterm'] );
				$this->db->or_like( 'title', $conds['searchterm'] );
				$this->db->group_end();
				
			}
			 
	    }

  		$this->db->group_by('bs_touches.item_id');
  		$this->db->order_by('t_count', "DESC");
  		$this->db->order_by( 'bs_touches.added_date', "desc" );
  		

  		return $this->db->count_all_results();
	}

	/**
	* Gets popular Items record
	*/
	function get_item_by ( $conds = array(), $limit = false, $offset = false ){


		//where clause
		$this->db->select('bs_items.*, count(bs_touches.item_id) as t_count');    
  		$this->db->from('bs_items');
  		$this->db->join('bs_touches', 'bs_items.id = bs_touches.item_id');

  		if ( isset( $conds['cat_id'] )) {
			if ($conds['cat_id'] != "" ) {
				if ($conds['cat_id'] != '0') {
					$this->db->where( 'bs_items.cat_id', $conds['cat_id'] );	
				} 
				
			}
		}

		//  sub category id condition 
		if ( isset( $conds['sub_cat_id'] )) {
			if ($conds['sub_cat_id'] != "" ) {
				if ($conds['sub_cat_id'] != '0') {
					$this->db->where( 'bs_items.sub_cat_id', $conds['sub_cat_id'] );
				}
				
			}
			
		}

		//  item type id condition 
		if ( isset( $conds['item_type_id'] )) {
			if ($conds['item_type_id'] != "" ) {
				if ($conds['item_type_id'] != '0') {
					$this->db->where( 'bs_items.item_type_id', $conds['item_type_id'] );
				}
				
			}
			
		}

		//  item price type id condition 
		if ( isset( $conds['item_price_type_id'] )) {
			if ($conds['item_price_type_id'] != "" ) {
				if ($conds['item_price_type_id'] != '0') {
					$this->db->where( 'bs_items.item_price_type_id', $conds['item_price_type_id'] );
				}
				
			}
			
		}

		//  item currency id condition 
		if ( isset( $conds['item_currency_id'] )) {
			if ($conds['item_currency_id'] != "" ) {
				if ($conds['item_currency_id'] != '0') {
					$this->db->where( 'bs_items.item_currency_id', $conds['item_currency_id'] );
				}
				
			}
			
		}

		//  status condition 
		if ( isset( $conds['status'] )) {
			if ($conds['status'] != "" ) {
				if ($conds['status'] != '0') {
					$this->db->where( 'bs_items.status', $conds['status'] );
				}
				
			}
			
		}

		if ( isset( $conds['searchterm'] ) || isset( $conds['date'] )) {
			$dates = $conds['date'];

			if ($dates != "") {
				$vardate = explode('-',$dates,2);

				$temp_mindate = $vardate[0];
				$temp_maxdate = $vardate[1];		

				$temp_startdate = new DateTime($temp_mindate);
				$mindate = $temp_startdate->format('Y-m-d');

				$temp_enddate = new DateTime($temp_maxdate);
				$maxdate = $temp_enddate->format('Y-m-d');
			} else {
				$mindate = "";
			 	$maxdate = "";
			}
			
			if ($conds['searchterm'] == "" && $mindate != "" && $maxdate != "") {
				//got 2dates
				if ($mindate == $maxdate ) {

					$this->db->where("bs_touches.added_date BETWEEN DATE('".$mindate."') AND DATE('". $maxdate."' + INTERVAL 1 DAY)");

				} else {

					$today_date = date('Y-m-d');
					if($today_date == $maxdate) {
						$current_time = date('H:i:s');
						$maxdate = $maxdate . " ". $current_time;
					}

					$this->db->where( 'date(bs_touches.added_date) >=', $mindate );
   					$this->db->where( 'date(bs_touches.added_date) <=', $maxdate );

				}
				$this->db->like( '(title', $conds['searchterm'] );
				$this->db->or_like( 'title)', $conds['searchterm'] );
			} else if ($conds['searchterm'] != "" && $mindate != "" && $maxdate != "") {
				//got name and 2dates
				if ($mindate == $maxdate ) {

					$this->db->where("bs_touches.added_date BETWEEN DATE('".$mindate."') AND DATE('". $maxdate."' + INTERVAL 1 DAY)");

				} else {

					$today_date = date('Y-m-d');
					if($today_date == $maxdate) {
						$current_time = date('H:i:s');
						$maxdate = $maxdate . " ". $current_time;
					}

					$this->db->where( 'date(bs_touches.added_date) >=', $mindate );
   					$this->db->where( 'date(bs_touches.added_date) <=', $maxdate );

				}
				$this->db->group_start();
				$this->db->like( 'title', $conds['searchterm'] );
				$this->db->or_like( 'title', $conds['searchterm'] );
				$this->db->group_end();
			} else {
				//only name 
				$this->db->group_start();
				$this->db->like( 'title', $conds['searchterm'] );
				$this->db->or_like( 'title', $conds['searchterm'] );
				$this->db->group_end();
				
			}
			 
	    }

  		$this->db->group_by('bs_touches.item_id');
  		$this->db->order_by('t_count', "DESC");
  		$this->db->order_by( 'bs_touches.added_date', "desc" );
  		
  		


  		if ( $limit ) {
		// if there is limit, set the limit
			
			$this->db->limit($limit);
		}
		
		if ( $offset ) {
		// if there is offset, set the offset,
			
			$this->db->offset($offset);
		}

  		return $this->db->get();
  		  //print_r($this->db->last_query());die;

	}

	//get where in user

	function get_all_in_user( $conds = array() ) {

	  $sql = "SELECT * FROM `core_users` WHERE `user_id` IN(". $conds['chat_user_id'] .")";

		$query = $this->db->query($sql);

		return $query;

	}

	//get where in rating

	function get_all_in_rating( $conds1 = array() ) {

	  $sql = "SELECT * FROM `bs_ratings` WHERE `id` IN(". $conds1['id'] .")";

		$query = $this->db->query($sql);

		return $query;

	}	

	function get_all_in_chat( $conds = array() ) {

	  	$sql = "SELECT * FROM `bs_chat_history` WHERE `id` IN(". $conds['$id'] .") order by added_date desc";

		$query = $this->db->query($sql);

		return $query;

	}

	function get_all_not_in_noti( $conds = array() ) {

	  	$sql = "SELECT * FROM `bs_push_notification_messages` WHERE `id` NOT IN(". $conds['$id'] .") order by added_date desc";
		$query = $this->db->query($sql);

		return $query;

	}

	/**
	Returns user follow list
	*/
	function get_all_follower_by_user( $conds = array() , $limit = false, $offset = false)
	{
		//print_r($limit . "$offset");die;
		$sql = "SELECT *  FROM core_users WHERE  user_id in (". $conds['follower_and_following_user'] ." )";
		//print_r($sql);die;
		$query = $this->db->query($sql);

		return $query;
		  //print_r($this->db->last_query());die;
		
	}

	/* Delete the records by condition
	 *
	 * @param      array   $conds  The conds
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 to get item favourite list
	 */
	function get_item_favourite( $conds = array(), $limit = false, $offset = false  )
	{
		
		$this->db->select('bs_items.*'); 
		$this->db->from('bs_items');
		$this->db->join('bs_favourite', 'bs_favourite.item_id = bs_items.id');

		if(isset($conds['user_id'])) {

			if ($conds['user_id'] != "" || $conds['user_id'] != 0) {
					
					$this->db->where( 'user_id', $conds['user_id'] );	

			}

		}

		if ( $limit ) {
		// if there is limit, set the limit
			
			$this->db->limit($limit);
		}
		
		if ( $offset ) {
		// if there is offset, set the offset,
			$this->db->offset($offset);
		}
		
		return $this->db->get();
		// print_r($this->db->last_query());die;
 	}

 	// get user with status 2 for request code

	function user_exists( $conds = array()) {

		$sql = "SELECT * FROM core_users WHERE `user_email` = '" . $conds['user_email'] . "' AND `status` = '" . $conds['status'] . "' ";

		$query = $this->db->query($sql);

		return $query;
	}

	// get user with email conds

	function get_one_user_email( $conds = array()) {

		$sql = "SELECT * FROM core_users WHERE `user_email` = '" . $conds['user_email'] . "' ";

		$query = $this->db->query($sql);

		return $query;
	}


}