package com.panaceasoft.admotors.di;


import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.ui.apploading.AppLoadingActivity;
import com.panaceasoft.admotors.ui.apploading.AppLoadingFragment;
import com.panaceasoft.admotors.ui.blog.detail.BlogDetailActivity;
import com.panaceasoft.admotors.ui.blog.detail.BlogDetailFragment;
import com.panaceasoft.admotors.ui.blog.list.BlogListActivity;
import com.panaceasoft.admotors.ui.blog.list.BlogListFragment;
import com.panaceasoft.admotors.ui.buildtype.BuildTypeFragment;
import com.panaceasoft.admotors.ui.dashboard.DashboardSearchActivity;
import com.panaceasoft.admotors.ui.manufacturer.list.ManufacturerListFragment;
import com.panaceasoft.admotors.ui.manufacturer.manufacturerfilter.ManufacturerFilterFragment;
import com.panaceasoft.admotors.ui.manufacturer.list.ManufacturerListActivity;
import com.panaceasoft.admotors.ui.chat.chat.ChatActivity;
import com.panaceasoft.admotors.ui.chat.chat.ChatFragment;
import com.panaceasoft.admotors.ui.chat.chatimage.ChatImageFullScreenActivity;
import com.panaceasoft.admotors.ui.chat.chatimage.ChatImageFullScreenFragment;
import com.panaceasoft.admotors.ui.chathistory.BuyerFragment;
import com.panaceasoft.admotors.ui.chathistory.MessageFragment;
import com.panaceasoft.admotors.ui.chathistory.SellerFragment;
import com.panaceasoft.admotors.ui.city.menu.CityMenuFragment;
import com.panaceasoft.admotors.ui.city.selectedcity.SelectedCityActivity;
import com.panaceasoft.admotors.ui.city.selectedcity.SelectedCityFragment;
import com.panaceasoft.admotors.ui.contactus.ContactUsFragment;
import com.panaceasoft.admotors.ui.customcamera.CameraActivity;
import com.panaceasoft.admotors.ui.customcamera.CameraFragment;
import com.panaceasoft.admotors.ui.customcamera.setting.CameraSettingActivity;
import com.panaceasoft.admotors.ui.customcamera.setting.CameraSettingFragment;
import com.panaceasoft.admotors.ui.dashboard.DashBoardSearchManufacturerFragment;
import com.panaceasoft.admotors.ui.dashboard.DashBoardSearchFragment;
import com.panaceasoft.admotors.ui.dashboard.DashBoardSearchModelFragment;
import com.panaceasoft.admotors.ui.forceupdate.ForceUpdateActivity;
import com.panaceasoft.admotors.ui.forceupdate.ForceUpdateFragment;
import com.panaceasoft.admotors.ui.fueltype.FuelTypeFragment;
import com.panaceasoft.admotors.ui.gallery.GalleryActivity;
import com.panaceasoft.admotors.ui.gallery.GalleryFragment;
import com.panaceasoft.admotors.ui.gallery.detail.GalleryDetailActivity;
import com.panaceasoft.admotors.ui.gallery.detail.GalleryDetailFragment;
import com.panaceasoft.admotors.ui.item.detail.ItemActivity;
import com.panaceasoft.admotors.ui.item.detail.ItemFragment;
import com.panaceasoft.admotors.ui.item.entry.ItemEntryActivity;
import com.panaceasoft.admotors.ui.item.entry.ItemEntryFragment;
import com.panaceasoft.admotors.ui.item.favourite.FavouriteListActivity;
import com.panaceasoft.admotors.ui.item.favourite.FavouriteListFragment;
import com.panaceasoft.admotors.ui.item.history.HistoryFragment;
import com.panaceasoft.admotors.ui.item.history.UserHistoryListActivity;
import com.panaceasoft.admotors.ui.item.itemcondition.ItemConditionFragment;
import com.panaceasoft.admotors.ui.item.itemcurrency.ItemCurrencyTypeFragment;
import com.panaceasoft.admotors.ui.item.itemdealoption.ItemDealOptionTypeFragment;
import com.panaceasoft.admotors.ui.item.itemfromfollower.ItemFromFollowerListActivity;
import com.panaceasoft.admotors.ui.item.itemfromfollower.ItemFromFollowerListFragment;
import com.panaceasoft.admotors.ui.item.itemlocation.ItemLocationFragment;
import com.panaceasoft.admotors.ui.item.itempricetype.ItemPriceTypeFragment;
import com.panaceasoft.admotors.ui.item.itemtype.ItemTypeFragment;
import com.panaceasoft.admotors.ui.item.itemtype.SearchViewActivity;
import com.panaceasoft.admotors.ui.item.loginUserItem.LoginUserItemFragment;
import com.panaceasoft.admotors.ui.item.loginUserItem.LoginUserItemListActivity;
import com.panaceasoft.admotors.ui.item.map.MapActivity;
import com.panaceasoft.admotors.ui.item.map.MapFragment;
import com.panaceasoft.admotors.ui.item.map.PickMapFragment;
import com.panaceasoft.admotors.ui.item.map.mapFilter.MapFilteringActivity;
import com.panaceasoft.admotors.ui.item.map.mapFilter.MapFilteringFragment;
import com.panaceasoft.admotors.ui.item.rating.RatingListActivity;
import com.panaceasoft.admotors.ui.item.rating.RatingListFragment;
import com.panaceasoft.admotors.ui.item.readmore.ReadMoreActivity;
import com.panaceasoft.admotors.ui.item.readmore.ReadMoreFragment;
import com.panaceasoft.admotors.ui.item.search.searchlist.SearchListActivity;
import com.panaceasoft.admotors.ui.item.search.searchlist.SearchListFragment;
import com.panaceasoft.admotors.ui.item.search.specialfilterbyattributes.FilteringActivity;
import com.panaceasoft.admotors.ui.item.search.specialfilterbyattributes.FilteringFragment;
import com.panaceasoft.admotors.ui.itemcolor.ItemColorFragment;
import com.panaceasoft.admotors.ui.language.LanguageFragment;
import com.panaceasoft.admotors.ui.location.LocationActivity;
import com.panaceasoft.admotors.ui.model.ModelActivity;
import com.panaceasoft.admotors.ui.model.ModelFragment;
import com.panaceasoft.admotors.ui.notification.detail.NotificationActivity;
import com.panaceasoft.admotors.ui.notification.detail.NotificationFragment;
import com.panaceasoft.admotors.ui.notification.list.NotificationListActivity;
import com.panaceasoft.admotors.ui.notification.list.NotificationListFragment;
import com.panaceasoft.admotors.ui.notification.setting.NotificationSettingActivity;
import com.panaceasoft.admotors.ui.notification.setting.NotificationSettingFragment;
import com.panaceasoft.admotors.ui.privacypolicy.PrivacyPolicyActivity;
import com.panaceasoft.admotors.ui.privacypolicy.PrivacyPolicyFragment;
import com.panaceasoft.admotors.ui.safetytip.SafetyTipFragment;
import com.panaceasoft.admotors.ui.safetytip.SafetyTipsActivity;
import com.panaceasoft.admotors.ui.sellertype.SellerTypeFragment;
import com.panaceasoft.admotors.ui.setting.SettingActivity;
import com.panaceasoft.admotors.ui.setting.SettingFragment;
import com.panaceasoft.admotors.ui.setting.appinfo.AppInfoActivity;
import com.panaceasoft.admotors.ui.setting.appinfo.AppInfoFragment;
import com.panaceasoft.admotors.ui.itemtransmission.ItemTransmissionFragment;
import com.panaceasoft.admotors.ui.user.PasswordChangeActivity;
import com.panaceasoft.admotors.ui.user.PasswordChangeFragment;
import com.panaceasoft.admotors.ui.user.phonelogin.PhoneLoginActivity;
import com.panaceasoft.admotors.ui.user.phonelogin.PhoneLoginFragment;
import com.panaceasoft.admotors.ui.user.ProfileEditActivity;
import com.panaceasoft.admotors.ui.user.ProfileEditFragment;
import com.panaceasoft.admotors.ui.user.ProfileFragment;
import com.panaceasoft.admotors.ui.user.UserForgotPasswordActivity;
import com.panaceasoft.admotors.ui.user.UserForgotPasswordFragment;
import com.panaceasoft.admotors.ui.user.UserLoginActivity;
import com.panaceasoft.admotors.ui.user.UserLoginFragment;
import com.panaceasoft.admotors.ui.user.UserRegisterActivity;
import com.panaceasoft.admotors.ui.user.UserRegisterFragment;
import com.panaceasoft.admotors.ui.user.verifyphone.VerifyMobileActivity;
import com.panaceasoft.admotors.ui.user.userlist.UserListActivity;
import com.panaceasoft.admotors.ui.user.userlist.UserListFragment;
import com.panaceasoft.admotors.ui.user.userlist.detail.UserDetailActivity;
import com.panaceasoft.admotors.ui.user.userlist.detail.UserDetailFragment;
import com.panaceasoft.admotors.ui.user.verifyemail.VerifyEmailActivity;
import com.panaceasoft.admotors.ui.user.verifyemail.VerifyEmailFragment;
import com.panaceasoft.admotors.ui.user.verifyphone.VerifyMobileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//import com.panaceasoft.psbuyandsell.ui.followinguser.FollowingUserActivity;
//import com.panaceasoft.psbuyandsell.ui.followinguser.FollowingUserFragment;
//import com.panaceasoft.psbuyandsell.ui.followinguser.detail.FollowingUserDetailActivity;
//import com.panaceasoft.psbuyandsell.ui.followinguser.detail.FollowingUserDetailFragment;

