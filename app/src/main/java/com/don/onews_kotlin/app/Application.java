package com.don.onews_kotlin.app;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.utils.LogUtils;
import com.don.onews_kotlin.base.BaseApplication;
import com.don.onews_kotlin.ui.SplashActivity;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by drcom on 2017/3/16.
 */

public class Application extends BaseApplication {
    public static final String APP_ID = "2882303761517594875";
    public static final String APP_KEY = "5401759493875";
    public static final String TAG = "miui.push";


    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly 异常上报
        //第三个参数为：测试阶段设置成true，发布时设置为false。
        CrashReport.initCrashReport(getApplicationContext(), "1a4f584cf6", false);

        //小米推送
        //初始化push推送服务
        if(shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
        LoggerInterface newLogger = new LoggerInterface() {
            @Override
            public void setTag(String tag) {
                // ignore
            }
            @Override
            public void log(String content, Throwable t) {
                LogUtils.d(TAG, content, t);
            }
            @Override
            public void log(String content) {
                LogUtils.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
    }
    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }


}

