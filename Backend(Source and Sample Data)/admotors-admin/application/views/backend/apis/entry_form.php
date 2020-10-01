
<?php
	$attributes = array( 'id' => 'api-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>
	<h5><?php echo get_msg('api_info')?></h5>

	<table class="table table-striped table-bordered mt-3 animated fadeInRight">

		<?php $count = $this->uri->segment(4) or $count = 0; ?>

		<?php if ( !empty( $apis ) && count( $apis->result()) > 0 ): ?>

			<?php foreach ( $apis->result() as $api ): ?>
				
				<tr>
					<td><?php echo ++$count;?></td>
					<td><?php echo $api->api_name;?></td>
					<td><?php echo get_msg( 'api_order_by' ); ?></td>
					
					<td>
					<?php 
						$options = $api_constants[$api->api_constant];

						echo form_dropdown(
							'order_by_field[]',
							$options,
							set_value( 'order_by_field[]', @$api->order_by_field ),
							'class="form-control form-control-sm" id="order_by_field"'
						);
					?>
					</td>

					<td>
					<?php 
						$options = array( 'asc' => 'Ascending', 'desc' => 'Descending');

						echo form_dropdown(
							'order_by_type[]',
							$options,
							set_value( 'order_by_type[]', @$api->order_by_type ),
							'class="form-control form-control-sm" id="order_by_type"'
						);
					?>
					</td>

				</tr>

				<input type="hidden" name="api_id[]" value="<?php echo $api->api_id; ?>"/>

			<?php endforeach; ?>

		<?php else: ?>
				
			<?php $this->load->view( $template_path .'/partials/no_data' ); ?>

		<?php endif; ?>

	</table>	
	
	<button type="submit" name="save" class="btn btn-sm btn-primary">
		<?php echo get_msg('btn_save')?>
	</button>
	
	</a>

<?php echo form_close(); ?>
