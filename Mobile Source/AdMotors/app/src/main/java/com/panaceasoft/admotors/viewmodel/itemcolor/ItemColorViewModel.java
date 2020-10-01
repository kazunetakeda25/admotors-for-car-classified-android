package com.panaceasoft.admotors.viewmodel.itemcolor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.repository.itemcolor.ItemColorRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Color;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class ItemColorViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<Color>>> itemColorListData;
    private MutableLiveData<ItemColorViewModel.TmpDataHolder> itemColorListObj = new MutableLiveData<>();

    //endregion

    //region Constructors

    @Inject
    ItemColorViewModel(ItemColorRepository repository) {

        Utils.psLog("ItemColorViewModel");

        itemColorListData = Transformations.switchMap(itemColorListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ItemColorViewModel : transmission");
            return repository.getAllItemColorList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setItemColorListObj(String limit, String offset) {
        if (!isLoading) {
            ItemColorViewModel.TmpDataHolder tmpDataHolder = new ItemColorViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            itemColorListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Color>>> getItemColorListData() {
        return itemColorListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}