/**
 * Created by Panacea-Soft on 11/15/17.
 * Contact Email : teamps.is.cool@gmail.com
 */


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FavouriteListModule.class)
    abstract FavouriteListActivity contributeFavouriteListActivity();

    @ContributesAndroidInjector(modules = UserHistoryModule.class)
    abstract UserHistoryListActivity contributeUserHistoryListActivity();

    @ContributesAndroidInjector(modules = UserRegisterModule.class)
    abstract UserRegisterActivity contributeUserRegisterActivity();

    @ContributesAndroidInjector(modules = UserForgotPasswordModule.class)
    abstract UserForgotPasswordActivity contributeUserForgotPasswordActivity();

    @ContributesAndroidInjector(modules = UserLoginModule.class)
    abstract UserLoginActivity contributeUserLoginActivity();

    @ContributesAndroidInjector(modules = PasswordChangeModule.class)
    abstract PasswordChangeActivity contributePasswordChangeActivity();

    @ContributesAndroidInjector(modules = FilteringModule.class)
    abstract FilteringActivity filteringActivity();

    @ContributesAndroidInjector(modules = SubCategoryActivityModule.class)
    abstract ModelActivity subCategoryActivity();

    @ContributesAndroidInjector(modules = NotificationModule.class)
    abstract NotificationListActivity notificationActivity();

    @ContributesAndroidInjector(modules = CameraSettingActivityModule.class)
    abstract CameraSettingActivity cameraSettingActivity();

   @ContributesAndroidInjector(modules = PhoneLoginActivityModule.class)
    abstract PhoneLoginActivity contributePhoneLoginActivity();

    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchListActivity contributeSearchListActivity();

    @ContributesAndroidInjector(modules = CameraActivityModule.class)
    abstract CameraActivity contributeCameraActivity();

    @ContributesAndroidInjector(modules = ItemEntryActivityModule.class)
    abstract ItemEntryActivity contributeItemEntryActivity();

    @ContributesAndroidInjector(modules = NotificationDetailModule.class)
    abstract NotificationActivity notificationDetailActivity();

    @ContributesAndroidInjector(modules = ItemActivityModule.class)
    abstract ItemActivity itemActivity();

    @ContributesAndroidInjector(modules = SafetyTipsActivityModule.class)
    abstract SafetyTipsActivity safetyTipsActivity();

    @ContributesAndroidInjector(modules = GalleryDetailActivityModule.class)
    abstract GalleryDetailActivity galleryDetailActivity();

    @ContributesAndroidInjector(modules = GalleryActivityModule.class)
    abstract GalleryActivity galleryActivity();

    @ContributesAndroidInjector(modules = SearchByCategoryActivityModule.class)
    abstract DashboardSearchActivity searchByCategoryActivity();

    @ContributesAndroidInjector(modules = readMoreActivityModule.class)
    abstract ReadMoreActivity readMoreActivity();

    @ContributesAndroidInjector(modules = EditSettingModule.class)
    abstract SettingActivity editSettingActivity();

    @ContributesAndroidInjector(modules = LanguageChangeModule.class)
    abstract NotificationSettingActivity languageChangeActivity();

    @ContributesAndroidInjector(modules = ProfileEditModule.class)
    abstract ProfileEditActivity contributeProfileEditActivity();

    @ContributesAndroidInjector(modules = AppInfoModule.class)
    abstract AppInfoActivity AppInfoActivity();

    @ContributesAndroidInjector(modules = CategoryListActivityAppInfoModule.class)
    abstract ManufacturerListActivity categoryListActivity();

    @ContributesAndroidInjector(modules = RatingListActivityModule.class)
    abstract RatingListActivity ratingListActivity();

    @ContributesAndroidInjector(modules = SelectedCityModule.class)
    abstract SelectedCityActivity selectedShopActivity();

    @ContributesAndroidInjector(modules = SelectedShopListBlogModule.class)
    abstract BlogListActivity selectedShopListBlogActivity();

    @ContributesAndroidInjector(modules = BlogDetailModule.class)
    abstract BlogDetailActivity blogDetailActivity();

    @ContributesAndroidInjector(modules = MapActivityModule.class)
    abstract MapActivity mapActivity();

    @ContributesAndroidInjector(modules = forceUpdateModule.class)
    abstract ForceUpdateActivity forceUpdateActivity();

    @ContributesAndroidInjector(modules = MapFilteringModule.class)
    abstract MapFilteringActivity mapFilteringActivity();

    @ContributesAndroidInjector(modules = SearchViewActivityModule.class)
    abstract SearchViewActivity searchViewActivity();

    @ContributesAndroidInjector(modules = chatActivityModule.class)
    abstract ChatActivity chatActivity();

    @ContributesAndroidInjector(modules = ImageFullScreenModule.class)
    abstract ChatImageFullScreenActivity imageFullScreenActivity();

    @ContributesAndroidInjector(modules = LoginUserItemModule.class)
    abstract LoginUserItemListActivity contributeLoginUserItemListActivity();

    @ContributesAndroidInjector(modules = FollowerUserModule.class)
    abstract UserListActivity contributeFollowerUserListActivity();

    @ContributesAndroidInjector(modules = VerifyEmailModule.class)
    abstract VerifyEmailActivity contributeVerifyEmailActivity();

    @ContributesAndroidInjector(modules = VerifyMobileModule.class)
    abstract VerifyMobileActivity contributeVerifyMobileActivity();

    @ContributesAndroidInjector(modules = FollowerUserDetailModule.class)
    abstract UserDetailActivity contributeFollowerUserDetailActivity();

    @ContributesAndroidInjector(modules = AppLoadingActivityModule.class)
    abstract AppLoadingActivity appLoadingActivity();

    @ContributesAndroidInjector(modules = ItemFromFollowerListModule.class)
    abstract ItemFromFollowerListActivity itemFromFollowerListActivity();

    @ContributesAndroidInjector(modules = LocationActivityModule.class)
    abstract LocationActivity locationActivity();

    @ContributesAndroidInjector(modules = PrivacyAndPolicyActivityModule.class)
    abstract PrivacyPolicyActivity privacyPolicyActivity();

}


