package com.don.onews_kotlin.ui.home.adapter

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.don.onews_kotlin.R
import com.don.onews_kotlin.bean.NewsSummary
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder

/**
 * Created by drcom on 2017/3/21.
 */

class ListViewAdapter(layoutResId: Int, data: List<NewsSummary>) : BaseQuickAdapter<NewsSummary>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: NewsSummary) {
        Glide.with(mContext)
                .load(item.imgsrc)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(helper.getView(R.id.book_info_image_url))
        helper.setText(R.id.newstitle, String.format(mContext.resources.getString(R.string.newstitle), item.title))
        helper.setText(R.id.newsauthor, item.ename)
        helper.setText(R.id.newstime, String.format(mContext.resources.getString(R.string.newstime), item.ptime))
    }
}