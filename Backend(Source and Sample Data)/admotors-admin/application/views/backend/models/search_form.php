<div class='row my-3'>
	<div class='col-6'>
	<?php
		$attributes = array('class' => 'form-inline');
		echo form_open( $module_site_url .'/search', $attributes);
	?>
		
		<div class="form-group mr-3">

			<?php echo form_input(array(
				'name' => 'searchterm',
				'value' => set_value( 'searchterm' ),
				'class' => 'form-control form-control-sm',
				'placeholder' => get_msg( 'btn_search' )
			)); ?>

	  	</div>

	  	<div class="form-group" style="padding-right: 3px;">

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
					set_value( 'manufacturer_id', show_data( @$models->manufacturer_id), false ),
					'class="form-control form-control-sm mr-3" id="manufacturer_id"'
				);
			?>

	  	</div>

	  	<div class="form-group" style="padding-right: 2px;">
		  	<button type="submit" class="btn btn-sm btn-primary">
		  		<?php echo get_msg( 'btn_search' )?>
		  	</button>
	  	</div>

	  	<div class="form-group">
		  	<a href='<?php echo $module_site_url; ?>' class='btn btn-sm btn-primary'>
				<?php echo get_msg( 'btn_reset' )?>
			</a>
	  	</div>
	
	<?php echo form_close(); ?>

	</div>	

	<div class='col-6'>
		<a href='<?php echo $module_site_url .'/add';?>' class='btn btn-sm btn-primary pull-right'>
			<span class='fa fa-plus'></span> 
			<?php echo get_msg( 'model_add' )?>
		</a>
	</div>

</div>