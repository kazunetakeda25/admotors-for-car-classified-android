<script>
function runAfterJQ() {

	$(document).ready(function(){

		$(document).delegate('.ban','click',function(){
			var btn = $(this);
			var id = $(this).attr('userid');

			$.ajax({
				url: "<?php echo $module_site_url .'/ban/';?>"+id,
				method:'GET',
				success:function(msg){
					if(msg == 'true')
						btn.addClass('unban btn-danger')
							.removeClass('btn-primary-green ban')
							.html('Unban');
					else
						console.log( 'System error occured. Please contact your system administrator.' );
				}
			});
		});
		
		$(document).delegate('.unban','click',function(){
			var btn = $(this);
			var id = $(this).attr('userid');

			$.ajax({
				url: "<?php echo $module_site_url .'/unban/';?>"+id,
				method:'GET',
				success:function(msg){
					if(msg == 'true')
						btn.addClass('ban btn-primary-green')
							.removeClass('btn-danger unban')
							.html('Ban');
					else
						console.log( 'System error occured. Please contact your system administrator.' );
				}
			});
		});

		// Delete Trigger
		$('.btn-delete').click(function(){

			// get id and links
			var id = $(this).attr('id');
			var btnYes = $('.btn-yes').attr('href');
			var btnNo = $('.btn-no').attr('href');

			// modify link with id
			$('.btn-yes').attr( 'href', btnYes + id );
			$('.btn-no').attr( 'href', btnNo + id );
		});

	});
}
</script>
<?php
	// Delete Confirm Message Modal
	$data = array(
		'title' => get_msg( 'delete_user_label' ),
		'message' => get_msg( 'user_delete_confirm_message' ) .'<br>',
		'yes_all_btn' => get_msg( 'user_yes_all_label' ),
		'no_only_btn' => get_msg( 'user_no_only_label' )
	);
	
	$this->load->view( $template_path .'/components/delete_confirm_modal', $data );
?>