@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract ContactUsFragment contributeContactUsFragment();

    @ContributesAndroidInjector
    abstract VerifyMobileFragment contributeVerifyMobileFragment();

    @ContributesAndroidInjector
    abstract PhoneLoginFragment contributePhoneLoginFragment();

    @ContributesAndroidInjector
    abstract BuyerFragment contributeBuyerFragment();

    @ContributesAndroidInjector
    abstract SellerFragment contributeSellerFragment();

    @ContributesAndroidInjector
    abstract UserLoginFragment contributeUserLoginFragment();

    @ContributesAndroidInjector
    abstract UserForgotPasswordFragment contributeUserForgotPasswordFragment();

    @ContributesAndroidInjector
    abstract UserRegisterFragment contributeUserRegisterFragment();

    @ContributesAndroidInjector
    abstract NotificationSettingFragment contributeNotificationSettingFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract LanguageFragment contributeLanguageFragment();

    @ContributesAndroidInjector
    abstract FavouriteListFragment contributeFavouriteListFragment();

    @ContributesAndroidInjector
    abstract SettingFragment contributEditSettingFragment();

    @ContributesAndroidInjector
    abstract HistoryFragment historyFragment();

    @ContributesAndroidInjector
    abstract NotificationListFragment contributeNotificationFragment();

    @ContributesAndroidInjector
    abstract AppInfoFragment contributeAppInfoFragment();

    @ContributesAndroidInjector
    abstract SelectedCityFragment contributeSelectedCityFragment();

    @ContributesAndroidInjector
    abstract SearchListFragment contributeSearchListFragment();

    @ContributesAndroidInjector
    abstract ManufacturerListFragment contributeCategoryListFragment();

    @ContributesAndroidInjector
    abstract MessageFragment contributeMessageFragment();

    @ContributesAndroidInjector
    abstract DashBoardSearchFragment contributeDashBoardSearchFragment();

    @ContributesAndroidInjector
    abstract VerifyEmailFragment contributeVerifyEmailFragment();

    @ContributesAndroidInjector
    abstract PrivacyPolicyFragment contributePrivacyPolicyFragment();
}


