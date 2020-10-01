
<script>

	function jqvalidate() {

		$('#wallpaper-form').validate({
			rules:{
				
				"images[]":{
					required: true
				},
				cat_id:{
					indexCheck : ""
				},
				modes:{
					indexCheck : ""
				},
				types:{
					indexCheck: ""
				},
				point:{
					pointChecking : ""
				}
			},
			messages:{
				
				"images[]":{
					required: "Please File Upload Photo."
				},
				cat_id:{
					indexCheck: "Please Select Category Name."
				},
				modes:{
					indexCheck: "Please Select Modes."
				},
				types:{
					indexCheck: "Please Select Types."
				},
				point:{
					pointChecking : "Please Fill Point."
				}
			}
		});

		// custom validation
		jQuery.validator.addMethod("indexCheck",function( value, element ) {
			
		   if(value == 0) {
		    	return false;
		   } else {
		    	return true;
		   };
			   
		});
		
		jQuery.validator.addMethod("blankCheck",function( value, element ) {
			
			   if(value == "") {
			    	return false;
			   } else {
			    	return true;
			   }
		})


		jQuery.validator.addMethod("pointChecking",function( value, element ) {
			
			   
			   if($("#types").val() == 2) {
			   		
			   		if(value == "" || value == 0) {
			   			return false;
			   		} else {
			   			return true;
			   		}

			   } else {
			   		return true;
			   }

		})

	}

	function runAfterJQ() {

		$('.delete-img').click(function(e){
			e.preventDefault();

			// get id and image
			var id = $(this).attr('id');

			// do action
			var action = '<?php echo $module_site_url .'/delete_cover_photo/'; ?>' + id + '/<?php echo @$wallpaper->wallpaper_id; ?>';
			console.log( action );
			$('.btn-delete-image').attr('href', action);
		});

		$('.image-popup-vertical-fit').magnificPopup({
		type: 'image',
		closeOnContentClick: true,
		mainClass: 'mfp-img-mobile',
		image: {
			verticalFit: true
		}
		
	});

	$(document).ready(function() {
		$('.thumbnail').magnificPopup({
			delegate: 'a',
			type: 'image',
		});
	});

	$("#types").change(function () {
		    if(this.value==1) { //3rd radiobutton
	            $("#point").attr("disabled", "disabled"); 
	            $("#point").val(0);
	        }
	        else {
	            $("#point").removeAttr("disabled"); 
	        }
	});

	$('input[name="point"]').keyup(function(e)
                                {
	  if (/\D/g.test(this.value))
	  {
	    // Filter non-digits from input value.
	    this.value = this.value.replace(/\D/g, '');
	  }
	});

	//For File Upload 
	$("#fileUpload").on('change', function () {

	     //Get count of selected files
	     var countFiles = $(this)[0].files.length;

	     var imgPath = $(this)[0].value;
	     var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
	     var image_holder = $("#image-holder");
	     image_holder.empty();

	     if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
	         if (typeof (FileReader) != "undefined") {

	             //loop for each file selected for uploaded.
	             for (var i = 0; i < countFiles; i++) {

	                 var reader = new FileReader();
	                 reader.onload = function (e) {
	                     $("<img />", {
	                         "src": e.target.result,
	                             "class": "thumb-image"
	                     }).appendTo(image_holder);
	                 }

	                 image_holder.show();
	                 reader.readAsDataURL($(this)[0].files[i]);
	             }

	         } else {
	             alert("This browser does not support FileReader.");
	         }
	     } else {
	         alert("Pls select only images");
	     }
	 });


}
</script>

<?php 
	// replace cover photo modal
	$data = array(
		'title' => get_msg('upload_photo'),
		'img_type' => 'wallpaper',
		'img_parent_id' => @$wallpaper->wallpaper_id
	);

	$this->load->view( $template_path .'/components/photo_upload_modal', $data );

	// delete cover photo modal
	$this->load->view( $template_path .'/components/delete_cover_photo_modal' ); 
?>