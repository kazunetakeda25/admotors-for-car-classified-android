<div class="table-responsive animated fadeInRight">
	<table class="table m-0 table-striped">

		<tr>
			<th><?php echo get_msg('no')?></th>
			<th><?php echo get_msg('user_name')?></th>
			<th><?php echo get_msg('user_email')?></th>
			<th><?php echo get_msg('role')?></th>
			
			

		</tr>

		<?php $count = $this->uri->segment(4) or $count = 0; ?>
			
		<?php if ( !empty( $users ) && count( $users->result()) > 0 ): ?>
				
			<?php foreach($users->result() as $user): ?>
				
				<tr>
					<td><?php echo ++$count;?></td>
					<td><?php echo $user->user_name;?></td>
					<td><?php echo $user->user_email;?></td>
					<td><?php echo $this->Role->get_name( $user->role_id );?></td>
					
					

				</tr>
			
			<?php endforeach; ?>

		<?php else: ?>
			
		<?php $this->load->view( $template_path .'/partials/no_data' ); ?>

	<?php endif; ?>

</table>
</div>