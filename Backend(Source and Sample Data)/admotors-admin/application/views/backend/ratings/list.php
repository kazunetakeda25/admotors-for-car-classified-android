<div class="table-responsive animated fadeInRight">
	<table class="table m-0 table-striped">
		<tr>
			<th><?php echo get_msg('no'); ?></th>
			<th><?php echo get_msg('user_name'); ?></th>
			<th><?php echo get_msg('overall_rating'); ?></th>
		</tr>
		
		<?php $count = $this->uri->segment(4) or $count = 0; ?>

		<?php if ( !empty( $ratings ) && count( $ratings->result()) > 0 ): ?>

			<?php foreach($ratings->result() as $rate): ?>
				<tr>
					<td><?php echo ++$count;?></td>
					<td><?php echo $rate->user_name; ?></td>					
					<td><?php echo $rate->overall_rating . ' stars';?></td>
				</tr>

			<?php endforeach; ?>

		<?php else: ?>
				
			<?php $this->load->view( $template_path .'/partials/no_data' ); ?>

		<?php endif; ?>

	</table>
</div>