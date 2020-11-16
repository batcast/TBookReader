package com.example.tbookreader.help

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

object ActivityHelp : Application.ActivityLifecycleCallbacks {
    private val activities: MutableList<WeakReference<Activity>> = arrayListOf()

    fun size() = activities.size

    /*判断指定Activity是否存在*/
    fun isExist(activityClass: Class<*>): Boolean {
        activities.forEach {
            if (it.get()?.javaClass == activityClass) {
                return true
            }
        }
        return false
    }

    /*添加Activity*/
    fun add(activity: Activity) {
        activities.add(WeakReference(activity))
    }

    /*移除指定Activity*/
    fun remove(activity: Activity) {
        for (temp in activities) {
            if (null != temp.get() && temp.get() === activity) {
                activities.remove(temp)
                break
            }
        }
    }
    /*移除指定Activity class*/
    fun remove(activityClass: Class<*>){
        val iterator = activities.iterator()
        while (iterator.hasNext()){
            val item = iterator.next()
            if (item.get()?.javaClass == activityClass) {
                iterator.remove()
            }
        }
    }

    /*关闭指定activity*/
    fun finishActivity(vararg activities:Activity){
        activities.forEach {
            it.finish()
        }
    }
    /*关闭指定activity class*/
    fun finishActivity(vararg activityClasses: Class<*>){
        val waitFinish = ArrayList<WeakReference<Activity>>()
        for (temp in activities){
            for (activityClass in activityClasses){
                if (temp.get()?.javaClass == activityClass){
                    waitFinish.add(temp)
                    break
                }
            }
        }
        waitFinish.forEach{
            it.get()?.finish()
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        add(activity)
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        remove(activity)
    }


}