package com.panaceasoft.admotors.viewmodel.itemmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.repository.itemmodel.ItemModelRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Model;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class ItemModelViewModel extends PSViewModel {

    private LiveData<Resource<List<Model>>> allModelListData;
    private MutableLiveData<TmpDataHolder> allModelListObj = new MutableLiveData<>();

    private LiveData<Resource<List<Model>>> modelListData;
    private MutableLiveData<TmpDataHolder> modelListObj = new MutableLiveData<>();

    private LiveData<Resource<Boolean>> nextPageLoadingStateData;
    private MutableLiveData<TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    private final LiveData<Resource<List<Model>>> modelListByManufacturerIdData;
    private MutableLiveData<ListByCatIdTmpDataHolder> modelListByManufacturerIdObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageModelListByManufacturerIdData;
    private MutableLiveData<ListByCatIdTmpDataHolder> nextPageModelListByManufacturerIdObj = new MutableLiveData<>();

    public String manufacturerId = "";

    @Inject
    ItemModelViewModel(ItemModelRepository repository) {
        Utils.psLog("Inside SubCategoryViewModel");

        allModelListData = Transformations.switchMap(allModelListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("allModelListData");
            return repository.getAllItemModelList(Config.API_KEY);
        });

        modelListData = Transformations.switchMap(modelListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("modelListData");
            return repository.getModelList(Config.API_KEY,obj.manufacturerId, obj.limit, obj.offset);
        });

        nextPageLoadingStateData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("nextPageLoadingStateData");
            return repository.getNextPageModel(obj.manufacturerId, obj.limit, obj.offset);
        });

        modelListByManufacturerIdData = Transformations.switchMap(modelListByManufacturerIdObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ItemManufacturerViewModel : categories");
            return repository.getModelWithManufacturerId(obj.offset, obj.manufacturerId);
        });

        nextPageModelListByManufacturerIdData = Transformations.switchMap(nextPageModelListByManufacturerIdObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("Category List.");
            return repository.getNextPageModelWithManufacturerId(obj.limit, obj.offset, obj.manufacturerId);
        });
    }

    //list by cat id
    public void setModelListByManufacturerIdObj(String manufacturerId, String limit, String offset){

        ListByCatIdTmpDataHolder tmpDataHolder = new ListByCatIdTmpDataHolder(limit, offset,manufacturerId);

        modelListByManufacturerIdObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<Model>>> getModelListByManufacturerIdData()
    {
        return modelListByManufacturerIdData;
    }

    //endregion

    public void setAllModelListObj() {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            allModelListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public void setNextPageSubCategoryListByCatIdObj( String limit, String offset, String manufacturerId)
    {
        ListByCatIdTmpDataHolder tmpDataHolder = new ListByCatIdTmpDataHolder(limit, offset,  manufacturerId);

        nextPageModelListByManufacturerIdObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getNextPageSubCategoryListByCatIdData() {
        return nextPageModelListByManufacturerIdData;
    }

    public LiveData<Resource<List<Model>>> getAllModelListData() {
        return allModelListData;
    }


    public void setModelListData(String manufacturerId, String limit, String offset) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.manufacturerId = manufacturerId;
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            modelListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Model>>> getModelListData() {
        return modelListData;
    }

    public void setNextPageLoadingStateObj(String manufacturerId, String limit, String offset) {

        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.manufacturerId = manufacturerId;
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            nextPageLoadingStateObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
        return nextPageLoadingStateData;
    }


    class TmpDataHolder {
        public String loginUserId = "";
        public String offset = "";
        public String limit = "";
        public String manufacturerId = "";
        public Boolean isConnected = false;


    }

    class ListByCatIdTmpDataHolder {
        public String limit = "";
        public String offset = "";
        public String manufacturerId = "";

        public ListByCatIdTmpDataHolder(String limit, String offset,String manufacturerId) {
            this.limit = limit;
            this.offset = offset;
            this.manufacturerId = manufacturerId;
        }
    }

}
