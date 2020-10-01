<div class="table-responsive animated fadeInRight">
	<table class="table m-0 table-striped">
		<tr>
			<th><?php echo get_msg('no'); ?></th>
			<th><?php echo get_msg('wallpaper_name'); ?></th>
			<th><?php echo get_msg('user_name'); ?></th>
			<th><?php echo get_msg('wallpaper_img'); ?></th>
		</tr>
		
		<?php $count = $this->uri->segment(4) or $count = 0; ?>

		<?php if ( !empty( $favourites ) && count( $favourites->result()) > 0 ): ?>

			<?php foreach($favourites->result() as $favourite): ?>
				
				<tr>
					<td><?php echo ++$count;?></td>
					<td><?php echo $this->Wallpaper->get_one($favourite->wallpaper_id)->wallpaper_name;?></td>
					<td><?php echo $this->User->get_one($favourite->user_id)->user_name;?></td>
					<?php $default_photo = get_default_photo( $favourite->wallpaper_id, 'wallpaper' ); ?>	
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