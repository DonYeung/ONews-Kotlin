package com.don.onews_kotlin.api;

/**
 * Created by drcom on 2017/3/17.
 */

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *封装一个retrofit集成0kHttp3的抽象基类
 */
public abstract class RetrofitUtils {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static APIService mAPIService;
    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.INSTANCE.getOkHttpClient();
            }
            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(ApiConstant.INSTANCE.getHOST() + "/")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofit;
    }

    public static APIService getAPIService() {
        if (mAPIService == null) {
            mAPIService = getRetrofit().create(APIService.class);
        }
        return mAPIService;
    }

}
