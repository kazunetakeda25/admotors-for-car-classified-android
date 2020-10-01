package com.panaceasoft.admotors.ui.dashboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.ads.AdRequest;
import com.google.android.material.appbar.AppBarLayout;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentDashboardSearchBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.viewmodel.item.ItemViewModel;

public class DashBoardSearchFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private String manufacturerId = Constants.NO_DATA;
    private String modelId = Constants.NO_DATA;
    private String typeId = Constants.NO_DATA;
    private String priceTypeId = Constants.NO_DATA;
    private String dealOptionId = Constants.NO_DATA;
    private String conditionId = Constants.NO_DATA;
    private String transmissionId = Constants.NO_DATA;
    private String itemColorId = Constants.NO_DATA;
    private String fuelTypeId = Constants.NO_DATA;
    private String buildTypeId = Constants.NO_DATA;
    private String sellerTypeId = Constants.NO_DATA;
    private String locationId = Constants.EMPTY_STRING;
    private String currencyId = Constants.EMPTY_STRING;
    private PSDialogMsg psDialogMsg;
    private ItemViewModel itemViewModel;


    @VisibleForTesting
    private AutoClearedValue<FragmentDashboardSearchBinding> binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDashboardSearchBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_search, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE__SEARCH_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_MANUFACTURER) {

            this.manufacturerId = data.getStringExtra(Constants.MANUFACTURER_ID);
            binding.get().manufacturerTextView.setText(data.getStringExtra(Constants.MANUFACTURER_NAME));
            itemViewModel.holder.manufacturer_id = this.manufacturerId;
            this.modelId ="";
            itemViewModel.holder.model_id = this.modelId;
            binding.get().modelTextView.setText("");

        } else if (requestCode == Constants.REQUEST_CODE__SEARCH_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_MODEL) {

            this.modelId = data.getStringExtra(Constants.MODEL_ID);
            binding.get().modelTextView.setText(data.getStringExtra(Constants.MODEL_NAME));
            itemViewModel.holder.model_id = this.modelId;
        }
        else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_TYPE) {

            this.typeId = data.getStringExtra(Constants.ITEM_TYPE_ID);
            binding.get().typeTextView.setText(data.getStringExtra(Constants.ITEM_TYPE_NAME));
            itemViewModel.holder.type_id = this.typeId;
        }
        else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_PRICE_TYPE) {

            this.priceTypeId = data.getStringExtra(Constants.ITEM_PRICE_TYPE_ID);
            binding.get().priceTypeTextView.setText(data.getStringExtra(Constants.ITEM_PRICE_TYPE_NAME));
            itemViewModel.holder.price_type_id = this.priceTypeId;
        }
        else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_CONDITION_TYPE) {

            this.conditionId = data.getStringExtra(Constants.ITEM_CONDITION_TYPE_ID);
            binding.get().itemConditionTextView.setText(data.getStringExtra(Constants.ITEM_CONDITION_TYPE_NAME));
            itemViewModel.holder.condition_id = this.conditionId;
        }


        else if (requestCode == Constants.REQUEST_CODE__SEARCH_VIEW_FRAGMENT && resultCode == Constants.RESULT_CODE__SEARCH_WITH_TRANSMISSION) {

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

    }

    @Override
    public void onDispatched() {

    }

    @Override
    protected void initUIAndActions() {
        if(getActivity() instanceof MainActivity)  {
            Toolbar toolbar = ((MainActivity) getActivity()).binding.toolbar;
            if (toolbar.getParent() instanceof AppBarLayout){
                ((AppBarLayout)toolbar.getParent()).setExpanded(true,true);
            }
            ((MainActivity) getActivity()).setToolbarText(((MainActivity) getActivity()).binding.toolbar, getString(R.string.menu__search));
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
            ((MainActivity)getActivity()).updateToolbarIconColor(Color.WHITE);
            ((MainActivity)getActivity()).updateMenuIconWhite();
            ((MainActivity) getActivity()).refreshPSCount();
        }

        psDialogMsg = new PSDialogMsg(getActivity(), false);

        if (Config.SHOW_ADMOB && connectivity.isConnected()) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            binding.get().adView.loadAd(adRequest);
        } else {
            binding.get().adView.setVisibility(View.GONE);
        }

        binding.get().setItemName.setHint(R.string.search__notSet);
        binding.get().manufacturerTextView.setHint(R.string.search__notSet);
        binding.get().modelTextView.setHint(R.string.search__notSet);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        AutoClearedValue<AlertDialog.Builder> alertDialog = new AutoClearedValue<>(this, builder);
        alertDialog.get().setTitle(getResources().getString(R.string.Feature_UI__search_alert_manufacturer_title));

        binding.get().manufacturerTextView.setText("");
        binding.get().modelTextView.setText("");

        binding.get().categorySelectionView.setOnClickListener(view -> navigationController.navigateToSearchActivityCategoryFragment(this.getActivity(), Constants.MANUFACTURER, manufacturerId, modelId));

        binding.get().modelSelectionView.setOnClickListener(view -> {

            if (manufacturerId.equals(Constants.NO_DATA) || manufacturerId.isEmpty()) {

                psDialogMsg.showWarningDialog(getString(R.string.error_message__choose_manufacturer), getString(R.string.app__ok));

                psDialogMsg.show();

            } else {
                navigationController.navigateToSearchActivityCategoryFragment(this.getActivity(), Constants.MODEL, manufacturerId, modelId);
            }
        });

        binding.get().typeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_TYPE, typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().itemConditionCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_CONDITION_TYPE, typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().priceTypeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(), Constants.ITEM_PRICE_TYPE, typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().colorCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(),Constants.ITEM_COLOR,typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().transmissionCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(),Constants.TRANSMISSION,typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().fuelTypeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(),Constants.FUEL_TYPE,typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().buildTypeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(),Constants.BUILD_TYPE,typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().sellerTypeCardView.setOnClickListener(view -> navigationController.navigateToSearchViewActivity(this.getActivity(),Constants.SELLER_TYPE,typeId, priceTypeId, conditionId, dealOptionId,currencyId,locationId,transmissionId,itemColorId,fuelTypeId,buildTypeId,sellerTypeId));

        binding.get().filter.setOnClickListener(view -> {

            // Get Name
            itemViewModel.holder.keyword = binding.get().setItemName.getText().toString();
            itemViewModel.holder.max_price = binding.get().highestPriceEditText1.getText().toString();
            itemViewModel.holder.min_price = binding.get().lowestPriceEditText1.getText().toString();
            itemViewModel.holder.location_id = selected_location_id;
            itemViewModel.holder.type_name = binding.get().typeTextView.getText().toString();
            itemViewModel.holder.conditionName = binding.get().itemConditionTextView.getText().toString();
            itemViewModel.holder.priceTypeName = binding.get().priceTypeTextView.getText().toString();
            itemViewModel.holder.transmissionName = binding.get().transmissionTextView.getText().toString();
            itemViewModel.holder.colorName = binding.get().colorTextView.getText().toString();
            itemViewModel.holder.fuelTypeName = binding.get().fuelTypeTextView.getText().toString();
            itemViewModel.holder.buildTypeName = binding.get().buildTypeTextView.getText().toString();
            itemViewModel.holder.sellerTypeName = binding.get().sellerTypeTextView.getText().toString();

            // Set to Intent
            navigationController.navigateToHomeFilteringActivity(DashBoardSearchFragment.this.getActivity(), itemViewModel.holder, null, itemViewModel.holder.lat, itemViewModel.holder.lng, Constants.MAP_MILES);

        });

    }

    @Override
    protected void initViewModels() {
        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
    }
}
