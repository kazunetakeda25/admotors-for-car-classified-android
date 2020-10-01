<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Encoder and Decoder for Input Output
 */
class PS_Security 
{
	/**
	 * Encode string, array, object
	 *
	 * @param      <type>  $data   The data
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function clean_input( $data )
	{
		if ( is_array( $data )) {
		// if data is array, array map
			
			return array_map(array($this,'clean_input'), $data);
		}
		
		if ( is_object( $data )) {
		// if data is object, clean_input each variable
			
			$tmp = clone $data; // avoid modifing original object
			foreach ( $data as $k => $var )
				$tmp->{$k} = $this->clean_input( $var );
			return $tmp;
		}

		// clean_input the string
		return htmlspecialchars($data);
	}

	/**
	 * Decode string, array, object
	 *
	 * @param      <type>  $data   The data
	 *
	 * @return     <type>  ( description_of_the_return_value )
	 */
	function clean_output( $data )
	{
		if ( is_array( $data )) {
		// if data is array, array map
			
			return array_map(array($this,'clean_output'), $data);
		}

		if ( is_object( $data )) {
		// if data is object, clean_output each variable
			
			$tmp = clone $data; // avoid modifing original object
			foreach ( $data as $k => $var )
				$tmp->{$k} = $this->clean_output( $var );
			return $tmp;
		}
		
		// clean_output the string
		return htmlspecialchars_decode( $data );
	}
}