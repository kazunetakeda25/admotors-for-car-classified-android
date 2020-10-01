<div class="table-responsive animated fadeInRight">
	<div class="card-header">
	  <h3 class="card-title">
	  	<?php echo get_msg('contact_info_label')?>
	  </h3>
	</div>

	<div class="card-body p-0">
		<table class="table m-0 table-striped">
			<tr>
				<th><?php echo get_msg('contact_name')?></th>
				<td><?php echo $contact->contact_name;?></td>
			</tr>
			<tr>
				<th><?php echo get_msg('contact_email')?></th>
				<td><?php echo $contact->contact_email;?></td>
			</tr>
			<tr>
				<th><?php echo get_msg('contact_phone')?></th>
				<td><?php echo $contact->contact_phone;?></td>
			</tr>
			<tr>
				<th><?php echo get_msg('about_contact_label')?></th>
				<td><?php echo $contact->contact_message;?></td>
			</tr>
		</table>
	</div>

	<div class="card-footer text-center">
		<a class="btn btn-primary" href="<?php echo $module_site_url ?>" class="btn"><?php echo get_msg('back_button')?></a>
	</div>
</div>