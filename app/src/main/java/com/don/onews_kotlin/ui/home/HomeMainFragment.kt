package com.don.onews_kotlin.ui.home

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View


import com.blankj.utilcode.utils.LogUtils
import com.don.onews_kotlin.R
import com.don.onews_kotlin.app.AppConstant
import com.don.onews_kotlin.base.*
import com.don.onews_kotlin.utils.MyUtils
import java.util.ArrayList


/**
 * Created by drcom on 2017/3/21.
 */

class HomeMainFragment <T : BasePresenter<*, *>, E : BaseModel> : BaseFragment<T, E>() {


    private var tabs: TabLayout? = null

    private var viewPager: ViewPager? = null

    private var fab: FloatingActionButton? = null
    private var fragmentAdapter: BaseFragmentAdapter? = null

    override fun getLayoutResource(): Int {
        return R.layout.app_bar_home
    }

    override fun initPresenter() {

    }

    override fun initView(view: View) {
        tabs = view.findViewById(R.id.tabs) as TabLayout
        viewPager = view.findViewById(R.id.view_pagerr) as ViewPager
        fab = view.findViewById(R.id.fab) as FloatingActionButton

        val channelType = ArrayList<String>()
        channelType.add(0, "headline")
        channelType.add(1, "list")
        channelType.add(2, "list")
        channelType.add(3, "list")
        channelType.add(4, "list")
        channelType.add(5, "list")
        channelType.add(6, "list")
        channelType.add(7, "list")

        val channelId = ArrayList<String>()
        channelId.add(0, "T1348647909107")
        channelId.add(1, "T1348649580692")
        channelId.add(2, "T1348648756099")
        channelId.add(3, "T1348648141035")
        channelId.add(4, "T1348649079062")
        channelId.add(5, "T1348648517839")
        channelId.add(6, "T1348648650048")
        channelId.add(7, "T1370583240249")

        val channelName = ArrayList<String>()
        channelName.add(0, "头条")
        channelName.add(1, "科技")
        channelName.add(2, "财经")
        channelName.add(3, "军事")
        channelName.add(4, "体育")
        channelName.add(5, "娱乐")
        channelName.add(6, "电影")
        channelName.add(7, "精选")

        val mNewsFragmentList = ArrayList<Fragment>()
        for (i in channelType.indices) {
            mNewsFragmentList.add(createListFragments(channelId[i], channelType[i]))
        }

        fragmentAdapter = BaseFragmentAdapter(childFragmentManager, mNewsFragmentList, channelName)



        LogUtils.d("HomeMainFragment", "channelName:" + channelName[0])
        LogUtils.d("HomeMainFragment", "mNewsFragmentList:" + mNewsFragmentList[0])
        LogUtils.d("HomeMainFragment", "getChildFragmentManager:" + childFragmentManager)
        viewPager!!.adapter = fragmentAdapter
        tabs!!.setupWithViewPager(viewPager)
        MyUtils.dynamicSetTabLayoutMode(tabs)
        setPageChangeListener()


    }

    private fun setPageChangeListener() {
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun createListFragments(chanelId: String, channelType: String): HomeFragment {
        val fragment = HomeFragment()

        val bundle = Bundle()
        bundle.putString(AppConstant.NEWS_TYPE, channelType)
        bundle.putString(AppConstant.NEWS_ID, chanelId)
        fragment.arguments = bundle
        return fragment
    }
}
