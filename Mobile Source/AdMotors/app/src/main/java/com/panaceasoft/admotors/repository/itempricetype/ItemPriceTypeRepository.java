package com.panaceasoft.admotors.repository.itempricetype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.ItemPriceTypeDao;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.ItemPriceType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemPriceTypeRepository extends PSRepository {
    private ItemPriceTypeDao itemPriceTypeDao;

    @Inject
    ItemPriceTypeRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ItemPriceTypeDao itemPriceTypeDao) {

        super(psApiService, appExecutors, db);
        this.itemPriceTypeDao = itemPriceTypeDao;
    }

    public LiveData<Resource<List<ItemPriceType>>> getAllItemPriceTypeList(String limit, String offset) {

        return new NetworkBoundResource<List<ItemPriceType>, List<ItemPriceType>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<ItemPriceType> itemTypeList) {
                Utils.psLog("SaveCallResult of getAllCategoriesWithUserId");

                try {
                    db.runInTransaction(() -> {
                        itemPriceTypeDao.deleteAllItemPriceType();

                        itemPriceTypeDao.insertAll(itemTypeList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<ItemPriceType> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<ItemPriceType>> loadFromDb() {

                Utils.psLog("Load From Db (All Categories)");

                return itemPriceTypeDao.getAllItemPriceType();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<ItemPriceType>>> createCall() {
                Utils.psLog("Call Get All Categories webservice.");

                return psApiService.getItemPriceTypeList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of About Us");
            }

        }.asLiveData();
    }
}