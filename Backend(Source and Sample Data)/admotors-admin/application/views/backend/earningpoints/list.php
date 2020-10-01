<div class="table-responsive animated fadeInRight">
	<table class="table m-0 table-striped">
		<tr>
			<th><?php echo get_msg('no'); ?></th>
			<th><?php echo get_msg('user_name'); ?></th>
			<th><?php echo get_msg('wallpaper_name'); ?></th>
			<th><?php echo get_msg('earn_point_label'); ?></th>
			<th><?php echo get_msg('currency_symbol'); ?></th>
			<th><?php echo get_msg('wallpaper_img'); ?></th>
		</tr>
		
		<?php $count = $this->uri->segment(4) or $count = 0; ?>

		<?php if ( !empty( $earnpoints ) && count( $earnpoints->result()) > 0 ): ?>

			<?php foreach($earnpoints->result() as $earn): ?>
				
				<tr>
					<td><?php echo ++$count;?></td>
					<td><?php echo $this->User->get_one($earn->user_id)->user_name; ?></td>
					<td><?php echo $this->Wallpaper->get_one($earn->wallpaper_id)->wallpaper_name; ?></td>
					<td><?php echo $earn->earn_point; ?></td>
					<td><?php echo $earn->currency_symbol; ?></td>
					<?php $default_photo = get_default_photo( $earn->wallpaper_id, 'wallpaper' ); ?>	
						<?php 
							$photo_width = $default_photo->img_width;
							$photo_height = $default_photo->img_height;
							$width = "";
							$height = "";
							if ( $photo_width > $photo_height ) {
								$width = "150px";
								$height = "100px";
							} elseif ( $photo_width < $photo_height) {
								$width = "80px";
								$height = "100px";
							} else {
								$width = "100px";
								$height = "100px";
							}
						?>
					<td><img style="width: <?php echo $width ?>;height: <?php echo $height ?>;" src="<?php echo img_url( '/thumbnail/'. $default_photo->img_path ); ?>"/></td>

				</tr>

			<?php endforeach; ?>

		<?php else: ?>
				
			<?php $this->load->view( $template_path .'/partials/no_data' ); ?>

		<?php endif; ?>

	</table>
</div>