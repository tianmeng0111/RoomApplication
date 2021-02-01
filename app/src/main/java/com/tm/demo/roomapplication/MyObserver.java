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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        // ...
        Log.e(mActivityTag, "------ onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        // ...
        Log.e(mActivityTag, "------ onStart");
    }

    // 使用注解  @OnLifecycleEvent 来表明该方法需要监听指定的生命周期事件, 该方法具有了生命周期感知能力
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        // ...
        Log.e(mActivityTag, "------ onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        // ...
        Log.e(mActivityTag, "------ onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        // ...
        Log.e(mActivityTag, "------ onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        // ...
        Log.e(mActivityTag, "------ onDestroy");
    }


}
