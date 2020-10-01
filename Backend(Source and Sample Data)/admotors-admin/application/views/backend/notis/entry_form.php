<?php 
	$attributes = array( 'id' => 'gcm-form', 'enctype' => 'multipart/form-data');
	echo form_open( $module_site_url . '/push_message', $attributes);
?>
	
<section class="content animated fadeInRight">
	<div class="card card-info">
	    <div class="card-header">
	        <h3 class="card-title"><?php echo get_msg('noti_info')?></h3>
	    </div>
        <!-- /.card-header -->
        <div class="card-body">
            <div class="row">
             	<div class="col-md-6">
            		<div class="form-group">
						<label> 
							<?php 
								if($this->Noti->count_all() > 0) {
									
									echo get_msg( 'total_label' );

									echo $this->Noti->count_all();
									
									if($this->Noti->count_all() == 1) {
										echo get_msg( 'device_label' );
									} else {
										echo get_msg( 'device_label' );
									}

									echo get_msg( 'registered_label' );
								}
								?> 
						</label>
						<br>
						
						<label>
							<?php echo get_msg('noti_message_label') ?> 
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('noti_message_tooltips')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'>
							</a>
						</label>
						
						<textarea class="form-control" name="message" placeholder="<?php echo get_msg('noti_message_label')?>" rows="8"></textarea>
					</div>

					<?php if ( !isset( $noti )): ?>

							<div class="form-group">
							
								<label>
									<?php echo get_msg('noti_img')?>
									<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('cat_photo_tooltips')?>">
										<span class='glyphicon glyphicon-info-sign menu-icon'>
									</a>
								</label>

								<br/>

								<input class="btn btn-sm" type="file" name="images1">
							</div>

					<?php endif; ?>	

                </div>

                <div class="col-md-6"  style="padding-left: 50px;">
					<div class="form-group">
						<label>
							<?php echo get_msg('noti_des_label') ?> 
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('noti_message_tooltips')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'>
							</a>
						</label>
						
						<textarea class="form-control" name="description" placeholder="<?php echo get_msg('noti_des_label')?>" rows="5"></textarea>
					</div>
              	</div>
              	<!--  col-md-6  -->

            </div>
            <!-- /.row -->
        </div>
        <!-- /.card-body -->

		<div class="card-footer">
            <button type="submit" class="btn btn-sm btn-primary">
				<?php echo get_msg('btn_save')?>
			</button>

			<a href="<?php echo $module_site_url; ?>" class="btn btn-sm btn-primary">
				<?php echo get_msg('btn_cancel')?>
			</a>
        </div>
       
    </div>
    <!-- card info -->
</section>
				

	
	

<?php echo form_close(); ?>