<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * PanaceaSoft Image Uploader
 */
class PS_Image
{
	// codeigniter instance
	protected $CI;
	
	// photo path to upload
	protected $upload_path;
	
	// photo path for thumbnail
	protected $upload_thumbnail_path;

	// photo url to upload
	public $upload_url;
	
	// photo url for thumbnail
	public $upload_thumbnail_url;
	
	// accepted media types
	protected $accept_image_type;

	/**
	 * Initialize Upload Config and load the Upload Library
	 */
	function __construct()
	{
		// load configurations
		$this->load_config();

		// load libraries
		$this->load_libraries();
	}

	/**
	 * Load Configurations
	 */
	function load_config()
	{
		// get CI instance
		$this->CI =& get_instance();

		// get upload path config
		$tmp_upload_path = $this->CI->config->item( 'upload_path' );
		$tmp_thumbnail_path = $this->CI->config->item( 'upload_thumbnail_path' );
		
		// get upload path config
		$this->upload_path = FCPATH . $tmp_upload_path;
		
		// get upload thumbnail config
		$this->upload_thumbnail_path = FCPATH . $tmp_thumbnail_path;

		// get upload path config
		$this->upload_url = base_url( $tmp_upload_path );
		
		// get upload thumbnail config
		$this->upload_thumbnail_url = base_url( $tmp_thumbnail_path);
		
		// get accepted media types
		$this->accept_image_type = $this->CI->config->item( 'image_type' );
	}

	/**
	 * Loads libraries.
	 */
	function load_libraries()
	{		
		// load file helper
		$this->CI->load->helper('file');

		// load image library
		$this->CI->load->library('image_lib');

		// load uploader library
		$this->CI->load->library('upload');

		// setup uploader configuration
		$config['upload_path'] = $this->upload_path;
		$config['allowed_types'] = $this->accept_image_type;
		$config['overwrite'] = FALSE;
		$this->CI->upload->initialize($config);
	}
	
	/**
	 * Upload image and resize the required dimension
	 *
	 * @param      <type>          $files   The files
	 * @param      integer|string  $userId  The user identifier
	 * @param      string          $type    The type
	 *
	 * @return     array           ( description_of_the_return_value )
	 */
	function upload( $files )
	{
		// empty array to return processed data
		$data = array();

		if ( empty( $files )) {
		// if there is no file, show error
			
			$data['error'] = "Choose file to upload";
			return $data;
		}
		
		// loop if the files array
		foreach ( $files as $field => $file ) {

			// assign the file name
			$_FILES[$field]['name'] = $_FILES[$field]['name'];
				
			if ( $file['error'] == 0 ) {    
			// if there is no error in file,		
					
				if ( $this->CI->upload->do_upload( $field )) {
				// if file uploading is success

					// get uploaded data
					$uploaded_data = $this->CI->upload->data();
					
					// keep uploaded data in return object
					$data[] = $uploaded_data;

					// create thumbnail
					$image_path = $uploaded_data['full_path'];

					$thumb_width  =   round($uploaded_data['image_width'] * 0.25, 0);
					$thumb_height =   round($uploaded_data['image_height'] * 0.25, 0);


					$this->create_thumbnail( $image_path, $thumb_width, $thumb_height );

				} else {
				// if file uploading is fail,	
					
					// return error
					$data['error'] = $this->CI->upload->display_errors();
				}
			}
		}

		if ( empty( $data )) {
			$data['error'] = "No file is uploaded";
		}
			
		return $data;
	}

	function upload_cover( $files )
	{
		// empty array to return processed data
		$data = array();

		if ( empty( $files )) {
		// if there is no file, show error
			
			$data['error'] = "Choose file to upload";
			return $data;
		}
		
		// loop if the files array
		foreach ( $files as $field => $file ) {

			if($field == "cover") {

				// assign the file name
				$_FILES[$field]['name'] = $_FILES[$field]['name'];
					
				if ( $file['error'] == 0 ) {    
				// if there is no error in file,		
						
					if ( $this->CI->upload->do_upload( $field )) {
					// if file uploading is success

						// get uploaded data
						$uploaded_data = $this->CI->upload->data();
						
						// keep uploaded data in return object
						$data[] = $uploaded_data;

						// create thumbnail
						$image_path = $uploaded_data['full_path'];

						$thumb_width  =   round($uploaded_data['image_width'] * 0.25, 0);
						$thumb_height =   round($uploaded_data['image_height'] * 0.25, 0);


						$this->create_thumbnail( $image_path, $thumb_width, $thumb_height );

					} else {
					// if file uploading is fail,	
						
						// return error
						$data['error'] = $this->CI->upload->display_errors();
					}
				}

			}
		}

		if ( empty( $data )) {
			$data['error'] = "No file is uploaded";
		}
			
		return $data;
	}

	function upload_icon( $files )
	{
		// empty array to return processed data
		$data = array();

		if ( empty( $files )) {
		// if there is no file, show error
			
			$data['error'] = "Choose file to upload";
			return $data;
		}
		
		// loop if the files array
		foreach ( $files as $field => $file ) {

			if($field == "icon") {

				// assign the file name
				$_FILES[$field]['name'] = $_FILES[$field]['name'];
					
				if ( $file['error'] == 0 ) {    
				// if there is no error in file,		
						
					if ( $this->CI->upload->do_upload( $field )) {
					// if file uploading is success

						// get uploaded data
						$uploaded_data = $this->CI->upload->data();
						
						// keep uploaded data in return object
						$data[] = $uploaded_data;

						// create thumbnail
						$image_path = $uploaded_data['full_path'];

						$thumb_width  =   round($uploaded_data['image_width'] * 0.25, 0);
						$thumb_height =   round($uploaded_data['image_height'] * 0.25, 0);


						$this->create_thumbnail( $image_path, $thumb_width, $thumb_height );

					} else {
					// if file uploading is fail,	
						
						// return error
						$data['error'] = $this->CI->upload->display_errors();
					}
				}

			}
		}

		if ( empty( $data )) {
			$data['error'] = "No file is uploaded";
		}
			
		return $data;
	}

	/**
	 * Creats a thumbnail by passed width and height
	 *
	 * @param      <type>   $image_data  The image data
	 * @param      integer  $width       The width
	 * @param      integer  $height      The height
	 */
	function create_thumbnail( $image_path, $width = 150, $height = 100 )
	{
		// create thumbnail
		$this->CI->image_lib->clear();

		$config = array(
			'source_image' => $image_path, //$image_data['full_path'],
			'new_image' => $this->upload_thumbnail_path,
			'maintain_ration' => true,
			'width' => $width,
			'height' => $height
		);

		$this->CI->image_lib->initialize($config);
		$this->CI->image_lib->resize();
	}

	/**
	 * deletes original image and thunmbnail image if exists
	 *
	 * @param      <type>   $img_filename  The image filename
	 *
	 * @return     boolean  ( description_of_the_return_value )
	 */
	function delete_images ( $img_filename )
	{
		if ( empty( $img_filename )) {
		// if file name is empty, return true
			
			return true;
		}

		// delete original photo
		$img_path = $this->upload_path . $img_filename;
		if ( file_exists( $img_path )) {

			unlink( $img_path );
		}

		// delete thumbnail photo
		$thumb_path = $this->upload_thumbnail_path . $img_filename;
		if ( file_exists( $thumb_path )) {
			
			unlink( $thumb_path );
		}

		return true;
	}
}
?>