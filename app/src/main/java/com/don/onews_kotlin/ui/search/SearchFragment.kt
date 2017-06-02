package com.don.onews_kotlin.ui.search

import android.widget.TextView


import butterknife.BindView
import com.don.onews_kotlin.R
import com.don.onews_kotlin.base.BaseFragment

/**
 * Created by drcom on 2017/3/17.
 */

class SearchFragment : BaseFragment<T, E>(){
    @BindView(R.id.message)
    internal var message: TextView? = null


    override fun getLayoutId(): Int {
        return  R.layout.fragment_search
    }
    override fun initPresenter() {

    }

    override protected fun initView() {

    }

}
