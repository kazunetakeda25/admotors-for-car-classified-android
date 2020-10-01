<script>

	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>

	function jqvalidate() {

		$('#seller-form').validate({
			rules:{
				seller_type:{
					blankCheck : "",
					minlength: 3,
					remote: "<?php echo $module_site_url .'/ajx_exists/'.@$seller->id; ?>"
				}
			},
			messages:{
				seller_type:{
					blankCheck : "<?php echo get_msg( 'err_seller_name' ) ;?>",
					minlength: "<?php echo get_msg( 'err_seller_len' ) ;?>",
					remote: "<?php echo get_msg( 'err_seller_exist' ) ;?>."
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