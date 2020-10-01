<?php
	
	$attributes = array( 'id' => 'version-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>

<section class="content animated fadeInRight">
	<div class="card card-info">
	    <div class="card-header">
	        <h3 class="card-title"><?php echo get_msg('ver_info')?></h3>
	    </div>
        <!-- /.card-header -->
        <div class="card-body">
            <div class="row">
             	<div class="col-md-6">
                		<div class="form-group">
	                   		<label> <span style="font-size: 17px; color: red;">*</span>
								<?php echo get_msg('ver_no_label')?>
								<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('name_tooltips')?>">
									<span class='glyphicon glyphicon-info-sign menu-icon'>
								</a>
							</label>

							<?php echo form_input( array(
								'name' => 'version_no',
								'value' => set_value( 'version_no', show_data( @$version->version_no ), false ),
								'class' => 'form-control form-control-sm',
								'placeholder' => get_msg( 'ver_no_label' ),
								'id' => 'version_no'
							)); ?>
                  		</div>

	                  	<div class="form-group">
							<label> <span style="font-size: 17px; color: red;">*</span>
								<?php echo get_msg('ver_msg_label')?>
								<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('about_description_tooltips')?>">
									<span class='glyphicon glyphicon-info-sign menu-icon'>
								</a>
							</label>
							<textarea class="form-control" name="version_message" placeholder="<?php echo get_msg('ver_msg_label')?>" rows="5"><?php echo $version->version_message; ?></textarea>
						</div>

	                </div>

                  	<div class="col-md-6"  style="padding-left: 50px;">
		              	<div class="form-group">
	                   		<label> <span style="font-size: 17px; color: red;">*</span>
								<?php echo get_msg('ver_title_label')?>
								<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('ver_title_label')?>">
									<span class='glyphicon glyphicon-info-sign menu-icon'>
								</a>
							</label>

							<?php echo form_input( array(
								'name' => 'version_title',
								'value' => set_value( 'version_title', show_data( @$version->version_title ), false ),
								'class' => 'form-control form-control-sm',
								'placeholder' => get_msg( 'ver_title_label' ),
								'id' => 'version_title'
							)); ?>
                  		</div>

                  		<div class="form-group">
							<div class="form-check">
								<label class="form-check-label">
								
								<?php 

									if( $version->version_force_update == 1 ) {
										echo form_checkbox( array(
											'name' => 'version_force_update',
											'id' => 'version_force_update',
											'value' => 'accept',
											'checked' => true,
											'class' => 'form-check-input'
										));	
									
									}  else {

										echo form_checkbox( array(
											'name' => 'version_force_update',
											'id' => 'version_force_update',
											'value' => 'accept',
											'checked' => false,
											'class' => 'form-check-input'
										));	
									}

									echo get_msg( 'ver_force_update' ); 

								?>

								</label>
							</div>
						</div>

						<div class="form-group">
							<div class="form-check">
								<label class="form-check-label">
								
								<?php 
								
								if( $version->version_need_clear_data == 1 ) {

									echo form_checkbox( array(
										'name' => 'version_need_clear_data',
										'id' => 'version_need_clear_data',
										'value' => 'accept',
										'checked' => true,
										'class' => 'form-check-input'
									));	

								} else {

									echo form_checkbox( array(
										'name' => 'version_need_clear_data',
										'id' => 'version_need_clear_data',
										'value' => 'accept',
										'checked' => false,
										'class' => 'form-check-input'
									));	

								}

								echo get_msg( 'ver_need_data' ); 

								?>

								</label>
							</div>
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