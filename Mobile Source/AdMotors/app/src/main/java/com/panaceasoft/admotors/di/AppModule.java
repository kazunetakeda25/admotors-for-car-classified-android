package com.panaceasoft.admotors.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.AboutUsDao;
import com.panaceasoft.admotors.db.BlogDao;
import com.panaceasoft.admotors.db.BuildTypeDao;
import com.panaceasoft.admotors.db.ChatHistoryDao;
import com.panaceasoft.admotors.db.CityDao;
import com.panaceasoft.admotors.db.CityMapDao;
import com.panaceasoft.admotors.db.DeletedObjectDao;
import com.panaceasoft.admotors.db.FuelTypeDao;
import com.panaceasoft.admotors.db.HistoryDao;
import com.panaceasoft.admotors.db.HomeBannerDao;
import com.panaceasoft.admotors.db.ImageDao;
import com.panaceasoft.admotors.db.ItemManufacturerDao;
import com.panaceasoft.admotors.db.ItemCollectionHeaderDao;
import com.panaceasoft.admotors.db.ItemColorDao;
import com.panaceasoft.admotors.db.ItemConditionDao;
import com.panaceasoft.admotors.db.ItemCurrencyDao;
import com.panaceasoft.admotors.db.ItemDao;
import com.panaceasoft.admotors.db.ItemDealOptionDao;
import com.panaceasoft.admotors.db.ItemLocationDao;
import com.panaceasoft.admotors.db.ItemMapDao;
import com.panaceasoft.admotors.db.ItemPriceTypeDao;
import com.panaceasoft.admotors.db.ItemModelDao;
import com.panaceasoft.admotors.db.ItemTypeDao;
import com.panaceasoft.admotors.db.MessageDao;
import com.panaceasoft.admotors.db.NotificationDao;
import com.panaceasoft.admotors.db.PSAppInfoDao;
import com.panaceasoft.admotors.db.PSAppVersionDao;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.db.PSCountDao;
import com.panaceasoft.admotors.db.RatingDao;
import com.panaceasoft.admotors.db.SellerTypeDao;
import com.panaceasoft.admotors.db.TransmissionDao;
import com.panaceasoft.admotors.db.UserDao;
import com.panaceasoft.admotors.db.UserMapDao;
import com.panaceasoft.admotors.utils.AppLanguage;
import com.panaceasoft.admotors.utils.Connectivity;
import com.panaceasoft.admotors.utils.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Panacea-Soft on 11/15/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton
    @Provides
    PSApiService providePSApiService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Config.APP_API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(PSApiService.class);

    }

    @Singleton
    @Provides
    PSCoreDb provideDb(Application app) {
        return Room.databaseBuilder(app, PSCoreDb.class, "psmulticity.db")
                //.addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    Connectivity provideConnectivity(Application app) {
        return new Connectivity(app);
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
    }

    @Singleton
    @Provides
    UserDao provideUserDao(PSCoreDb db) {
        return db.userDao();
    }

    @Singleton
    @Provides
    UserMapDao provideUserMapDao(PSCoreDb db) {
        return db.userMapDao();
    }

    @Singleton
    @Provides
    AppLanguage provideCurrentLanguage(SharedPreferences sharedPreferences) {
        return new AppLanguage(sharedPreferences);
    }

    @Singleton
    @Provides
    AboutUsDao provideAboutUsDao(PSCoreDb db) {
        return db.aboutUsDao();
    }

    @Singleton
    @Provides
    ImageDao provideImageDao(PSCoreDb db) {
        return db.imageDao();
    }

    @Singleton
    @Provides
    ItemCurrencyDao provideItemCurrencyDao(PSCoreDb db) {
        return db.itemCurrencyDao();
    }

    @Singleton
    @Provides
    ItemTypeDao provideItemTypeDao(PSCoreDb db) {
        return db.itemTypeDao();
    }

    @Singleton
    @Provides
    ItemPriceTypeDao provideItemPriceTypeDao(PSCoreDb db) {
        return db.itemPriceTypeDao();
    }

    @Singleton
    @Provides
    HistoryDao provideHistoryDao(PSCoreDb db) {
        return db.historyDao();
    }

    @Singleton
    @Provides
    RatingDao provideRatingDao(PSCoreDb db) {
        return db.ratingDao();
    }

    @Singleton
    @Provides
    ItemDealOptionDao provideItemDealOptionDao(PSCoreDb db) {
        return db.itemDealOptionDao();
    }

    @Singleton
    @Provides
    ItemConditionDao provideItemConditionDao(PSCoreDb db) {
        return db.itemConditionDao();
    }

    @Singleton
    @Provides
    ItemLocationDao provideItemLocationDao(PSCoreDb db) {
        return db.itemLocationDao();
    }

    @Singleton
    @Provides
    NotificationDao provideNotificationDao(PSCoreDb db) {
        return db.notificationDao();
    }

    @Singleton
    @Provides
    BlogDao provideNewsFeedDao(PSCoreDb db) {
        return db.blogDao();
    }

    @Singleton
    @Provides
    PSAppInfoDao providePSAppInfoDao(PSCoreDb db) {
        return db.psAppInfoDao();
    }

    @Singleton
    @Provides
    PSAppVersionDao providePSAppVersionDao(PSCoreDb db) {
        return db.psAppVersionDao();
    }

    @Singleton
    @Provides
    DeletedObjectDao provideDeletedObjectDao(PSCoreDb db) {
        return db.deletedObjectDao();
    }

    @Singleton
    @Provides
    CityDao provideCityDao(PSCoreDb db) {
        return db.cityDao();
    }

    @Singleton
    @Provides
    CityMapDao provideCityMapDao(PSCoreDb db) {
        return db.cityMapDao();
    }

    @Singleton
    @Provides
    ItemDao provideItemDao(PSCoreDb db) {
        return db.itemDao();
    }

    @Singleton
    @Provides
    ItemMapDao provideItemMapDao(PSCoreDb db) {
        return db.itemMapDao();
    }

    @Singleton
    @Provides
    ItemManufacturerDao provideCityCategoryDao(PSCoreDb db) {
        return db.itemManufacturerDao();
    }

    @Singleton
    @Provides
    ItemCollectionHeaderDao provideItemCollectionHeaderDao(PSCoreDb db) {
        return db.itemCollectionHeaderDao();
    }

    @Singleton
    @Provides
    ItemModelDao provideItemSubCategoryDao(PSCoreDb db) {
        return db.itemModelDao();
    }

    @Singleton
    @Provides
    ChatHistoryDao provideChatHistoryDao(PSCoreDb db) {
        return db.chatHistoryDao();
    }

    @Singleton
    @Provides
    MessageDao provideMessageDao(PSCoreDb db) {
        return db.messageDao();
    }

    @Singleton
    @Provides
    PSCountDao providePSCountDao(PSCoreDb db) {
        return db.psCountDao();
    }

    @Singleton
    @Provides
    TransmissionDao provideTransmissionDao(PSCoreDb db) {
        return db.transmissionDao();
    }

    @Singleton
    @Provides
    ItemColorDao provideItemColorDao(PSCoreDb db) {
        return db.itemColorDao();
    }

    @Singleton
    @Provides
    FuelTypeDao provideFuelTypeDao(PSCoreDb db) {
        return db.fuelTypeDao();
    }

    @Singleton
    @Provides
    BuildTypeDao provideBuildTypeDao(PSCoreDb db) {
        return db.buildTypeDao();
    }

    @Singleton
    @Provides
    SellerTypeDao provideSellerTypeDao(PSCoreDb db) {
        return db.sellerTypeDao();
    }

    @Singleton
    @Provides
    HomeBannerDao provideHomeBannerDao(PSCoreDb db) {
        return db.homeBannerDao();
    }
}
