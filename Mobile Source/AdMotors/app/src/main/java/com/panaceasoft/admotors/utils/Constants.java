package com.panaceasoft.admotors.utils;

/**
 * Created by Panacea-Soft on 3/19/19.
 * Contact Email : teamps.is.cool@gmail.com
 */


public interface Constants {

    //region General

    String EMPTY_STRING = "";
    String SPACE_STRING = " ";
    String ZERO = "0";
    String ONE = "1";
    String TWO = "2";
    String THREE = "3";
    String FOUR = "4";
    String FIVE = "5";

    String NO_DATA = "NO_DATA";
    String DASH = "-";

    //endregion

    //chat

    String CHAT_TO_SELLER = "to_seller";//don't change
    String CHAT_TO_BUYER = "to_buyer";//don't change
    String CHAT_TYPE_SELLER = "seller";//don't change
    String CHAT_TYPE_BUYER = "buyer";//don't change
    String CHAT_OFFER_STATUS = "offerStatus";//don't change
    String CHAT_IS_SOLD = "isSold";//don't change
    String CHAT_FROM_BUYER = "CHAT_FROM_BUYER";
    String CHAT_FROM_SELLER = "CHAT_FROM_SELLER";
    String CHAT_FLAG = "CHAT_FLAG";

    int CHAT_DATE_UI = 0; //don't change

    int CHAT_SENDER_UI = 1; //don't change
    int CHAT_SENDER_IMAGE_UI = 2; //don't change

    int CHAT_RECEIVER_UI = 3; //don't change
    int CHAT_RECEIVER_IMAGE_UI = 4; //don't change

    int CHAT_SENDER_OFFER_UI = 5; //don't change
    int CHAT_RECEIVER_OFFER_UI = 6; //don't change

    int CHAT_SENDER_OFFER_ACCEPT_UI = 7; //don't change
    int CHAT_RECEIVER_OFFER_ACCEPT_UI = 8; //don't change

    int CHAT_SENDER_OFFER_REJECT_UI = 9; //don't change
    int CHAT_RECEIVER_OFFER_REJECT_UI = 10; //don't change

    int CHAT_ITEM_HAS_BEEN_SOLD = 11; //don't change


    int CHAT_STATUS_NULL = 0;
    int CHAT_STATUS_OFFER = 1;
    int CHAT_STATUS_REJECT = 2;
    int CHAT_STATUS_ACCEPT = 3;

    int CHAT_TYPE_TEXT = 0;
    int CHAT_TYPE_IMAGE = 1;
    int CHAT_TYPE_OFFER = 2;
    int CHAT_TYPE_DATE = 3;
    int CHAT_TYPE_SOLD = 4;


    //endregion

    // region City

    String CITY_ID = "CITY_ID";
    String CITY_NAME = "CITY_NAME";
    String CITY = "CITY"; // Don't Change
    String CITY_HOLDER = "CITY_HOLDER";
    String CITY_START_DATE = "CITY_START_DATE";
    String CITY_END_DATE = "CITY_END_DATE";
    String CITY_TEL = "tel:"; // Don't Change
    String CITY_LAT = "CITY_LAT";
    String CITY_LNG = "CITY_LNG";

    //endregion

    // region Blog/News

    String BLOG_ID = "BLOG_ID";

    //endregion

    //region REQUEST CODE AND RESULT CODE

    int REQUEST_CODE__SEARCH_FRAGMENT = 1001;
    int REQUEST_CODE__NOTIFICATION_LIST_FRAGMENT = 1002;
    int REQUEST_CODE__ITEM_LIST_FRAGMENT = 1003;
    int REQUEST_CODE__COMMENT_LIST_FRAGMENT = 1004;
    int REQUEST_CODE__ITEM_FRAGMENT = 1005;
    int REQUEST_CODE__PROFILE_FRAGMENT = 1006;
    int REQUEST_CODE__MAP_FILTERING = 1007;
    int REQUEST_CODE__SELECTED_CITY_FRAGMENT = 1008;
    int REQUEST_CODE__SEARCH_VIEW_FRAGMENT = 1009;

