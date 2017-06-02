package com.don.onews_kotlin.base

/**
 * Created by drcom on 2017/3/16.
 */

import android.content.Context

import com.don.onews_kotlin.utils.baserx.RxManager

/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
abstract class BasePresenter<T, E> {
    var mContext: Context? = null
    var mModel: E? = null
    var mView: T? = null
    var mRxManage = RxManager()

    fun setVM(v: T, m: E) {
        this.mView = v
        this.mModel = m
    }

    fun onDestroy() {
        mRxManage?.clear()
    }
}
