<ul class="breadcrumb" style="background-color: #ffff; margin-left: -10px;">

	<li class="breadcrumb-item">
		<a href="<?php echo $be_url; ?>">
			<?php echo get_msg( 'dashboard' ); ?>
		</a>
	</li>

	<?php if ( !empty( $urls )): ?>

	<li class="breadcrumb-item">
		<span class="divider"></span>

		<a href="<?php echo $module_site_url; ?>">
			<?php echo ucfirst( strtolower( $module_name )); ?>
		</a>
	</li>	

		<?php if ( !is_array( $urls )): ?>
		
		<li class="breadcrumb-item">
			<span class="divider"></span>
			
			<?php echo $urls; ?>

		</li>

		<?php else: ?>

			<?php  foreach ( $urls as $url ):  ?>

				<li class="breadcrumb-item">

					<span class="divider"></span>

					<?php if ( !empty( $url['url'] )): ?>

						<?php $link = $be_url .'/'. strtolower( $module_name ) .'/'. $url['url']; ?>
					
						<a href="<?php echo $link; ?>">
							
							<?php echo $url['label']; ?>
						
						</a>
					
					<?php else: ?>
				
						<?php echo $url['label']; ?>

					<?php endif; ?>
				</li>

			<?php endforeach; ?>

		<?php endif; ?>

	<?php else: ?>

	<li class="breadcrumb-item">
		<?php echo ucfirst( strtolower( $module_name )); ?>
	</li>	

	<?php endif; ?>

</ul>