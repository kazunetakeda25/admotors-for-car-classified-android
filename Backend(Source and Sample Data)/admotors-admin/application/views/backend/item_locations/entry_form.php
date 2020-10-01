<?php
	$attributes = array( 'id' => 'location-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>
	
<section class="content animated fadeInRight">
	<div class="col-md-12">
		<div class="card card-info">
		    <div class="card-header">
		        <h3 class="card-title"><?php echo get_msg('location_info')?></h3>
		    </div>
	        <!-- /.card-header -->
	        <div class="card-body">
	            <div class="row">
	            	<div class="col-md-6">
	            		<div class="form-group">
	                   		<label>
	                   			<span style="font-size: 17px; color: red;">*</span>
								<?php echo get_msg('location_name')?>
								<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('cat_name_tooltips')?>">
									<span class='glyphicon glyphicon-info-sign menu-icon'>
								</a>
							</label>

							<?php echo form_input( array(
								'name' => 'name',
								'value' => set_value( 'name', show_data( @$location->name ), false ),
								'class' => 'form-control form-control-sm',
								'placeholder' => get_msg( 'type_name' ),
								'id' => 'name'
							)); ?>
	              		</div>
	              	</div>

	              	<div class="col-md-6">
	              		
	              		<div id="location_map" style="width: 700px; height: 400px;"></div>

	        			<div class="clearfix">&nbsp;</div>

	        			<div class="form-group">
			              <label><?php echo get_msg('itm_lat_label') ?>
			                <a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('city_lat_label')?>">
			                  <span class='glyphicon glyphicon-info-sign menu-icon'>
			                </a>
			              </label>

			              <br>

			              <?php 
			                echo form_input( array(
			                  'type' => 'text',
			                  'name' => 'lat',
			                  'id' => 'lat',
			                  'class' => 'form-control',
			                  'placeholder' => '',
			                  'value' => set_value( 'lat', show_data( @$location->lat ), false ),
			                ));
			              ?>
			            </div>

			            <div class="form-group">
			              <label><?php echo get_msg('itm_lng_label') ?>
			                <a href="#" class="tooltip-ps" data-toggle="tooltip" 
			                  title="<?php echo get_msg('city_lng_tooltips')?>">
			                  <span class='glyphicon glyphicon-info-sign menu-icon'>
			                </a>
			              </label>

			              <br>

			              <?php 
			                echo form_input( array(
			                  'type' => 'text',
			                  'name' => 'lng',
			                  'id' => 'lng',
			                  'class' => 'form-control',
			                  'placeholder' => '',
			                  'value' => set_value( 'lng', show_data( @$location->lng ), false ),
			                ));
			              ?>
			            </div>
			            <!-- form group -->
		            </div>
            		
	              		
	            <!-- /.row -->
	        	</div>
	        <!-- /.card-body -->
	   		</div>
	   		<?php 
				if ( isset( $location )) { 
			?>
				<input type="hidden" id="edit_location" name="edit_location" value="1">
			<?php		
				} else {
			?>
				<input type="hidden" id="edit_location" name="edit_location" value="0">
			<?php } ?> 
			<div class="card-footer">
	            <button type="submit" class="btn btn-sm btn-primary">
					<?php echo get_msg('btn_save')?>
				</button>

				<a href="<?php echo $module_site_url; ?>" class="btn btn-sm btn-primary">
					<?php echo get_msg('btn_cancel')?>
				</a>
	        </div>
	       
		</div>

	</div>
</section>
				

	
	

<?php echo form_close(); ?>