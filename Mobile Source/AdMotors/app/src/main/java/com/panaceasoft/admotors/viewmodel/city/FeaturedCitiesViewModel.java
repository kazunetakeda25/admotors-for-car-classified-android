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

public class FeaturedCitiesViewModel extends PSViewModel {

    private final LiveData<Resource<List<City>>> featuredCityListData;
    private MutableLiveData<FeaturedCitiesViewModel.CityListTmpDataHolder> cityListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPagefeaturedCityListData;
    private MutableLiveData<FeaturedCitiesViewModel.CityListTmpDataHolder> nextPageCityListObj = new MutableLiveData<>();

    public CityParameterHolder featuredCitiesParameterHolder = new CityParameterHolder().getFeaturedCities();

    @Inject
    FeaturedCitiesViewModel(CityRepository repository) {

        //region Getting City List

        featuredCityListData = Transformations.switchMap(cityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getCityListByValue(obj.limit, obj.offset, obj.parameterHolder);

        });

        nextPagefeaturedCityListData = Transformations.switchMap(nextPageCityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextPageCityList(obj.limit, obj.offset, obj.parameterHolder);

        });

        //endregion

    }


    //region Getting City List

    public void setFeaturedCityListObj(String limit, String offset, CityParameterHolder parameterHolder) {
        FeaturedCitiesViewModel.CityListTmpDataHolder tmpDataHolder = new FeaturedCitiesViewModel.CityListTmpDataHolder(limit, offset, parameterHolder);

        this.cityListObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<City>>> getFeaturedCityListData() {
        return featuredCityListData;
    }

    public void setNextPageFeaturedCityListObj(String limit, String offset, CityParameterHolder parameterHolder) {
        FeaturedCitiesViewModel.CityListTmpDataHolder tmpDataHolder = new FeaturedCitiesViewModel.CityListTmpDataHolder(limit, offset, parameterHolder);

        this.nextPageCityListObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getNextPageFeaturedCityListData() {
        return nextPagefeaturedCityListData;
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
