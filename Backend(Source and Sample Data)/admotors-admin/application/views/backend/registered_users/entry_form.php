
<?php
	$attributes = array('id' => 'user-form');
	echo form_open( '', $attributes );
?>
<section class="content animated fadeInRight">

	<div class="card card-info">
	    <div class="card-header">
	      <h3 class="card-title"><?php echo get_msg('user_info')?></h3>
	    </div>

	    <form role="form">
      		<div class="card-body">
        		<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label><?php echo get_msg('user_name').":"; ?></label><br>
						</div>
						<div class="form-group">
							<label><?php echo get_msg('user_email').":"; ?></label><br>
						</div>
						<div class="form-group">
							<label><?php echo get_msg('user_phone').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('user_address').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('user_city').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('about_me').":"; ?></label><br>
						</div>	
						
						<div class="form-group">	
							<label><?php echo get_msg('email_verify').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('facebook_verify').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('google_verify').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('phone_verify').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('follower_count').":"; ?></label><br>
						</div>
						<div class="form-group">	
							<label><?php echo get_msg('following_count').":"; ?></label>
						</div>
						<div class="form-group">
							<label><?php echo get_msg('user_profile').":"; ?></label><br>
						</div>	
					</div>

					<div class="col-md-3">
						<div class="form-group">
							<label><?php if ($user->user_name == "") {
									echo "N.A";
							} else{
								echo $user->user_name;

							} ?>
							</label><br>
						</div>
						<div class="form-group">
							<label><?php if ($user->user_email == "") {
									echo "N.A";
							} else{
								echo $user->user_email;

							} ?>
							</label><br>
						</div>
						<div class="form-group">
							<label><?php if ($user->user_phone == "") {
									echo "N.A";
							} else{
								echo $user->user_phone;

							} ?>
							</label><br>
						</div>
						<div class="form-group">
							<label><?php if ($user->user_address == "") {
									echo "N.A";
							} else{
								echo $user->user_address;

							} ?>
							</label><br>
						</div>
						<div class="form-group">
							<label><?php if ($user->city == "") {
									echo "N.A";
							} else{
								echo $user->city;

							} ?>
							</label><br>
						</div>
						<div class="form-group">
							<label><?php if ($user->user_about_me == "") {
									echo "N.A";
							} else{
								echo $user->user_about_me;

							} ?>
							</label><br>
						</div>
						
						<div class="form-group">
							<label><?php if ($user->email_verify == "1") {
									echo "Yes";
							} else{
								
									echo "No";
							} ?>
							</label><br>
							
						</div>

						<div class="form-group">
							<label><?php if ($user->facebook_verify == "1") {
									echo "Yes";
							} else{
								
									echo "No";
							} ?>
							</label><br>
							
						</div>

						<div class="form-group">
							<label><?php if ($user->google_verify == "1") {
									echo "Yes";
							} else{
								
									echo "No";
							} ?>
							</label><br>
							
						</div>

						<div class="form-group">
							<label><?php if ($user->phone_verify == "1") {
									echo "Yes";
							} else{
								
									echo "No";
							} ?>
							</label><br>
							
						</div>

						<div class="form-group">
							<label><?php if ($user->follower_count == "") {
									echo "N.A";
							} else{
								echo $user->follower_count;

							} ?>
							</label><br>
						</div>
						<div class="form-group">
							<label><?php if ($user->following_count == "") {
									echo "N.A";
							} else{
								echo $user->following_count;

							} ?>
							</label><br>
						</div>
						<div class="form-group">
							<div class="thumbnail">
								<?php  if ($user->user_profile_photo == "") {
									echo "N.A";
								} else{ 
									?>
									<img src="<?php echo img_url('/' . $user->user_profile_photo); ?>" style="width: 100px; height: 100px;">

								<?php  } ?>

								
							</div>				
						</div>

					</div>
				</div>	

			</div>
		</form>
	</div>		
	
	
<?php echo form_close(); ?>