<?php
	defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Model class for Item table
 */
class Item extends PS_Model {

	/**
	 * Constructs the required data
	 */
	function __construct() 
	{
		parent::__construct( 'bs_items', 'id', 'itm_' );
	}

	/**
	 * Implement the where clause
	 *
	 * @param      array  $conds  The conds
	 */
	function custom_conds( $conds = array())
	{
		
		// default where clause
		if (isset( $conds['status'] )) {
			$this->db->where( 'status', $conds['status'] );
		}
		
		// order by
		if ( isset( $conds['order_by_field'] )) {
			$order_by_field = $conds['order_by_field'];
			$order_by_type = $conds['order_by_type'];
			
			$this->db->order_by( 'bs_items.'.$order_by_field, $order_by_type);
		} else {
			$this->db->order_by('added_date', 'desc' );
		}

		// id condition
		if ( isset( $conds['id'] )) {
			$this->db->where( 'id', $conds['id'] );
		}

		// id condition
		if ( isset( $conds['added_user_id'] )) {
			$this->db->where( 'added_user_id', $conds['added_user_id'] );
		}

		// manufacturer_id condition
		if ( isset( $conds['manufacturer_id'] )) {
			
			if ($conds['manufacturer_id'] != "") {
				if($conds['manufacturer_id'] != '0'){
					$this->db->where( 'manufacturer_id', $conds['manufacturer_id'] );	
				}

			}			
		}

		//  model_id condition 
		if ( isset( $conds['model_id'] )) {
			
			if ($conds['model_id'] != "") {
				if($conds['model_id'] != '0'){
				
					$this->db->where( 'model_id', $conds['model_id'] );	
				}

			}			
		}

		//  color_id condition 
		if ( isset( $conds['color_id'] )) {
			
			if ($conds['color_id'] != "") {
				if($conds['color_id'] != '0'){
				
					$this->db->where( 'color_id', $conds['color_id'] );	
				}

			}			
		}

		//  fuel_type_id condition 
		if ( isset( $conds['fuel_type_id'] )) {
			
			if ($conds['fuel_type_id'] != "") {
				if($conds['fuel_type_id'] != '0'){
				
					$this->db->where( 'fuel_type_id', $conds['fuel_type_id'] );	
				}

			}			
		}

		//  build_type_id condition 
		if ( isset( $conds['build_type_id'] )) {
			
			if ($conds['build_type_id'] != "") {
				if($conds['build_type_id'] != '0'){
				
					$this->db->where( 'build_type_id', $conds['build_type_id'] );	
				}

			}			
		}

		//  seller_type_id condition 
		if ( isset( $conds['seller_type_id'] )) {
			
			if ($conds['seller_type_id'] != "") {
				if($conds['seller_type_id'] != '0'){
				
					$this->db->where( 'seller_type_id', $conds['seller_type_id'] );	
				}

			}			
		}

		//  transmission_id condition 
		if ( isset( $conds['transmission_id'] )) {
			
			if ($conds['transmission_id'] != "") {
				if($conds['transmission_id'] != '0'){
				
					$this->db->where( 'transmission_id', $conds['transmission_id'] );	
				}

			}			
		}

		// Type id
		if ( isset( $conds['item_type_id'] )) {
			
			if ($conds['item_type_id'] != "") {
				if($conds['item_type_id'] != '0'){
				
					$this->db->where( 'item_type_id', $conds['item_type_id'] );	
				}

			}			
		}
	  
		// Price id
		if ( isset( $conds['item_price_type_id'] )) {
			
			if ($conds['item_price_type_id'] != "") {
				if($conds['item_price_type_id'] != '0'){
				
					$this->db->where( 'item_price_type_id', $conds['item_price_type_id'] );	
				}

			}			
		}
	   
		// Currency id
		if ( isset( $conds['item_currency_id'] )) {
			
			if ($conds['item_currency_id'] != "") {
				if($conds['item_currency_id'] != '0'){
				
					$this->db->where( 'item_currency_id', $conds['item_currency_id'] );	
				}

			}			
		}

		// location id
		if ( isset( $conds['item_location_id'] )) {
			
			if ($conds['item_location_id'] != "") {
				if($conds['item_location_id'] != '0'){
				
					$this->db->where( 'item_location_id', $conds['item_location_id'] );	
				}

			}			
		}

		// condition_of_item id condition
		if ( isset( $conds['condition_of_item_id'] )) {
			$this->db->where( 'condition_of_item_id', $conds['condition_of_item_id'] );
		}

		// description condition
		if ( isset( $conds['description'] )) {
			$this->db->where( 'description', $conds['description'] );
		}

		// highlight_info condition
		if ( isset( $conds['highlight_info'] )) {
			$this->db->where( 'highlight_info', $conds['highlight_info'] );
		}

		// business_mode condition
		if ( isset( $conds['business_mode'] )) {
			$this->db->where( 'business_mode', $conds['business_mode'] );
		}

		// highlight_info condition
		if ( isset( $conds['highlight_info'] )) {
			$this->db->where( 'highlight_info', $conds['highlight_info'] );
		}

		// plate_number condition
		if ( isset( $conds['plate_number'] )) {
			$this->db->where( 'plate_number', $conds['plate_number'] );
		}

		// engine_power condition
		if ( isset( $conds['engine_power'] )) {
			$this->db->where( 'engine_power', $conds['engine_power'] );
		}

		// steering_position condition
		if ( isset( $conds['steering_position'] )) {
			$this->db->where( 'steering_position', $conds['steering_position'] );
		}

		// no_of_owner condition
		if ( isset( $conds['no_of_owner'] )) {
			$this->db->where( 'no_of_owner', $conds['no_of_owner'] );
		}

		// trim_name condition
		if ( isset( $conds['trim_name'] )) {
			$this->db->where( 'trim_name', $conds['trim_name'] );
		}

		// vehicle_id condition
		if ( isset( $conds['vehicle_id'] )) {
			$this->db->where( 'vehicle_id', $conds['vehicle_id'] );
		}

		// price_type condition
		if ( isset( $conds['price_type'] )) {
			$this->db->where( 'price_type', $conds['price_type'] );
		}

		// price_unit condition
		if ( isset( $conds['price_unit'] )) {
			$this->db->where( 'price_unit', $conds['price_unit'] );
		}

		// year condition
		if ( isset( $conds['year'] )) {
			$this->db->where( 'year', $conds['year'] );
		}

		// max_passengers condition
		if ( isset( $conds['max_passengers'] )) {
			$this->db->where( 'max_passengers', $conds['max_passengers'] );
		}

		// no_of_doors condition
		if ( isset( $conds['no_of_doors'] )) {
			$this->db->where( 'no_of_doors', $conds['no_of_doors'] );
		}

		// mileage condition
		if ( isset( $conds['mileage'] )) {
			$this->db->where( 'mileage', $conds['mileage'] );
		}

		// license_expiration_date condition
		if ( isset( $conds['license_expiration_date'] )) {
			$this->db->where( 'license_expiration_date', $conds['license_expiration_date'] );
		}

		// searchterm
		if ( isset( $conds['searchterm'] )) {
			$this->db->group_start();
			$this->db->like( 'title', $conds['searchterm'] );
			$this->db->or_like( 'description', $conds['searchterm'] );
			$this->db->or_like( 'condition_of_item_id', $conds['searchterm'] );
			$this->db->or_like( 'highlight_info', $conds['searchterm'] );
			$this->db->group_end();
		}

		if( isset($conds['max_price']) ) {
			if( $conds['max_price'] != 0 ) {
				$this->db->where( 'price <=', $conds['max_price'] );
			}	

		}

		if( isset($conds['min_price']) ) {

			if( $conds['min_price'] != 0 ) {
				$this->db->where( 'price >=', $conds['min_price'] );
			}

		}

		
	}

}