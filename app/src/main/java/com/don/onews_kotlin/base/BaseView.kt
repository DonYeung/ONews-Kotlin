package com.don.onews_kotlin.base

/**
 * Created by drcom on 2017/3/16.
 */

interface BaseView {
    /*******内嵌加载 */
    //显示加载页
    fun showProgress()

    //关闭加载页
    fun hideProgress()

    //显示加载失败
    fun showLoadFailMsg(msg: String)

    //显示已加载所有数据
    fun showLoadCompleteAllData()
}