    int REQUEST_CODE__FIRST_GALLERY = 1011;
    int REQUEST_CODE__SEC_GALLERY = 1012;
    int REQUEST_CODE__THIRD_GALLERY = 1013;
    int REQUEST_CODE__FOURTH_GALLERY = 1014;
    int REQUEST_CODE__FIFTH_GALLERY = 1015;
    int REQUEST_CODE__PERMISSION_CODE = 1016;

    int REQUEST_CODE__FIRST_CAMERA = 1017;
    int REQUEST_CODE__SEC_CAMERA = 1018;
    int REQUEST_CODE__THIRD_CAMERA = 1019;
    int REQUEST_CODE__FOURTH_CAMERA = 1020;
    int REQUEST_CODE__FIFTH_CAMERA = 1021;

    int REQUEST_CODE__FIRST_CUSTOM_CAMERA = 1022;
    int REQUEST_CODE__SEC_CUSTOM_CAMERA = 1023;
    int REQUEST_CODE__THIRD_CUSTOM_CAMERA = 1024;
    int REQUEST_CODE__FOURTH_CUSTOM_CAMERA = 1025;
    int REQUEST_CODE__FIFTH_CUSTOM_CAMERA = 1026;

    int REQUEST_CODE__BUYER_CHAT_FRAGMENT = 1027;
    int REQUEST_CODE__SELLER_CHAT_FRAGMENT = 1029;
    int REQUEST_CODE__PHONE_CALL_PERMISSION = 2030;

    int RESULT_LOAD_IMAGE = 1;
    int RESULT_OK = -1;

    int RESULT_CODE__SEARCH_WITH_MANUFACTURER = 2001;
    int RESULT_CODE__SEARCH_WITH_MODEL = 2002;
    int RESULT_CODE__REFRESH_NOTIFICATION = 2003;
    int RESULT_CODE__SPECIAL_FILTER = 2004;
    int RESULT_CODE__CATEGORY_FILTER = 2005;
    int RESULT_CODE__REFRESH_COMMENT_LIST = 2006;
    int RESULT_CODE__LOGOUT_ACTIVATED = 2007;
    int RESULT_CODE__MAP_FILTERING = 2008;
    int RESULT_CODE__SEARCH_WITH_ITEM_TYPE = 2009;
    int RESULT_CODE__SEARCH_WITH_ITEM_PRICE_TYPE = 2010;
    int RESULT_CODE__SEARCH_WITH_ITEM_CURRENCY_TYPE = 2011;
    int RESULT_CODE__SEARCH_WITH_ITEM_OPTION_TYPE = 2012;
    int RESULT_CODE__IMAGE_CATEGORY = 2013;
    int RESULT_CODE__SEARCH_WITH_ITEM_CONDITION_TYPE = 2014;
    int RESULT_CODE__SEARCH_WITH_ITEM_LOCATION_TYPE = 2015;
    int RESULT_CODE__FROM_MAP_VIEW = 2016;
    int RESULT_CODE__TO_MAP_VIEW = 2017;
    int RESULT_CODE__ITEM_ENTRY_WITH_CUSTOM_CAMERA = 2018;
    int RESULT_CODE__CHAT_FRAGMENT = 2019;
    int RESULT_CODE__SEARCH_WITH_TRANSMISSION = 2020;
    int RESULT_CODE_SEARCH_WITH_ITEM_COLOR = 2021;
    int RESULT_CODE_SEARCH_WITH_FUEL_TYPE = 2022;
    int RESULT_CODE_SEARCH_WITH_BUILD_TYPE = 2023;
    int RESULT_CODE_SEARCH_WITH_SELLER_TYPE = 2024;

    //endregion

