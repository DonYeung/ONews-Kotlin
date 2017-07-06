package com.don.onews_kotlin.ui.home.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.blankj.utilcode.utils.LogUtils
import com.bumptech.glide.Glide
import com.don.onews_kotlin.R
import com.don.onews_kotlin.app.AppConstant
import com.don.onews_kotlin.base.BaseActivity
import com.don.onews_kotlin.bean.NewsDetail
import com.don.onews_kotlin.ui.home.contract.NewsDetailContract
import com.don.onews_kotlin.ui.home.model.NewsDetailModel
import com.don.onews_kotlin.ui.home.presenter.NewsDetailPresenter
import com.don.onews_kotlin.utils.TimeUtil
import com.don.onews_kotlin.utils.baserx.RxSchedulers
import com.don.onews_kotlin.widget.URLImageGetter
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView
import java.util.concurrent.TimeUnit

import rx.Observable
import rx.Subscriber

/**
 * Created by drcom on 2017/4/7.
 */

class NewsDetailActivity : BaseActivity<NewsDetailPresenter, NewsDetailModel>(), NewsDetailContract.View, BaseQuickAdapter.RequestLoadMoreListener, SpringView.OnFreshListener, BaseQuickAdapter.OnRecyclerViewItemClickListener {

    private var newsDetailPhotoIv: ImageView? = null
    private var maskView: View? = null
    private var toolbar: Toolbar? = null
    private var toolbarLayout: CollapsingToolbarLayout? = null
    private var appBar: AppBarLayout? = null
    private var newsDetailFromTv: TextView? = null
    private var newsDetailBodyTv: TextView? = null
    //    @BindView(R.id.springview)
    //    SpringView springview;
    //    @BindView(R.id.progress)
    //    ProgressActivity progress;

    private var postId: String? = null
    private var imgUrl: String? = null
    private var mNewsTitle: String? = null
    private var mUrlImageGetter: URLImageGetter? = null

    override fun getLayoutId(): Int {
        return R.layout.act_news_detail
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }


    override fun initView() {
        newsDetailPhotoIv = findViewById(R.id.news_detail_photo_iv) as? ImageView
        maskView = findViewById(R.id.mask_view) as? View
        toolbar = findViewById(R.id.toolbar) as? Toolbar
        toolbarLayout = findViewById(R.id.toolbar_layout) as? CollapsingToolbarLayout
        appBar = findViewById(R.id.app_bar) as? AppBarLayout
        newsDetailFromTv = findViewById(R.id.news_detail_from_tv) as? TextView
        newsDetailBodyTv = findViewById(R.id.news_detail_body_tv) as? TextView

        /**1.appBarLayout的背景颜色调为半透明或者透明 app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
         * 2.将toolbar的背景颜色调为半透明或者透明(沉浸状态栏（4.4以上系统有效）)
         */
        SetTranslanteBar()
        postId = intent.getStringExtra(AppConstant.NEWS_POST_ID)
        imgUrl = intent.getStringExtra(AppConstant.NEWS_IMG_RES)
        LogUtils.d("NEWS_POST_ID:" + postId)
        mPresenter.loadNewsDetailDataRequest(postId!!)
        toolbar?.setNavigationOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition()
            } else {
                finish()
            }
        }
    }

    override fun onItemClick(view: View, position: Int) {

    }

    override fun returnNewsDetailData(newsDetail: NewsDetail) {
        mNewsTitle = newsDetail?.title

        val newsSource = newsDetail.source
        val newsTime = TimeUtil.formatDate(newsDetail.ptime)
        val newsBody = newsDetail.body
        val NewsImgSrc = imgUrl

        setToolBarLayout(mNewsTitle)
        //mNewsDetailTitleTv.setText(newsTitle);
        newsDetailFromTv?.text = getString(R.string.news_from, newsSource, newsTime)
        setNewsDetailPhotoIv(NewsImgSrc)
        setNewsDetailBodyTv(newsDetail, newsBody)
    }

    override fun onLoadMoreRequested() {

    }

    override fun onRefresh() {

    }

    override fun onLoadmore() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showLoadFailMsg(msg: String) {
        //设置加载错误页显示
        //        progress.showError(getResources().getDrawable(R.mipmap.ic_launcher), AppConstant.ERROR_TITLE, AppConstant.ERROR_CONTEXT, AppConstant.ERROR_BUTTON, new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                //请求网络数据
        //                mPresenter.loadNewsDetailDataRequest(postId);
        //            }
        //        });
    }

    override fun showLoadCompleteAllData() {

    }

    private fun setToolBarLayout(newsTitle: String?) {
        toolbarLayout?.title = newsTitle
        toolbarLayout?.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white))
        toolbarLayout?.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_text))
    }

    private fun setNewsDetailPhotoIv(imgSrc: String?) {
        Glide.with(this).load(imgSrc)
                .fitCenter()
                .error(R.mipmap.ic_empty_picture)
                .crossFade().into(newsDetailPhotoIv)
    }

    private fun setNewsDetailBodyTv(newsDetail: NewsDetail, newsBody: String?) {
        mRxManager.add(Observable.timer(500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.io_main<Long>())
                .subscribe(object : Subscriber<Long>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(aLong: Long?) {
                        setBody(newsDetail, newsBody)
                    }
                }))
    }

    private fun setBody(newsDetail: NewsDetail, newsBody: String?) {
        val imgTotal = newsDetail.img?.size
        if (isShowBody(newsBody, imgTotal)) {
            //              mNewsDetailBodyTv.setMovementMethod(LinkMovementMethod.getInstance());//加这句才能让里面的超链接生效,实测经常卡机崩溃
            mUrlImageGetter = URLImageGetter(newsDetailBodyTv, newsBody, imgTotal!!)
            newsDetailBodyTv?.text = Html.fromHtml(newsBody, mUrlImageGetter, null)
        } else {
            newsDetailBodyTv?.text = Html.fromHtml(newsBody)
        }
    }

    private fun isShowBody(newsBody: String?, imgTotal: Int?): Boolean {
        return imgTotal!! >= 2 && newsBody != null
    }

    private fun getImgSrcs(newsDetail: NewsDetail): String {
        val imgSrcs = newsDetail.img
        val imgSrc: String
        if (imgSrcs != null && imgSrcs.size > 0) {
            imgSrc = imgSrcs[0].src!!
        } else {
            imgSrc = intent.getStringExtra(AppConstant.NEWS_IMG_RES)
        }
        return imgSrc
    }

    companion object {

        fun startAction(context: Context, postId: String?, imgUrl: String?) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(AppConstant.NEWS_POST_ID, postId)
            intent.putExtra(AppConstant.NEWS_IMG_RES, imgUrl)
            LogUtils.d("imgUrl:" + imgUrl)
            context.startActivity(intent)
        }
    }


}
