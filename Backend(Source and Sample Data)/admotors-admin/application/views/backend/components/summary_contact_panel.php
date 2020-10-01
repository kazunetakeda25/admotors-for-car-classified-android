
  <div class="card-header">
    <h3 class="card-title">
      <span class="badge badge-warning" style="height: 30px; padding: 10px; font-size: 14px;">

        <?php echo get_msg('total_label'); ?>
        <?php echo get_msg('divider_label'); ?>
        <?php echo $total_count; ?>
        <?php echo get_msg('messages_label'); ?>

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
  <div class="card-body p-0" style="height: 188px;">
    <ul class="products-list product-list-in-card pl-2 pr-2">
      <?php if ( ! empty( $data )): ?>
        <?php foreach($data as $d): ?>
          <?php $wallpaper_count = $this->Contact->count_all_by(array("contact_id" => $d->contact_id)); ?>
          <li class="item">
            <div class="product-img">
              <img src="<?php echo base_url('assets/dist/img/email.png'); ?>" alt="Product Image" class="img-size-50">
            </div>
            <div class="product-info">
              <?php echo $d->contact_name; ?>
                <span class="float-right"> By : <?php echo $d->contact_email; ?></span>
              <span class="product-description">
                <?php echo $d->contact_message; ?>
              </span>
            </div>
          </li>
        <?php endforeach; ?>
      <?php endif; ?>
    </ul>
  </div>

  <!-- /.card-body -->
  <div class="card-footer text-center">
    <a href="<?php echo site_url('admin/contacts'); ?>" class="uppercase">
     <?php echo get_msg('view_all_label'); ?>
    </a>
  </div>
