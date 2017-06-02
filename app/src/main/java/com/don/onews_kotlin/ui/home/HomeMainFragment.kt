package com.don.onews_kotlin.ui.home

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager


import java.util.ArrayList

import butterknife.BindView
import com.don.onews_kotlin.R
import com.don.onews_kotlin.app.AppConstant
import com.don.onews_kotlin.base.BaseFragment
import com.don.onews_kotlin.base.BaseFragmentAdapter
import com.don.onews_kotlin.utils.MyUtils

/**
 * Created by drcom on 2017/3/21.
 */

class HomeMainFragment : BaseFragment<T, E>() {


    //    @BindView(R.id.toolbar)
    //    Toolbar toolbar;
    @BindView(R.id.tabs)
    internal var tabs: TabLayout? = null
    @BindView(R.id.view_pager)
    internal var viewPager: ViewPager? = null
    @BindView(R.id.fab)
    internal var fab: FloatingActionButton? = null
    private var fragmentAdapter: BaseFragmentAdapter? = null

    override fun getLayoutId(): Int {
        return  R.layout.app_bar_home
    }
    override fun initPresenter() {

    }

    override protected fun initView() {
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

        fragmentAdapter = BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelName)
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
        fragment.setArguments(bundle)
        return fragment
    }
}
