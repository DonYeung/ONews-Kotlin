package com.don.onews_kotlin.ui.video.model


import com.don.onews_kotlin.api.Apiwrapper
import com.don.onews_kotlin.bean.Video
import com.don.onews_kotlin.ui.video.contract.VideoContract
import rx.Observable

/**
 * Created by drcom on 2017/03/22
 */

class VideoModel : VideoContract.Model {

    override fun loadVideoListData(type: String, category_id: String, startPage: Int): Observable<Video> {
        return Apiwrapper.getInstance().getvideo(type, category_id, startPage) as Observable<Video>
    }
}