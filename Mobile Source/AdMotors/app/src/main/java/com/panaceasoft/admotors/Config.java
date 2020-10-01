package com.panaceasoft.admotors;

import com.panaceasoft.admotors.utils.Constants;

/**
 * Created by Panacea-Soft on 29/11/2019.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class Config {

    /**
     * AppVersion
     * For your app, you need to change according based on your app version
     */
    public static String APP_VERSION = "1.1";

    /**
     * APP Setting
     * Set false, your app is production
     * It will turn off the logging Process
     */
    public static boolean IS_DEVELOPMENT = true;

    /**
     * API URL
     * Change your backend url
     */
    public static final String APP_API_URL = "http://www.panacea-soft.com/admotors-admin/index.php/";
    public static final String APP_IMAGES_URL = "http://www.panacea-soft.com/admotors-admin/uploads/";
    public static final String APP_IMAGES_THUMB_URL = "http://www.panacea-soft.com/admotors-admin/uploads/thumbnail/";


    /**
     * API Key
     * If you change here, you need to update in server.
     */
    public static final String API_KEY = "teampsisthebest";


    /**
     * For default language change, please check
     * LanguageFragment for language code and country code
     * ..............................................................
     * Language             | Language Code     | Country Code
     * ..............................................................
     * "English"            | "en"              | ""
     * "Arabic"             | "ar"              | ""
     * "Chinese (Mandarin)" | "zh"              | ""
     * "French"             | "fr"              | ""
     * "German"             | "de"              | ""
     * "India (Hindi)"      | "hi"              | "rIN"
     * "Indonesian"         | "in"              | ""
     * "Italian"            | "it"              | ""
     * "Japanese"           | "ja"              | ""
     * "Korean"             | "ko"              | ""
     * "Malay"              | "ms"              | ""
     * "Portuguese"         | "pt"              | ""
     * "Russian"            | "ru"              | ""
     * "Spanish"            | "es"              | ""
     * "Thai"               | "th"              | ""
     * "Turkish"            | "tr"              | ""
     * ..............................................................
     */
    public static final String LANGUAGE_CODE = "en";
    public static final String DEFAULT_LANGUAGE_COUNTRY_CODE = "";
    public static final String DEFAULT_LANGUAGE = LANGUAGE_CODE;

    /**
     * Loading Limit Count Setting
     */
    public static final int API_SERVICE_CACHE_LIMIT = 5; // Minutes Cache

    public static int RATING_COUNT = 30;

    public static int ITEM_COUNT = 30;

    public static int LIST_MANUFACTURER_COUNT = 30;

    public static int LIST_NEW_FEED_COUNT = 30;

    public static int NOTI_LIST_COUNT = 30;

    public static int COMMENT_COUNT = 30;

    public static int LIST_NEW_FEED_COUNT_PAGER = 10; // cannot equal 15

    public static int HISTORY_COUNT = 30;

    public static int CHAT_HISTORY_COUNT = 30;

    public static int LOGIN_USER_ITEM_COUNT = 6;

    public static final String LIMIT_FROM_DB_COUNT = "10";

    public static int LIMIT_MANUFACTURER_COUNT_IN_DASHBOARD = 12;

    public static int HOME_BANNER_PAGER_COUNT = 20;

    /**
     * Price Format
     * Need to change according to your format that you need
     * E.g.
     * ",###.00"   => 2,555.00
     * "###.00"    => 2555.00
     * ".00"       => 2555.00
     * ",###"      => 2555
     * ",###,0"    => 2555.0
     */
    public static final String DECIMAL_PLACES_FORMAT = ",###";
    /**
     * Show currency at font or back
     * true => $ 2,555.00
     * false => 2,555,00 $
     */
    public static final boolean SYMBOL_SHOW_FRONT = true;


    /**
     * Region playstore
     */
    public static String PLAYSTORE_MARKET_URL_FIX = "market://details?id=";
    public static String PLAYSTORE_HTTP_URL_FIX = "http://play.google.com/store/apps/details?id=";

    /**
     * Image Cache and Loading
     */
    public static int IMAGE_CACHE_LIMIT = 250; // Mb
    public static boolean PRE_LOAD_FULL_IMAGE = true;


    /**
     * Admob Setting
     */
    public static final Boolean SHOW_ADMOB = true;


    /**
     * Firebase Configs
     */
    public static final String SUCCESSFULLY_DELETED = "deleted";
    public static final String SUCCESSFULLY_SAVED = "saved";
    public static final String CHAT = "Chat";
    public static final String MESSAGE = "Message";
    public static final String USER_PRESENCE = "User_Presence";
    public static final String CHAT_WITH = "Current_Chat_With";
    public static final String ACTIVE = "Online (Active)";
    public static final String INACTIVE = "Online (Inactive)";
    public static final String OFFLINE = "OFFLINE";

    /**
     * GDPR Configs
     */
    public static String CONSENTSTATUS_PERSONALIZED = "PERSONALIZED";
    public static String CONSENTSTATUS_NON_PERSONALIZED = "NON_PERSONALIZED";
    public static String CONSENTSTATUS_UNKNOWN = "UNKNOWN";
    public static String CONSENTSTATUS_CURRENT_STATUS = "UNKNOWN";
    public static String CONSENTSTATUS_IS_READY_KEY = "CONSENTSTATUS_IS_READY";

    /**
     * Policy Url
     */
    public static String POLICY_URL = "http://www.panacea-soft.com/policy/policy.html";

    /**
     * URI Authority File
     */
    public static String AUTHORITYFILE = ".fileprovider";

    /**
     * Facebook login Config
     */
    public static boolean ENABLE_FACEBOOK_LOGIN = true;

    /**
     * Google login Config
     */
    public static boolean ENABLE_GOOGLE_LOGIN = true;

    /**
     * Phone login Config
     */
    public static boolean ENABLE_PHONE_LOGIN = true;

    /**
     * New item upload setting
     */
    public static boolean CLOSE_ENTRY_AFTER_SUBMIT = true;

    /**
     * Default Camera Config
     *
     * Constants.DEFAULT_CAMERA   // Build In Default Camera
     * Constants.CUSTOM_CAMERA    // Custom Camera
     */
    public static String CAMERA_CONFIG = Constants.DEFAULT_CAMERA;
}
