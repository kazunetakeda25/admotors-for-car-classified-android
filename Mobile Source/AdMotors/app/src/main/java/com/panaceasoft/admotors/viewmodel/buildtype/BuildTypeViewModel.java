package com.panaceasoft.admotors.viewmodel.buildtype;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.panaceasoft.admotors.repository.buildtype.BuildTypeRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.BuildType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class BuildTypeViewModel extends PSViewModel {


    //region Variables

    private final LiveData<Resource<List<BuildType>>> buildTypeListData;
    private MutableLiveData<BuildTypeViewModel.TmpDataHolder> buildTypeListObj = new MutableLiveData<>();

    //endregion

    //region Constructors

    @Inject
    BuildTypeViewModel(BuildTypeRepository repository) {

        Utils.psLog("BuildTypeViewModel");

        buildTypeListData = Transformations.switchMap(buildTypeListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("BuildTypeViewModel : transmission");
            return repository.getAllBuildTypeList(obj.limit, obj.offset);
        });

    }

    //endregion

    public void setBuildTypeListObj(String limit, String offset) {
        if (!isLoading) {
            BuildTypeViewModel.TmpDataHolder tmpDataHolder = new BuildTypeViewModel.TmpDataHolder();
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            buildTypeListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<BuildType>>> getBuildTypeListData() {
        return buildTypeListData;
    }


    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
    }
}


