package com.don.onews_kotlin.app

import com.don.onews_kotlin.base.BaseApplication
import com.tencent.bugly.crashreport.CrashReport

/**
 * Created by drcom on 2017/3/16.
 */

class Application : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //Bugly 异常上报
        //第三个参数为：测试阶段设置成true，发布时设置为false。
        CrashReport.initCrashReport(applicationContext, "1a4f584cf6", true)
    }
}

