package com.panaceasoft.admotors.viewmodel.homebanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.panaceasoft.admotors.repository.homebanner.HomeBannerRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Banner;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class HomeBannerViewModel extends PSViewModel {

    private final LiveData<Resource<List<Banner>>> homeBannerData;
    private MutableLiveData<HomeBannerViewModel.TmpDataHolder> homeBannerObj = new MutableLiveData<>();

    @Inject
    HomeBannerViewModel(HomeBannerRepository repository) {

        homeBannerData = Transformations.switchMap(homeBannerObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getHomeBannerList(obj.limit, obj.offset);

        });
    }

    public void setHomeBannerObj(String limit, String offset) {
        HomeBannerViewModel.TmpDataHolder tmpDataHolder = new HomeBannerViewModel.TmpDataHolder(limit, offset);

        this.homeBannerObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<Banner>>> getHomeBannerData() {
        return homeBannerData;
    }

    class TmpDataHolder {
        String  limit, offset;

        public TmpDataHolder(String limit, String offset) {
            this.limit = limit;
            this.offset = offset;
        }
    }
}
