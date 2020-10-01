package com.panaceasoft.admotors.ui.manufacturer.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemManufacturerFilterBinding;
import com.panaceasoft.admotors.databinding.ItemModelFilterBinding;
import com.panaceasoft.admotors.ui.manufacturer.manufacturerfilter.Grouping;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.Manufacturer;
import com.panaceasoft.admotors.viewobject.Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ManufacturerFilterAdapter extends BaseExpandableListAdapter {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final filteringClickCallback callback;
    private List<Manufacturer> manufacturerList = new ArrayList<>();
    private List<Model> modelListOrg = new ArrayList<>();
    private LinkedHashMap<Manufacturer, List<Model>> map = new LinkedHashMap<>();
    private Grouping grouping = new Grouping();
    private TextView selectedView = null;
    private String manufacturerId, modelId;


    public ManufacturerFilterAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                     filteringClickCallback callback, String manufacturerId, String modelId) {

        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.manufacturerId = manufacturerId;
        this.modelId = modelId;
    }

    public void replaceManufacturer(List<Manufacturer> manufacturerList) {
        this.manufacturerList = manufacturerList;
        if (manufacturerList != null && manufacturerList.size() != 0) {
            map = grouping.group(manufacturerList, modelListOrg);
        }

    }

    public void replaceModel(List<Model> modelList) {
        this.modelListOrg = modelList;
        if (manufacturerList.size() != 0 && modelListOrg.size() != 0) {

            map = grouping.group(manufacturerList, modelListOrg);
        }
    }

    @Override
    public int getGroupCount() {
        if (manufacturerList == null) {
            return 0;
        }
        return manufacturerList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        List<Model> sub;
        if (manufacturerList != null) {
            sub = this.map.get(this.manufacturerList.get(i));
            if (sub != null) {
                return sub.size();
            }
        }

        return 0;
    }


    @Override
    public Object getGroup(int i) {
        return manufacturerList.get(i);
    }

    @Override
    public Model getChild(int i, int i1) {
        List<Model> modelList = this.map.get(this.manufacturerList.get(i));
        if (modelList != null) {
            return modelList.get(i1);
        }
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup viewGroup) {

        ItemManufacturerFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_manufacturer_filter, viewGroup, false, dataBindingComponent);
        
        binding.setManufacturer(manufacturerList.get(i));

        if (manufacturerList.get(i).id.equals(manufacturerId)) {
            binding.groupview.setBackgroundColor(viewGroup.getResources().getColor(R.color.md_green_50));
        }

        if (isExpanded) {
            Utils.toggleUporDown(binding.dropdownimage);
        }

        return binding.getRoot();

    }

    @Override
    public View getChildView(int i, int childPosition, boolean b, View view, ViewGroup viewGroup) {

        ItemModelFilterBinding modelItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_model_filter, viewGroup, false, dataBindingComponent);
        
        Model model = getChild(i, childPosition);
        modelItemBinding.setModel(model);

        if (manufacturerList.get(i).id.equals(manufacturerId) && model.id.equals(modelId)) {
            modelItemBinding.modelItem.setCompoundDrawablesWithIntrinsicBounds(null, null, viewGroup.getResources().getDrawable(R.drawable.baseline_check_green_24), null);
            selectedView = modelItemBinding.modelItem;
        }

        modelItemBinding.getRoot().setOnClickListener(view1 -> {

            if (selectedView != null) {
                selectedView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

            }

            modelItemBinding.modelItem.setCompoundDrawablesWithIntrinsicBounds(null, null, viewGroup.getResources().getDrawable(R.drawable.baseline_check_green_24), null);

            manufacturerId = (manufacturerList.get(i).id);

            if (childPosition != 0) {
                modelId = model.id;
            } else {
                modelId = Constants.ZERO;
            }

            callback.onClick(manufacturerId, modelId);

            ((Activity) viewGroup.getContext()).finish();
        });

        return modelItemBinding.getRoot();

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public interface filteringClickCallback {
        void onClick(String manufacturerId, String modelId);
    }

}
