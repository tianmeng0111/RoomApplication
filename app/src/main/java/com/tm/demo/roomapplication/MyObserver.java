package com.tm.demo.roomapplication;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * LifecycleObserver 接口是一个空接口，主要是给注解处理器使用
 */
public class MyObserver implements LifecycleObserver {

    private static final String TAG = "MyObserver";

    private String mActivityTag;

    public MyObserver(String activityTag) {
        mActivityTag = activityTag;
    }

    // 使用注解  @OnLifecycleEvent 来表明该方法需要监听指定的生命周期事件, 该方法具有了生命周期感知能力
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectListener() {
        // ...
        Log.d(mActivityTag, "connectListener: ------ OnResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        // ...
        Log.d(mActivityTag, "disconnectListener: ------ OnPause");
    }

}
