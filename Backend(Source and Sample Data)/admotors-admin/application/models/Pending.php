<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for wallpaper table
 */
class Pending extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_wallpapers', 'wallpaper_id', 'paper' );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{		
		// default where clause
		if ( !isset( $conds['no_publish_filter'] )) {
			
			$this->db->where( 'wallpaper_is_published', 2 );
		}

		// order by
		if ( isset( $conds['order_by'] )) {
			
			$order_by_field = $conds['order_by_field'];
			$order_by_type = $conds['order_by_type'];
			
			$this->db->order_by( 'bs_wallpapers.'.$order_by_field, $order_by_type);
		}

		// popular conditions
		if ( $this->is_filter_popular( $conds )) {

			$this->db->reset_query();

			$n = $this->table_name;
			$t = 'bs_touches';

			$this->db->select( "{$n}.*, count({$t}.user_id) touch_count" );

			// join user_categories table by wallpaper_id
			$this->db->join( $t, $t .'.wallpaper_id = '. $this->table_name .'.wallpaper_id');

			// group by news id
			$this->db->group_by( $n .'.wallpaper_id' );

			// condition for user_categories table
			$this->db->order_by( "touch_count", "desc" );
		}

		// wallpaper_name condition
		if ( isset( $conds['wallpaper_name'] )) {
			$this->db->where( 'wallpaper_name', $conds['wallpaper_name'] );
		}

		// category id condition
		if ( isset( $conds['cat_id'] )) {
			
			if ($conds['cat_id'] != "") {
				if($conds['cat_id'] != '0'){
				
					$this->db->where( 'cat_id', $conds['cat_id'] );	
				}

			}			
		}

		// color id condition
		if ( isset( $conds['color_id'] )) {
			
			if ($conds['color_id'] != "") {
				if($conds['color_id'] != '0') {
				
					$this->db->where( 'color_id', $conds['color_id'] );	
				}

			}			
		}

		// type condition
		if ( isset( $conds['types'] )) {
			
			if ($conds['types'] != "") {
				if($conds['types'] != '0') {
				
					$this->db->where( 'types', $conds['types'] );	
				}

			}			
		}

		// searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->like( 'wallpaper_name', $conds['searchterm'] );
		}
		if ( isset( $conds['searchterm'] )) {
			$this->db->like( 'cat_id', $conds['cat_id'] );
		}


		
		if ( isset( $conds['is_portrait'] ) || isset( $conds['is_landscape'] ) || isset( $conds['is_square'] )) {
			if($conds['is_portrait'] != "" || $conds['is_landscape'] != "" || $conds['is_square']) {
				
				$this->db->group_start();
				$this->db->or_where( 'is_portrait', $conds['is_portrait'] );
				$this->db->or_where( 'is_landscape', $conds['is_landscape'] );
				$this->db->or_where( 'is_square', $conds['is_square'] );
				$this->db->group_end();

			}
		}



		// point condition
		if ( isset( $conds['point_min'] ) ) {
			$this->db->where( 'point >= ', $conds['point_min'] );
		}

		if ( isset( $conds['point_max'] ) ) {
			$this->db->where( 'point <= ', $conds['point_max'] );
		}

		// point condition
		if ( isset( $conds['rating_min'] ) ) {
			$this->db->where( 'overall_rating >= ', $conds['rating_min'] );
		}

		if ( isset( $conds['rating_max'] ) ) {
			$this->db->where( 'overall_rating <= ', $conds['rating_max'] );
		}
		



	}

	/**
	 * Determines if filter popular.
	 *
	 * @return     boolean  True if filter popular, False otherwise.
	 */
	function is_filter_popular( $conds )
	{
		return ( isset( $conds['popular'] ) && $conds['popular'] == 1 );
	}
}