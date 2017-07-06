package com.don.onews_kotlin.ui.home.presenter

import com.blankj.utilcode.utils.LogUtils
import com.don.onews_kotlin.bean.NewsSummary
import com.don.onews_kotlin.ui.home.contract.HomeContract
import com.don.onews_kotlin.utils.baserx.RxSubscriber

/**
 * Created by drcom on 2017/03/17
 */

class HomePresenter : HomeContract.Presenter() {
    private var isload: Boolean = false
    override fun loadHomeListDataRequest(type: String, key: String, startPage: Int, isLoad: Boolean) {
        isload = isLoad
        mRxManage.add(mModel?.loadHomeListData(type, key, startPage)
                ?.subscribe(object : RxSubscriber<List<NewsSummary>>(mContext, false) {
                     override fun _onNext(homeData: List<NewsSummary>) {

                        if (isload) {
                            //api 列表数据等于20即到底
                            if (homeData.size == 0) {
                                mView?.showLoadCompleteAllData()
                                mView?.hideProgress()
                            } else {
                                mView?.addHomeListData(homeData)
                                mView?.hideProgress()
                            }
                        } else {

                            mView?.returnHomeListData(homeData)
                            mView?.hideProgress()

                        }


                    }

                     override fun _onError(message: String) {
                        LogUtils.d("message:" + message)
                        mView?.showLoadFailMsg(message)

                    }
                }))

    }


}