<script>

	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>

	function jqvalidate() {

		$('#build-form').validate({
			rules:{
				car_type:{
					blankCheck : "",
					minlength: 3,
					remote: "<?php echo $module_site_url .'/ajx_exists/'.@$build->id; ?>"
				}
			},
			messages:{
				car_type:{
					blankCheck : "<?php echo get_msg( 'err_color_name' ) ;?>",
					minlength: "<?php echo get_msg( 'err_color_len' ) ;?>",
					remote: "<?php echo get_msg( 'err_color_exist' ) ;?>."
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

</script>