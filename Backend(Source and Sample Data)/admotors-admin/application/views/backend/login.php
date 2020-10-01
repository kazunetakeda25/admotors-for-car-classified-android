<div class='container mt-5'>
	<div class='row justify-content-center'>
		<div class='col-8 col-md-5'>

    		<?php
    		$attributes = array('id' => 'login-form','method' => 'POST');
    		echo form_open(site_url('login'), $attributes);
    		?>

			<h2>
				<label class="login-title">
					<?php echo $site_name; ?>
				</label>
			</h2>
			<hr/>
			
			<?php flash_msg(); ?>
					
			<div class="form-group">
				<label><font color="#000"><?php echo get_msg( 'user_email' ); ?></font></label>
				<input class="form-control" type="text" placeholder="<?php echo get_msg( 'user_email' ); ?>" name='email' value="<?php echo set_value( 'email' ); ?>">
			</div>
			
			<div class="form-group">
				<label><font color="#000"><?php echo get_msg( 'user_password' ); ?></font></label>
				<input class="form-control" type="password" placeholder="<?php echo get_msg( 'user_password' ); ?>" name='password' value="<?php echo set_value( 'password' ); ?>">
			</div>
					
			<button class="btn btn-primary" type="submit"><?php echo get_msg( 'signin' ); ?></button>
									
			<?php echo form_close();  ?>

			<hr>

			<a href="<?php echo site_url( 'reset_request' ); ?>">Forgot Password?</a>

		</div>
	</div>
</div>
<script>
	function jqvalidate() {
		$(document).ready(function(){
			$('#login-form').validate({
				rules:{
					email: "required",
					password: "required"
				},
				messages:{
					email: "Please fill username.",
					password: "Please fill password"
				}
			});
		});
	}
</script>