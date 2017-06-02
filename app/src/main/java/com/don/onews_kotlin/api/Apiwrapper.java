package com.don.onews_kotlin.api;


import com.don.onews_kotlin.bean.GankVideoData;
import com.don.onews_kotlin.bean.HomeData;
import com.don.onews_kotlin.bean.HttpResult;
import com.don.onews_kotlin.bean.NewsDetail;
import com.don.onews_kotlin.bean.NewsSummary;
import com.don.onews_kotlin.bean.Video;
import com.don.onews_kotlin.utils.TimeUtil;
import com.don.onews_kotlin.utils.baserx.RxSchedulers;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by drcom on 2017/3/17.
 */

public class Apiwrapper extends RetrofitUtils {
    private static Apiwrapper mAPIWrapper;

    public Apiwrapper() {
    }

    public static Apiwrapper getInstance() {
        if (mAPIWrapper == null) {
            mAPIWrapper = new Apiwrapper();
        }
        return mAPIWrapper;
    }

    public Observable getHomeTop(String type, String key, int page){

        Observable observable =RetrofitUtils.getAPIService().getHomeTop(type,key,page)
                .map(new HttpResultFunc<HomeData.ResultBean>())//过滤HttpResult头
                .compose(RxSchedulers.<HomeData.ResultBean>io_main());//声明线程调度

        return observable;
    }

    public Observable getNewsList(String type, final String id, int page){

        Observable observable =RetrofitUtils.getAPIService().getNewsList(type,id,page)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> map) {
//                        if (id.endsWith(ApiConstant.HOST)) {
//                            // 房产实际上针对地区的它的id与返回key不同
//                            return Observable.from(map.get("北京"));
//                        }
                        return Observable.from(map.get(id));
                    }
                }
                )
                //转化时间
                .map(new Func1<NewsSummary, NewsSummary>() {
                    @Override
                    public NewsSummary call(NewsSummary newsSummary) {
                        String ptime = TimeUtil.formatDate(newsSummary.getPtime());
                        newsSummary.setPtime(ptime);
                        return newsSummary;
                    }
                })
                .distinct()//去重
                .toSortedList(new Func2<NewsSummary, NewsSummary, Integer>() {
                    @Override
                    public Integer call(NewsSummary newsSummary, NewsSummary newsSummary2) {
                        return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
                    }
                })
                .compose(RxSchedulers.<List<NewsSummary>>io_main());//声明线程调度

        return observable;
    }

    public Observable getNewDetail(final String postId){
        Observable observable =RetrofitUtils.getAPIService().getNewDetail(postId)
                .map(new Func1<Map<String, NewsDetail>, NewsDetail>() {
                    @Override
                    public NewsDetail call(Map<String, NewsDetail> map) {
                        NewsDetail newsDetail = map.get(postId);
                        return newsDetail;
                    }
                })
                //声明线程调度
                .compose(RxSchedulers.<NewsDetail>io_main());
                return observable;
    }

    public Observable getGankVideo(String type, int startPage){
        Observable observable =RetrofitUtils.getAPIService().getGankVideoList(type,startPage)
                //声明线程调度
                .compose(RxSchedulers.<GankVideoData>io_main());

        return observable;
    }

    public Observable getvideo(String type, String category_id, int startPage){
        Observable observable =RetrofitUtils.getAPIService().getvideo(type,category_id,startPage)
                //声明线程调度
                .compose(RxSchedulers.<Video>io_main());

        return observable;
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private  class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getError_code() !=0 ) {
                throw new ApiException(httpResult);
            }
            return httpResult.getResult();
        }
    }
}
