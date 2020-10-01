<script>

	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>

	function jqvalidate() {

		$('#category-form').validate({
			rules:{
				cat_name:{
					blankCheck : "",
					minlength: 3,
					remote: "<?php echo $module_site_url .'/ajx_exists/'.@$category->cat_id; ?>"
				},
				images1:{
					required: true
				},
				icon:{
					required: true
				}
			},
			messages:{
				cat_name:{
					blankCheck : "<?php echo get_msg( 'err_cat_name' ) ;?>",
					minlength: "<?php echo get_msg( 'err_cat_len' ) ;?>",
					remote: "<?php echo get_msg( 'err_cat_exist' ) ;?>."
				},
				images1:{
					required: "Please File Upload Photo."
				},
				icon:{
					required: "Please File Upload Icon."
				}
			}
		});
		// custom validation
		jQuery.validator.addMethod("blankCheck",function( value, element ) {
			
			   if(value == "") {
			    	return false;
			   } else {
			    	return true;
			   }
		})
	}

	<?php endif; ?>

	function runAfterJQ() {

		$('.delete-img').click(function(e){
			e.preventDefault();

			// get id and image
			var id = $(this).attr('id');

			// do action
			var action = '<?php echo $module_site_url .'/delete_cover_photo/'; ?>' + id + '/<?php echo @$category->cat_id; ?>';
			console.log( action );
			$('.btn-delete-image').attr('href', action);
		});
	}
</script>

<?php 
	// replace cover photo modal
	$data = array(
		'title' => get_msg('upload_photo'),
		'img_type' => 'category',
		'img_parent_id' => @$requestcategory->request_cat_id
	);

	$this->load->view( $template_path .'/components/photo_upload_modal', $data );

	// delete cover photo modal
	$this->load->view( $template_path .'/components/delete_cover_photo_modal' ); 

	// replace cover icon modal
	$data = array(
		'title' => get_msg('upload_icon'),
		'img_type' => 'category-icon',
		'img_parent_id' => @$requestcategory->request_cat_id
	);
		$this->load->view( $template_path .'/components/icon_upload_modal', $data );

	// delete cover photo modal
	$this->load->view( $template_path .'/components/delete_cover_photo_modal' ); 
?>