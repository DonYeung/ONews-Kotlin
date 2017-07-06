package com.don.onews_kotlin.ui.search

import android.view.View
import android.widget.TextView

import com.don.onews_kotlin.R
import com.don.onews_kotlin.base.BaseFragment
import com.don.onews_kotlin.ui.video.model.VideoModel
import com.don.onews_kotlin.ui.video.presenter.VideoPresenter

/**
 * Created by drcom on 2017/3/17.
 */

class SearchFragment : BaseFragment<VideoPresenter, VideoModel>(){
    private var message: TextView? = null


    override fun getLayoutResource(): Int {
        return  R.layout.fragment_search
    }
    override fun initPresenter() {

    }

    override fun initView(view: View?) {
        message = view?.findViewById(R.id.message) as TextView
    }

}
