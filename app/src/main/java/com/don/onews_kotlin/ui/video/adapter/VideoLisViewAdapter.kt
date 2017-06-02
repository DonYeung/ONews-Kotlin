package com.don.onews_kotlin.ui.video.adapter

import android.view.View

import com.bumptech.glide.Glide
import com.don.onews_kotlin.R
import com.don.onews_kotlin.bean.Video
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder
import com.xiaochao.lcrapiddeveloplibrary.Video.JCVideoPlayerStandard

/**
 * Created by drcom on 2017/3/23.
 */

class VideoLisViewAdapter : BaseQuickAdapter<Video.DataBean> {

    constructor(layoutResId: Int, data: List<Video.DataBean>) : super(layoutResId, data) {}

    constructor(data: List<Video.DataBean>) : super(data) {}

    constructor(contentView: View, data: List<Video.DataBean>) : super(contentView, data) {}

    override fun convert(helper: BaseViewHolder, item: Video.DataBean) {
        //        ((JCVideoPlayerStandard)helper.getView(R.id.video_list_item_playr)).setUp(item.getVideo_url(),item.getName());
        //        helper.setImageRoundUrl(R.id.iv_logo,item.getImg_url());
        helper.setText(R.id.video_list_item_text_title, item.time)
        helper.setText(R.id.tv_play_time, String.format(mContext.resources.getString(R.string.video_play_times), item.view_count))
        //对视频的赋值 添加视频播放地址(使用原地址  .mp4之类的  这个要注意)和标题
        (helper.getView(R.id.video_list_item_playr) as JCVideoPlayerStandard).setUp(item.video_url, item.name)
        Glide.with(mContext)
                .load(item.img_url)
                .crossFade()
                .placeholder(R.mipmap.ic_empty_picture)
                .into((helper.getView(R.id.video_list_item_playr) as JCVideoPlayerStandard).thumbImageView)
    }


}