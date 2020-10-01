package com.panaceasoft.admotors.viewmodel.rating;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.repository.rating.RatingRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Rating;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class RatingViewModel extends PSViewModel {

    //region variables

    private final LiveData<Resource<List<Rating>>> ratingList;
    private MutableLiveData<RatingViewModel.TmpDataHolder> ratingListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageLoadingStateData;
    private MutableLiveData<RatingViewModel.TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> ratingPostData;
    private MutableLiveData<RatingViewModel.TmpDataHolder> ratingPostObj = new MutableLiveData<>();

    public float ratingValue = 0;
    public float numStar;
    public boolean isData = false;
    public String userId;

    //region Constructor
    @Inject
    RatingViewModel(RatingRepository repository) {
        Utils.psLog("Inside RatingViewModel");

        //Latest Rating List
        ratingList = Transformations.switchMap(ratingListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ratingList");

            return repository.getRatingListByProductId(Config.API_KEY, obj.itemUserId, obj.limit, obj.offset);
        });

        nextPageLoadingStateData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("nextPageLoadingStateData");
            return repository.getNextPageRatingListByProductId(obj.itemUserId, obj.limit, obj.offset);
        });

        ratingPostData = Transformations.switchMap(ratingPostObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            Utils.psLog("ratingPostData");

            return repository.uploadRatingPostToServer(obj.title, obj.description, obj.rating, obj.loginUserId, obj.itemUserId);
        });

    }
    //endregion

    //Get Latest Rating
    public void setRatingListObj(String itemUserId, String limit, String offset) {
        if (!isLoading) {
            RatingViewModel.TmpDataHolder tmpDataHolder = new RatingViewModel.TmpDataHolder();
            tmpDataHolder.itemUserId = itemUserId;
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            ratingListObj.setValue(tmpDataHolder);

            //start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Rating>>> getRatingList() {
        return ratingList;
    }

    //Get Latest Rating Next Page
    public void setNextPageLoadingStateObj(String itemUserId, String limit, String offset) {

        if (!isLoading) {
            RatingViewModel.TmpDataHolder tmpDataHolder = new RatingViewModel.TmpDataHolder();
            tmpDataHolder.itemUserId = itemUserId;
            tmpDataHolder.offset = offset;
            tmpDataHolder.limit = limit;
            nextPageLoadingStateObj.setValue(tmpDataHolder);

            //start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
        return nextPageLoadingStateData;
    }

    //endregion

    //Get Rating post
    public void setRatingPostObj(String title, String description, String rating,String userId, String itemUserId) {

        if (!isLoading) {
            RatingViewModel.TmpDataHolder tmpDataHolder = new RatingViewModel.TmpDataHolder();
            tmpDataHolder.itemUserId = itemUserId;
            tmpDataHolder.loginUserId = userId;
            tmpDataHolder.rating = rating;
            tmpDataHolder.description = description;
            tmpDataHolder.title = title;

            ratingPostObj.setValue(tmpDataHolder);

            //start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getRatingPostData() {
        return ratingPostData;
    }

    //endregion


    //region Holder

    class TmpDataHolder {
        public String itemUserId = "";
        public String loginUserId = "";
        public String offset = "";
        public String limit = "";
        public Boolean isConnected = false;
        public String title = "";
        public String description = "";
        public String rating = "";
        public String cityId = "";

    }

}
