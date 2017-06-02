package com.don.onews_kotlin.ui

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.blankj.utilcode.utils.LogUtils
import com.don.onews_kotlin.R
import com.don.onews_kotlin.base.BaseActivity
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener

class MainActivity : BaseActivity<T, E>() {


    private var hometabToolbarTextviewTitle: TextView? = null
    private var navigation: BottomNavigationView? = null
    private var controller: FragmentController? = null

    private val mOnNavigationItemSelectedListener = OnBottomNavigationItemClickListener { item ->
        when (item) {
            R.id.navigation_home -> {
                hometabToolbarTextviewTitle?.setText(R.string.title_home)
                setDefaultFragment(item)
            }
            R.id.navigation_dashboard -> {
                hometabToolbarTextviewTitle!!.setText(R.string.title_dashboard)
                setDefaultFragment(item)
            }
            R.id.navigation_notifications -> {
                hometabToolbarTextviewTitle!!.setText(R.string.title_notifications)
                setDefaultFragment(item)
            }
            R.id.navigation_setting -> {
                hometabToolbarTextviewTitle!!.setText(R.string.title_setting)
                setDefaultFragment(item)
            }
        }
        false
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter() {
    }

    override fun initView() {
        hometabToolbarTextviewTitle = findViewById(R.id.hometab_toolbar_textview_title) as TextView
        navigation = findViewById(R.id.navigation) as BottomNavigationView


        val bottomNavigationItem0: BottomNavigationItem = BottomNavigationItem(getResources().getString(R.string.news), ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_home_black_24dp)
        val bottomNavigationItem1: BottomNavigationItem = BottomNavigationItem(getResources().getString(R.string.videos), ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_dashboard_black_24dp)
        val bottomNavigationItem2: BottomNavigationItem = BottomNavigationItem(getResources().getString(R.string.search), ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_notifications_black_24dp)
        val bottomNavigationItem3: BottomNavigationItem = BottomNavigationItem(getResources().getString(R.string.setting), ContextCompat.getColor(this, R.color.colorPrimary), R.drawable.ic_notifications_black_24dp)

        navigation?.addTab(bottomNavigationItem0)
        navigation?.addTab(bottomNavigationItem1)
        navigation?.addTab(bottomNavigationItem2)
        navigation?.addTab(bottomNavigationItem3)
        navigation?.isWithText(true)//是否带文字
        navigation?.setItemActiveColorWithoutColoredBackground(R.color.colorPrimary)
        navigation?.setOnBottomNavigationItemClickListener(mOnNavigationItemSelectedListener)

        controller = FragmentController.getInstance(this, R.id.content)
        controller?.showFragment(0)
    }

    /**
     * @param position
     * * 切换fragment
     */
    fun setDefaultFragment(position: Int) {
        LogUtils.d("主页菜单position" + position)
        when (position) {
            0 ->
                //推荐
                controller?.showFragment(0)
            1 ->
                //书库视频
                controller?.showFragment(1)
            2 ->
                //搜索
                controller?.showFragment(2)
            3 ->
                //设置
                controller?.showFragment(3)
            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FragmentController.onDestroy()
    }
}