    String DEFAULT_CAMERA = "DEFAULT_CAMERA";
    String CUSTOM_CAMERA = "CUSTOM_CAMERA";
    String CAMERA_TYPE = "CAMERA_TYPE";

    //region Platform

    String PLATFORM = "android"; // Please don't change!


    //region AppInfo type_name

    String APPINFO_NAME_CITY = "APPINFO_NAME_CITY";
    String APPINFO_NAME_ITEM = "APPINFO_NAME_ITEM";
    String APPINFO_NAME_CATEGORY = "APPINFO_NAME_CATEGORY";
    String APPINFO_PREF_VERSION_NO = "APPINFO_PREF_VERSION_NO";
    String APPINFO_PREF_FORCE_UPDATE = "APPINFO_PREF_FORCE_UPDATE";
    String APPINFO_FORCE_UPDATE_MSG = "APPINFO_FORCE_UPDATE_MSG";
    String APPINFO_FORCE_UPDATE_TITLE = "APPINFO_FORCE_UPDATE_TITLE";


    //endregion

    //map
    String MAP_MILES = "8";//cannot change
    String MAP_PICK = "MAP_PICK";
    String MAP = "MAP";
    String MAP_FLAG = "MAP_FLAG";

    //endregion

    //region User
    String FACEBOOK_ID = "FACEBOOK_ID";
    String PHONE_ID = "PHONE_ID";
    String GOOGLE_ID = "GOOGLE_ID";
    String USER_ID = "USER_ID";
    String OTHER_USER_ID = "OTHER_USER_ID";
    String OTHER_USER_NAME = "OTHER_USER_NAME";
    String USER_NAME = "USER_NAME";
    String USER_PHONE = "USER_PHONE";
    String USER_EMAIL = "USER_EMAIL";
    String USER_NO_USER = "nologinuser"; // Don't Change
    String USER_NO_DEVICE_TOKEN = "nodevicetoken"; // Don't Change
    String USER_PASSWORD = "password";
    String RECEIVE_USER_ID = "RECEIVE_USER_ID";
    String RECEIVE_USER_NAME = "RECEIVE_USER_NAME";
    String RECEIVE_USER_IMG_URL = "RECEIVE_USER_IMG_URL";
    String USER_EMAIL_TO_VERIFY = "USER_EMAIL_TO_VERIFY";
    String USER_PASSWORD_TO_VERIFY = "USER_PASSWORD_TO_VERIFY";
    String USER_NAME_TO_VERIFY = "USER_NAME_TO_VERIFY";
    String USER_ID_TO_VERIFY = "USER_ID_TO_VERIFY";

    String USER_PARAM_HOLDER_KEY = "USER_PARAM_HOLDER_KEY";


    //endregion

    //PROFILE

//    String PROFILE_FRAGMENT_TYPE = "PROFILE_FRAGMENT_TYPE";
//    String USER_LOGIN_TYPE = "USER_LOGIN_TYPE";
//    String USER_REGISTER_TYPE = "USER_REGISTER_TYPE";
////    String FB_REGISTER_TYPE = "FB_REGISTER_TYPE";
//    String PROFILE_TYPE = "PROFILE_TYPE";
//    String VERIFY_EMAIL_TYPE = "VERIFY_EMAIL_TYPE";

    //region Product

    String ITEM_PARAM_HOLDER_KEY = "ITEM_PARAM_HOLDER_KEY";

    String ITEM_NAME = "ITEM_NAME";
    String ITEM_COUNT = "ITEM_COUNT";
    String ITEM_TAG = "ITEM_TAG";
    String ITEM_ID = "ITEM_id";
    String ITEM_HOLDER = "ITEM_HOLDER";
    String ITEM_USER_ID = "ITEM_USER_ID";
    String ADD_NEW_ITEM = "ADD_NEW_ITEM";

    //endregion

    String ITEM_TITLE = "item_title";
    String ITEM_DESCRIPTION = "item_desc";

    //region Filtering Don't Change

