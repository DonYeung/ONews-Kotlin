package com.don.onews_kotlin.ui.home.contract


import com.don.onews_kotlin.base.BaseModel
import com.don.onews_kotlin.base.BasePresenter
import com.don.onews_kotlin.base.BaseView
import com.don.onews_kotlin.bean.NewsSummary

import rx.Observable

/**
 * Created by drcom on 2017/3/17.
 */

interface HomeContract {
    interface Model : BaseModel {
        //请求获取视频
        fun loadHomeListData(type: String, id: String, startPage: Int): Observable<List<NewsSummary>>
    }

    interface View : BaseView {
        fun returnHomeListData(homeData: List<NewsSummary>)
        fun addHomeListData(homeDatas: List<NewsSummary>)
    }


    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun loadHomeListDataRequest(type: String, id: String, startPage: Int, isLoad: Boolean)
    }


}