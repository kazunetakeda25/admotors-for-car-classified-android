<div class="table-responsive animated fadeInRight">
	<table class="table m-0 table-striped">
		<tr>
			<th><?php echo get_msg('no'); ?></th>
			<th><?php echo get_msg('wallpaper_name'); ?></th>
			<th><?php echo get_msg('download_count'); ?></th>
			<th><?php echo get_msg('wallpaper_img'); ?></th>
		</tr>
		
		<?php $count = $this->uri->segment(4) or $count = 0; ?>

		<?php if ( !empty( $downloads ) && count( $downloads->result()) > 0 ): ?>

			<?php foreach($downloads->result() as $down): ?>
				
				<tr>
					<td><?php echo ++$count;?></td>
					
					<td><?php echo $this->Wallpaper->get_one($down->wallpaper_id)->wallpaper_name;?></td>
					
					<td><?php 

					
						echo $down->download_count;
						

					?></td>
					<?php $default_photo = get_default_photo( $down->wallpaper_id, 'wallpaper' ); ?>	
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