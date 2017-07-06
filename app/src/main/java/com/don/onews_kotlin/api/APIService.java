package com.don.onews_kotlin.api;


import com.don.onews_kotlin.bean.GankVideoData;
import com.don.onews_kotlin.bean.HomeData;
import com.don.onews_kotlin.bean.HttpResult;
import com.don.onews_kotlin.bean.NewsDetail;
import com.don.onews_kotlin.bean.NewsSummary;
import com.don.onews_kotlin.bean.Video;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by drcom on 2017/3/17.
 */

public interface APIService {

    //获取首页详情
    @GET("/toutiao/index")
    Observable<HttpResult<HomeData.ResultBean>> getHomeTop(@Query("type") String type,
                                                           @Query("key") String key,
                                                           @Query("page") int page);

    //获取新闻首页
    @GET("http://c.m.163.com/nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String,List<NewsSummary>>> getNewsList(@Path("type") String type,
                                                          @Path("id") String id,
                                                          @Path("startPage") int startPage);
    //获取新闻详情　
    @GET("http://c.m.163.com/nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewDetail(
            @Path("postId") String postId);


    @GET(ApiConstant.GANKHOST+"data/{type}/10/{Page}")
    Observable<GankVideoData> getGankVideoList(@Path("type") String type,
                                               @Path("Page") int startPage);

    @POST("http://www.shiyan360.cn/index.php/api/chuangke_list")
    Observable<Video> getvideo(
            @Query("desc_type") String desc_type,
            @Query("category_id") String category_id,
            @Query("page") int page);


}
