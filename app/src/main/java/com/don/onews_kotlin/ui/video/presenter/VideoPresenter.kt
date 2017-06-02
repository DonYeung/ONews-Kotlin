package com.don.onews_kotlin.ui.video.presenter

import com.blankj.utilcode.utils.LogUtils
import com.don.onews_kotlin.bean.Video
import com.don.onews_kotlin.ui.video.contract.VideoContract
import com.don.onews_kotlin.utils.baserx.RxSubscriber

/**
 * Created by drcom on 2017/03/22
 */

class VideoPresenter : VideoContract.Presenter() {
    private var isload: Boolean = false
    override fun loadVideoListDataRequest(type: String, category_id: String, startPage: Int, isLoad: Boolean) {
        isload = isLoad

        mRxManage?.add(mModel?.loadVideoListData(type, category_id, startPage)
                ?.subscribe(object : RxSubscriber<Video>(mContext, false) {
                     override fun _onNext(videoDatas: Video) {
                        LogUtils.d("isload:" + isload)
                        if (isload) {
                            //api 视频列表数据小于20即到底
                            if (videoDatas.data?.size!! < 20) {
                                mView?.showLoadCompleteAllData()
                                mView?.hideProgress()
                            } else {
                                mView?.addVideoListData(videoDatas)
                                mView?.hideProgress()
                            }
                        } else {

                            mView?.returnVideoListData(videoDatas)
                            mView?.hideProgress()

                        }

                    }

                     override fun _onError(message: String) {
                        LogUtils.d("message" + message)
                        mView?.showLoadFailMsg(message)
                    }
                }))
    }
}