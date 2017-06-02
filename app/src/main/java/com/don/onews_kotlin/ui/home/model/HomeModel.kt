package com.don.onews_kotlin.ui.home.model


import com.don.onews_kotlin.api.Apiwrapper
import com.don.onews_kotlin.bean.NewsSummary
import com.don.onews_kotlin.ui.home.contract.HomeContract

import rx.Observable

/**
 * Created by drcom on 2017/03/17
 */

class HomeModel : HomeContract.Model {

    override fun loadHomeListData(type: String, key: String, startPage: Int): Observable<List<NewsSummary>> {
        return Apiwrapper.getInstance().getNewsList(type, key, startPage) as Observable<List<NewsSummary>>
    }
}