@Module
abstract class ProfileEditModule {
    @ContributesAndroidInjector
    abstract ProfileEditFragment contributeProfileEditFragment();
}


@Module
abstract class ItemActivityModule {
    @ContributesAndroidInjector
    abstract ItemFragment contributeItemFragment();
}

@Module
abstract class SafetyTipsActivityModule {
    @ContributesAndroidInjector
    abstract SafetyTipFragment contributeSafetyTipFragment();
}

@Module
abstract class FavouriteListModule {
    @ContributesAndroidInjector
    abstract FavouriteListFragment contributeFavouriteFragment();
}


@Module
abstract class UserRegisterModule {
    @ContributesAndroidInjector
    abstract UserRegisterFragment contributeUserRegisterFragment();
}

@Module
abstract class UserForgotPasswordModule {
    @ContributesAndroidInjector
    abstract UserForgotPasswordFragment contributeUserForgotPasswordFragment();
}

@Module
abstract class UserLoginModule {
    @ContributesAndroidInjector
    abstract UserLoginFragment contributeUserLoginFragment();
}

@Module
abstract class PasswordChangeModule {
    @ContributesAndroidInjector
    abstract PasswordChangeFragment contributePasswordChangeFragment();
}


@Module
abstract class NotificationModule {
    @ContributesAndroidInjector
    abstract NotificationListFragment notificationFragment();
}

