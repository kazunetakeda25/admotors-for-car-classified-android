package com.panaceasoft.admotors.ui.item.entry;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentItemEntryBinding;
import com.panaceasoft.admotors.databinding.ItemEntryBottomBoxBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.image.ImageViewModel;
import com.panaceasoft.admotors.viewmodel.item.ItemViewModel;
import com.panaceasoft.admotors.viewobject.Image;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemEntryFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private String manufacturerId = Constants.EMPTY_STRING;
    private String modelId = Constants.EMPTY_STRING;
    private String typeId = Constants.EMPTY_STRING;
    private String priceTypeId = Constants.EMPTY_STRING;
    private String dealOptionId = Constants.EMPTY_STRING;
    private String conditionId = Constants.EMPTY_STRING;
    private String locationId = Constants.EMPTY_STRING;
    private String currencyId = Constants.EMPTY_STRING;
    private String businessMode = Constants.EMPTY_STRING;
    private String licenceStatus = Constants.EMPTY_STRING;
    private String transmissionId = Constants.EMPTY_STRING;
    private String itemColorId = Constants.EMPTY_STRING;
    private String fuelTypeId = Constants.EMPTY_STRING;
    private String buildTypeId = Constants.EMPTY_STRING;
    private String sellerTypeId = Constants.EMPTY_STRING;
    private String firstImageId = Constants.EMPTY_STRING;
    private String secImageId = Constants.EMPTY_STRING;
    private String thirdImageId = Constants.EMPTY_STRING;
    private String fourthImageId = Constants.EMPTY_STRING;
    private String fifthImageId = Constants.EMPTY_STRING;

    private boolean isFirstImageSelected = false;
    private boolean isSecImageSelected = false;
    private boolean isThirdImageSelected = false;
    private boolean isFourthImageSelected = false;
    private boolean isFifthImageSelected = false;

    private PSDialogMsg psDialogMsg;
    private ItemViewModel itemViewModel;
    private ImageViewModel imageViewModel;
    private String imagePath = "";
    private GoogleMap map;
    private Marker marker;
    private List<String> imagePathList = new ArrayList<>();
    private boolean selected = false;
    private int imageCount = 0;
    private ProgressDialog progressDialog;
    private boolean isUploadSuccess = false;
    private Calendar dateTime = Calendar.getInstance();

    @VisibleForTesting
    private AutoClearedValue<FragmentItemEntryBinding> binding;
    private AutoClearedValue<BottomSheetDialog> mBottomSheetDialog;
    private AutoClearedValue<ItemEntryBottomBoxBinding> bottomBoxLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentItemEntryBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_entry, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);
        setHasOptionsMenu(true);
        initializeMap(savedInstanceState);

        return binding.get().getRoot();
    }

    private void initializeMap(Bundle savedInstanceState) {
        try {
            if (this.getActivity() != null) {
                MapsInitializer.initialize(this.getActivity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.get().mapView.onCreate(savedInstanceState);
        bindMap(selectedLat, selectedLng);

    }

    private void bindMap(String latValue, String lngValue) {
        binding.get().mapView.onResume();

        binding.get().mapView.getMapAsync(googleMap -> {
            map = googleMap;

            try {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.valueOf(latValue), Double.valueOf(lngValue)))
                        .title("City Name"));

                //zoom
                if (!latValue.isEmpty() && !lngValue.isEmpty()) {
                    int zoomLevel = 15;
                    // Animating to the touched position
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latValue), Double.parseDouble(lngValue)), zoomLevel));
                }
            } catch (Exception e) {
                Utils.psErrorLog("", e);
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE__SEARCH_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_MANUFACTURER) {

            this.manufacturerId = data.getStringExtra(Constants.MANUFACTURER_ID);
            binding.get().manufacturerTextView.setText(data.getStringExtra(Constants.MANUFACTURER_NAME));
            itemViewModel.holder.manufacturer_id = this.manufacturerId;
            this.modelId = "";
            itemViewModel.holder.model_id = this.modelId;
            binding.get().modelTextView.setText("");

        } else if (requestCode == Constants.REQUEST_CODE__SEARCH_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_MODEL) {
            this.modelId = data.getStringExtra(Constants.MODEL_ID);
            binding.get().modelTextView.setText(data.getStringExtra(Constants.MODEL_NAME));
            itemViewModel.holder.model_id = this.modelId;
        } else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_TYPE) {

            this.typeId = data.getStringExtra(Constants.ITEM_TYPE_ID);
            binding.get().typeTextView.setText(data.getStringExtra(Constants.ITEM_TYPE_NAME));
            itemViewModel.holder.type_id = this.typeId;
        } else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_PRICE_TYPE) {

            this.priceTypeId = data.getStringExtra(Constants.ITEM_PRICE_TYPE_ID);
            binding.get().priceTypeTextView.setText(data.getStringExtra(Constants.ITEM_PRICE_TYPE_NAME));
            itemViewModel.holder.price_type_id = this.priceTypeId;
        } else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_CURRENCY_TYPE) {

            this.currencyId = data.getStringExtra(Constants.ITEM_CURRENCY_TYPE_ID);
            binding.get().priceTextView.setText(data.getStringExtra(Constants.ITEM_CURRENCY_TYPE_NAME));
            itemViewModel.holder.currency_id = this.currencyId;
        } else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_TRANSMISSION) {

            this.transmissionId = data.getStringExtra(Constants.TRANSMISSION);
            binding.get().transmissionTextView.setText(data.getStringExtra(Constants.TRANSMISSION_NAME));
            itemViewModel.holder.transmissionId = this.transmissionId;
        }else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE_SEARCH_WITH_ITEM_COLOR) {

            this.itemColorId = data.getStringExtra(Constants.ITEM_COLOR_ID);
            binding.get().colorTextView.setText(data.getStringExtra(Constants.ITEM_COLOR_VALUE));
            itemViewModel.holder.color_id = this.itemColorId;
        }else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE_SEARCH_WITH_FUEL_TYPE) {

            this.fuelTypeId = data.getStringExtra(Constants.FUEL_TYPE_ID);
            binding.get().fuelTypeTextView.setText(data.getStringExtra(Constants.FUEL_TYPE_NAME));
            itemViewModel.holder.fuelType_id = this.fuelTypeId;
        }else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE_SEARCH_WITH_BUILD_TYPE) {

            this.buildTypeId = data.getStringExtra(Constants.BUILD_TYPE_ID);
            binding.get().buildTypeTextView.setText(data.getStringExtra(Constants.BUILD_TYPE_NAME));
            itemViewModel.holder.buildType_id = this.buildTypeId;
        }else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE_SEARCH_WITH_SELLER_TYPE) {

            this.sellerTypeId = data.getStringExtra(Constants.SELLER_TYPE_ID);
            binding.get().sellerTypeTextView.setText(data.getStringExtra(Constants.SELLER_TYPE_NAME));
            itemViewModel.holder.sellerTypeId = this.sellerTypeId;
        }
        else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_CONDITION_TYPE) {

            this.conditionId = data.getStringExtra(Constants.ITEM_CONDITION_TYPE_ID);
            binding.get().itemConditionTextView.setText(data.getStringExtra(Constants.ITEM_CONDITION_TYPE_NAME));
            itemViewModel.holder.condition_id = this.conditionId;
        } else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_LOCATION_TYPE) {

            this.locationId = data.getStringExtra(Constants.ITEM_LOCATION_TYPE_ID);
            itemViewModel.latValue = data.getStringExtra(Constants.LAT);
            itemViewModel.lngValue = data.getStringExtra(Constants.LNG);
            binding.get().locationTextView.setText(data.getStringExtra(Constants.ITEM_LOCATION_TYPE_NAME));
            itemViewModel.holder.location_id = this.locationId;

            itemViewModel.mapLat = itemViewModel.latValue;
            itemViewModel.mapLng = itemViewModel.lngValue;

            bindMap(itemViewModel.latValue, itemViewModel.lngValue);
        } else if (requestCode == Constants.RESULT_CODE__TO_MAP_VIEW && resultCode == Constants.RESULT_CODE__FROM_MAP_VIEW) {

            itemViewModel.latValue = data.getStringExtra(Constants.LAT);
            itemViewModel.lngValue = data.getStringExtra(Constants.LNG);

            changeCamera();

            bindingLatLng(itemViewModel.latValue, itemViewModel.lngValue);
        }

        //image  gallery upload

        if ((requestCode == Constants.REQUEST_CODE__FIRST_GALLERY || requestCode == Constants.REQUEST_CODE__SEC_GALLERY || requestCode == Constants.REQUEST_CODE__THIRD_GALLERY ||
                requestCode == Constants.REQUEST_CODE__FOURTH_GALLERY || requestCode == Constants.REQUEST_CODE__FIFTH_GALLERY)
                && resultCode == Constants.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            if (requestCode == Constants.REQUEST_CODE__FIRST_GALLERY) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().firstImageView, selectedImage);
                itemViewModel.firstImagePath = convertToImagePath(selectedImage, filePathColumn);
                isFirstImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__SEC_GALLERY) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().secImageView, selectedImage);
                itemViewModel.secImagePath = convertToImagePath(selectedImage, filePathColumn);
                isSecImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__THIRD_GALLERY) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().thirdImageView, selectedImage);
                itemViewModel.thirdImagePath = convertToImagePath(selectedImage, filePathColumn);
                isThirdImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__FOURTH_GALLERY) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().fouthImageView, selectedImage);
                itemViewModel.fouthImagePath = convertToImagePath(selectedImage, filePathColumn);
                isFourthImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__FIFTH_GALLERY) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().fifthImageView, selectedImage);
                itemViewModel.fifthImagePath = convertToImagePath(selectedImage, filePathColumn);
                isFifthImageSelected = true;
            }


        }

        //image camera

        if ((requestCode == Constants.REQUEST_CODE__FIRST_CAMERA || requestCode == Constants.REQUEST_CODE__SEC_CAMERA || requestCode == Constants.REQUEST_CODE__THIRD_CAMERA ||
                requestCode == Constants.REQUEST_CODE__FOURTH_CAMERA || requestCode == Constants.REQUEST_CODE__FIFTH_CAMERA) && resultCode == Constants.RESULT_OK) {
            selected = true;

            if (requestCode == Constants.REQUEST_CODE__FIRST_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().firstImageView, navigationController.photoURI);
                itemViewModel.firstImagePath = Utils.currentPhotoPath;
                isFirstImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__SEC_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().secImageView, navigationController.photoURI);
                itemViewModel.secImagePath = Utils.currentPhotoPath;//photoURI.getPath();
                isSecImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__THIRD_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().thirdImageView, navigationController.photoURI);
                itemViewModel.thirdImagePath = Utils.currentPhotoPath;
                isThirdImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__FOURTH_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().fouthImageView, navigationController.photoURI);
                itemViewModel.fouthImagePath = Utils.currentPhotoPath;
                isFourthImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__FIFTH_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindFullImageUri(binding.get().fifthImageView, navigationController.photoURI);
                itemViewModel.fifthImagePath = Utils.currentPhotoPath;
                isFifthImageSelected = true;
            }
        }

        //custom camera

        if ((requestCode == Constants.REQUEST_CODE__FIRST_CUSTOM_CAMERA || requestCode == Constants.REQUEST_CODE__SEC_CUSTOM_CAMERA || requestCode == Constants.REQUEST_CODE__THIRD_CUSTOM_CAMERA ||
                requestCode == Constants.REQUEST_CODE__FOURTH_CUSTOM_CAMERA || requestCode == Constants.REQUEST_CODE__FIFTH_CUSTOM_CAMERA) && resultCode == Constants.RESULT_CODE__ITEM_ENTRY_WITH_CUSTOM_CAMERA) {

            itemViewModel.customImageUri = data.getStringExtra(Constants.IMAGE_PATH);
            selected = true;

            if (requestCode == Constants.REQUEST_CODE__FIRST_CUSTOM_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindStorageImageUri(binding.get().firstImageView, itemViewModel.customImageUri);
                itemViewModel.firstImagePath = itemViewModel.customImageUri;
                isFirstImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__SEC_CUSTOM_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindStorageImageUri(binding.get().secImageView, itemViewModel.customImageUri);
                itemViewModel.secImagePath = itemViewModel.customImageUri;
                isSecImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__THIRD_CUSTOM_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindStorageImageUri(binding.get().thirdImageView, itemViewModel.customImageUri);
                itemViewModel.thirdImagePath = itemViewModel.customImageUri;
                isThirdImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__FOURTH_CUSTOM_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindStorageImageUri(binding.get().fouthImageView, itemViewModel.customImageUri);
                itemViewModel.fouthImagePath = itemViewModel.customImageUri;
                isFourthImageSelected = true;
            }
            if (requestCode == Constants.REQUEST_CODE__FIFTH_CUSTOM_CAMERA) {
                dataBindingComponent.getFragmentBindingAdapters().bindStorageImageUri(binding.get().fifthImageView, itemViewModel.customImageUri);
                itemViewModel.fifthImagePath = itemViewModel.customImageUri;
                isFifthImageSelected = true;
            }
        }
        //endregion
    }

    private String convertToImagePath(Uri selectedImage, String[] filePathColumn) {

        if (getActivity() != null && selectedImage != null) {
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

            selected = true;
            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);

                cursor.close();
            }
        }
        return imagePath;
    }

    @Override
    public void onDispatched() {


    }

    @Override
    protected void initUIAndActions() {

        itemViewModel.latValue = selectedLat;
        itemViewModel.lngValue = selectedLng;

        if (getActivity() instanceof MainActivity) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
            ((MainActivity) getActivity()).updateMenuIconWhite();
        }

        if (getContext() != null) {

            BottomSheetDialog mBottomSheetDialog2 = new BottomSheetDialog(getContext());
            mBottomSheetDialog = new AutoClearedValue<>(this, mBottomSheetDialog2);

            ItemEntryBottomBoxBinding bottomBoxLayoutBinding2 = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.item_entry_bottom_box, null, false);
            bottomBoxLayoutBinding = new AutoClearedValue<>(this, bottomBoxLayoutBinding2);
            mBottomSheetDialog.get().setContentView(bottomBoxLayoutBinding.get().getRoot());

        }

        psDialogMsg = new PSDialogMsg(getActivity(), false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.message__loading));
        progressDialog.setCancelable(false);

        if (Config.SHOW_ADMOB && connectivity.isConnected()) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            binding.get().adView.loadAd(adRequest);
        } else {
            binding.get().adView.setVisibility(View.GONE);
        }

        binding.get().titleEditText.setHint(R.string.search__notSet);
        binding.get().manufacturerTextView.setHint(R.string.search__notSet);
        binding.get().modelTextView.setHint(R.string.search__notSet);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        AutoClearedValue<AlertDialog.Builder> alertDialog = new AutoClearedValue<>(this, builder);
        alertDialog.get().setTitle(getResources().getString(R.string.Feature_UI__search_alert_manufacturer_title));

        binding.get().manufacturerTextView.setText("");
        binding.get().modelTextView.setText("");

        binding.get().manufacturerSelectionView.setOnClickListener(view -> navigationController.navigateToSearchActivityCategoryFragment(this.getActivity(), Constants.MANUFACTURER, manufacturerId, modelId));

        binding.get().subCategorySelectionView.setOnClickListener(view -> {

            if (manufacturerId.equals(Constants.NO_DATA) || manufacturerId.isEmpty()) {

                psDialogMsg.showWarningDialog(ItemEntryFragment.this.getString(R.string.error_message__choose_manufacturer), ItemEntryFragment.this.getString(R.string.app__ok));

                psDialogMsg.show();

            } else {
                navigationController.navigateToSearchActivityCategoryFragment(ItemEntryFragment.this.getActivity(), Constants.MODEL, manufacturerId, modelId);
            }
        });

        binding.get().transmissionCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.TRANSMISSION, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().colorCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_COLOR, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().fuelTypeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.FUEL_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().buildTypeTextView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.BUILD_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().sellerTypeTextView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.SELLER_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().typeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().itemConditionCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_CONDITION_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().priceTypeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_PRICE_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().locationCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_LOCATION_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().priceCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_CURRENCY_TYPE, typeId, priceTypeId, conditionId, dealOptionId, currencyId, locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().licenceExpirationDateTextView.setOnClickListener(view -> openDatePicker(binding.get().licenceExpirationDateTextView));
        binding.get().mapViewButton.setOnClickListener(v -> {

            map.clear();
            if(itemViewModel.itemId.equals(Constants.ADD_NEW_ITEM)) {
                navigationController.navigateToMapActivity(ItemEntryFragment.this.getActivity(), selectedLng, selectedLat, Constants.MAP_PICK);
            }else {
                navigationController.navigateToMapActivity(ItemEntryFragment.this.getActivity(), itemViewModel.mapLng, itemViewModel.mapLat, Constants.MAP_PICK);
            }

        });


        binding.get().submitButton.setOnClickListener(view -> {
            if (itemViewModel.firstImagePath == null && itemViewModel.secImagePath == null && itemViewModel.thirdImagePath == null && itemViewModel.fouthImagePath == null && itemViewModel.fifthImagePath == null) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_image), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().titleEditText.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_list_title), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().manufacturerTextView.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_manufacturer), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().modelTextView.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_modle), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().plateNumTextView.getText().toString().isEmpty()){
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_item_plate_number),getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().enginePowerTextView.getText().toString().isEmpty()){
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_item_engine_power),getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().transmissionTextView.getText().toString().isEmpty()){
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_item_transmission),getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().yearTextView.getText().toString().isEmpty()){
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_item_year),getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().typeTextView.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_type), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().itemConditionTextView.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_item_condition), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().priceEditText.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_price), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().priceTextView.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_currency_symbol), getString(R.string.app__ok));
                psDialogMsg.show();
            } else if (binding.get().descEditText.getText().toString().isEmpty()) {
                psDialogMsg.showWarningDialog(getString(R.string.item_entry_need_description), getString(R.string.app__ok));
                psDialogMsg.show();
            }  else {

                isUploadSuccess = false;

                getImagePathList();

                checkIsShop();
                checkLicenseStatus();
                if (itemViewModel.itemId != null) {
                    if (!itemViewModel.itemId.equals(Constants.ADD_NEW_ITEM)) {//edit
                        itemViewModel.setUploadItemObj(this.manufacturerId, this.modelId, this.typeId, this.priceTypeId, this.currencyId, this.conditionId, this.locationId,this.itemColorId,this.fuelTypeId,
                                this.buildTypeId,this.sellerTypeId,this.transmissionId ,binding.get().descEditText.getText().toString(), binding.get().highlightInfoEditText.getText().toString(),
                                binding.get().priceEditText.getText().toString(), businessMode, itemViewModel.is_sold_out, binding.get().titleEditText.getText().toString(),
                                binding.get().addressEditText.getText().toString(), itemViewModel.latValue, itemViewModel.lngValue, binding.get().plateNumTextView.getText().toString(),
                                binding.get().enginePowerTextView.getText().toString(),binding.get().steeringPositionTextView.getText().toString(), binding.get().numberOfOwnerTextView.getText().toString(),
                                binding.get().trimNameTextView.getText().toString(),binding.get().vehicleIdTextView.getText().toString(), binding.get().priceUnitTextView.getText().toString(),
                                binding.get().yearTextView.getText().toString(),licenceStatus,binding.get().maximumPassengersTextView.getText().toString(),
                                binding.get().numberOfDoorTextView.getText().toString(), binding.get().mileageTextView.getText().toString(),binding.get().licenceExpirationDateTextView.getText().toString(), itemViewModel.itemId, loginUserId);
                    } else {//add new item
                        itemViewModel.setUploadItemObj(this.manufacturerId, this.modelId, this.typeId, this.priceTypeId, this.currencyId, this.conditionId, this.locationId,this.itemColorId,this.fuelTypeId,
                                this.buildTypeId,this.sellerTypeId,this.transmissionId,binding.get().descEditText.getText().toString(), binding.get().highlightInfoEditText.getText().toString(),
                                binding.get().priceEditText.getText().toString(), businessMode,"", binding.get().titleEditText.getText().toString(),
                                binding.get().addressEditText.getText().toString(), itemViewModel.latValue, itemViewModel.lngValue,binding.get().plateNumTextView.getText().toString(),
                                binding.get().enginePowerTextView.getText().toString(),binding.get().steeringPositionTextView.getText().toString(), binding.get().numberOfOwnerTextView.getText().toString(),
                                binding.get().trimNameTextView.getText().toString(),binding.get().vehicleIdTextView.getText().toString(), binding.get().priceUnitTextView.getText().toString(),
                                binding.get().yearTextView.getText().toString(),licenceStatus,binding.get().maximumPassengersTextView.getText().toString(),
                                binding.get().numberOfDoorTextView.getText().toString(), binding.get().mileageTextView.getText().toString(),binding.get().licenceExpirationDateTextView.getText().toString(), "", loginUserId);
                    }

                }

                progressDialog.show();
            }

        });

        binding.get().firstImageView.setOnClickListener(v -> {
            mBottomSheetDialog.get().show();
            ButtonSheetClick(Constants.ONE);
        });

        binding.get().secImageView.setOnClickListener(v -> {
            mBottomSheetDialog.get().show();
            ButtonSheetClick(Constants.TWO);
        });

        binding.get().thirdImageView.setOnClickListener(v -> {
            mBottomSheetDialog.get().show();
            ButtonSheetClick(Constants.THREE);
        });

        binding.get().fouthImageView.setOnClickListener(v -> {
            mBottomSheetDialog.get().show();
            ButtonSheetClick(Constants.FOUR);
        });

        binding.get().fifthImageView.setOnClickListener(v -> {
            mBottomSheetDialog.get().show();
            ButtonSheetClick(Constants.FIVE);
        });


    }

    private void getImagePathList() {

        if (!itemViewModel.firstImagePath.isEmpty()) {
            imagePathList.add(itemViewModel.firstImagePath);
        }
        if (!itemViewModel.secImagePath.isEmpty()) {
            imagePathList.add(itemViewModel.secImagePath);
        }
        if (!itemViewModel.thirdImagePath.isEmpty()) {
            imagePathList.add(itemViewModel.thirdImagePath);
        }
        if (!itemViewModel.fouthImagePath.isEmpty()) {
            imagePathList.add(itemViewModel.fouthImagePath);
        }
        if (!itemViewModel.fifthImagePath.isEmpty()) {
            imagePathList.add(itemViewModel.fifthImagePath);
        }
    }

    private void checkIsShop() {

        if (binding.get().isShopCheckBox.isChecked()) {
            businessMode = Constants.ONE;
        } else {
            businessMode = Constants.ZERO;
        }
    }

    private void checkLicenseStatus(){
        if(binding.get().licenceStatusCheckBox.isChecked()){
            licenceStatus = Constants.ONE;
        } else {
            licenceStatus = Constants.ZERO;
        }
    }
    private void ButtonSheetClick(String flag) {
        bottomBoxLayoutBinding.get().cameraButton.setOnClickListener(v -> {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                openCamera(flag);
            } else {
                if (getActivity() != null) {
                    if ((getActivity()).checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                            ((getActivity()).checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)) {

                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, Constants.REQUEST_CODE__PERMISSION_CODE);
                    } else {
                        openCamera(flag);
                    }
                }
            }

            mBottomSheetDialog.get().dismiss();
        });

        bottomBoxLayoutBinding.get().galleryButton.setOnClickListener(v -> {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                navigationController.navigateToGallery(getActivity(), flag);
            } else {
                if (getActivity() != null) {
                    if ((getActivity()).checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                            ((getActivity()).checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)) {

                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, Constants.REQUEST_CODE__PERMISSION_CODE);
                    } else {
                        //granted
                        navigationController.navigateToGallery(getActivity(), flag);
                    }
                }
            }

            mBottomSheetDialog.get().dismiss();

        });


    }

    private void openCamera(String flag) {
        if (getActivity() != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Images.Media.TITLE, "New Picture");
            contentValues.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");

            if (cameraType.equals(Constants.DEFAULT_CAMERA)) {
                navigationController.navigateToCamera(getActivity(), flag);
//                dispatchTakePictureIntent();
            } else {
                navigationController.navigateToCustomCamera(getActivity(), flag);
            }

        }
    }

    private void openDatePicker(TextView editText){

        DatePickerDialog.OnDateSetListener datePickerDialog = (view, year, monthOfYear, dayOfMonth) -> {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate(editText);
        };
        new DatePickerDialog(getContext(), datePickerDialog, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateDate(TextView editText) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String shortTimeStr = sdf.format(dateTime.getTime());
        editText.setText(shortTimeStr);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.REQUEST_CODE__PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //open camera
                openCamera(Constants.ZERO);
            } else {
                //permission denied
                Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void initViewModels() {
        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);
        imageViewModel = new ViewModelProvider(this, viewModelFactory).get(ImageViewModel.class);

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

        getIntentData();

        if( itemViewModel.itemId.equals(Constants.ADD_NEW_ITEM)){
            binding.get().licenceStatusCheckBox.setChecked(true);
        }

        bindingLatLng(itemViewModel.latValue, itemViewModel.lngValue);

        getItemDetail();

        getImageList();

        itemViewModel.getUploadItemData().observe(this, result -> {

            if (result != null) {

                switch (result.status) {
                    case SUCCESS:
                        if (result.data != null) {
                            if (selected) {

                                progressDialog.cancel();
                                itemViewModel.itemId = result.data.id;

                                if (isFirstImageSelected) {//reload
                                    if (imagePathList.size() > 1) {//multi image from start
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(0), itemViewModel.itemId, firstImageId);
                                    } else {//single image from end for last update
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(imagePathList.size() - 1), itemViewModel.itemId, firstImageId);

                                    }
                                    progressDialog.show();
                                    isFirstImageSelected = false;
                                } else if (isSecImageSelected) {
                                    if (imagePathList.size() > 1) {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(0), itemViewModel.itemId, secImageId);
                                    } else {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(imagePathList.size() - 1), itemViewModel.itemId, secImageId);
                                    }
                                    progressDialog.show();
                                    isSecImageSelected = false;
                                } else if (isThirdImageSelected) {
                                    if (imagePathList.size() > 1) {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(0), itemViewModel.itemId, thirdImageId);
                                    } else {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(imagePathList.size() - 1), itemViewModel.itemId, thirdImageId);
                                    }
                                    progressDialog.show();
                                    isThirdImageSelected = false;
                                } else if (isFourthImageSelected) {
                                    if (imagePathList.size() > 1) {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(0), itemViewModel.itemId, fourthImageId);
                                    } else {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(imagePathList.size() - 1), itemViewModel.itemId, fourthImageId);
                                    }
                                    progressDialog.show();
                                    isFourthImageSelected = false;
                                } else if (isFifthImageSelected) {
                                    if (imagePathList.size() > 1) {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(0), itemViewModel.itemId, fifthImageId);
                                    } else {
                                        itemViewModel.setUploadItemImageObj(imagePathList.get(imagePathList.size() - 1), itemViewModel.itemId, fifthImageId);
                                    }
                                    progressDialog.show();
                                    isFifthImageSelected = false;
                                }


                            } else {
                                Toast.makeText(getActivity(), "item upload success", Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();

                                if (Config.CLOSE_ENTRY_AFTER_SUBMIT) {
                                    if (getActivity() != null) {
                                        getActivity().finish();
                                    }
                                }

                            }

                        }

                        break;

                    case ERROR:
                        progressDialog.cancel();
                        psDialogMsg.showErrorDialog(getString(R.string.error_message__item_cannot_upload), getString(R.string.app__ok));
                        psDialogMsg.show();
                        break;
                }
            }

        });

        itemViewModel.getUploadItemImageData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:
                        progressDialog.cancel();
                        if (!isUploadSuccess) {
//                            int toastImageCount = imageCount+1;
//                            Toast.makeText(ItemEntryFragment.this.getActivity(), "Success image : "+toastImageCount+" uploaded", Toast.LENGTH_SHORT).show();
                            imageCount += 1;

                            if (imagePathList.size() > imageCount) {
                                ItemEntryFragment.this.callImageUpload(imageCount);//first is one
                            } else {

                                isUploadSuccess = true;
                                imageViewModel.setImageParentId(Constants.IMAGE_TYPE_PRODUCT, itemViewModel.itemId);

                                if(Config.CLOSE_ENTRY_AFTER_SUBMIT){
                                    if(getActivity()!=null){
                                        getActivity().finish();
                                    }
                                }


                            }
                        }
