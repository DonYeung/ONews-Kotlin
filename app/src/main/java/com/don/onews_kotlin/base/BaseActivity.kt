package com.don.onews_kotlin.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window

import com.blankj.utilcode.utils.ToastUtils

import butterknife.ButterKnife
import butterknife.Unbinder
import com.don.onews_kotlin.BuildConfig
import com.don.onews_kotlin.app.Application
import com.don.onews_kotlin.utils.StatusBarCompat
import com.don.onews_kotlin.utils.TUtil
import com.don.onews_kotlin.utils.baserx.RxManager

/**
 * Created by drcom on 2017/3/16.
 */

abstract class BaseActivity<T : BasePresenter<*, *>, E : BaseModel> : AppCompatActivity() {
    var mPresenter: T? = null
    var mModel: E? = null
    var mRxManager: RxManager? = null

    var mContext: Context? = null
    internal var mUnbinder: Unbinder? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRxManager = RxManager()
        doBeforeSetcontentView()
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        mContext = this
        mUnbinder = ButterKnife.bind(this)
        mPresenter = TUtil.getT(this, 0)
        mModel = TUtil.getT(this, 1)
        if (mPresenter != null) {
            mPresenter?.mContext = this
        }
        this.initPresenter()
        this.initView()
    }

    /**
     * 设置layout前配置
     */
    private fun doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        BaseApplication.getInstance().addActivity(this)
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // 默认着色状态栏
        SetStatusBarColor()

    }

    /*********************子类实现*****************************/
    //获取布局文件
    abstract fun getLayoutId(): Int

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    abstract fun initPresenter()

    //初始化view
    abstract fun initView()


    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected fun SetStatusBarColor() {
        //        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this,R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected fun SetStatusBarColor(color: Int) {
        StatusBarCompat.setStatusBarColor(this, color)
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected fun SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this)
    }

    /**
     * 通过Class跳转界面
     */
    fun startActivity(cls: Class<*>) {
        startActivity(cls, null)
    }
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
        intent.setClass(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }


    /**
     * 含有Bundle通过Class跳转界面
     */
    fun startActivity(cls: Class<*>, bundle: Bundle? = null) {
        val intent = Intent()
        intent.setClass(this, cls)
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

    override fun onResume() {
        super.onResume()
        //debug版本不统计crash
        if (!BuildConfig.LOG_DEBUG) {
        }
    }

    override fun onPause() {
        super.onPause()
        //debug版本不统计crash
        if (!BuildConfig.LOG_DEBUG) {
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null)
            mPresenter!!.onDestroy()
        mRxManager?.clear()
        mUnbinder?.unbind()
        BaseApplication.getInstance().finishActivity(this)
    }
}