@Module
abstract class CameraSettingActivityModule {
    @ContributesAndroidInjector
    abstract CameraSettingFragment cameraSettingFragment();
}

@Module
abstract class PhoneLoginActivityModule {
    @ContributesAndroidInjector
    abstract PhoneLoginFragment cameraPhoneLoginFragment();
}

@Module
abstract class NotificationDetailModule {
    @ContributesAndroidInjector
    abstract NotificationFragment notificationDetailFragment();
}

@Module
abstract class UserHistoryModule {
    @ContributesAndroidInjector
    abstract HistoryFragment contributeHistoryFragment();
}

@Module
abstract class AppInfoModule {
    @ContributesAndroidInjector
    abstract AppInfoFragment contributeAppInfoFragment();
}

@Module
abstract class CategoryListActivityAppInfoModule {
    @ContributesAndroidInjector
    abstract ManufacturerListFragment contributeCategoryFragment();

}

@Module
abstract class RatingListActivityModule {
    @ContributesAndroidInjector
    abstract RatingListFragment contributeRatingListFragment();
}

@Module
abstract class readMoreActivityModule {
    @ContributesAndroidInjector
    abstract ReadMoreFragment contributeReadMoreFragment();
}

@Module
abstract class EditSettingModule {
    @ContributesAndroidInjector
    abstract SettingFragment EditSettingFragment();
}

