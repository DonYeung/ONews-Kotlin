package com.don.onews_kotlin.base;

/**
 * Created by drcom on 2017/3/16.
 */

public interface BaseView {
    /*******内嵌加载*******/
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //显示加载失败
    void showLoadFailMsg(String msg);
    //显示已加载所有数据
    void showLoadCompleteAllData();
}