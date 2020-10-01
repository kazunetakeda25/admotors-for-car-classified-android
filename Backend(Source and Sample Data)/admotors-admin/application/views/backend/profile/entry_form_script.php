<script>

	<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>
	
	function jqvalidate() {
		$('#user-form').validate({
			rules:{
				user_name:{
					required: true,
					minlength: 4
				},
				user_email:{
					required: true,
					email: true,
					remote: '<?php echo $module_site_url ."/ajx_exists/". @$user->user_id ; ?>'
				},
				user_password:{
					minlength: 4
				},
				conf_password:{
					equalTo: '#user_password'
				}
			},
			messages:{
				user_name:{
					required: "<?php echo get_msg( 'err_user_name_blank' ); ?>",
					minlength: "<?php echo get_msg( 'err_user_name_len' ); ?>"
				},
				user_email:{
					required: "<?php echo get_msg( 'err_user_email_blank' ); ?>",
					email: "<?php echo get_msg( 'err_user_email_invalid' ); ?>",
					remote: "<?php echo get_msg( 'err_user_email_exist' ); ?>"
				},
				user_password:{
					minlength: "<?php echo get_msg( 'err_user_pass_len' ); ?>"
				},
				conf_password:{
					equalTo: "<?php echo get_msg( 'err_user_pass_conf_not_match' ); ?>"
				}
			}
		});
	}

	<?php endif; ?>

</script>