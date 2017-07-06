package com.don.onews_kotlin.base;

/**
 * Created by drcom on 2017/3/16.
 */

import android.content.Context;

import com.don.onews_kotlin.utils.baserx.RxManager;


/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;


    }
    public void onDestroy() {
        mRxManage.clear();
    }
}
