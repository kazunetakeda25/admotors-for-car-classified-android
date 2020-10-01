        <!-- jQuery UI 1.11.4 -->
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
          $.widget.bridge('uibutton', $.ui.button)
        </script>
        <!-- Bootstrap 4 -->
        <script src="<?php echo base_url( 'assets/plugins/bootstrap/js/bootstrap.bundle.min.js' ); ?>"></script>
        <!-- Morris.js charts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="<?php echo base_url( 'assets/plugins/morris/morris.min.js' ); ?>"></script>
        <!-- Sparkline -->
        <script src="<?php echo base_url( 'assets/plugins/sparkline/jquery.sparkline.min.js' ); ?>"></script>
        <!-- jvectormap -->
        <script src="<?php echo base_url( 'assets/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js' ); ?>"></script>
        <script src="<?php echo base_url( 'assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js' ); ?>"></script>
        <!-- jQuery Knob Chart -->
        <script src="<?php echo base_url( 'assets/plugins/knob/jquery.knob.js' ); ?>"></script>
        <!-- daterangepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
        <script src="<?php echo base_url( 'assets/plugins/daterangepicker/daterangepicker.js' ); ?>"></script>
        <!-- color picker -->
         <script src="<?php echo base_url( 'assets/plugins/colorpicker/bootstrap-colorpicker.min.js' ); ?>"></script>
        <!-- datepicker -->
        <script src="<?php echo base_url( 'assets/plugins/datepicker/bootstrap-datepicker.js' ); ?>"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="<?php echo base_url( 'assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js' ); ?>"></script>
        <!-- Slimscroll -->
        <script src="<?php echo base_url( 'assets/plugins/slimScroll/jquery.slimscroll.min.js' ); ?>"></script>
        <!-- FastClick -->
        <script src="<?php echo base_url( 'assets/plugins/fastclick/fastclick.js' ); ?>"></script>
        <!-- AdminLTE App(This is sidebar and nav action) -->
        <script src="<?php echo base_url( 'assets/dist/js/adminlte.js' ); ?>"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="<?php echo base_url( 'assets/dist/js/demo.js' ); ?>"></script>
       <!-- openstreetmap leaflet js -->
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css" integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ==" crossorigin=""/>
        <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==" crossorigin=""></script>
        <!-- Load Esri Leaflet from CDN -->
        <script src="https://unpkg.com/esri-leaflet"></script>

        <!-- Esri Leaflet Geocoder -->
        <link rel="stylesheet" href="https://unpkg.com/esri-leaflet-geocoder/dist/esri-leaflet-geocoder.css">
        <script src="https://unpkg.com/esri-leaflet-geocoder"></script>
        <script>
            <?php
                if (isset($item)) {
                    $lat = $item->lat;
                    $lng = $item->lng;
            ?>
                    var itm_map = L.map('itm_location').setView([<?php echo $lat;?>, <?php echo $lng;?>], 5);
            <?php
                } else {
            ?>
                    var itm_map = L.map('itm_location').setView([0, 0], 5);
            <?php
                }
            ?>

            const itm_attribution =
            '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
            const itm_tileUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
            const itm_tiles = L.tileLayer(itm_tileUrl, { itm_attribution });
            itm_tiles.addTo(itm_map);
            <?php if(isset($item)) {?>
                var itm_marker = new L.Marker(new L.LatLng(<?php echo $lat;?>, <?php echo $lng;?>));
                itm_map.addLayer(itm_marker);
                // results = L.marker([<?php echo $lat;?>, <?php echo $lng;?>]).addTo(mymap);

            <?php } else { ?>
                var itm_marker = new L.Marker(new L.LatLng(0, 0));
                //mymap.addLayer(marker2);
            <?php } ?>
            var itm_searchControl = L.esri.Geocoding.geosearch().addTo(itm_map);
            var results = L.layerGroup().addTo(itm_map);

            itm_searchControl.on('results',function(data){
                results.clearLayers();

                for(var i= data.results.length -1; i>=0; i--) {
                    itm_map.removeLayer(itm_marker);
                    results.addLayer(L.marker(data.results[i].latlng));
                    var itm_search_str = data.results[i].latlng.toString();
                    var itm_search_res = itm_search_str.substring(itm_search_str.indexOf("(") + 1, itm_search_str.indexOf(")"));
                    var itm_searchArr = new Array();
                    itm_searchArr = itm_search_res.split(",");

                    document.getElementById("lat").value = itm_searchArr[0].toString();
                    document.getElementById("lng").value = itm_searchArr[1].toString(); 
                   
                }
            })
            var popup = L.popup();

            function onMapClick(e) {

                var itm = e.latlng.toString();
                var itm_res = itm.substring(itm.indexOf("(") + 1, itm.indexOf(")"));
                itm_map.removeLayer(itm_marker);
                results.clearLayers();
                results.addLayer(L.marker(e.latlng));   

                var itm_tmpArr = new Array();
                itm_tmpArr = itm_res.split(",");

                document.getElementById("lat").value = itm_tmpArr[0].toString(); 
                document.getElementById("lng").value = itm_tmpArr[1].toString();
            }

            itm_map.on('click', onMapClick);
        </script>
        <script>
            <?php
                if (isset($location)) {
                    $lat = $location->lat;
                    $lng = $location->lng;
            ?>
                    var mymap = L.map('location_map').setView([<?php echo $lat;?>, <?php echo $lng;?>], 5);
            <?php
                } else {
            ?>
                    var mymap = L.map('location_map').setView([0, 0], 5);
            <?php
                }
            ?>

            const attribution =
            '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
            const tileUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
            const tiles = L.tileLayer(tileUrl, { attribution });
            tiles.addTo(mymap);
            <?php if(isset($location)) {?>
                var marker2 = new L.Marker(new L.LatLng(<?php echo $lat;?>, <?php echo $lng;?>));
                mymap.addLayer(marker2);
                // results = L.marker([<?php echo $lat;?>, <?php echo $lng;?>]).addTo(mymap);

            <?php } else { ?>
                var marker2 = new L.Marker(new L.LatLng(0, 0));
                //mymap.addLayer(marker2);
            <?php } ?>
            var searchControl = L.esri.Geocoding.geosearch().addTo(mymap);
            var results = L.layerGroup().addTo(mymap);

            searchControl.on('results',function(data){
                results.clearLayers();

                for(var i= data.results.length -1; i>=0; i--) {
                    mymap.removeLayer(marker2);
                    results.addLayer(L.marker(data.results[i].latlng));
                    var search_str = data.results[i].latlng.toString();
                    var search_res = search_str.substring(search_str.indexOf("(") + 1, search_str.indexOf(")"));
                    var searchArr = new Array();
                    searchArr = search_res.split(",");

                    document.getElementById("lat").value = searchArr[0].toString();
                    document.getElementById("lng").value = searchArr[1].toString(); 
                   
                }
            })
            var popup = L.popup();

            function onMapClick(e) {

                var str = e.latlng.toString();
                var str_res = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                mymap.removeLayer(marker2);
                results.clearLayers();
                results.addLayer(L.marker(e.latlng));   

                var tmpArr = new Array();
                tmpArr = str_res.split(",");

                document.getElementById("lat").value = tmpArr[0].toString(); 
                document.getElementById("lng").value = tmpArr[1].toString();
            }

            mymap.on('click', onMapClick);
        </script>
         <script>
            <?php
                if (isset($app)) {
                    $lat = $app->lat;
                    $lng = $app->lng;
            ?>
                    var app_map = L.map('app_location').setView([<?php echo $lat;?>, <?php echo $lng;?>], 5);
            <?php
                } else {
            ?>
                    var app_map = L.map('app_location').setView([0, 0], 5);
            <?php
                }
            ?>

            const app_attribution =
            '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
            const app_tileUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
            const app_tiles = L.tileLayer(app_tileUrl, { app_attribution });
            app_tiles.addTo(app_map);
            <?php if(isset($app)) {?>
                var app_marker = new L.Marker(new L.LatLng(<?php echo $lat;?>, <?php echo $lng;?>));
                app_map.addLayer(app_marker);
                // results = L.marker([<?php echo $lat;?>, <?php echo $lng;?>]).addTo(mymap);

            <?php } else { ?>
                var app_marker = new L.Marker(new L.LatLng(0, 0));
                //mymap.addLayer(marker2);
            <?php } ?>
            var app_searchControl = L.esri.Geocoding.geosearch().addTo(app_map);
            var results = L.layerGroup().addTo(app_map);

            app_searchControl.on('results',function(data){
                results.clearLayers();

                for(var i= data.results.length -1; i>=0; i--) {
                    app_map.removeLayer(app_marker);
                    results.addLayer(L.marker(data.results[i].latlng));
                    var app_search_str = data.results[i].latlng.toString();
                    var app_search_res = app_search_str.substring(app_search_str.indexOf("(") + 1, app_search_str.indexOf(")"));
                    var app_searchArr = new Array();
                    app_searchArr = app_search_res.split(",");

                    document.getElementById("lat").value = app_searchArr[0].toString();
                    document.getElementById("lng").value = app_searchArr[1].toString(); 
                   
                }
            })
            var popup = L.popup();

            function onMapClick(e) {

                var app = e.latlng.toString();
                var app_res = app.substring(app.indexOf("(") + 1, app.indexOf(")"));
                app_map.removeLayer(app_marker);
                results.clearLayers();
                results.addLayer(L.marker(e.latlng));   

                var app_tmpArr = new Array();
                app_tmpArr = app_res.split(",");

                document.getElementById("lat").value = app_tmpArr[0].toString(); 
                document.getElementById("lng").value = app_tmpArr[1].toString();
            }

            app_map.on('click', onMapClick);
        </script>

		<?php show_analytic(); ?>
        <script src="<?php echo base_url( 'assets/validator/jquery.validate.js' ); ?>"></script>
         
		<script type="text/javascript">
			
			// functions to run after jquery is loaded
			if ( typeof runAfterJQ == "function" ) runAfterJQ();

			<?php if ( $this->config->item( 'client_side_validation' ) == true ): ?>
				
				// functions to run after jquery is loaded
				if ( typeof jqvalidate == "function" ) jqvalidate();

			<?php endif; ?>

            $('.page-sidebar-menu li').removeClass('active');

            // highlight submenu item
            $('li a[href="' + this.location.pathname + '"]').parent().addClass('active');

            // Highlight parent menu item.
            $('ul a[href="' + this.location.pathname + '"]').parents('li').addClass('active');

            

		</script>

        <script>
  
          $(function () {
              //Date range picker
            $('#reservation').daterangepicker()

            })

        </script>

		<?php if ( isset( $load_gallery_js )) : ?>

			<?php $this->load->view( $template_path .'/components/gallery_script' ); ?>	

		<?php endif; ?>

	</div>
 <!-- ./ wrapper -->
</body>
</html>