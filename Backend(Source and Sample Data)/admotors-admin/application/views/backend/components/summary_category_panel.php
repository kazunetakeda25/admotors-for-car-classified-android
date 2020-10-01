 <!-- Category LIST -->
	<div class="card-header">
		<h3 class="card-title">
			<span class="badge badge-warning" style="height: 30px; padding: 10px; font-size: 14px;">

			    <?php echo get_msg('total_label'); ?>
		      	<?php echo get_msg('divider_label'); ?>
		      	<?php echo $total_count; ?>
		      	<?php echo get_msg('categories_label'); ?>
		      	
			</span>
		</h3>

		<div class="card-tools">
			
			

		    <button type="button" class="btn btn-tool" data-widget="collapse">
		    	<i class="fa fa-minus"></i>
		    </button>
		    <button type="button" class="btn btn-tool" data-widget="remove">
		    	<i class="fa fa-times"></i>
		    </button>
		</div>
	</div>
	<!-- /.card-header -->
    <div class="card-body p-0" style="height: 150px;">
    	<br>
    	<ul class="users-list clearfix">
    		<?php if ( ! empty( $data )): ?>
          		<?php foreach($data as $d): ?>
          			<?php $category = get_default_photo( $d->cat_id, 'category-icon' ); ?>
					<?php $category_count = $this->Wallpaper->count_all_by(array("cat_id" => $d->cat_id)); ?>
          			<li>
			            <div class="crop-circle">
			            <img src="<?php echo img_url( '/thumbnail/'. $category->img_path); ?>" style="border-radius: 50%; width:60px; height:60px;">
			            </div>
			            <a class="users-list-name" href="<?php echo site_url('/admin/categories/edit/' . $d->cat_id )  ; ?>"><?php echo $d->cat_name; ?></a>
          			</li>
       			
       		 	<?php endforeach; ?>
			<?php endif; ?>
		 </ul>
	</div>
	<!-- /.card-body -->
	<div class="card-footer text-center">
		<a href="<?php echo site_url('admin/categories'); ?>"><?php echo get_msg('view_all_label'); ?></a>
	</div>
<!-- /.card-footer -->

