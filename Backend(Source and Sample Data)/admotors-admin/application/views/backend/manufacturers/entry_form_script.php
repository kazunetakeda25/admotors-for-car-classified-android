<script>

	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>

	function jqvalidate() {

		$('#manufacturer-form').validate({
			rules:{
				name:{
					blankCheck : "",
					minlength: 3,
					remote: "<?php echo $module_site_url .'/ajx_exists/'.@$manufacturer->id; ?>"
				},
				images1:{
					required: true
				},
				icon:{
					required: true
				}
			},
			messages:{
				name:{
					blankCheck : "<?php echo get_msg( 'err_manu_name' ) ;?>",
					minlength: "<?php echo get_msg( 'err_manu_len' ) ;?>",
					remote: "<?php echo get_msg( 'err_manu_exist' ) ;?>."
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
			var action = '<?php echo $module_site_url .'/delete_cover_photo/'; ?>' + id + '/<?php echo @$manufacturer->id; ?>';
			console.log( action );
			$('.btn-delete-image').attr('href', action);
		});
	}
</script>

<?php 
	// replace cover photo modal
	$data = array(
		'title' => get_msg('upload_photo'),
		'img_type' => 'manufacturer',
		'img_parent_id' => @$manufacturer->id
	);

	$this->load->view( $template_path .'/components/photo_upload_modal', $data );

	// delete cover photo modal
	$this->load->view( $template_path .'/components/delete_cover_photo_modal' ); 

	// replace cover icon modal
	$data = array(
		'title' => get_msg('upload_icon'),
		'img_type' => 'manufacturer-icon',
		'img_parent_id' => @$manufacturer->id
	);
		$this->load->view( $template_path .'/components/icon_upload_modal', $data );

	// delete cover photo modal
	$this->load->view( $template_path .'/components/delete_cover_photo_modal' ); 
?>