@Module
abstract class LanguageChangeModule {
    @ContributesAndroidInjector
    abstract NotificationSettingFragment notificationSettingFragment();
}

@Module
abstract class EditProfileModule {
    @ContributesAndroidInjector
    abstract ProfileFragment ProfileFragment();
}

@Module
abstract class SubCategoryActivityModule {
    @ContributesAndroidInjector
    abstract ModelFragment contributeSubCategoryFragment();

}

@Module
abstract class FilteringModule {

    @ContributesAndroidInjector
    abstract ManufacturerFilterFragment contributeTypeFilterFragment();

    @ContributesAndroidInjector
    abstract FilteringFragment contributeSpecialFilteringFragment();

}

@Module
abstract class SearchActivityModule {
    @ContributesAndroidInjector
    abstract SearchListFragment contributefeaturedProductFragment();

    @ContributesAndroidInjector
    abstract ManufacturerListFragment contributeCategoryFragment();

    @ContributesAndroidInjector
    abstract ManufacturerFilterFragment contributeTypeFilterFragment();

}


@Module
abstract class CameraActivityModule {
    @ContributesAndroidInjector
    abstract CameraFragment contributeCameraFragment();
}

@Module
abstract class ItemEntryActivityModule {
    @ContributesAndroidInjector
    abstract ItemEntryFragment contributeItemEntryFragment();
}

@Module
abstract class GalleryDetailActivityModule {
    @ContributesAndroidInjector
    abstract GalleryDetailFragment contributeGalleryDetailFragment();
}

@Module
abstract class GalleryActivityModule {
    @ContributesAndroidInjector
    abstract GalleryFragment contributeGalleryFragment();
}

@Module
abstract class SearchByCategoryActivityModule {

    @ContributesAndroidInjector
    abstract DashBoardSearchManufacturerFragment contributeDashBoardSearchCategoryFragment();

    @ContributesAndroidInjector
    abstract DashBoardSearchModelFragment contributeDashBoardSearchSubCategoryFragment();
}

@Module
abstract class SelectedCityModule {

    @ContributesAndroidInjector
    abstract SearchListFragment contributefeaturedProductFragment();

    @ContributesAndroidInjector
    abstract ManufacturerListFragment categoryListFragment();

    @ContributesAndroidInjector
    abstract SelectedCityFragment contributeSelectedCityFragment();

    @ContributesAndroidInjector
    abstract ManufacturerFilterFragment contributeTypeFilterFragment();

    @ContributesAndroidInjector
    abstract CityMenuFragment contributeCityMenuFragment();

    @ContributesAndroidInjector
    abstract DashBoardSearchFragment contributeDashBoardSearchFragment();
}

