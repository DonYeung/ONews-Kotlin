package com.don.onews_kotlin.api;

import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.NetworkUtils;
import com.don.onews_kotlin.app.AppConstant;
import com.don.onews_kotlin.base.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by drcom on 2017/3/17.
 */
public class OkHttp3Utils {
    private static OkHttpClient mOkHttpClient;
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";


    /**
     * 获取OkHttpClient对象
     */
    public static OkHttpClient getOkHttpClient() {
        //是否开启Log
        HttpLoggingInterceptor interceptorLog = new HttpLoggingInterceptor();
        if(AppConstant.IsRelease)
            interceptorLog.setLevel(HttpLoggingInterceptor.Level.NONE);
        else
            interceptorLog.setLevel(HttpLoggingInterceptor.Level.NONE);

        //缓存
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        if (null == mOkHttpClient) {

            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptorLog)
                    .cache(cache)
                    .build();

        }

        return mOkHttpClient;
    }

    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    public static String getCacheControl() {
        return NetworkUtils.isConnected()? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }
}

