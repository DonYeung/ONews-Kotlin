package com.don.onews_kotlin.app

/**
 * Created by drcom on 2017/3/16.
 */

class AppConstant {
    //    public static final String DATA_TYPE_WELFARE = "福利";
    //    public static final String DATA_TYPE_ANDROID = "Android";
    //    public static final String DATA_TYPE_IOS = "iOS";
    //    public static final String DATA_TYPE_EXTEND_RESOURCES = "拓展资源";
    //    public static final String DATA_TYPE_JS = "前端";
    //    public static final String DATA_TYPE_APP = "App";
    //    public static final String DATA_TYPE_RECOMMEND = "瞎推荐";
    //    public static final String DATA_TYPE_ALL = "all";

    ///// ********************* ↓ 以下是请求结果定义（用于UI层） ↓ *************************  //////

    object ReqResult {

        /**
         * 请求成功
         */
        val SUCCESS = 200

        /**
         * 无数据
         */
        val NO_DATA = 301


        /**
         * ACCESS_TOKEN 无效失效
         */
        val ACCESS_TOKEN_INVALID = 302


        /**
         * 网络请求超时或网络不通
         */
        val NET_WORK_FAIL = 490


    }

    companion object {

        var IsTestVersion = true    //是否测试服务器
        var IsRelease = false    //是否对外发布版本
        var isHasNewversion = false//是否有新版本
        /**
         * 请求成功
         */
        val REQUEST_SUCCESSFULLY = "1"

        /**
         * 请求失败
         */
        val REQUEST_FAILED = "0"

        val ERROR_TITLE = "网络请求失败"
        val ERROR_CONTEXT = "是网络先动的手不怪我"
        val ERROR_BUTTON = "重试"

        val HOME_TYPE = "type"
        var NEWS_LINK = "NEWS_LINK"
        var NEWS_TITLE = "NEWS_TITLE"
        var NEWS_POST_ID = "NEWS_POST_ID"
        var NEWS_IMG_RES = "NEWS_IMG_RES"
        val TRANSITION_ANIMATION_NEWS_PHOTOS = "transition_animation_news_photos"

        var NEWS_TYPE = "news_type"
        var NEWS_ID = "news_id"


        val VIDEO_TYPE = "VIDEO_TYPE"
        var VIDEO_LINK = "NEWS_LINK"
        var VIDEO_TITLE = "NEWS_TITLE"


        val DATA_TYPE_REST_VIDEO = "休息视频"
    }

}
