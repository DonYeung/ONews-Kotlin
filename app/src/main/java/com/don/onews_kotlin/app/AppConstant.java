package com.don.onews_kotlin.app;

/**
 * Created by drcom on 2017/3/16.
 */

public class AppConstant {

    public static boolean IsTestVersion = true;	//是否测试服务器
    public static boolean IsRelease = false;	//是否对外发布版本
    public static boolean isHasNewversion = false;//是否有新版本
    /**
     * 请求成功
     */
    public final static String REQUEST_SUCCESSFULLY  = "1";

    /**
     * 请求失败
     */
    public final static String REQUEST_FAILED = "0";

    public final static  String ERROR_TITLE="网络请求失败";
    public final static  String ERROR_CONTEXT="是网络先动的手不怪我";
    public final static  String ERROR_BUTTON="重试";

    public static final String HOME_TYPE = "type";
    public static  String NEWS_LINK = "NEWS_LINK";
    public static  String NEWS_TITLE = "NEWS_TITLE";
    public static  String NEWS_POST_ID = "NEWS_POST_ID";
    public static  String NEWS_IMG_RES = "NEWS_IMG_RES";
    public static final String TRANSITION_ANIMATION_NEWS_PHOTOS = "transition_animation_news_photos";

    public static  String NEWS_TYPE = "news_type";
    public static  String NEWS_ID = "news_id";


    public static final String VIDEO_TYPE = "VIDEO_TYPE";
    public static  String VIDEO_LINK = "NEWS_LINK";
    public static  String VIDEO_TITLE = "NEWS_TITLE";


    public static final String DATA_TYPE_REST_VIDEO = "休息视频";
//    public static final String DATA_TYPE_WELFARE = "福利";
//    public static final String DATA_TYPE_ANDROID = "Android";
//    public static final String DATA_TYPE_IOS = "iOS";
//    public static final String DATA_TYPE_EXTEND_RESOURCES = "拓展资源";
//    public static final String DATA_TYPE_JS = "前端";
//    public static final String DATA_TYPE_APP = "App";
//    public static final String DATA_TYPE_RECOMMEND = "瞎推荐";
//    public static final String DATA_TYPE_ALL = "all";

    ///// ********************* ↓ 以下是请求结果定义（用于UI层） ↓ *************************  //////

    public class  ReqResult
    {

        /**
         * 请求成功
         */
        public final static int SUCCESS  = 200;

        /**
         * 无数据
         */
        public final static int NO_DATA  = 301;


        /**
         * ACCESS_TOKEN 无效失效
         */
        public final static int ACCESS_TOKEN_INVALID  = 302;


        /**
         * 网络请求超时或网络不通
         */
        public final static int NET_WORK_FAIL  = 490;


    }

}
