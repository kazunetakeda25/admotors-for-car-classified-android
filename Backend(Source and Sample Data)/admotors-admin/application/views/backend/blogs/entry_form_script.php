<script>

	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>

	function jqvalidate() {

		$('#blog-form').validate({
			rules:{
				name:{
					blankCheck : "",
					minlength: 3,
					remote: "<?php echo $module_site_url .'/ajx_exists/'.@$blog->id; ?>"
					
				},
				
			},
			messages:{
				name:{
					blankCheck : "<?php echo get_msg( 'err_blog_name' ) ;?>",
					minlength: "<?php echo get_msg( 'err_blog_len' ) ;?>",
					remote: "<?php echo get_msg( 'err_blog_exist' ) ;?>."
				}
				
			},

			submitHandler: function(form) {
		        if ($("#blog-form").valid()) {
		            form.submit();
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
		});

	}
	

	<?php endif; ?>
	
</script>

