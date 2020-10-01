<script>
	function jqvalidate() {

		$(document).ready(function(){
			$('#app-form').validate({
				rules:{
					title:{
						required: true,
						minlength: 4
					}
				},
				messages:{
					title:{
						required: "Please fill title.",
						minlength: "The length of title must be greater than 4"
					}
				}
			});
		});

	
	}
</script>