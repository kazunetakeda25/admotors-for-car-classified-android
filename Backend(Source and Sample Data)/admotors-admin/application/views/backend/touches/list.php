<div class="table-responsive animated fadeInRight">
	<table class="table m-0 table-striped">
		<tr>
			<th><?php echo get_msg('no'); ?></th>
			<th><?php echo get_msg('wallpaper_name'); ?></th>
			<th><?php echo get_msg('view_count'); ?></th>
			<th><?php echo get_msg('wallpaper_img'); ?></th>
		</tr>
		
		<?php $count = $this->uri->segment(4) or $count = 0; ?>

		<?php if ( !empty( $touches ) && count( $touches->result()) > 0 ): ?>

			<?php foreach($touches->result() as $touch): ?>
				
				<tr>
					<td><?php echo ++$count;?></td>
					<td><?php echo $this->Wallpaper->get_one($touch->wallpaper_id)->wallpaper_name;?></td>
					<td><?php 
					$conds['wallpaper_id'] = $touch->wallpaper_id;
					echo $this->Touch->count_all_by($conds);

					?></td>
					<?php $default_photo = get_default_photo( $touch->wallpaper_id, 'wallpaper' ); ?>	
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
					<td>

						<?php if($default_photo->img_path != "") { ?>

						<img style="width: 

						<?php 

						echo $width ?>;height: 
						<?php echo $height ?>;" src="<?php echo img_url( '/thumbnail/'. $default_photo->img_path ); 

						?>"/>

						<?php } else { ?>

							<img width="100px" height="100px" src="<?php echo img_url( '/placeholder_image.png' ); 

						?>">

						<?php } ?>

					</td>

				</tr>

			<?php endforeach; ?>

		<?php else: ?>
				
			<?php $this->load->view( $template_path .'/partials/no_data' ); ?>

		<?php endif; ?>

	</table>
</div>