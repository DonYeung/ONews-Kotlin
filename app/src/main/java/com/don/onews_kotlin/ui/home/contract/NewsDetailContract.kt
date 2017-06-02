package com.don.onews_kotlin.ui.home.contract


import com.don.onews_kotlin.base.BaseModel
import com.don.onews_kotlin.base.BasePresenter
import com.don.onews_kotlin.base.BaseView
import com.don.onews_kotlin.bean.NewsDetail

import rx.Observable

/**
 * Created by drcom on 2017/4/7.
 */

interface NewsDetailContract {
    interface Model : BaseModel {
        //请求获取新闻详情
        fun loadNewsDetailData(postId: String): Observable<NewsDetail>
    }

    interface View : BaseView {
        fun returnNewsDetailData(homeData: NewsDetail)
    }


    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun loadNewsDetailDataRequest(postId: String)
    }

}
