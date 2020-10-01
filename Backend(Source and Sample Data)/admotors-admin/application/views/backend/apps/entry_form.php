<?php
	$attributes = array( 'id' => 'app-form', 'enctype' => 'multipart/form-data');
	echo form_open( '', $attributes);
?>

<div class="card card-info">
  <div class="card-header">
      <h3 class="card-title"><?php echo get_msg('app_setting')?></h3>
  </div>
  <!-- /.card-header -->
  <div class="card-body">
    <div class="row">
      <div class="col-md-6">
        <div class="form-group">
          <label>Home</label>
          

          <select onchange="$('#image-home').attr('src', this.options[this.selectedIndex].id);" class="form-control" name="app_home" style="width: 100%;">
              <?php
              $array = array('home_1' => base_url( 'assets/App Images/Home Page/Home_1.png'), 'home_2' => base_url( 'assets/App Images/Home Page/Home_2.png'));
                  foreach ($array as $key=>$value) {
                    
                    if($key == $app->app_home) {
                      echo '<option id="'.$value.'" value="'.$key.'" selected>'.$key.'</option>';
                    } else {
                       echo '<option id="'.$value.'" value="'.$key.'">'.$key.'</option>';
                    }
                  }
              ?>
          </select>
        <img src="<?php echo $value ?>" name="image-home" id="image-home" style="width: 50%;height:30%;padding-left: 30px;padding-top: 10px;">

        </div>


       <div class="form-group">
          <label>Grid</label>
          
          <select onchange="$('#grid').attr('src', this.options[this.selectedIndex].id);" class="form-control" name="app_grid" style="width: 100%;">
              <?php
              $array = array('grid_1' => base_url( 'assets/App Images/Grid Page/Grid_1.jpg'), 'grid_2' => base_url( 'assets/App Images/Grid Page/Grid_2.jpg'));
                  foreach ($array as $key=>$value) {
                    
                    if($key == $app->app_grid) {
                      echo '<option id="'.$value.'" value="'.$key.'" selected>'.$key.'</option>';
                    } else {
                       echo '<option id="'.$value.'" value="'.$key.'">'.$key.'</option>';
                    }
                  }
              ?>
              <input type="hidden" name="" value="">
          </select>
        <img src="<?php echo $value ?>" name="grid" value="<?php echo $value; ?>" id="grid" style="width: 50%;height:50%;padding-left: 30px;padding-top: 10px;">

         
        </div>

        <div class="form-group">
          <label>Detail</label>
          
          <select onchange="$('#detail').attr('src', this.options[this.selectedIndex].id);" class="form-control" name="app_detail" style="width: 100%;">
              <?php
              $array = array('detail_1' => base_url( 'assets/App Images/Detail Page/Detail_1.png'), 'detail_2' => base_url( 'assets/App Images/Detail Page/Detail_2.jpg'));
                  foreach ($array as $key=>$value) {
                    
                    if($key == $app->app_detail) {
                      echo '<option id="'.$value.'" value="'.$key.'" selected>'.$key.'</option>';
                    } else {
                       echo '<option id="'.$value.'" value="'.$key.'">'.$key.'</option>';
                    }
                  }
              ?>
             
          </select>

        <img src="<?php echo $value ?>" name="detail" id="detail" style="width: 50%;height:50%;padding-left: 30px;padding-top: 10px;">


        </div>
       
      </div>
    </div>
  </div>
  <!-- card body -->
  <div class="card-footer">
    <button type="submit" class="btn btn-sm btn-primary">
      <?php echo get_msg('btn_save')?>
    </button>
  </div>
</div>


<?php echo form_close(); ?>