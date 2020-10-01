package com.panaceasoft.admotors.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.panaceasoft.admotors.viewmodel.aboutus.AboutUsViewModel;
import com.panaceasoft.admotors.viewmodel.apploading.PSAPPLoadingViewModel;
import com.panaceasoft.admotors.viewmodel.blog.BlogViewModel;
import com.panaceasoft.admotors.viewmodel.buildtype.BuildTypeViewModel;
import com.panaceasoft.admotors.viewmodel.chat.ChatViewModel;
import com.panaceasoft.admotors.viewmodel.chathistory.ChatHistoryViewModel;
import com.panaceasoft.admotors.viewmodel.city.CityViewModel;
import com.panaceasoft.admotors.viewmodel.city.FeaturedCitiesViewModel;
import com.panaceasoft.admotors.viewmodel.city.PopularCitiesViewModel;
import com.panaceasoft.admotors.viewmodel.city.RecentCitiesViewModel;
import com.panaceasoft.admotors.viewmodel.clearalldata.ClearAllDataViewModel;
import com.panaceasoft.admotors.viewmodel.common.NotificationViewModel;
import com.panaceasoft.admotors.viewmodel.common.PSNewsViewModelFactory;
import com.panaceasoft.admotors.viewmodel.contactus.ContactUsViewModel;
import com.panaceasoft.admotors.viewmodel.fueltype.FuelTypeViewModel;
import com.panaceasoft.admotors.viewmodel.homebanner.HomeBannerViewModel;
import com.panaceasoft.admotors.viewmodel.homelist.HomeTrendingCategoryListViewModel;
import com.panaceasoft.admotors.viewmodel.image.ImageViewModel;
import com.panaceasoft.admotors.viewmodel.item.FavouriteViewModel;
import com.panaceasoft.admotors.viewmodel.item.HistoryViewModel;
import com.panaceasoft.admotors.viewmodel.item.PopularItemViewModel;
import com.panaceasoft.admotors.viewmodel.item.RecentItemViewModel;
import com.panaceasoft.admotors.viewmodel.item.SpecsViewModel;
import com.panaceasoft.admotors.viewmodel.item.TouchCountViewModel;
import com.panaceasoft.admotors.viewmodel.itemmanufacturer.ItemManufacturerViewModel;
import com.panaceasoft.admotors.viewmodel.itemcolor.ItemColorViewModel;
import com.panaceasoft.admotors.viewmodel.itemcondition.ItemConditionViewModel;
import com.panaceasoft.admotors.viewmodel.itemcurrency.ItemCurrencyViewModel;
import com.panaceasoft.admotors.viewmodel.itemdealoption.ItemDealOptionViewModel;
import com.panaceasoft.admotors.viewmodel.itemfromfollower.ItemFromFollowerViewModel;
import com.panaceasoft.admotors.viewmodel.itemlocation.ItemLocationViewModel;
import com.panaceasoft.admotors.viewmodel.itempricetype.ItemPriceTypeViewModel;
import com.panaceasoft.admotors.viewmodel.itemmodel.ItemModelViewModel;
import com.panaceasoft.admotors.viewmodel.itemtransmission.ItemTransmissionViewModel;
import com.panaceasoft.admotors.viewmodel.itemtype.ItemTypeViewModel;
import com.panaceasoft.admotors.viewmodel.notification.NotificationsViewModel;
import com.panaceasoft.admotors.viewmodel.pscount.PSCountViewModel;
import com.panaceasoft.admotors.viewmodel.rating.RatingViewModel;
import com.panaceasoft.admotors.viewmodel.sellertype.SellerTypeViewModel;
import com.panaceasoft.admotors.viewmodel.user.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Panacea-Soft on 11/16/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PSNewsViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AboutUsViewModel.class)
    abstract ViewModel bindAboutUsViewModel(AboutUsViewModel aboutUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemLocationViewModel.class)
    abstract ViewModel bindItemLocationViewModel(ItemLocationViewModel itemLocationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemDealOptionViewModel.class)
    abstract ViewModel bindItemDealOptionViewModel(ItemDealOptionViewModel itemDealOptionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemConditionViewModel.class)
    abstract ViewModel bindItemConditionViewModel(ItemConditionViewModel itemConditionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel.class)
    abstract ViewModel bindImageViewModel(ImageViewModel imageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemTypeViewModel.class)
    abstract ViewModel bindItemTypeViewModel(ItemTypeViewModel itemTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RatingViewModel.class)
    abstract ViewModel bindRatingViewModel(RatingViewModel ratingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    abstract ViewModel bindNotificationViewModel(NotificationViewModel notificationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemFromFollowerViewModel.class)
    abstract ViewModel bindItemFromFollowerViewModel(ItemFromFollowerViewModel itemFromFollowerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemPriceTypeViewModel.class)
    abstract ViewModel bindItemPriceTypeViewModel(ItemPriceTypeViewModel itemPriceTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemCurrencyViewModel.class)
    abstract ViewModel bindItemCurrencyViewModel(ItemCurrencyViewModel itemCurrencyViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel.class)
    abstract ViewModel bindContactUsViewModel(ContactUsViewModel contactUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel.class)
    abstract ViewModel bindFavouriteViewModel(FavouriteViewModel favouriteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TouchCountViewModel.class)
    abstract ViewModel bindTouchCountViewModel(TouchCountViewModel touchCountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SpecsViewModel.class)
    abstract ViewModel bindProductSpecsViewModel(SpecsViewModel specsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel.class)
    abstract ViewModel bindHistoryProductViewModel(HistoryViewModel historyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemManufacturerViewModel.class)
    abstract ViewModel bindCityCategoryViewModel(ItemManufacturerViewModel itemManufacturerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel.class)
    abstract ViewModel bindNotificationListViewModel(NotificationsViewModel notificationListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeTrendingCategoryListViewModel.class)
    abstract ViewModel bindHomeTrendingCategoryListViewModel(HomeTrendingCategoryListViewModel transactionListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BlogViewModel.class)
    abstract ViewModel bindNewsFeedViewModel(BlogViewModel blogViewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(PSAppInfoViewModel.class)
//    abstract ViewModel bindPSAppInfoViewModel(PSAppInfoViewModel psAppInfoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ClearAllDataViewModel.class)
    abstract ViewModel bindClearAllDataViewModel(ClearAllDataViewModel clearAllDataViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel.class)
    abstract ViewModel bindCityViewModel(CityViewModel cityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(com.panaceasoft.admotors.viewmodel.item.ItemViewModel.class)
    abstract ViewModel bindItemViewModel(com.panaceasoft.admotors.viewmodel.item.ItemViewModel itemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PopularItemViewModel.class)
    abstract ViewModel bindPopularItemViewModel(PopularItemViewModel popularItemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RecentItemViewModel.class)
    abstract ViewModel bindRecentItemViewModel(RecentItemViewModel recentItemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PSAPPLoadingViewModel.class)
    abstract ViewModel bindPSAPPLoadingViewModel(PSAPPLoadingViewModel psappLoadingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PopularCitiesViewModel.class)
    abstract ViewModel bindPopularCitiesViewModel(PopularCitiesViewModel popularCitiesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FeaturedCitiesViewModel.class)
    abstract ViewModel bindFeaturedCitiesViewModel(FeaturedCitiesViewModel featuredCitiesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RecentCitiesViewModel.class)
    abstract ViewModel bindRecentCitiesViewModel(RecentCitiesViewModel recentCitiesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemModelViewModel.class)
    abstract ViewModel bindItemSubCategoryViewModel(ItemModelViewModel itemModelViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel.class)
    abstract ViewModel bindChatViewModel(ChatViewModel chatViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChatHistoryViewModel.class)
    abstract ViewModel bindSellerViewModel(ChatHistoryViewModel chatHistoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PSCountViewModel.class)
    abstract ViewModel bindPSCountViewModel(PSCountViewModel psCountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemTransmissionViewModel.class)
    abstract ViewModel bindTransmissionViewModel(ItemTransmissionViewModel transmissionViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ItemColorViewModel.class)
    abstract ViewModel bindItemColorViewModel(ItemColorViewModel itemColorViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FuelTypeViewModel.class)
    abstract ViewModel bindFueltypeViewModel(FuelTypeViewModel fuelTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BuildTypeViewModel.class)
    abstract ViewModel bindBuildtypeViewModel(BuildTypeViewModel buildTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SellerTypeViewModel.class)
    abstract ViewModel bindSellertypeViewModel(SellerTypeViewModel sellerTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeBannerViewModel.class)
    abstract ViewModel bindHomeBannerViewModel(HomeBannerViewModel homeBannerViewModel);
}


