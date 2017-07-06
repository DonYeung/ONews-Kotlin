package com.don.onews_kotlin.ui.video.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.don.onews_kotlin.R;
import com.don.onews_kotlin.bean.Video;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;
import com.xiaochao.lcrapiddeveloplibrary.Video.JCVideoPlayerStandard;

import java.util.List;

/**
 * Created by drcom on 2017/3/23.
 */

public class VideoLisViewAdapter extends BaseQuickAdapter<Video.DataBean> {

    public VideoLisViewAdapter(int layoutResId, List<Video.DataBean> data) {
        super(layoutResId, data);
    }

    public VideoLisViewAdapter(List<Video.DataBean> data) {
        super(data);
    }

    public VideoLisViewAdapter(View contentView, List<Video.DataBean> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Video.DataBean item) {
//        ((JCVideoPlayerStandard)helper.getView(R.id.video_list_item_playr)).setUp(item.getVideo_url(),item.getName());
//        helper.setImageRoundUrl(R.id.iv_logo,item.getImg_url());
        helper.setText(R.id.video_list_item_text_title,item.getTime());
        helper.setText(R.id.tv_play_time,String.format(mContext.getResources().getString(R.string.video_play_times), String.valueOf(item.getView_count())));
        //对视频的赋值 添加视频播放地址(使用原地址  .mp4之类的  这个要注意)和标题
        ((JCVideoPlayerStandard)helper.getView(R.id.video_list_item_playr)).setUp(item.getVideo_url(),item.getName());
        Glide.with(mContext)
                .load(item.getImg_url())
                .crossFade()
                .placeholder(R.mipmap.ic_empty_picture)
                .into((((JCVideoPlayerStandard) helper.getView(R.id.video_list_item_playr)).thumbImageView));
    }


}