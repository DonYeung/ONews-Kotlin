package com.don.onews_kotlin.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

import com.don.onews_kotlin.ui.home.HomeMainFragment
import com.don.onews_kotlin.ui.search.SearchFragment
import com.don.onews_kotlin.ui.setting.SettingFragment
import com.don.onews_kotlin.ui.video.fragment.VideoFragment

import java.util.ArrayList


class FragmentController private constructor(activity: FragmentActivity, private val containerId: Int) {
    private val fm: FragmentManager
    private var fragments: ArrayList<Fragment>? = null

    init {
        fm = activity.supportFragmentManager
        initFragment()
    }

    private fun initFragment() {
        fragments = ArrayList<Fragment>()
        fragments!!.add(HomeMainFragment())
        fragments!!.add(VideoFragment())
        fragments!!.add(SearchFragment())
        fragments!!.add(SettingFragment())

        val ft = fm.beginTransaction()
        for (fragment in fragments!!) {
            ft.add(containerId, fragment)
        }
        //		ft.commit();
        ft.commitAllowingStateLoss()
    }

    fun showFragment(position: Int) {
        hideFragments()
        val fragment = fragments!![position]
        val ft = fm.beginTransaction()
        ft.show(fragment)
        //		ft.commit();
        ft.commitAllowingStateLoss()
    }

    fun hideFragments() {
        val ft = fm.beginTransaction()
        for (fragment in fragments!!) {
            if (fragment != null) {
                ft.hide(fragment)
            }
        }
        //		ft.commit();
        ft.commitAllowingStateLoss()
    }

    fun getFragment(position: Int): Fragment {
        return fragments!![position]
    }

    companion object {

        private var controller: FragmentController? = null

        fun getInstance(activity: FragmentActivity, containerId: Int): FragmentController {
            if (controller == null) {
                controller = FragmentController(activity, containerId)
            }
            return controller!!
        }

        fun onDestroy() {
            controller = null
        }
    }
}