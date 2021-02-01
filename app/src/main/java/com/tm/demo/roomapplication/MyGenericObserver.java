package com.tm.demo.roomapplication;

import android.util.Log;

import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * GenericLifecycleObserver 继承LifecycleObserver，多实现了state改变的回调
 */
public class MyGenericObserver implements GenericLifecycleObserver {

    private static final String TAG = "MyGenericObserver";

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Log.d(TAG, "onStateChanged: event =" + event);
    }
}