//                        else {
//                            if (ItemEntryFragment.this.getActivity() != null) {
//                                ItemEntryFragment.this.getActivity().finish();
//                            }
//                        }

                        break;

                    case ERROR:

                        Toast.makeText(ItemEntryFragment.this.getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                        psDialogMsg.showErrorDialog(ItemEntryFragment.this.getString(R.string.error_message__image_cannot_upload), ItemEntryFragment.this.getString(R.string.app__ok));
                        psDialogMsg.show();
                        break;
                }

            }
        });
    }

    private void getImageList() {
        LiveData<Resource<List<Image>>> imageListLiveData = imageViewModel.getImageListLiveData();
        imageViewModel.setImageParentId(Constants.IMAGE_TYPE_PRODUCT, itemViewModel.itemId);
        imageListLiveData.observe(this, listResource -> {
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (listResource != null && listResource.data != null) {
                Utils.psLog("Got Data");

                //fadeIn Animation
                fadeIn(binding.get().getRoot());

                // Update the data
                bindingImageListData(listResource.data);
//                this.binding.get().executePendingBindings();

            } else {
                //noinspection Constant Conditions
                Utils.psLog("Empty Data");
            }
        });
    }

    private void bindingImageListData(List<Image> imageList) {

        if (imageList.size() != 0) {
            if (imageList.size() == 1) {
                firstImageId = imageList.get(0).imgId;
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().firstImageView, imageList.get(0).imgPath);
            }
            if (imageList.size() == 2) {
                firstImageId = imageList.get(0).imgId;
                secImageId = imageList.get(1).imgId;
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().firstImageView, imageList.get(0).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().secImageView, imageList.get(1).imgPath);
            }
            if (imageList.size() == 3) {
                firstImageId = imageList.get(0).imgId;
                secImageId = imageList.get(1).imgId;
                thirdImageId = imageList.get(2).imgId;
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().firstImageView, imageList.get(0).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().secImageView, imageList.get(1).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().thirdImageView, imageList.get(2).imgPath);
            }
            if (imageList.size() == 4) {
                firstImageId = imageList.get(0).imgId;
                secImageId = imageList.get(1).imgId;
                thirdImageId = imageList.get(2).imgId;
                fourthImageId = imageList.get(3).imgId;
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().firstImageView, imageList.get(0).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().secImageView, imageList.get(1).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().thirdImageView, imageList.get(2).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().fouthImageView, imageList.get(3).imgPath);
            }
            if (imageList.size() == 5) {
                firstImageId = imageList.get(0).imgId;
                secImageId = imageList.get(1).imgId;
                thirdImageId = imageList.get(2).imgId;
                fourthImageId = imageList.get(3).imgId;
                fifthImageId = imageList.get(4).imgId;
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().firstImageView, imageList.get(0).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().secImageView, imageList.get(1).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().thirdImageView, imageList.get(2).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().fouthImageView, imageList.get(3).imgPath);
                dataBindingComponent.getFragmentBindingAdapters().bindFullImage(binding.get().fifthImageView, imageList.get(4).imgPath);
            }
        }
    }

    private void getItemDetail() {

        LiveData<Item> historyItemList = itemViewModel.getItemDetailFromDBByIdData();
        if (historyItemList != null) {
            historyItemList.observe(this, listResource -> {
                if (listResource != null) {
                    bindingItemDetailData(listResource);
                }

            });
        }
    }

    private void bindingItemDetailData(Item item) {
        binding.get().titleEditText.setText(item.title);
        itemViewModel.holder.manufacturer_id = item.manufacutrerId;
        itemViewModel.holder.model_id = item.modelId;
        itemViewModel.holder.type_id = item.itemTypeId;
        itemViewModel.holder.condition_id = item.conditionOfItem;
        itemViewModel.holder.price_type_id = item.itemPriceTypeId;
        itemViewModel.holder.currency_id = item.itemCurrencyId;
        itemViewModel.holder.location_id = item.itemLocation.id;
        itemViewModel.holder.color_id = item.colorId;
        itemViewModel.holder.fuelType_id = item.fuelTypeId;
        itemViewModel.holder.buildType_id = item.buildTypeId;
        itemViewModel.holder.sellerTypeId = item.sellerTypeId;
        itemViewModel.holder.transmissionId = item.transmissionId;
        itemViewModel.is_sold_out = item.isSoldOut;
        this.manufacturerId = item.manufacutrerId;
        this.modelId = item.modelId;
        this.typeId = item.itemTypeId;
        this.conditionId = item.conditionOfItem;
        this.priceTypeId = item.itemPriceTypeId;
        this.currencyId = item.itemCurrencyId;
        this.locationId = item.itemLocation.id;
        this.itemColorId = item.colorId;
        this.fuelTypeId = item.fuelTypeId;
        this.buildTypeId = item.buildTypeId;
        this.sellerTypeId = item.sellerTypeId;
        this.transmissionId = item.transmissionId;
        binding.get().manufacturerTextView.setText(item.manufacturer.name);
        binding.get().modelTextView.setText(item.model.name);
        binding.get().typeTextView.setText(item.itemType.name);
        binding.get().itemConditionTextView.setText(item.itemCondition.name);
        binding.get().priceTypeTextView.setText(item.itemPriceType.name);
        binding.get().priceTextView.setText(item.itemCurrency.currencySymbol);
        binding.get().locationTextView.setText(item.itemLocation.name);
        binding.get().colorTextView.setText(item.color.colorValue);
        binding.get().fuelTypeTextView.setText(item.fuelType.fuelName);
        binding.get().buildTypeTextView.setText(item.buildType.carType);
        binding.get().sellerTypeTextView.setText(item.sellerType.sellerType);
        binding.get().transmissionTextView.setText(item.transmission.name);
        binding.get().plateNumTextView.setText(item.plateNumber);
        binding.get().enginePowerTextView.setText(item.enginePower);
        binding.get().mileageTextView.setText(item.mileage);
//        binding.get().licenceExpirationDateTextView.setText(item.licenseExpirationDate);
        binding.get().yearTextView.setText(item.year);
        binding.get().steeringPositionTextView.setText(item.steeringPosition);
        binding.get().numberOfOwnerTextView.setText(item.noOfOwner);
        binding.get().trimNameTextView.setText(item.trimName);
        binding.get().vehicleIdTextView.setText(item.vehicleId);
        bindingIsLicenseStatus(item.licenceStatus);
        binding.get().priceUnitTextView.setText(item.priceUnit);
        bindMap(item.lat, item.lng);
        itemViewModel.mapLat = item.lat;
        itemViewModel.mapLng = item.lng;
        bindingLatLng(item.lat, item.lng);
        binding.get().priceEditText.setText(item.price);
        binding.get().highlightInfoEditText.setText(item.highlightInfo);
        binding.get().descEditText.setText(item.description);
        bindingIsShop(item.businessMode);
        binding.get().addressEditText.setText(item.address);

        if(item.maxPassengers.equals("0")){
            binding.get().maximumPassengersTextView.setText("");
        }else {
            binding.get().maximumPassengersTextView.setText(item.maxPassengers);
        }

        if(item.noOfDoors.equals("0")){
            binding.get().numberOfDoorTextView.setText("");
        }else {
            binding.get().numberOfDoorTextView.setText(item.noOfDoors);
        }

        String licenceExpirationDate;
        if(item.licenseExpirationDate != null && item.licenseExpirationDate.length() > 0){
            licenceExpirationDate = item.licenseExpirationDate.substring(0,item.licenseExpirationDate.length() -8);

            binding.get().licenceExpirationDateTextView.setText(licenceExpirationDate);
        }
    }

    private void bindingIsShop(String businessMode) {
        if (businessMode.equals(Constants.ONE)) {
            binding.get().isShopCheckBox.setChecked(true);
        } else {
            binding.get().isShopCheckBox.setChecked(false);
        }
    }

    private void bindingIsLicenseStatus(String licenseStatus){
        if (licenseStatus.equals(Constants.ONE)){
            binding.get().licenceStatusCheckBox.setChecked(true);
        } else {
            binding.get().licenceStatusCheckBox.setChecked(false);
        }
    }
    private void getIntentData() {
        try {
            if (getActivity() != null) {
                if (getActivity().getIntent().getExtras() != null) {

                    itemViewModel.itemId = getActivity().getIntent().getExtras().getString(Constants.ITEM_ID);
                    this.locationId = getActivity().getIntent().getExtras().getString(Constants.SELECTED_LOCATION_ID);
                    itemViewModel.holder.location_id = this.locationId;
                    String locationName = getActivity().getIntent().getExtras().getString(Constants.SELECTED_LOCATION_NAME);
                    binding.get().locationTextView.setText(locationName);

                    if (itemViewModel.itemId != null) {
                        if (!itemViewModel.itemId.equals(Constants.ADD_NEW_ITEM)) {//edit

                            itemViewModel.setItemDetailFromDBById(itemViewModel.itemId);

                        }
                    }
                }
            }
        } catch (Exception e) {
            Utils.psErrorLog("", e);
        }
    }

    private void callImageUpload(int imageCount) {

        if (isSecImageSelected) {
            itemViewModel.setUploadItemImageObj(imagePathList.get(imageCount), itemViewModel.itemId, secImageId);
            isSecImageSelected = false;
        } else if (isThirdImageSelected) {
            itemViewModel.setUploadItemImageObj(imagePathList.get(imageCount), itemViewModel.itemId, thirdImageId);
            isThirdImageSelected = false;
        } else if (isFourthImageSelected) {
            itemViewModel.setUploadItemImageObj(imagePathList.get(imageCount), itemViewModel.itemId, fourthImageId);
            isFourthImageSelected = false;
        } else if (isFifthImageSelected) {
            itemViewModel.setUploadItemImageObj(imagePathList.get(imageCount), itemViewModel.itemId, fifthImageId);
            isFifthImageSelected = false;
        }

    }

    private void bindingLatLng(String latValue, String lngValue) {
        binding.get().latitudeEditText.setText(latValue);
        binding.get().lngEditText.setText(lngValue);
    }

    private void changeCamera() {

        if (marker != null) {
            marker.remove();
        }

        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(Double.valueOf(itemViewModel.latValue), Double.valueOf(itemViewModel.lngValue))).zoom(10).bearing(10).tilt(10).build()));

        marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(Double.valueOf(itemViewModel.latValue), Double.valueOf(itemViewModel.lngValue)))
                .title("Shop Name"));
    }

}
