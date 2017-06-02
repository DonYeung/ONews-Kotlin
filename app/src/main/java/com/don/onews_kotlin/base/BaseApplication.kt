package com.don.onews_kotlin.base

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context

import com.blankj.utilcode.utils.CrashUtils
import com.blankj.utilcode.utils.LogUtils
import com.blankj.utilcode.utils.ToastUtils
import com.blankj.utilcode.utils.Utils

import java.util.Stack

/**
 * Created by drcom on 2017/3/16.
 */

open class BaseApplication : Application() {
    //记录当前栈里所有activity
    private var activityStack: Stack<Activity>? = null

    /**
     * 应用实例
     */
    private var baseApplication: BaseApplication? = null

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        if (baseApplication != null) {
            Utils.init(baseApplication)//初始化工具类
        }

        //初始化log
        LogUtils.getBuilder().setLogSwitch(true).setLog2FileSwitch(true).setTag("ONews").setLogFilter('v').create()

        CrashUtils.getInstance().init()//奔溃初始化
        ToastUtils.init(true) //toast初始化

    }
    /**
     * 获得实例
     * @return
     */
    companion object {
        fun getInstance():BaseApplication{
            return Inner.base
        }
    }
    private  object Inner{
        val base = BaseApplication()
    }
    /**
     * 添加Activity到堆栈
     */
     fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack<Activity>()
        }
        activityStack?.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        try {
            val activity = activityStack?.lastElement()
            return activity
        } catch (e: Exception) {
            //            e.printStackTrace();
            return null
        }

    }

    /**
     * 获取当前Activity的前一个Activity
     */
    //    fun preActivity(): Activity? {
//        val index = (activityStack?.size) - 2
//        if (index < 0) {
//            return null
//        }
//        val activity = activityStack?.get(index)
//        return activity
//    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack?.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack?.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        try {
            for (activity in activityStack!!) {
                if (activity.javaClass == cls) {
                    finishActivity(activity)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 返回到指定的activity

     * @param cls
     */
    fun returnToActivity(cls: Class<*>) {
        while (activityStack!!.size != 0)
            if (activityStack!!.peek().javaClass == cls) {
                break
            } else {
                finishActivity(activityStack!!.peek())
            }
    }


    /**
     * 是否已经打开指定的activity
     * @param cls
     * *
     * @return
     */
    fun isOpenActivity(cls: Class<*>): Boolean {
        if (activityStack != null) {
            var i = 0
            val size = activityStack!!.size
            while (i < size) {
                if (cls == activityStack!!.peek().javaClass) {
                    return true
                }
                i++
            }
        }
        return false
    }

    /**
     * 退出应用程序

     * @param context      上下文
     * *
     * @param isBackground 是否开开启后台运行
     */
    fun AppExit(context: Context, isBackground: Boolean?) {
        try {
            finishAllActivity()
            val activityMgr = context
                    .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.restartPackage(context.packageName)
        } catch (e: Exception) {

        } finally {
            // 注意，如果您有后台程序运行，请不要支持此句子
            if ((!isBackground!!)) {
                // 关闭进程
                android.os.Process.killProcess(android.os.Process.myPid())//获取PID
                System.exit(0)
            }
        }
    }




    val appContext: Context
        get() = baseApplication!!


}


