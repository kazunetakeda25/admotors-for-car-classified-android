<script>
	function jqvalidate() {
		//alert('asdadasd');
		$(document).ready(function(){
			$('#massupload-form').validate({
				rules:{
					name:{
						required: true
					}
				},
				messages:{
					name:{
						required: "Please select the csv file."
					}
				}
			});
		});
	}

</script>