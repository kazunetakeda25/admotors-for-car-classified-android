<div class="table-responsive animated fadeInRight">
	<table class="table m-0 table-striped">
		<tr>
			<th><?php echo get_msg('no'); ?></th>
			<th><?php echo get_msg('wallpaper_name'); ?></th>
			<th><?php echo get_msg('wallpaper_cat'); ?></th>
			<th><?php echo get_msg('wallpaper_img'); ?></th>
			<th><?php echo get_msg('free_premium'); ?></th>
			<th><?php echo get_msg('wallpaper_point'); ?></th>
			
			<?php if ( $this->ps_auth->has_access( EDIT )): ?>
				
				<th><?php echo get_msg('btn_edit')?></th>
			
			<?php endif; ?>
		</tr>
		
		<?php $count = $this->uri->segment(4) or $count = 0; ?>

		<?php if ( !empty( $pendings ) && count( $pendings->result()) > 0 ): ?>

			<?php foreach($pendings->result() as $pending): ?>
				
				<tr>
					<td><?php echo ++$count;?></td>
					<td><?php echo $pending->wallpaper_name;?></td>
					<td><?php echo $this->Category->get_one($pending->cat_id)->cat_name;?></td>

					<?php $default_photo = get_default_photo( $pending->wallpaper_id, 'wallpaper' ); ?>	
					
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
					<td>
						<?php if ( $pending->types == 1 ): ?>
							<button class="btn btn-default" style="font-weight: bold;background-color: #4cceac;width: 80px;">Free</button>
						<?php else:?>
							<button class="btn btn-default" style="font-weight: bold;background-color: #f49389;width: 80px;">Premium</button>
						<?php endif;?>
					</td>

					<td>
						<?php echo $pending->point; ?>
					</td>

					<?php if ( $this->ps_auth->has_access( EDIT )): ?>
				
						<td>
							<a href='<?php echo $module_site_url .'/edit/'. $pending->wallpaper_id; ?>'>
								<i style='font-size: 18px;' class='fa fa-pencil-square-o'></i>
							</a>
						</td>
					
					<?php endif; ?>

				</tr>

			<?php endforeach; ?>

		<?php else: ?>
				
			<?php $this->load->view( $template_path .'/partials/no_data' ); ?>

		<?php endif; ?>

	</table>
</div>