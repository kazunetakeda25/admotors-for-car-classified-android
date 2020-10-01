<?php
	$attributes = array( 'id' => 'wallpaper-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>
	
<section class="content animated fadeInRight">
	<div class="card card-info">
	    <div class="card-header">
	        <h3 class="card-title"><?php echo get_msg('wallpaper_info')?></h3>
	    </div>
        <!-- /.card-header -->
        <div class="card-body">
            <div class="row">
             	<div class="col-md-6">
            		<div class="form-group">
						<label>
							<span style="font-size: 17px; color: red;">*</span>
							<?php echo get_msg('wallpaper_name')?>
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('wallpaper_name_tooltips')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'>
							</a>
						</label>

						<?php echo form_input( array(
							'name' => 'wallpaper_name',
							'value' => set_value( 'wallpaper_name', show_data( @$pending->wallpaper_name ), false ),
							'class' => 'form-control form-control-sm',
							'placeholder' => get_msg( 'wallpaper_name' ),
							'id' => 'wallpaper_name',
							'readonly' => 'readonly'
						)); ?>

					</div>

                  	<div class="form-group">
						<label>
							<?php echo get_msg('wallpaper_search_tags')?>
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('wallpaper_search_tags_tooltips')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'/>
							</a>
						</label>

						<?php echo form_input( array(
							'name' => 'wallpaper_search_tags',
							'value' => set_value( 'wallpaper_search_tags', show_data( @$pending->wallpaper_search_tags ), false ),
							'class' => 'form-control form-control-sm',
							'placeholder' => get_msg( 'wallpaper_search_tags' ),
							'id' => 'wallpaper_search_tags',
							'readonly' => 'readonly'
						)); ?>

					</div>

					<div class="form-group">
						<label>
							<span style="font-size: 17px; color: red;">*</span>
							<?php echo get_msg('wallpaper_types')?>
						</label>

						<select class="form-control" name="types" id="types" disabled="true">
							<option><?php echo get_msg('select_wallpaper_types');?></option>

							<?php
							$array = array('Free' => 1, 'Premium' => 2);
		    					foreach ($array as $key=>$value) {
		    						
		    						if($value == $pending->types) {
			    						echo '<option value="'.$value.'" selected>'.$key.'</option>';
			    					} else {
			    						echo '<option value="'.$value.'">'.$key.'</option>';
			    					}
		    					}
							?>
						</select>

					</div>

					<div class="form-group">
						<label>
							<span style="font-size: 17px; color: red;">*</span>
							<?php echo get_msg('wallpaper_point')?>
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('wallpaper_point')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'/>
							</a>
						</label>

						<?php echo form_input( array(
							'name' => 'point',
							'value' => set_value( 'point', show_data( @$pending->point ), false ),
							'class' => 'form-control form-control-sm',
							'placeholder' => get_msg( 'wallpaper_point' ),
							'id' => 'point',
							'readonly' => 'readonly'
						)); ?>

					</div>

					<div class="form-group">
						<label>
							<?php echo get_msg('credit')?>
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('wallpaper_search_tags_tooltips')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'/>
							</a>
						</label>

						<?php echo form_input( array(
							'name' => 'credit',
							'value' => set_value( 'credit', show_data( @$wallpaper->credit ), false ),
							'class' => 'form-control form-control-sm',
							'placeholder' => get_msg( 'credit' ),
							'id' => 'credit',
							'readonly' => 'readonly'
						)); ?>

					</div>
					
					<?php if ( !isset( $pending )): ?>

					<div class="form-group">
					
						<label>
							<span style="font-size: 17px; color: red;">*</span>
							<?php echo get_msg('wallpaper_img')?>
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('wallpaper_photo_tooltips')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'>
							</a>
						</label>

						<br/>

						<input class="btn btn-sm" type="file" name="images1">
					</div>

					<?php else: ?>

					<label>
						<span style="font-size: 17px; color: red;">*</span>
						<?php echo get_msg('wallpaper_img')?>
						<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('wallpaper_photo_tooltips')?>">
							<span class='glyphicon glyphicon-info-sign menu-icon'>
						</a>
					</label> 
					
					
					
					<hr/>
					
					<?php
						$conds = array( 'img_type' => 'wallpaper', 'img_parent_id' => $pending->wallpaper_id );
						$images = $this->Image->get_all_by( $conds )->result();
					?>
						
					<?php if ( count($images) > 0 ): ?>
						
						<div class="row">

						<?php $i = 0; foreach ( $images as $img ) :?>

							<?php if ($i>0 && $i%3==0): ?>
									
							</div><div class='row'>
							
							<?php endif; ?>
								
							<div class="col-md-4" style="height:100">

								<div class="thumbnail">
									<?php 
										$photo_width = $default_photo->img_width;
										$photo_height = $default_photo->img_height;
										$width = "";
										$height = "";
										if ( $photo_width > $photo_height ) {
											$width = "450px";
											$height = "300px";
										} elseif ( $photo_width < $photo_height) {
											$width = "240px";
											$height = "300px";
										} else {
											$width = "300px";
											$height = "300px";
										}
									?>
									<a  href="<?php echo $this->ps_image->upload_url . $img->img_path; ?>">
										<img style="width: <?php echo $width ?>;height: <?php echo $height ?>;" src="<?php echo $this->ps_image->upload_thumbnail_url . $img->img_path; ?>">
									</a>
									<br/>
									
									<p class="text-center">
										
										<a data-toggle="modal" data-target="#deletePhoto" class="delete-img" id="<?php echo $img->img_id; ?>"   
											image="<?php echo $img->img_path; ?>">
											Remove
										</a>
									</p>

								</div>

							</div>

						<?php $i++; endforeach; ?>

						</div>
					
						<?php endif; ?>

					<?php endif; ?>		
                </div>

                <div class="col-md-6">
		            <div class="form-group">
						<label>
							<?php echo get_msg( 'cat_name' )?>
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('cat_name_tooltips')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'>
							</a>
						</label>
						
						<?php 
							$options = array();
							$options[0]=get_msg('cat_select');
							foreach ( $this->Category->get_all()->result() as $cat) {

								$options[$cat->cat_id]=$cat->cat_name;
							
							}

							echo form_dropdown(
								'cat_id',
								$options,
								set_value( 'cat_id', show_data( @$pending->cat_id), false ),
								'class="form-control form-control-sm mr-3" id="cat_id" disabled',
								''
							);
						?>
						
					</div>

					<div class="form-group">
						<label>
							<?php echo get_msg('select_color')?>
						</label>

						<?php
							$options=array();
							$options[0]=get_msg('select_color');
							foreach ($this->Color->get_all()->result() as $color) {

								$options[$color->id]=$color->name;
								
							}

							echo form_dropdown(
								'color_id',
								$options,
								set_value( 'color_id', show_data( @$pending->color_id), false ),
								'class="form-control form-control-sm mr-3" id="color_id" disabled'
							);
						?>

					</div>

					<div class="form-group">
						<label>
							<span style="font-size: 17px; color: red;">*</span>
							<?php echo get_msg('wallpaper_modes_label')?>
						</label>

						<select class="form-control" name="modes" id="modes" value="<?php echo $value; ?>" disabled>
							<option value="0"><?php echo get_msg('select_wallpaper_mode');?></option>

							<?php
								
								$array = array('is_portrait' => 1, 'is_landscape' => 2, 'is_square' => 3);
								

	    						if($pending->is_portrait == 1) {
		    						echo '<option value="1" selected>is_portrait</option>';
		    					} else if( $pending->is_landscape == 1){
		    						echo '<option value="2" selected>is_landscape</option>';
		    					} else {
		    						echo '<option value="3" selected>is_square</option>';
		    					} 

			    					

							?>
						</select>

					</div>
                </div>
                <!--  col-md-6  -->

            </div>
            <hr>
            <div class="form-group" style="background-color: #edbbbb; padding: 20px;">
						<label>
							<strong><?php echo get_msg('select_status')?></strong>
						</label>

						<select id="wallpaper_is_published" name="wallpaper_is_published" class="form-control">
						   <option value="1">Approved</option>
						   <option value="3">Reject</option>
						   
						</select>
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