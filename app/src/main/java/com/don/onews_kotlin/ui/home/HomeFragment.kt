package com.don.onews_kotlin.ui.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.blankj.utilcode.utils.LogUtils
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter
import com.xiaochao.lcrapiddeveloplibrary.container.RotationHeader
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView

import butterknife.BindView
import com.don.onews_kotlin.R
import com.don.onews_kotlin.app.AppConstant
import com.don.onews_kotlin.base.BaseFragment
import com.don.onews_kotlin.bean.NewsSummary
import com.don.onews_kotlin.ui.home.activity.NewsDetailActivity
import com.don.onews_kotlin.ui.home.adapter.ListViewAdapter
import com.don.onews_kotlin.ui.home.contract.HomeContract
import com.don.onews_kotlin.ui.home.model.HomeModel
import com.don.onews_kotlin.ui.home.presenter.HomePresenter

/**
 * Created by drcom on 2017/3/17.
 */

class HomeFragment : BaseFragment<HomePresenter, HomeModel>(), HomeContract.View, BaseQuickAdapter.RequestLoadMoreListener, SpringView.OnFreshListener, BaseQuickAdapter.OnRecyclerViewItemClickListener {


    @BindView(R.id.rv_list)
    internal var mRecyclerView: RecyclerView? = null
    @BindView(R.id.springview)
    internal var springview: SpringView? = null
    @BindView(R.id.progress)
    internal var progress: ProgressActivity? = null

    private var mQuickAdapter: ListViewAdapter? = null
    private var PageIndex = 0
    private var mNewsId: String = ""
    private var mNewsType: String = ""


    override fun getLayoutId(): Int {
        return  R.layout.fragment_home
    }

    override fun initPresenter() {
        mPresenter?.setVM(this, mModel!!)
    }

    override  protected fun initView() {
        if (getArguments() != null) {

            mNewsType = getArguments().getString(AppConstant.NEWS_TYPE)
            mNewsId = getArguments().getString(AppConstant.NEWS_ID)
        }

        //设置下拉刷新监听
        springview!!.setListener(this)
        //设置下拉刷新样式
        springview!!.header = RotationHeader(getActivity())
        //springView.setFooter(new RotationFooter(this));mRecyclerView内部集成的自动加载  上啦加载用不上   在其他View使用
        //设置RecyclerView的显示模式  当前List模式
        mRecyclerView!!.layoutManager = LinearLayoutManager(getActivity())
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView!!.setHasFixedSize(true)
        //设置页面为加载中..
        progress!!.showLoading()
        mRecyclerView!!.isNestedScrollingEnabled = false

        //设置适配器
        mQuickAdapter = ListViewAdapter(R.layout.list_view_item_layout, null!!)
        //设置加载动画
        mQuickAdapter!!.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        //设置是否自动加载以及加载个数
        mQuickAdapter!!.openLoadMore(10, true)
        //将适配器添加到RecyclerView
        mRecyclerView!!.adapter = mQuickAdapter
        //设置自动加载监听
        mQuickAdapter!!.setOnLoadMoreListener(this) //api只有一页数据
        //请求网络数据
        mPresenter?.loadHomeListDataRequest(mNewsType, mNewsId, PageIndex, false)

        mQuickAdapter?.setOnRecyclerViewItemClickListener(this)

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
            PageIndex = 0
            //请求网络数据
            mPresenter?.loadHomeListDataRequest(mNewsType, mNewsId, PageIndex, false)
        }
    }


    override fun returnHomeListData(homeData: List<NewsSummary>) {
        LogUtils.d("homeData:" + homeData[0].ptime)

        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter!!.setNewData(homeData)//新增数据
        mQuickAdapter!!.openLoadMore(20, true)//设置是否可以下拉加载  以及加载条数
        springview!!.onFinishFreshAndLoad()//刷新完成

    }

    override fun addHomeListData(homeDatas: List<NewsSummary>) {
        //新增自动加载的的数据
        mQuickAdapter!!.notifyDataChangedAfterLoadMore(homeDatas, true)
    }

    override fun onLoadMoreRequested() {
        PageIndex += 20
        //请求网络数据
        mPresenter?.loadHomeListDataRequest(mNewsType, mNewsId, PageIndex, true)
    }

    override fun onRefresh() {
        PageIndex = 0
        mPresenter?.loadHomeListDataRequest(mNewsType, mNewsId, PageIndex, false)
    }

    override fun onLoadmore() {

    }

    override fun onItemClick(view: View, position: Int) {
        //        Intent intent = new Intent(getActivity(), BookInfoActivity.class);
        //        intent.putExtra("bookid", mQuickAdapter.getItem(position).getId());
        //        startActivity(intent);
        var postId = mQuickAdapter?.getItem(position)?.postid
        var imgUrl = mQuickAdapter?.getItem(position)?.imgsrc
        NewsDetailActivity.startAction(getActivity(), postId!!, imgUrl!!)
        LogUtils.d("点击：" + mQuickAdapter!!.getItem(position).title)

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


}
