package com.don.onews_kotlin.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.blankj.utilcode.utils.ToastUtils

import butterknife.ButterKnife
import butterknife.Unbinder
import com.don.onews_kotlin.utils.TUtil
import com.don.onews_kotlin.utils.baserx.RxManager

/**
 * Created by drcom on 2017/3/16.
 */

abstract class BaseFragment<T : BasePresenter<*, *>, E : BaseModel> : Fragment() {
    protected var rootView: View? = null
    var mPresenter: T? = null
    var mModel: E? = null
    var mRxManager: RxManager? = null
    internal var mUnbinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutId(), container, false)
        mRxManager = RxManager()
        ButterKnife.bind(this, rootView!!)
        mUnbinder = ButterKnife.bind(this, rootView!!)
        mPresenter = TUtil.getT(this, 0)
        mModel = TUtil.getT(this, 1)
        if (mPresenter != null) {
            mPresenter!!.mContext = this.activity
        }
        initPresenter()
        initView()
        return rootView
    }

    //获取布局文件
    //获取布局文件
    abstract fun getLayoutId(): Int

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    abstract fun initPresenter()

    //初始化view
    protected abstract fun initView()

    /**
     * 通过Class跳转界面
     */
    fun startActivityForResult(cls: Class<*>, requestCode: Int) {
        startActivityForResult(cls, null, requestCode)
    }

    /**
     * 含有Bundle通过Class跳转界面
     */
    fun startActivityForResult(cls: Class<*>, bundle: Bundle?,
                               requestCode: Int) {
        val intent = Intent()
        intent.setClass(activity, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * 含有Bundle通过Class跳转界面
     */
    @JvmOverloads fun startActivity(cls: Class<*>, bundle: Bundle? = null) {
        val intent = Intent()
        intent.setClass(activity, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }


    /**
     * 短暂显示Toast提示(来自String)
     */
    fun showShortToast(text: String) {
        ToastUtils.showShortToast(text)
    }

    /**
     * 短暂显示Toast提示(id)
     */
    fun showShortToast(resId: Int) {
        ToastUtils.showShortToast(resId)
    }

    /**
     * 长时间显示Toast提示(来自res)
     */
    fun showLongToast(resId: Int) {
        ToastUtils.showLongToast(resId)
    }

    /**
     * 长时间显示Toast提示(来自String)
     */
    fun showLongToast(text: String) {
        ToastUtils.showLongToast(text)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mUnbinder?.unbind()
        if (mPresenter != null)
            mPresenter?.onDestroy()
        mRxManager?.clear()
    }


}
/**
 * 通过Class跳转界面
 */
