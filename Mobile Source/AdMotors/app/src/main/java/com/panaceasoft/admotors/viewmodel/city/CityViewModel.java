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

public class CityViewModel extends PSViewModel {

    private final LiveData<Resource<List<City>>> cityListData;
    private MutableLiveData<CityListTmpDataHolder> cityListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageCityListData;
    private MutableLiveData<CityListTmpDataHolder> nextPageCityListObj = new MutableLiveData<>();

//    private final LiveData<Resource<City>> cityData;
//    private MutableLiveData<CityTmpDataHolder> shopObj = new MutableLiveData<>();

    public CityParameterHolder cityParameterHolder = new CityParameterHolder();
    public String selectedCityId = "";
    public String cityName = "";
    public String lat = "";
    public String lng = "";

    @Inject
    CityViewModel(CityRepository repository) {

        //region Getting City List

        cityListData = Transformations.switchMap(cityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getCityListByValue(obj.limit, obj.offset, obj.parameterHolder);

        });

        nextPageCityListData = Transformations.switchMap(nextPageCityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextPageCityList(obj.limit, obj.offset, obj.parameterHolder);

        });

//        cityData = Transformations.switchMap(shopObj, obj -> {
//
//            if (obj == null) {
//                return AbsentLiveData.create();
//            }
//
//            return repository.getCity(obj.apiKey, obj.cityId);
//
//        });

        //endregion

    }

    //region city Detail

//    public void setCityObj(String apiKey, String cityId) {
//        CityTmpDataHolder tmpDataHolder = new CityTmpDataHolder(apiKey, cityId);
//
//        this.shopObj.setValue(tmpDataHolder);
//    }
//
//    public LiveData<Resource<City>> getCityData() {
//        return cityData;
//    }

    //endregion

    //region Getting City List

    public void setCityListObj(String limit, String offset, CityParameterHolder parameterHolder) {
        CityListTmpDataHolder tmpDataHolder = new CityListTmpDataHolder(limit, offset, parameterHolder);

        this.cityListObj.setValue(tmpDataHolder);

        setLoadingState(true);
    }

    public LiveData<Resource<List<City>>> getCityListData() {
        return cityListData;
    }

    public void setNextPageCityListObj(String limit, String offset, CityParameterHolder parameterHolder) {

        if(!isLoading)
        {
            CityListTmpDataHolder tmpDataHolder = new CityListTmpDataHolder(limit, offset, parameterHolder);

            this.nextPageCityListObj.setValue(tmpDataHolder);

            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageCityListData() {
        return nextPageCityListData;
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

    class CityTmpDataHolder {
        String apiKey, cityId;

        CityTmpDataHolder(String apiKey, String cityId) {
            this.apiKey = apiKey;
            this.cityId = cityId;
        }
    }
}
