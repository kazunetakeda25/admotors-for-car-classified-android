
<?php
	$attributes = array( 'id' => 'apikey-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>
	
<section class="content animated fadeInRight">
	<div class="col-md-6">
	<div class="card card-info">
	    <div class="card-header">
	        <h3 class="card-title"><?php echo get_msg('apikey_info')?></h3>
	    </div>
        <!-- /.card-header -->
        <div class="card-body">
            <div class="row">
             	<div class="col-md-12">
            		<div class="form-group">
                   		<label>
							<?php echo get_msg('key_label')?>
							<a href="#" class="tooltip-ps" data-toggle="tooltip" title="<?php echo get_msg('key_label')?>">
								<span class='glyphicon glyphicon-info-sign menu-icon'>
							</a>
						</label>

						
						<input type="text" name="key" onkeypress="return (event.charCode > 48 && 
						 event.charCode < 57) || (event.charCode > 96 && event.charCode < 123)" 
						 placeholder="Full Name" value="<?php echo $apikey->key; ?>" class="form-control">
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