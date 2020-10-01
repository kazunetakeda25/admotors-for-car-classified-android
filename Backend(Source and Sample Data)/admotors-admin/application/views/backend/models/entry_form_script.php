<script>
	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>

	function jqvalidate() {

		$('#model-form').validate({
			rules:{
				name:{
					blankCheck : "",
					minlength: 3,
					remote: "<?php echo $module_site_url .'/ajx_exists/'.@$model->id; ?>"
				},
				manufacturer_id: {
		       		indexCheck : ""
		      	}
			},
			messages:{
				name:{
					blankCheck : "<?php echo get_msg( 'err_model_name' ) ;?>",
					minlength: "<?php echo get_msg( 'err_model_len' ) ;?>",
					remote: "<?php echo get_msg( 'err_model_exist' ) ;?>."
				},
				manufacturer_id:{
			       indexCheck: "<?php echo $this->lang->line('f_item_model_required'); ?>"
			    }
			}
		});
		
		jQuery.validator.addMethod("indexCheck",function( value, element ) {
			
			   if(value == 0) {
			    	return false;
			   } else {
			    	return true;
			   };

			   
		});

		// custom validation
		jQuery.validator.addMethod("blankCheck",function( value, element ) {
			
			   if(value == "") {
			    	return false;
			   } else {
			   		
			    	return true;
			   };
		})

	}

	<?php endif; ?>

		$('.delete-img').click(function(e){
			e.preventDefault();

			// get id and image
			var id = $(this).attr('id');

			// do action
			var action = '<?php echo $module_site_url .'/delete_cover_photo/'; ?>' + id + '/<?php echo @$model->id; ?>';
			console.log( action );
			$('.btn-delete-image').attr('href', action);
			
		});

</script>

<?php 
	// replace cover photo modal
	$data = array(
		'title' => get_msg('upload_photo'),
		'img_type' => 'model',
		'img_parent_id' => @$model->id
	);

	$this->load->view( $template_path .'/components/photo_upload_modal', $data );
	// delete cover photo modal
	$this->load->view( $template_path .'/components/delete_cover_photo_modal' ); 

	// replace icon icon modal
	$data = array(
		'title' => get_msg('upload_icon'),
		'img_type' => 'model_icon',
		'img_parent_id' => @$model->id
	);
		$this->load->view( $template_path .'/components/icon_upload_modal', $data );
		// delete icon photo modal
		$this->load->view( $template_path .'/components/delete_icon_modal' ); 
?>