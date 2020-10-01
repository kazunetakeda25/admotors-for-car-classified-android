<script>

	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>

	function jqvalidate() {

		$('#fuel-form').validate({
			rules:{
				fuel_name:{
					blankCheck : "",
					minlength: 3,
					remote: "<?php echo $module_site_url .'/ajx_exists/'.@$fuel->id; ?>"
				}
			},
			messages:{
				fuel_name:{
					blankCheck : "<?php echo get_msg( 'err_fuel_name' ) ;?>",
					minlength: "<?php echo get_msg( 'err_fuel_len' ) ;?>",
					remote: "<?php echo get_msg( 'err_fuel_exist' ) ;?>."
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