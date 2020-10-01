package com.panaceasoft.admotors.ui.item.map.mapFilter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentMapFilteringBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

public class MapFilteringFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private String[] seekBarValues = {"0.5", "1", "2.5", "5", "10", "25", "50", "100", "200", "500", "All"};

    private int currentIndex = 1;

    private ItemParameterHolder itemParameterHolder;

    private GoogleMap map;

    private MarkerOptions markerOptions = null;

    private static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private Circle circle;

    private Bundle bundle;

    @VisibleForTesting
    private AutoClearedValue<FragmentMapFilteringBinding> binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentMapFilteringBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map_filtering, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        bundle = savedInstanceState;

        setHasOptionsMenu(true);

        return binding.get().getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_map_filtering, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.apply) {

            if (getActivity() != null) {

                if (currentIndex != seekBarValues.length - 1) {
                    //itemParameterHolder.mapMiles = String.valueOf(Utils.roundDouble(Float.parseFloat(seekBarValues[currentIndex]) * 0.621371, 3));
                    itemParameterHolder.mapMiles = getMiles(Float.parseFloat(seekBarValues[currentIndex]));
                    itemParameterHolder.location_id = Constants.EMPTY_STRING;
                } else {

                    resetTheValues();
                }

                navigationController.navigateBackToSearchFromMapFiltering(this.getActivity(), itemParameterHolder);
            }
        } else if (item.getItemId() == R.id.reset) {

            resetTheValues();

        }

        return super.onOptionsItemSelected(item);
    }

    private String getMiles(Float kmValue) {
        return String.valueOf(Utils.roundDouble(kmValue * 0.621371, 3));
    }

    private void initializeMap(Bundle savedInstanceState) {

        if (getActivity() != null) {
            itemParameterHolder = (ItemParameterHolder) getActivity().getIntent().getSerializableExtra(Constants.ITEM_HOLDER);

            if(!itemParameterHolder.lat.equals("") && !itemParameterHolder.lng.equals("") ) {
                itemParameterHolder.mapMiles = getMiles(100.0F);
            }
        }

        try {
            if (this.getActivity() != null) {
                MapsInitializer.initialize(this.getActivity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.get().mapView.onCreate(savedInstanceState);
        binding.get().mapView.onResume();

        binding.get().mapView.getMapAsync(googleMap -> {
            map = googleMap;

            updateUI(itemParameterHolder);

            checkToDisableSeekBar();

            map.setOnMapClickListener(latLng -> {

                if (markerOptions == null) {
                    markerOptions = new MarkerOptions();
                }

                checkToDisableSeekBar();

                // Creating a marker
                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                markerOptions.draggable(false);

                itemParameterHolder.lat = String.valueOf(latLng.latitude);
                itemParameterHolder.lng = String.valueOf(latLng.longitude);

                // Clears the previously touched position
                map.clear();



                int zoomlevel = 15;

                if(circle != null) {
                    zoomlevel = getZoomLevel(circle) ;
                    //Toast.makeText(getContext(), "aZ" + zoomlevel, Toast.LENGTH_SHORT).show();
                }

                // Animating to the touched position
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomlevel));

                // Placing a marker
                // the touched position
                map.addMarker(markerOptions);

                if (currentIndex == seekBarValues.length - 1) {
                    currentIndex = seekBarValues.length - 2;
                    binding.get().seekBar.setProgress(seekBarValues.length - 2);
                }

                drawCircle(latLng.latitude, latLng.longitude, seekBarValues[currentIndex]);


            });

            map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {

                    itemParameterHolder.lat = String.valueOf(marker.getPosition().latitude);
                    itemParameterHolder.lng = String.valueOf(marker.getPosition().longitude);

                    if (currentIndex == seekBarValues.length - 1) {
                        currentIndex = seekBarValues.length - 2;
                        binding.get().seekBar.setProgress(seekBarValues.length - 2);
                    }

                    if (!itemParameterHolder.lat.isEmpty() && !itemParameterHolder.lng.isEmpty()) {
                        drawCircle(marker.getPosition().latitude, marker.getPosition().longitude, seekBarValues[currentIndex]);
                    }
                }
            });

            getMyLocation();

        });
    }


    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {

        if (binding != null) {
            if (binding.get() != null) {
                if (binding.get().mapView != null) {

                    binding.get().mapView.onDestroy();

                    if (map != null) {
                        map.clear();
                    }

                }
            }
        }
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        binding.get().mapView.onLowMemory();
        super.onLowMemory();

    }

    @Override
    public void onPause() {
        binding.get().mapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (binding != null) {
            binding.get().mapView.onResume();
        }

    }

    @Override
    protected void initUIAndActions() {

        itemParameterHolder = new ItemParameterHolder();

        binding.get().startPointTextView.setText(String.format("%s km", seekBarValues[0]));

        binding.get().seekBar.setMax(10);

        binding.get().seekBar.setProgress(currentIndex);

        binding.get().seekBarValueTextView.setText(String.format("%s km", seekBarValues[currentIndex]));

        binding.get().seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentIndex = progress;

                if (progress != seekBarValues.length - 1) {

                    binding.get().seekBarValueTextView.setText(String.format("%s km", seekBarValues[progress]));

                    if (markerOptions == null) {
                        markerOptions = new MarkerOptions();
                    }

                    if(!itemParameterHolder.lat.isEmpty() && !itemParameterHolder.lng.isEmpty())
                    {
                        map.addMarker(markerOptions
                                .position(new LatLng(Double.valueOf(itemParameterHolder.lat), Double.valueOf(itemParameterHolder.lng)))
                                .title("City Name")
                                .draggable(false));
                    }

                } else {
                    binding.get().seekBarValueTextView.setText(seekBarValues[progress]);

                    if (map != null) {
                        map.clear();

                        markerOptions = null;

                    }

                }

                if (!itemParameterHolder.lat.isEmpty() && !itemParameterHolder.lng.isEmpty()) {
                    drawCircle(Double.valueOf(itemParameterHolder.lat), Double.valueOf(itemParameterHolder.lng), seekBarValues[currentIndex]);

                    int zoomlevel = 15;

                    if(circle != null) {
                        zoomlevel = getZoomLevel(circle);
                        //Toast.makeText(getContext(), "aZ" + zoomlevel, Toast.LENGTH_SHORT).show();
                    }

                    // Animating to the touched position
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(itemParameterHolder.lat), Double.parseDouble(itemParameterHolder.lng)), zoomlevel));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        initializeMap(bundle);

    }

    @Override
    protected void initViewModels() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getMyLocation();

            } else {

                if (getActivity() != null) {
                    this.getActivity().finish();
                }
            }
        }
    }

    private void getMyLocation() {

        if (this.getActivity() != null) {
            if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.

//                requestPermissions(this.getActivity(),
//                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            } else {

                if (map != null) {
                    map.setMyLocationEnabled(true);
                }
            }
        }

    }

    private int getZoomLevel(Circle circle) {
        int zoomLevel = 0;
        if (circle != null){
            double radius = circle.getRadius();
            double scale = radius / 500;
            zoomLevel =(int) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }

    private void drawCircle(Double lat, Double lng, String radius) {

        try {

            double temp = Double.valueOf(radius);

            if (circle != null) {

                circle.remove();
                circle = map.addCircle(new CircleOptions()
                        .center(new LatLng(lat, lng))
                        .radius(temp * 1000)
                        .strokeColor(getResources().getColor(R.color.md_blue_50))
                        .fillColor(0x220000b2));

            } else {
                circle = map.addCircle(new CircleOptions()
                        .center(new LatLng(lat, lng))
                        .radius(temp * 1000)
                        .strokeColor(getResources().getColor(R.color.md_blue_50))
                        .fillColor(0x220000b2));
            }

        } catch (NumberFormatException e) {

            if (circle != null) {
                circle.remove();
            }

            Utils.psLog(e.getMessage());
        }
    }


    private void resetTheValues() {
        itemParameterHolder.lat = "";
        itemParameterHolder.lng = "";
        itemParameterHolder.mapMiles = "";

        if (map != null) {
            map.clear();

            markerOptions = null;

            checkToDisableSeekBar();

        }

        if (circle != null) {
            circle.remove();
        }

        binding.get().seekBar.setProgress(seekBarValues.length);

    }

    private int findTheIndexOfTheValue(String value) {
        int index = 0;

        for (int i = 0; i < seekBarValues.length - 1; i++) {
            if (!value.equals("All")) {
                //if (String.valueOf(Utils.roundDouble(Float.parseFloat(seekBarValues[i]) * 0.621371, 3)).equals(value)) {
                if (getMiles(Float.parseFloat(seekBarValues[i])).equals(value)) {
                    index = i;
                }
            } else {
                index = seekBarValues.length - 1;
            }

        }

        return index;
    }

    private void updateUI(ItemParameterHolder itemParameterHolder) {

        if (!itemParameterHolder.lat.isEmpty() && !itemParameterHolder.lng.isEmpty() && !itemParameterHolder.mapMiles.isEmpty()) {

            if (map != null) {
                map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(Double.valueOf(itemParameterHolder.lat), Double.valueOf(itemParameterHolder.lng))).zoom(10).bearing(10).tilt(10).build()));

                if (markerOptions == null) {
                    markerOptions = new MarkerOptions();
                }

                map.addMarker(markerOptions
                        .position(new LatLng(Double.valueOf(itemParameterHolder.lat), Double.valueOf(itemParameterHolder.lng)))
                        .title("City Name")
                        .draggable(false));

                currentIndex = findTheIndexOfTheValue(itemParameterHolder.mapMiles);

                drawCircle(Double.valueOf(itemParameterHolder.lat), Double.valueOf(itemParameterHolder.lng), seekBarValues[currentIndex]);

                binding.get().seekBar.setProgress(currentIndex);

            }

        } else {

            binding.get().seekBar.setProgress(seekBarValues.length);

            currentIndex = seekBarValues.length - 1;

        }
    }

    private void checkToDisableSeekBar() {
        if (markerOptions == null) {
            binding.get().seekBar.setEnabled(false);
        } else {
            binding.get().seekBar.setEnabled(true);

        }
    }
}
