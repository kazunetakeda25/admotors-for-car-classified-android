
<div class="card-header">
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

<!-- /.card-header -->
<div class="card-body">
  <div class="row">
    <div class="col-md-6">
      <div class="chart-responsive">
        <canvas id="pieChart" height="150"></canvas>
      </div>
    </div>

    <!-- /.col -->

    <div class="col-md-6">
      <ul class="chart-legend clearfix">
          <?php 
            $color = array("1"=>"#dc3545","2"=>"#28a745","3"=>"#ffc107","4"=>"#17a2b8","5"=>"#007bff");
            $circle_color = array("1"=>"text-danger","2"=>"text-success","3"=>"text-warning","4"=>"text-info","5"=>"text-primary");
            $i = 0;
            foreach ($data as $d):
                $i++;
                $data_str .= "{
                      value    : '".$d->t_count."',
                      color    : '" .$color[$i]."',
                      highlight: '".$color[$i]."',
                      label    : '".$d->title."'
                   },
                    ";
          ?>
         
        <li><i class="fa fa-circle-o <?php echo $circle_color[$i]; ?>"></i><?php echo $d->title; ?>(<?php echo $d->t_count; ?> touches )</li>
        <?php endforeach; ?>
      </ul>
    </div>
    
    <!-- /.col -->
  </div>
  <!-- /.row -->
</div>
<!-- /.card-body -->

<div class="card-footer text-center">
  <a href="<?php echo $url; ?>"><?php echo get_msg('view_all_label'); ?></a>
</div>




<script>
   //-------------
  //- PIE CHART -
  //-------------

  // Get context with jQuery - using jQuery's .get() method.
  var pieChartCanvas = $('#pieChart').get(0).getContext('2d')
  var pieChart       = new Chart(pieChartCanvas)
  var PieData        = [
    <?php echo $data_str; ?>
  ]
  var pieOptions     = {
    //Boolean - Whether we should show a stroke on each segment
    segmentShowStroke    : true,
    //String - The colour of each segment stroke
    segmentStrokeColor   : '#fff',
    //Number - The width of each segment stroke
    segmentStrokeWidth   : 1,
    //Number - The percentage of the chart that we cut out of the middle
    percentageInnerCutout: 50, // This is 0 for Pie charts
    //Number - Amount of animation steps
    animationSteps       : 100,
    //String - Animation easing effect
    animationEasing      : 'easeOutBounce',
    //Boolean - Whether we animate the rotation of the Doughnut
    animateRotate        : true,
    //Boolean - Whether we animate scaling the Doughnut from the centre
    animateScale         : false,
    //Boolean - whether to make the chart responsive to window resizing
    responsive           : true,
    // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
    maintainAspectRatio  : false,
    //String - A legend template
    legendTemplate       : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<segments.length; i++){%><li><span style="background-color:<%=segments[i].fillColor%>"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>',
    //String - A tooltip template
    tooltipTemplate      : '<%=value %> <%=label%>'
  }
  //Create pie or douhnut chart
  // You can switch between pie and douhnut using the method below.
  pieChart.Doughnut(PieData, pieOptions)
  //-----------------
  //- END PIE CHART -
</script>