    String FILTERING_FILTER_NAME = "name"; // Don't Change
    String FILTERING_TYPE_FILTER = "tf"; // Don't Change
    String FILTERING_SPECIAL_FILTER = "sf"; // Don't Change
    String FILTERING_TYPE_NAME = "item"; // Don't Change
    String FILTERING_INACTIVE = ""; // Don't Change
    String FILTERING_TRENDING = "touch_count"; // Don't Change
    String FILTERING_FEATURE = "featured_date"; // Don't Change
    String FILTERING_ASC = "asc"; // Don't Change
    String FILTERING_DESC = "desc"; // Don't Change
    String FILTERING_ADDED_DATE = "added_date"; // Don't Change
    String FILTERING_HOLDER = "filter_holder"; // Don't Change
    String FILTERING_NAME = "title"; // Don't Change

    //endregion

    //region Manufacturer
    String MANUFACTURER_NAME = "MANUFACTURER_NAME";
    String MANUFACTURER_ID = "MANUFACTURER_ID";
    String MANUFACTURER = "MANUFACTURER";
    String MANUFACTURER_ALL = "ALL";
    String MANUFACTURER_FLAG = "MANUFACTURER_FLAG";

    //endregion

    //region model
    String MODEL_ID = "MODEL_ID";
    String MODEL = "MODEL";
    String MODEL_NAME = "MODEL_NAME";

    //endregion

    //region ITEM
    String ITEM_TYPE = "ITEM_TYPE";
    String ITEM_TYPE_FLAG = "ITEM_TYPE_FLAG";
    String ITEM_PRICE_TYPE = "ITEM_PRICE_TYPE";
    String ITEM_PRICE = "ITEM_PRICE";
    String ITEM_CURRENCY = "ITEM_CURRENCY";
    String ITEM_CURRENCY_TYPE = "ITEM_CURRENCY_TYPE";
    String ITEM_DEAL_OPTION_TYPE = "DEAL_OPTION_TYPE";
    String ITEM_CONDITION_TYPE = "ITEM_CONDITION_TYPE";
    String ITEM_LOCATION_TYPE = "ITEM_LOCATION_TYPE";
    String ITEM_TYPE_ID = "ITEM_TYPE_ID";
    String ITEM_TYPE_NAME = "ITEM_TYPE_NAME";
    String ITEM_PRICE_TYPE_ID = "ITEM_PRICE_TYPE_ID";
    String ITEM_PRICE_TYPE_NAME = "ITEM_PRICE_TYPE_NAME";
    String ITEM_CURRENCY_TYPE_ID = "ITEM_CURRENCY_TYPE_ID";
    String ITEM_CURRENCY_TYPE_NAME = "ITEM_CURRENCY_TYPE_NAME";
    String ITEM_OPTION_TYPE_ID = "ITEM_OPTION_TYPE_ID";
    String ITEM_OPTION_TYPE_NAME = "ITEM_OPTION_TYPE_NAME";
    String ITEM_CONDITION_TYPE_ID = "ITEM_CONDITION_TYPE_ID";
    String ITEM_CONDITION_TYPE_NAME = "ITEM_CONDITION_TYPE_NAME";
    String ITEM_LOCATION_TYPE_ID = "ITEM_LOCATION_TYPE_ID";
    String ITEM_LOCATION_TYPE_NAME = "ITEM_LOCATION_TYPE_NAME";
    String TRANSMISSION = "TRANSMISSION";
    String TRANSMISSION_ID = "TRANSMISSION_ID";
    String TRANSMISSION_NAME = "TRANSMISSION_NAME";
    String ITEM_COLOR = "ITEM_COLOR";
    String ITEM_COLOR_ID = "ITEM_COLOR_ID";
    String ITEM_COLOR_VALUE = "ITEM_COLOR_VALUE";
    String FUEL_TYPE = "FUEL_TYPE";
    String FUEL_TYPE_ID = "FUEL_TYPE_ID";
    String FUEL_TYPE_NAME = "FUEL_TYPE_NAME";
    String BUILD_TYPE = "BUILD_TYPE";
    String BUILD_TYPE_ID = "BUILD_TYPE_ID";
    String BUILD_TYPE_NAME = "BUILD_TYPE_NAME";
    String SELLER_TYPE = "SELLER_TYPE";
    String SELLER_TYPE_ID = "SELLER_TYPE_ID";
    String SELLER_TYPE_NAME = "SELLER_TYPE_NAME";
    String ITEM_PRICE_UNIT = "ITEM_PRICE_UNIT";

