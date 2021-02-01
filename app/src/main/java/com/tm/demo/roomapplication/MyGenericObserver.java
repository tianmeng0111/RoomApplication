package com.tm.demo.roomapplication;

import android.util.Log;

import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

public class MyGenericObserver implements GenericLifecycleObserver {

    private static final String TAG = "MyGenericObserver";

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Log.d(TAG, "onStateChanged: event =" + event);
    }
}
