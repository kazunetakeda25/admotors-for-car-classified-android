<!--Revenue CHART -->
<div class="card-header" style="border-top: 2px solid red;">
  <h3 class="card-title">
     <span class="badge badge-warning" style="height: 30px; padding: 10px; font-size: 14px;">
         <?php echo $panel_title; ?>
     </span>
  </h3>

  <div class="card-tools">
    <button type="button" class="btn btn-tool" data-widget="collapse"><i class="fa fa-minus"></i>
    </button>
    <button type="button" class="btn btn-tool" data-widget="remove"><i class="fa fa-times"></i>
    </button>
  </div>
</div>

<div class="box-body chart-responsive">
  <div class="chart" id="line-chart" style="height: 230px;"></div>
</div>

<?php 
    $jan_count = count($this->Itemreport->get_item_report(array('added_date'=>'1'))->result());
    $feb_count = count($this->Itemreport->get_item_report(array('added_date'=>'2'))->result());
    $mar_count = count($this->Itemreport->get_item_report(array('added_date'=>'3'))->result());
    $apr_count = count($this->Itemreport->get_item_report(array('added_date'=>'4'))->result());
    $may_count = count($this->Itemreport->get_item_report(array('added_date'=>'5'))->result());
    $jun_count = count($this->Itemreport->get_item_report(array('added_date'=>'6'))->result());
    $jul_count = count($this->Itemreport->get_item_report(array('added_date'=>'7'))->result());
    $aug_count = count($this->Itemreport->get_item_report(array('added_date'=>'8'))->result());
    $sep_count = count($this->Itemreport->get_item_report(array('added_date'=>'9'))->result());
    $oct_count = count($this->Itemreport->get_item_report(array('added_date'=>'10'))->result());
    $nov_count = count($this->Itemreport->get_item_report(array('added_date'=>'11'))->result());
    $dec_count = count($this->Itemreport->get_item_report(array('added_date'=>'12'))->result());

?>

<script>
  $(function () {
    "use strict";

    //Line CHART
    var monthNames = ["", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    ];
    Morris.Line({
        element: 'line-chart',
        data: [
            {y: 1, a: <?php echo $jan_count; ?>},
            {y: 2, a: <?php echo $feb_count; ?>},
            {y: 3, a: <?php echo $mar_count; ?>},
            {y: 4, a: <?php echo $apr_count; ?>},
            {y: 5, a: <?php echo $may_count; ?>},
            {y: 6, a: <?php echo $jun_count; ?>},
            {y: 7, a: <?php echo $jul_count; ?>},
            {y: 8, a: <?php echo $aug_count; ?>},
            {y: 9, a: <?php echo $sep_count; ?>},
            {y: 10, a: <?php echo $oct_count; ?>},
            {y: 11, a: <?php echo $nov_count; ?>},
            {y: 12, a: <?php echo $dec_count; ?>}
        ],
        xkey: 'y',
        parseTime: false,
        ykeys: ['a'],
        xLabelFormat: function (x) {
            var index = parseInt(x.src.y);
            return monthNames[index];
        },
        xLabels: "month",
        labels: ['Transaction'],
        lineColors: ['#00ffb2'],
        lineWidth        : 2,
        hideHover: 'auto'

    });

  });
</script>