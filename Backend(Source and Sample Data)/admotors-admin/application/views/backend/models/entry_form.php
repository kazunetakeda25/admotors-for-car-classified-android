<?php
	$attributes = array( 'id' => 'model-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>

	<section class="content animated fadeInRight">
		<div class="card card-info">
          <div class="card-header">
            <h3 class="card-title"><?php echo get_msg('model_info')?></h3>
          </div>

			<form role="form">
        		<div class="card-body">
					<div class="row">
						<div class="col-md-5">
							<div class="form-group">
								<label> <span style="font-size: 17px; color: red;">*</span>
									<?php echo get_msg('model_search_manu')?>
								</label>

								<?php
									$options=array();
									$options[0]=get_msg('model_search_manu');
									$manufacturers = $this->Manufacturer->get_all();
										foreach($manufacturers->result() as $manu) {
											$options[$manu->id]=$manu->name;
									}

									echo form_dropdown(
										'manufacturer_id',
										$options,
										set_value( 'manufacturer_id', show_data( @$model->manufacturer_id), false ),
										'class="form-control form-control-sm mr-3" id="manufacturer_id"'
									);
								?>

							</div>

							<div class="form-group">
								<label> <span style="font-size: 17px; color: red;">*</span>
									<?php echo get_msg('model_name')?>
								</label>

								<?php echo form_input( array(
									'name' => 'name',
									'value' => set_value( 'name', show_data( @$model->name), false ),
									'class' => 'form-control form-control-sm',
									'placeholder' => "Please Model Name",
									'id' => 'name'
								)); ?>

							</div>
						</div>

						<div class="col-md-5" style="padding-left: 50px;">
							<?php if ( !isset( $model )): ?>

							<div class="form-group">
								
								<label> <span style="font-size: 17px; color: red;">*</span>
									<?php echo get_msg('model_img')?> 
								</label>

								<br/>

								<input class="btn btn-sm" type="file" name="cover" id="cover">
							</div>

							<?php else: ?>

								<label> <span style="font-size: 17px; color: red;">*</span>
									<?php echo get_msg('model_img')?>
								</label> 
								
								<div class="btn btn-sm btn-primary btn-upload pull-right" data-toggle="modal" data-target="#uploadImage">
									<?php echo get_msg('btn_replace_photo')?>
								</div>
								
								<hr/>
								
								<?php
									$conds = array( 'img_type' => 'model', 'img_parent_id' => $model->id );
									
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

												<img src="<?php echo $this->ps_image->upload_thumbnail_url . $img->img_path; ?>">

												<br/>
												
												<p class="text-center">
													
													<a data-toggle="modal" data-target="#deletePhoto" class="delete-img" id="<?php echo $img->img_id; ?>"   
														image="<?php echo $img->img_path; ?>">
														<?php echo get_msg('Remove'); ?>
													</a>
												</p>

											</div>

										</div>

									<?php endforeach; ?>

									</div>
								
								<?php endif; ?>

							<?php endif; ?>	


							<?php if ( !isset( $model )): ?>

								<div class="form-group">
									
									<label> <span style="font-size: 17px; color: red;">*</span>
										<?php echo get_msg('model_icon')?> 
									</label>

									<br/>

									<input class="btn btn-sm" type="file" name="icon" id="icon">
								</div>

							<?php else: ?>

								<label> <span style="font-size: 17px; color: red;">*</span>
									<?php echo get_msg('model_icon')?>
								</label> 
								
								
								<div class="btn btn-sm btn-primary btn-upload pull-right" data-toggle="modal" data-target="#uploadIcon">
									<?php echo get_msg('btn_replace_icon')?>
								</div>
								
								<hr/>
								
								<?php

									$conds = array( 'img_type' => 'model_icon', 'img_parent_id' => $model->id );
									
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

												<img src="<?php echo $this->ps_image->upload_thumbnail_url . $img->img_path; ?>">

												<br/>
												
												<p class="text-center">
													
													<a data-toggle="modal" data-target="#deleteIcon" class="delete-img" id="<?php echo $img->img_id; ?>"   
														image="<?php echo $img->img_path; ?>">
														<?php echo get_msg('Remove'); ?>
													</a>
												</p>

											</div>

										</div>

									<?php endforeach; ?>

									</div>
								
								<?php endif; ?>

							<?php endif; ?>
						</div>
					
					</div>	

				</div>
			
				<div class="card-footer">
			     	<button type="submit" class="btn btn-sm btn-primary">
						<?php echo get_msg('btn_save')?>
					</button>

					<a href="<?php echo $module_site_url; ?>" class="btn btn-sm btn-primary">
						<?php echo get_msg('btn_cancel')?>
					</a>
			    </div>

			</div>


<?php echo form_close(); ?>

</section>