package com.panaceasoft.admotors.viewmodel.fueltype;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.panaceasoft.admotors.repository.fueltype.FuelTypeRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.FuelType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class FuelTypeViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<FuelType>>> fuelTypeListData;
    private MutableLiveData<FuelTypeViewModel.TmpDataHolder> fuelTypeListObj = new MutableLiveData<>();

    //endregion

    //region Constructors

    @Inject
    FuelTypeViewModel(FuelTypeRepository repository) {

        Utils.psLog("FuelTypeViewModel");

        fuelTypeListData = Transformations.switchMap(fuelTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("FuelTypeViewModel : transmission");
            return repository.getAllFuelTypeList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setFuelTypeListObj(String limit, String offset) {
        if (!isLoading) {
            FuelTypeViewModel.TmpDataHolder tmpDataHolder = new FuelTypeViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            fuelTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<FuelType>>> getFuelTypeListData() {
        return fuelTypeListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}


