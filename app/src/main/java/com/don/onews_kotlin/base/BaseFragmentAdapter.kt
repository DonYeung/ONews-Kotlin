package com.don.onews_kotlin.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.don.onews_kotlin.utils.CollectionUtils

import java.util.ArrayList

class BaseFragmentAdapter : FragmentPagerAdapter {

    internal var fragmentList: List<Fragment> = ArrayList()
    private var mTitles: List<String>? = null

    constructor(fm: FragmentManager, fragmentList: List<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }

    constructor(fm: FragmentManager, fragmentList: List<Fragment>, mTitles: List<String>) : super(fm) {
        this.fragmentList = fragmentList
        this.mTitles = mTitles
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (!CollectionUtils.isNullOrEmpty(mTitles)) mTitles!![position] else ""
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}