@Module
abstract class SelectedShopListBlogModule {

    @ContributesAndroidInjector
    abstract BlogListFragment contributeSelectedShopListBlogFragment();

}

@Module
abstract class BlogDetailModule {

    @ContributesAndroidInjector
    abstract BlogDetailFragment contributeBlogDetailFragment();
}

@Module
abstract class MapActivityModule {

    @ContributesAndroidInjector
    abstract MapFragment contributeMapFragment();

    @ContributesAndroidInjector
    abstract PickMapFragment contributePickMapFragment();

}

@Module
abstract class forceUpdateModule {

    @ContributesAndroidInjector
    abstract ForceUpdateFragment contributeForceUpdateFragment();
}

@Module
abstract class MapFilteringModule {

    @ContributesAndroidInjector
    abstract MapFilteringFragment contributeMapFilteringFragment();
}

@Module
abstract class SearchViewActivityModule {

    @ContributesAndroidInjector
    abstract ItemCurrencyTypeFragment contributeItemConditionTypeFragment();

    @ContributesAndroidInjector
    abstract ItemConditionFragment contributeItemConditionFragment();

    @ContributesAndroidInjector
    abstract ItemLocationFragment contributeItemLocationFragment();

    @ContributesAndroidInjector
    abstract ItemDealOptionTypeFragment contributeItemDealOptionTypeFragment();

    @ContributesAndroidInjector
    abstract ItemPriceTypeFragment contributeItemPriceTypeFragment();

    @ContributesAndroidInjector
    abstract ItemTypeFragment contributeItemTypeFragment();

    @ContributesAndroidInjector
    abstract ItemTransmissionFragment contributeTransmissionFragment();

    @ContributesAndroidInjector
    abstract ItemColorFragment contributeItemColorFragment();

    @ContributesAndroidInjector
    abstract FuelTypeFragment contributeFuelTypeFragment();

    @ContributesAndroidInjector
    abstract BuildTypeFragment contributeBuildTypeFragment();

    @ContributesAndroidInjector
    abstract SellerTypeFragment contributeSellerTypeFragment();
}

@Module
abstract class chatActivityModule {

    @ContributesAndroidInjector
    abstract ChatFragment contributeChatFragment();
}

@Module
abstract class ImageFullScreenModule {

    @ContributesAndroidInjector
    abstract ChatImageFullScreenFragment contributeImageFullScreenFragment();

}

@Module
abstract class LoginUserItemModule {
    @ContributesAndroidInjector
    abstract LoginUserItemFragment contributeLoginUserItemFragment();
}

@Module
abstract class FollowerUserModule {
    @ContributesAndroidInjector
    abstract UserListFragment contributeFollowerUserFragment();
}

@Module
abstract class VerifyEmailModule {
    @ContributesAndroidInjector
    abstract VerifyEmailFragment contributeVerifyEmailFragment();

}

@Module
abstract class VerifyMobileModule {
    @ContributesAndroidInjector
    abstract VerifyMobileFragment contributeVerifyMobileFragment();
}

@Module
abstract class FollowerUserDetailModule {
    @ContributesAndroidInjector
    abstract UserDetailFragment contributeFollowerUserDetailFragment();
}

@Module
abstract class AppLoadingActivityModule {

    @ContributesAndroidInjector
    abstract AppLoadingFragment contributeAppLoadingFragment();
}

@Module
abstract class ItemFromFollowerListModule {

    @ContributesAndroidInjector
    abstract ItemFromFollowerListFragment contributeItemFromFollowerListFragment();
}

@Module
abstract class LocationActivityModule {

    @ContributesAndroidInjector
    abstract ItemLocationFragment contributeItemLocationFragment();

}

@Module
abstract class PrivacyAndPolicyActivityModule {

    @ContributesAndroidInjector
    abstract PrivacyPolicyFragment contributePrivacyPolicyFragment();

}
