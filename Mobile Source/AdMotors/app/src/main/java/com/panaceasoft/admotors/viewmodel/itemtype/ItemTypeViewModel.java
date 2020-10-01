package com.panaceasoft.admotors.viewmodel.itemtype;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.repository.itemtype.ItemTypeRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.ItemType;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.holder.ManufacturerParameterHolder;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

import java.util.List;

import javax.inject.Inject;

public class ItemTypeViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<ItemType>>> itemTypeListData;
    private MutableLiveData<ItemTypeViewModel.TmpDataHolder> itemTypeListObj = new MutableLiveData<>();

//    private final LiveData<Resource<Boolean>> nextPageLoadingStateData;
//    private MutableLiveData<ItemTypeViewModel.TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    public ItemParameterHolder productParameterHolder = new ItemParameterHolder();

    public ManufacturerParameterHolder manufacturerParameterHolder = new ManufacturerParameterHolder();

    //endregion

    //region Constructors

    @Inject
    ItemTypeViewModel(ItemTypeRepository repository) {

        Utils.psLog("ItemTypeViewModel");

        itemTypeListData = Transformations.switchMap(itemTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ItemTypeViewModel : categories");
            return repository.getAllItemTypeList(obj.limit, obj.offset);
        });

//        nextPageLoadingStateData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
//            if (obj == null) {
//                return AbsentLiveData.create();
//            }
//
//            Utils.psLog("Category List.");
//            return repository.getNextSearchCityManufacturer(obj.limit, obj.offset);
//        });

    }

    //endregion

    public void setItemTypeListObj(String limit, String offset) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            itemTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<ItemType>>> getItemTypeListData() {
        return itemTypeListData;
    }

    //Get Latest Category Next Page
//    public void setNextPageLoadingStateObj(String limit, String offset) {
//
//        if (!isLoading) {
//            TmpDataHolder tmpDataHolder = new TmpDataHolder();
//            tmpDataHolder.offset = offset;
//            tmpDataHolder.limit = limit;
//            nextPageLoadingStateObj.setValue(tmpDataHolder);
//
//            // start loading
//            setLoadingState(true);
//        }
//    }
//
//    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
//        return nextPageLoadingStateData;
//    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
        public String cityId = "";
    }
}
