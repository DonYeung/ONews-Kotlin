package com.don.onews_kotlin.ui.video.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.blankj.utilcode.utils.LogUtils
import com.blankj.utilcode.utils.ToastUtils
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter
import com.xiaochao.lcrapiddeveloplibrary.Video.JCVideoPlayer
import com.xiaochao.lcrapiddeveloplibrary.container.RotationHeader
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView

import butterknife.BindView
import com.don.onews_kotlin.R
import com.don.onews_kotlin.app.AppConstant
import com.don.onews_kotlin.base.BaseFragment
import com.don.onews_kotlin.bean.Video
import com.don.onews_kotlin.ui.video.adapter.VideoLisViewAdapter
import com.don.onews_kotlin.ui.video.contract.VideoContract
import com.don.onews_kotlin.ui.video.model.VideoModel
import com.don.onews_kotlin.ui.video.presenter.VideoPresenter

/**
 * Created by drcom on 2017/3/22.
 */

class VideoFragment : BaseFragment<VideoPresenter, VideoModel>(), VideoContract.View, BaseQuickAdapter.RequestLoadMoreListener, SpringView.OnFreshListener, BaseQuickAdapter.OnRecyclerViewItemClickListener {


    @BindView(R.id.rv_list)
    internal var mRecyclerView: RecyclerView? = null
    @BindView(R.id.springview)
    internal var springview: SpringView? = null
    @BindView(R.id.progress)
    internal var progress: ProgressActivity? = null
    private var PageIndex = 1
    private val mVideoType: String? = null

    private var mQuickAdapter: VideoLisViewAdapter? = null


    override fun initPresenter() {
        mPresenter?.setVM(this, mModel!!)
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_video
    }
    override fun initView() {
        //设置下拉刷新监听
        springview!!.setListener(this)
        //设置下拉刷新样式
        springview!!.header = RotationHeader(getActivity())
        //springView.setFooter(new RotationFooter(this));mRecyclerView内部集成的自动加载  上啦加载用不上   在其他View使用
        //设置RecyclerView的显示模式  当前List模式
        mRecyclerView!!.layoutManager = LinearLayoutManager(getActivity())
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.isNestedScrollingEnabled = false
        //设置页面为加载中..
        progress!!.showLoading()


        //设置适配器
        mQuickAdapter = VideoLisViewAdapter(R.layout.video_list_view_item_layout, null)
        //设置加载动画
        mQuickAdapter!!.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        //设置是否自动加载以及加载个数
        mQuickAdapter!!.openLoadMore(10, true)
        //将适配器添加到RecyclerView
        mRecyclerView!!.adapter = mQuickAdapter
        //设置自动加载监听
        mQuickAdapter!!.setOnLoadMoreListener(this)

        //请求网络数据
        //        mPresenter.loadVideoListDataRequest(AppConstant.DATA_TYPE_REST_VIDEO, PageIndex,false);
        mPresenter?.loadVideoListDataRequest("0", "0", PageIndex, false)

        mQuickAdapter!!.setOnRecyclerViewItemClickListener(BaseQuickAdapter.OnRecyclerViewItemClickListener { view, position -> ToastUtils.showShortToast("点击：" + position) })

    }

    override fun showProgress() {
        progress!!.showLoading()
    }

    override fun hideProgress() {
        progress!!.showContent()
    }

    override fun showLoadFailMsg(msg: String) {
        //设置加载错误页显示
        progress!!.showError(getResources().getDrawable(R.mipmap.ic_launcher), AppConstant.ERROR_TITLE, AppConstant.ERROR_CONTEXT, AppConstant.ERROR_BUTTON) {
            PageIndex = 1
            //请求网络数据
            //                mPresenter.loadVideoListDataRequest(AppConstant.DATA_TYPE_REST_VIDEO, PageIndex,false);
            mPresenter?.loadVideoListDataRequest("0", "0", PageIndex, false)
        }
    }


    override fun onItemClick(view: View, position: Int) {

    }

    override fun returnVideoListData(videoDatas: Video) {
        LogUtils.d("videoDatas:" + videoDatas.data?.get(0)?.video_url)

        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter!!.setNewData(videoDatas.data)//新增数据
        mQuickAdapter!!.openLoadMore(10, true)//设置是否可以下拉加载  以及加载条数
        springview!!.onFinishFreshAndLoad()//刷新完成
    }

    override fun addVideoListData(videoDatas: Video) {
        //新增自动加载的的数据
        mQuickAdapter!!.notifyDataChangedAfterLoadMore(videoDatas.data, true)
    }

    //自动加载
    override fun onLoadMoreRequested() {
        PageIndex++
        //请求网络数据
        //        mPresenter.loadVideoListDataRequest(AppConstant.DATA_TYPE_REST_VIDEO, PageIndex,true);
        mPresenter?.loadVideoListDataRequest("0", "0", PageIndex, true)
        LogUtils.d("PageIndex:" + PageIndex)

    }

    //下拉刷新
    override fun onRefresh() {
        PageIndex = 1
        //请求网络数据
        //        mPresenter.loadVideoListDataRequest(AppConstant.DATA_TYPE_REST_VIDEO, PageIndex,false);
        mPresenter?.loadVideoListDataRequest("0", "0", PageIndex, false)

    }

    override fun onLoadmore() {

    }

    override fun showLoadCompleteAllData() {
        //所有数据加载完成后显示
        try {
            mQuickAdapter!!.notifyDataChangedAfterLoadMore(false)
            val view = getActivity().getLayoutInflater().inflate(R.layout.progress_loaded, mRecyclerView!!.parent as ViewGroup, false)
            mQuickAdapter!!.addFooterView(view)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }
}
