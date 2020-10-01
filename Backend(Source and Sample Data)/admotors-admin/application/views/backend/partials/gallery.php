
<?php $this->load->view( $template_path .'/partials/nav' ); ?>

<div class="container">
	<div class="row">
		<div class="col-3 sidebar teamps-sidebar-open">
			
			<?php $this->load->view( $template_path .'/partials/sidebar' ); ?>
		</div>
		

		<div class="col-9 main teamps-sidebar-push">
			
			<?php 
				// load breadcrumb
				show_breadcrumb( $action_title );

				// show flash message
				flash_msg();
			?>

			<div class="wrapper wrapper-content animated fadeInRight">
				
			<?php $this->load->view( $template_path .'/components/gallery' ); ?>

			</div>

		</div>
	</div>
</div>