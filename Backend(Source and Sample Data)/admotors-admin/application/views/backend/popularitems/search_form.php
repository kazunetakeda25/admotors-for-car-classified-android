<?php
	$attributes = array('id' => 'search-form', 'enctype' => 'multipart/form-data');
	echo form_open( $module_site_url .'/search', $attributes);
?>

<div class='row my-3'>
	<div class="col-12">
		<div class='form-inline'>
			<div class="form-group" style="padding-top: 3px;padding-right: 2px;">

				<?php echo form_input(array(
					'name' => 'searchterm',
					'value' => set_value( 'searchterm', $searchterm ),
					'class' => 'form-control form-control-sm mr-3',
					'placeholder' => 'Search'
				)); ?>

		  	</div>

		  	<div class="form-group mr-3">
	  			<div class="input-group">
				    <div class="input-group-prepend">
				      <span class="input-group-text">
				        <i class="fa fa-calendar"></i>
				      </span>
				    </div>
					   <?php echo form_input(array(
										'name' => 'date',
										'value' => set_value( 'date', $date),
										'class' => 'form-control',
										'placeholder' => '',
										'id' => 'reservation',
										'size' => '30',
										'readonly' => 'readonly'
									)); ?>

		 		</div>
	  		</div>

		  	<div class="form-group" style="padding-top: 3px;padding-right: 2px;">

				<?php
					$options=array();
					$options[0]=get_msg('model_search_manu');
					
					$manufacturers = $this->Manufacturer->get_all( );
					foreach($manufacturers->result() as $manu) {
						
							$options[$manu->id]=$manu->name;
					}
					
					echo form_dropdown(
						'manufacturer_id',
						$options,
						set_value( 'manufacturer_id', show_data( $manufacturer_id ), false ),
						'class="form-control form-control-sm mr-3" id="manufacturer_id"'
					);
				?> 

		  	</div>

	  		<div class="form-group" style="padding-top: 3px;">

				<?php
					if($selected_cat_id != "") {

						$options=array();
						$options[0]=get_msg('Prd_search_subcat');
						$conds['manufacturer_id'] = $selected_manufacturer_id;
						$models = $this->Model->get_all_by($conds);
						foreach($models->result() as $mod) {
							$options[$mod->id]=$mod->name;
						}
						echo form_dropdown(
							'model_id',
							$options,
							set_value( 'model_id', show_data( $model_id ), false ),
							'class="form-control form-control-sm mr-3" id="model_id"'
						);

					} else {

						$conds['manufacturer_id'] = $selected_manufacturer_id;
						$options=array();
						$options[0]=get_msg('select_model');

						echo form_dropdown(
							'model_id',
							$options,
							set_value( 'model_id', show_data( $model_id ), false ),
							'class="form-control form-control-sm mr-3" id="model_id"'
						);
					}

				?>

		  	</div>

		  	<div class="form-group" style="padding-top: 3px;padding-right: 2px;">

				<?php
					$options=array();
					$options[0]=get_msg('itm_select_type');
					
					$itemtypes = $this->Itemtype->get_all( );
					foreach($itemtypes->result() as $type) {
						
						$options[$type->id]=$type->name;
					}
					
					echo form_dropdown(
						'item_type_id',
						$options,
						set_value( 'item_type_id', show_data( $item_type_id ), false ),
						'class="form-control form-control-sm mr-3" id="item_type_id"'
					);
				?> 

		  	</div>

		  	<div class="form-group" style="padding-top: 3px;padding-right: 2px;">

				<?php
					$options=array();
					$options[0]=get_msg('itm_select_price');
					
					$pricetypes = $this->Pricetype->get_all( );
					foreach($pricetypes->result() as $price) {
						
						$options[$price->id]=$price->name;
					}
					
					echo form_dropdown(
						'item_price_type_id',
						$options,
						set_value( 'item_price_type_id', show_data( $item_price_type_id ), false ),
						'class="form-control form-control-sm mr-3" id="item_price_type_id"'
					);
				?> 

		  	</div>

		  	<div class="form-group mr-3" style="padding-top: 3px;padding-right: 2px;">

				<?php
					$options=array();
					$options[0]=get_msg('itm_select_currency');
					
					$currencies = $this->Currency->get_all( );
					foreach($currencies->result() as $currency) {
						
						$options[$currency->id]=$currency->currency_short_form;
					}
					
					echo form_dropdown(
						'item_currency_id',
						$options,
						set_value( 'item_currency_id', show_data( $item_currency_id ), false ),
						'class="form-control form-control-sm mr-3" id="item_currency_id"'
					);
				?> 

		  	</div>

		  		<div class="form-group" style="padding-top: 3px;padding-right: 2px;">
						
				<select class="form-control form-control-sm mr-3" name="status" id="status">
							
					<option value="0"><?php echo get_msg('select_status_label');?></option>

						<?php
							$array = array('Published' => 1, 'Reported' => 2);
	    					foreach ($array as $key=>$value) {

	    						if($value == $status) {
		    						echo '<option value="'.$value.'" selected>'.$key.'</option>';
		    					} else {
		    						echo '<option value="'.$value.'">'.$key.'</option>';
		    					}
	    					}
						?>
				</select> 
			</div>

		  	<div class="form-group" style="padding-top: 3px;padding-right: 5px;">
			  	<button type="submit" value="submit" name="submit" class="btn btn-sm btn-primary">
			  		<?php echo get_msg( 'btn_search' )?>
			  	</button>
		  	</div>
		
			<div class="form-group" style="padding-top: 3px;">
			  	<a href='<?php echo $module_site_url .'/index';?>' class='btn btn-sm btn-primary'>
					<?php echo get_msg( 'btn_reset' )?>
				</a>
		  	</div>

		</div>
	</div>

</div>

<?php echo form_close(); ?>

<script>
	
	
<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>
	function jqvalidate() {

		$('#manufacturer_id').on('change', function() {

			var value = $('option:selected', this).text().replace(/Value\s/, '');

			var manuId = $(this).val();

			$.ajax({
				url: '<?php echo $module_site_url . '/get_all_model/';?>' + manuId,
				method: 'GET',
				dataType: 'JSON',
				success:function(data){
					$('#model_id').html("");
					$.each(data, function(i, obj){
					    $('#model_id').append('<option value="'+ obj.id +'">' + obj.name+ '</option>');
					});
					$('#name').val($('#name').val() + " ").blur();
					$('#model_id').trigger('change');
				}
			});
		});

	}

<?php endif; ?>
</script>