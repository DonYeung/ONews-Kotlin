package com.don.onews_kotlin.ui.video.contract


import com.don.onews_kotlin.base.BaseModel
import com.don.onews_kotlin.base.BasePresenter
import com.don.onews_kotlin.base.BaseView
import com.don.onews_kotlin.bean.Video
import rx.Observable

/**
 * Created by drcom on 2017/3/22.
 */

interface VideoContract {
    interface Model : BaseModel {
        //请求获取视频
        //    Observable<GankVideoData> loadVideoListData(String type, int startPage);
        fun loadVideoListData(type: String, category_id: String, startPage: Int): Observable<Video>
    }

    interface View : BaseView {
        //        void returnVideoListData(GankVideoData videoDatas);
        //        void addVideoListData(GankVideoData videoDatas);
        fun returnVideoListData(videoDatas: Video)

        fun addVideoListData(videoDatas: Video)

    }


    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun loadVideoListDataRequest(type: String, category_id: String, startPage: Int, isLoad: Boolean)
    }


}