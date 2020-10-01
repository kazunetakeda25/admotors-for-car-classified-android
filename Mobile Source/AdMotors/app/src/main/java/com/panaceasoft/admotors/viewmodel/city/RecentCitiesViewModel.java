package com.panaceasoft.admotors.viewmodel.city;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.repository.city.CityRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.City;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.holder.CityParameterHolder;

import java.util.List;

import javax.inject.Inject;

public class RecentCitiesViewModel extends PSViewModel {

    private final LiveData<Resource<List<City>>> RecentCityListData;
    private MutableLiveData<RecentCitiesViewModel.CityListTmpDataHolder> cityListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageRecentCityListData;
    private MutableLiveData<RecentCitiesViewModel.CityListTmpDataHolder> nextPageCityListObj = new MutableLiveData<>();

    public CityParameterHolder recentCitiesParameterHolder = new CityParameterHolder().getRecentCities();

    @Inject
    RecentCitiesViewModel(CityRepository repository) {

        //region Getting City List

        RecentCityListData = Transformations.switchMap(cityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getCityListByValue(obj.limit, obj.offset, obj.parameterHolder);

        });

        nextPageRecentCityListData = Transformations.switchMap(nextPageCityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextPageCityList(obj.limit, obj.offset, obj.parameterHolder);

        });

        //endregion

    }


    //region Getting City List

    public void setRecentCityListObj(String limit, String offset, CityParameterHolder parameterHolder) {
        RecentCitiesViewModel.CityListTmpDataHolder tmpDataHolder = new RecentCitiesViewModel.CityListTmpDataHolder(limit, offset, parameterHolder);

        this.cityListObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<City>>> getRecentCityListData() {
        return RecentCityListData;
    }

    public void setNextPageRecentCityListObj(String limit, String offset, CityParameterHolder parameterHolder) {
        RecentCitiesViewModel.CityListTmpDataHolder tmpDataHolder = new RecentCitiesViewModel.CityListTmpDataHolder(limit, offset, parameterHolder);

        this.nextPageCityListObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getNextPageRecentCityListData() {
        return nextPageRecentCityListData;
    }

    //endregion


    class CityListTmpDataHolder {

        private String limit, offset;
        private CityParameterHolder parameterHolder;

        public CityListTmpDataHolder(String limit, String offset, CityParameterHolder parameterHolder) {
            this.limit = limit;
            this.offset = offset;
            this.parameterHolder = parameterHolder;
        }
    }
}