    //endregion

    String SELECTED_LOCATION_ID = "SELECTED_LOCATION_ID";
    String SELECTED_LOCATION_NAME = "SELECTED_LOCATION_NAME";
    String LOCATION_FLAG = "LOCATION_FLAG";
    String LOCATION_WITH_CLEAR_ICON = "LOCATION_WITH_CLEAR_ICON";
    String LOCATION_NOT_CLEAR_ICON = "LOCATION_NOT_CLEAR_ICON";
    String SELECT_LOCATION_FROM_HOME = "SELECT_LOCATION_FROM_HOME";

    //location

    //region Image

    String IMAGE_TYPE = "IMAGE_TYPE";
    String IMAGE_PARENT_ID = "IMAGE_PARENT_ID";
    String IMAGE_ID = "IMAGE_ID";
    String IMAGE_TYPE_PRODUCT = "item";

    //endregion

    //region Language

    String LANGUAGE_CODE = "Language";
    String LANGUAGE_COUNTRY_CODE ="Language_Country_Code";

    //endregion

    //region Comment

    String COMMENT_ID = "COMMENT_ID";
    String COMMENT_HEADER_ID = "COMMENT_HEADER_ID";

    //endregion


    //regionHistory

    String HISTORY_FLAG = "history_flag";

    //endregion


    //region Noti

    String NOTI_NEW_ID = "NOTI_NEW_ID";
    String NOTI_ID = "NOTI_ID";
    String NOTI_TOKEN = "NOTI_TOKEN";
    String NOTI_EXISTS_TO_SHOW = "IS_NOTI_EXISTS_TO_SHOW";
    String NOTI_MSG = "NOTI_MSG";
    String NOTI_SETTING = "NOTI_SETTING";
    String NOTI_HEADER_ID = "NOTI_HEADER_ID";
    String NOTI_ITEM_ID = "NOTI_ITEM_ID";
    String NOTI_BUYER_ID = "NOTI_BUYER_ID";
    String NOTI_SELLER_ID = "NOTI_SELLER_ID";
    String NOTI_SENDER_NAME = "NOTI_SENDER_NAME";
    String NOTI_SENDER_URL = "NOTI_SENDER_URL";
    String C_NOTI_ID = "C_NOTI_ID";


    //endregion


    //region FB Register

    String FB_FIELDS = "fields"; // Don't Change
    String FB_EMAILNAMEID = "email,name,id"; // Don't Change
    String FB_NAME_KEY = "name"; // Don't Change
    String FB_EMAIL_KEK = "email"; // Don't Change
    String FB_ID_KEY = "id"; // Don't Change

    //endregion

    //region Email Type

    String EMAIL_TYPE = "plain/text"; // Don't Change
    String HTTP = "http://"; // Don't Change

    //endregion

    //region BoostManger

    String GALLERY_BOOST = "android.gestureboost.GestureBoostManager"; // Don't Change
    String GALLERY_GESTURE = "sGestureBoostManager"; // Don't Change
    String GALLERY_ID = "id"; // Don't Change
    String GALLERY_CONTEXT = "mContext"; // Don't Change

    //endregion

    String LAT = "lat";

    String LNG = "lng";

    //region Message

    String TEXT = "text";
    String IMAGE = "IMAGE";

    String IMAGE_PATH = "IMAGE_PATH";

    String AFTERLOGOUT = "AFTERLOGOUT";
}

