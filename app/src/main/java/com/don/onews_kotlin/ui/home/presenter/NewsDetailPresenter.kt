package com.don.onews_kotlin.ui.home.presenter

import com.blankj.utilcode.utils.LogUtils
import com.don.onews_kotlin.bean.NewsDetail
import com.don.onews_kotlin.ui.home.contract.NewsDetailContract
import com.don.onews_kotlin.utils.baserx.RxSubscriber

/**
 * Created by drcom on 2017/4/7.
 */

class NewsDetailPresenter : NewsDetailContract.Presenter() {
    override fun loadNewsDetailDataRequest(postId: String) {
        mRxManage.add(mModel?.loadNewsDetailData(postId)
                ?.subscribe(object : RxSubscriber<NewsDetail>(mContext, false) {
                    protected override fun _onNext(newsDetail: NewsDetail?) {
                        if (newsDetail == null && newsDetail!!.equals("")) {
                            mView?.showLoadCompleteAllData()
                            mView?.hideProgress()
                        } else {
                            mView?.returnNewsDetailData(newsDetail)
                            mView?.hideProgress()
                        }

                    }

                    protected override fun _onError(message: String) {
                        LogUtils.d("message:" + message)
                        mView?.showLoadFailMsg(message)
                    }
                }))
    }
}
