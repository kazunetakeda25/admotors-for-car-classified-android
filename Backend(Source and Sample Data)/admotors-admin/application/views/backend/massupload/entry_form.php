
<?php
	$attributes = array( 'id' => 'massupload-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>
	
<section class="content animated fadeInRight">
	<div class="col-md-6">
	<div class="card card-info">
	    <div class="card-header">
	        <h3 class="card-title"><?php echo get_msg('massupload_info')?></h3>
	    </div>
        <!-- /.card-header -->
        <div class="card-body">
            <div class="row">
             	<div class="col-md-12">
            		<div class="form-group">
                   		


                   		<div class="form-group">
							
                   			<?php
                   				if( $message ) {
                   					echo "<br>";
                   					echo $message;
                   					echo "<br>";
                   				}
                   			 ?>

							<span style="font-size: 17px; color: red;">*</span>
							<label>
								<?php echo get_msg('select_csv_file');?> 
							</label>


							<br/>

							<input class="btn btn-sm" type="file" name="file" id="file">
						</div>

						<br>
						<label>
							<?php echo get_msg('csv_upload_instruction');?> 
						</label>

              		</div>
                </div>
                <!-- col-md-6 -->
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
</div>
    <!-- card info -->
</section>
				
<?php echo form_close(); ?>