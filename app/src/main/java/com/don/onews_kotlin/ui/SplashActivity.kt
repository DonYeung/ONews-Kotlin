package com.don.onews_kotlin.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import com.baidu.mobads.AppActivity
import com.baidu.mobads.SplashAd
import com.baidu.mobads.SplashAdListener
import com.blankj.utilcode.utils.LogUtils
import com.don.onews_kotlin.R

/**
 * Created by drcom on 2017/5/25.
 */
class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome);
        var adsRl: RelativeLayout = findViewById(R.id.adsRl) as RelativeLayout

        AppActivity.setActionBarColorTheme(AppActivity.ActionBarColorTheme.ACTION_BAR_WHITE_THEME)
        val listener: SplashAdListener? = object: SplashAdListener{
            override fun onAdFailed(p0: String?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.i("RSplashActivity", "onAdFailed");
                LogUtils.i("arg0:"+p0)
                jump();
            }

            override fun onAdDismissed() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.i("RSplashActivity", "onAdDismissed")
                jumpWhenCanClick(); // 跳转至您的应用主界面
            }

            override fun onAdPresent() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.i("RSplashActivity", "onAdPresent")
            }

            override fun onAdClick() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.i("RSplashActivity", "onAdClick")
            }

        }
        var adPlaceId: String = "2058622"; // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告

        SplashAd(this, adsRl, listener, adPlaceId, true)
    }
    var canJumpImmediately: Boolean =false

    private fun jump() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        this.finish();
    }

    private fun jumpWhenCanClick() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.d("SplashActivity", "this.hasWindowFocus():" + this.hasWindowFocus())
        if (canJumpImmediately) {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        } else {
            canJumpImmediately = true;
        }
    }

    override fun onResume() {
        super.onResume()
        if (canJumpImmediately) {
            jumpWhenCanClick()
        }
        canJumpImmediately = true
    }
}