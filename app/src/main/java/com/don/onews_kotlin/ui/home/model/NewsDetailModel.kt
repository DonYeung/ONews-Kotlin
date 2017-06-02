package com.don.onews_kotlin.ui.home.model


import com.don.onews_kotlin.api.Apiwrapper
import com.don.onews_kotlin.bean.NewsDetail
import com.don.onews_kotlin.ui.home.contract.NewsDetailContract

import rx.Observable

/**
 * Created by drcom on 2017/4/7.
 */

class NewsDetailModel : NewsDetailContract.Model {
    override fun loadNewsDetailData(postId: String): Observable<NewsDetail> {
        return Apiwrapper.getInstance().getNewDetail(postId) as Observable<NewsDetail>
    }
}
