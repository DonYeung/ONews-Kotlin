package com.don.onews_kotlin.api


import com.don.onews_kotlin.bean.GankVideoData
import com.don.onews_kotlin.bean.HomeData
import com.don.onews_kotlin.bean.HttpResult
import com.don.onews_kotlin.bean.NewsDetail
import com.don.onews_kotlin.bean.NewsSummary
import com.don.onews_kotlin.bean.Video

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by drcom on 2017/3/17.
 */

interface APIService {

    //获取首页详情
    @GET("/toutiao/index")
    fun getHomeTop(@Query("type") type: String,
                   @Query("key") key: String,
                   @Query("page") page: Int): Observable<HttpResult<HomeData.ResultBean>>

    //获取新闻首页
    @GET("http://c.m.163.com/nc/article/{type}/{id}/{startPage}-20.html")
    fun getNewsList(@Path("type") type: String,
                    @Path("id") id: String,
                    @Path("startPage") startPage: Int): Observable<Map<String, List<NewsSummary>>>

    //获取新闻详情　
    @GET("http://c.m.163.com/nc/article/{postId}/full.html")
    fun getNewDetail(
            @Path("postId") postId: String): Observable<Map<String, NewsDetail>>


//    @GET(ApiConstant.GANKHOST + "data/{type}/10/{Page}")
    @GET("http://gank.io/api/" + "data/{type}/10/{Page}")
    fun getGankVideoList(@Path("type") type: String,
                         @Path("Page") startPage: Int): Observable<GankVideoData>

    @POST("http://www.shiyan360.cn/index.php/api/chuangke_list")
    fun getvideo(
            @Query("desc_type") desc_type: String,
            @Query("category_id") category_id: String,
            @Query("page") page: Int): Observable<Video>


}
