package com.panaceasoft.admotors.viewmodel.item;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.repository.item.ItemRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class FavouriteViewModel extends PSViewModel {
    private final LiveData<Resource<Boolean>> sendFavouritePostData;
    private MutableLiveData<FavouriteViewModel.TmpDataHolder> sendFavouriteDataPostObj = new MutableLiveData<>();

    private final LiveData<Resource<List<Item>>> itemFavouriteData;
    private MutableLiveData<FavouriteViewModel.TmpDataHolder> itemFavouriteListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageFavouriteLoadingData;
    private MutableLiveData<FavouriteViewModel.TmpDataHolder> nextPageLoadingFavouriteObj = new MutableLiveData<>();

    @Inject
    FavouriteViewModel(ItemRepository itemRepository) {
        sendFavouritePostData = Transformations.switchMap(sendFavouriteDataPostObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }
            return itemRepository.uploadFavouritePostToServer(obj.itemId, obj.userId);
        });

        itemFavouriteData = Transformations.switchMap(itemFavouriteListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("itemFavouriteData");
            return itemRepository.getFavouriteList(Config.API_KEY, obj.userId, obj.offset);
        });

        nextPageFavouriteLoadingData = Transformations.switchMap(nextPageLoadingFavouriteObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("nextPageFavouriteLoadingData");
            return itemRepository.getNextPageFavouriteProductList(obj.userId, obj.offset);
        });
    }

    public void setFavouritePostDataObj(String itemId, String userId) {

        if(!isLoading) {
            FavouriteViewModel.TmpDataHolder tmpDataHolder = new FavouriteViewModel.TmpDataHolder();
            tmpDataHolder.itemId = itemId;
            tmpDataHolder.userId = userId;

            sendFavouriteDataPostObj.setValue(tmpDataHolder);
            setLoadingState(true);
        }

    }

    //region Getter And Setter for item detail List
    public void setItemFavouriteListObj(String loginUserId, String offset) {
        if (!isLoading) {
            FavouriteViewModel.TmpDataHolder tmpDataHolder = new FavouriteViewModel.TmpDataHolder();
            tmpDataHolder.userId = loginUserId;
            tmpDataHolder.offset = offset;
            itemFavouriteListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Item>>> getItemFavouriteData() {
        return itemFavouriteData;
    }
    //endregion

    //Get Favourite Next Page
    public void setNextPageLoadingFavouriteObj(String offset, String loginUserId) {

        if (!isLoading) {
            FavouriteViewModel.TmpDataHolder tmpDataHolder = new FavouriteViewModel.TmpDataHolder();
            tmpDataHolder.userId = loginUserId;
            tmpDataHolder.offset = offset;
            nextPageLoadingFavouriteObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageFavouriteLoadingData() {
        return nextPageFavouriteLoadingData;
    }

    public LiveData<Resource<Boolean>> getFavouritePostData() {
        return sendFavouritePostData;
    }

    class TmpDataHolder {
        public String itemId = "";
        public String userId = "";
        public String cityId = "";
        public String offset = "";
    }
}
