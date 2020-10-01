<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Likes Controller
 */

class Massupload extends BE_Controller {
		/**
	 * Construt required variables
	 */
	function __construct() {

		parent::__construct( MODULE_CONTROL, 'Mass Upload' );
		$this->load->library('uploader');
		$this->load->library('csvimport');
	}

	/**
	 * Load Api Key Entry Form
	 */

	function index( ) {

		$this->load_form($this->data);

	}

	

	function upload() {
		
		if ( $this->is_POST()) {


		$file = $_FILES['file']['name'];
		
		$ext = substr(strrchr($file, '.'), 1);
		//print_r($ext); die;
		



        if(strtolower($ext) == "csv") {

        	 	$upload_data = $this->uploader->upload($_FILES);
				


				if (!isset($upload_data['error'])) {
					foreach ($upload_data as $upload) {
						

						$file_data = $this->upload->data();
		            	$file_path =  './uploads/'.$file_data['file_name'];
		            	if ($this->csvimport->get_array($file_path)) {

			                $csv_array = $this->csvimport->get_array($file_path);

			                $i = 0; $s = 0; $f=0;
			                $fail_records = "";
			                foreach ($csv_array as $row) {
			                    
			                	


			                     if($row['wallpaper_name'] || $row['image_file']) {
			                     	//print_r($row['image_file']); die;

			                     	//Get Category Id 
			                     	$conds_cat['cat_name'] = trim($row['cat_name']);
			                     	$cat_id = $this->Category->get_one_by($conds_cat)->cat_id;

			                     	//Get Color Id
			                     	
			                     	$conds_color['name'] = trim($row['color_name']);
			                     	$color_id = $this->Color->get_one_by($conds_color)->id;
									//Get Types ID
									//print_r($row['wallpaper_types']); die;
									if(trim($row['wallpaper_types']) == "Free") {
										$types_id = 1;
									} else {
										$types_id = 2;
									}

									//Recommended Checking
									if(trim($row['is_recommended']) == 1) {
										//set current date 
										$recommended_date = date("Y-m-d H:i:s");  
									} else {
										$recommended_date = "";
									}

									if(trim($row['wallpaper_search_tags']) != "") {

										$wallpaper_search_tags_array = explode("@",$row['wallpaper_search_tags']);
										$wallpaper_search_tags_str = "";

										for($d = 0; $d < count($wallpaper_search_tags_array); $d++) {
											$wallpaper_search_tags_str .= trim($wallpaper_search_tags_array[$d]) . ",";
										}

									}
									
									//Get Image Info 

									$data_img = getimagesize(base_url() . "uploads/" . $row['image_file']);

									$data_thumb = getimagesize(base_url() . "uploads/thumbnail/" . $row['image_thumb_file']);

									
									if( count($data_img) != 1 || count($data_thumb) != 1 ) {

										$data = getimagesize(base_url() . "uploads/" . $row['image_file']);
										$img_width = $data[0];
										$img_height = $data[1];

									} 
									

									if( count($data_img) != 1 ) {
										
										if( count($data_thumb) != 1 ) {

										
										//Wallpaper must have category
										if($cat_id != "") {

											$insert_data = array(
												
												'cat_id'     		=>	$cat_id,
												'color_id'   		=>  $color_id,
												'wallpaper_name' 	=>  trim($row['wallpaper_name']),
												'types'             =>  $types_id,
												'is_recommended'    =>  trim($row['is_recommended']),
												'is_portrait'       =>  trim($row['is_portrait_mode']),
												'is_landscape'      =>  trim($row['is_landscape_mode']),
												'is_square'         =>  trim($row['is_square_mode']),
												'point'             =>  trim($row['point_value']),
												'wallpaper_is_published'    =>  trim($row['wallpaper_is_published']),
												'wallpaper_search_tags'     =>  rtrim($wallpaper_search_tags_str, ","),
												'is_batch_upload'     =>  1,
												'credit'     =>  trim($row['credit_wp'])
											);

												$wallpaper_id = 0;
												//print_r($insert_data); die;
												if($this->Wallpaper->save($insert_data, $wallpaper_id)) {
							                    	
													$wallpaper_id = ( !$wallpaper_id )? $insert_data['wallpaper_id']: $wallpaper_id ;

							                    	$image_data = array(

							                    		'img_parent_id' => $wallpaper_id,
							                    		'img_type' 		=> "wallpaper",
							                    		'img_path' 		=> trim($row['image_file']),
							                    		'img_width'     => $img_width,
							                    		'img_height'    => $img_height
													);

							                    	if($this->Image->save($image_data)) {
							                    		//both success

							                    		$s++;	

							                    	}

							                    } else {
							                    	$f++;
						                			$fail_records .= " - " . $row['wallpaper_name'] . " because of database error.<br>";
							                    }


											} else {
												//Category Missing
												$f++;
					                			$fail_records .= " - " . $row['wallpaper_name'] . " because of missing category.<br>";
											}	

										} else {
											//thumbnail missing 
											$f++;
				                			$fail_records .= " - " . $row['wallpaper_name'] . " because of missing wallpaper at 'uploads/thumbnail' folder.<br>";
										} 


									} else {
										//image at uploads missing 
										$f++;
				                			$fail_records .= " - " . $row['wallpaper_name'] . " because of missing wallpaper at 'uploads' folder.<br>";
									}


				                    
			                	} else {
			                		$f++;
			                		$fail_records .= " - " . $row['wallpaper_name'] . " because of missing wallpaper name.<br>";
			                	}



			                	$i++;

			                }

			                $result_str = "Total Wallpaper : " . $i . "<br>";
			                $result_str .= "Successed Wallpaper : " . $s . "<br>";
			                $result_str .= "Failed Wallpaper : " . $f .  "<br>" . $fail_records;
			                
			                //print_r($result_str); die;

			                $this->data['message'] = $result_str;
			                $this->load_form($this->data);

			                //$this->session->set_flashdata('success', $result_str);

			                //$content['content'] = $this->load->view('items/import_items',$data,true);		
							//$this->load_template($content, false);

			            } else {

			            	//echo "Something wrong in your uploaded data.";
			                
			            	$this->set_flash_msg( 'error', get_msg( 'something_wrong_upload' ));

			            	$this->load_form($this->data);

			    //             $data['error'] = "Something wrong in your uploaded data.";
			    //             $this->session->set_flashdata('error', $data['error']);
			    //              $content['content'] = $this->load->view('items/import_items',$data,true);		
							// $this->load_template($content, false);
			            }


					}
				} else {
					// $data['error'] = $upload_data['error'];

					// $this->session->set_flashdata('error', $data['error']);
		   //          $content['content'] = $this->load->view('items/import_items',$data,true);		
					// $this->load_template($content, false);

					//print_r($upload_data['error']);

					$this->set_flash_msg( 'error', $upload_data['error']);

			        $this->load_form($this->data);
				}

        } else {

        	//print_r('Please upload CSV file only.');

        	$this->set_flash_msg( 'error',  get_msg( 'pls_upload_csv' ));

		    $this->load_form($this->data);

   //          $this->session->set_flashdata('error', 'Please upload CSV file only.');

   //          $content['content'] = $this->load->view('items/import_items',$data,true);		
			// $this->load_template($content, false);

        }



		} else {
			$this->load_form($this->data);
		}
		

	}

}