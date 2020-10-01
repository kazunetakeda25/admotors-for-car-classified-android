package com.panaceasoft.admotors.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.panaceasoft.admotors.db.common.Converters;
import com.panaceasoft.admotors.viewobject.AboutUs;
import com.panaceasoft.admotors.viewobject.Banner;
import com.panaceasoft.admotors.viewobject.Blog;
import com.panaceasoft.admotors.viewobject.BuildType;
import com.panaceasoft.admotors.viewobject.ChatHistory;
import com.panaceasoft.admotors.viewobject.ChatHistoryMap;
import com.panaceasoft.admotors.viewobject.City;
import com.panaceasoft.admotors.viewobject.CityMap;
import com.panaceasoft.admotors.viewobject.Color;
import com.panaceasoft.admotors.viewobject.DeletedObject;
import com.panaceasoft.admotors.viewobject.FuelType;
import com.panaceasoft.admotors.viewobject.Image;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.ItemCategory;
import com.panaceasoft.admotors.viewobject.ItemCollection;
import com.panaceasoft.admotors.viewobject.ItemCollectionHeader;
import com.panaceasoft.admotors.viewobject.ItemCondition;
import com.panaceasoft.admotors.viewobject.ItemCurrency;
import com.panaceasoft.admotors.viewobject.ItemDealOption;
import com.panaceasoft.admotors.viewobject.ItemFavourite;
import com.panaceasoft.admotors.viewobject.ItemFromFollower;
import com.panaceasoft.admotors.viewobject.ItemHistory;
import com.panaceasoft.admotors.viewobject.ItemLocation;
import com.panaceasoft.admotors.viewobject.ItemMap;
import com.panaceasoft.admotors.viewobject.ItemPriceType;
import com.panaceasoft.admotors.viewobject.ItemSpecs;
import com.panaceasoft.admotors.viewobject.ItemSubCategory;
import com.panaceasoft.admotors.viewobject.ItemType;
import com.panaceasoft.admotors.viewobject.Manufacturer;
import com.panaceasoft.admotors.viewobject.Model;
import com.panaceasoft.admotors.viewobject.Noti;
import com.panaceasoft.admotors.viewobject.PSAppInfo;
import com.panaceasoft.admotors.viewobject.PSAppSetting;
import com.panaceasoft.admotors.viewobject.PSAppVersion;
import com.panaceasoft.admotors.viewobject.PSCount;
import com.panaceasoft.admotors.viewobject.Rating;
import com.panaceasoft.admotors.viewobject.SellerType;
import com.panaceasoft.admotors.viewobject.Transmission;
import com.panaceasoft.admotors.viewobject.User;
import com.panaceasoft.admotors.viewobject.UserLogin;
import com.panaceasoft.admotors.viewobject.UserMap;
import com.panaceasoft.admotors.viewobject.messageHolder.Message;


/**
 * Created by Panacea-Soft on 11/20/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Database(entities = {
        Image.class,
        User.class,
        UserLogin.class,
        AboutUs.class,
        ItemFavourite.class,
        Noti.class,
        ItemHistory.class,
        Blog.class,
        Rating.class,
        PSAppInfo.class,
        PSAppVersion.class,
        DeletedObject.class,
        City.class,
        CityMap.class,
        Item.class,
        ItemMap.class,
        ItemCategory.class,
        ItemCollectionHeader.class,
        ItemCollection.class,
        ItemSubCategory.class,
        ItemSpecs.class,
        ItemCurrency.class,
        ItemPriceType.class,
        ItemType.class,
        ItemLocation.class,
        ItemDealOption.class,
        ItemCondition.class,
        ItemFromFollower.class,
        Message.class,
        ChatHistory.class,
        ChatHistoryMap.class,
        PSAppSetting.class,
        UserMap.class,
        PSCount.class,
        Manufacturer.class,
        Model.class,
        Transmission.class,
        Color.class,
        FuelType.class,
        BuildType.class,
        SellerType.class,
        Banner.class
}, version = 1, exportSchema = false)
// app version 1.1 = db version 1
// app version 1.0 = db version 1


@TypeConverters({Converters.class})

public abstract class PSCoreDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public UserMapDao userMapDao();

    abstract public HistoryDao historyDao();

    abstract public SpecsDao specsDao();

    abstract public AboutUsDao aboutUsDao();

    abstract public ImageDao imageDao();

    abstract public ItemDealOptionDao itemDealOptionDao();

    abstract public ItemConditionDao itemConditionDao();

    abstract public ItemLocationDao itemLocationDao();

    abstract public ItemCurrencyDao itemCurrencyDao();

    abstract public ItemPriceTypeDao itemPriceTypeDao();

    abstract public ItemTypeDao itemTypeDao();

    abstract public RatingDao ratingDao();

    abstract public NotificationDao notificationDao();

    abstract public BlogDao blogDao();

    abstract public PSAppInfoDao psAppInfoDao();

    abstract public PSAppVersionDao psAppVersionDao();

    abstract public DeletedObjectDao deletedObjectDao();

    abstract public CityDao cityDao();

    abstract public CityMapDao cityMapDao();

    abstract public ItemDao itemDao();

    abstract public ItemMapDao itemMapDao();

    abstract public ItemManufacturerDao itemManufacturerDao();

    abstract public ItemCollectionHeaderDao itemCollectionHeaderDao();

    abstract public ItemModelDao itemModelDao();

    abstract public ChatHistoryDao chatHistoryDao();

    abstract public MessageDao messageDao();

    abstract public PSCountDao psCountDao();

    abstract public TransmissionDao transmissionDao();

    abstract public ItemColorDao itemColorDao();

    abstract public FuelTypeDao fuelTypeDao();

    abstract public BuildTypeDao buildTypeDao();

    abstract public SellerTypeDao sellerTypeDao();

    abstract public HomeBannerDao homeBannerDao();

//    /**
//     * Migrate from:
//     * version 1 - using Room
//     * to
//     * version 2 - using Room where the {@link } has an extra field: addedDateStr
//     */
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE news "
//                    + " ADD COLUMN addedDateStr INTEGER NOT NULL DEFAULT 0");
//        }
//    };

    /* More migration write here */
}