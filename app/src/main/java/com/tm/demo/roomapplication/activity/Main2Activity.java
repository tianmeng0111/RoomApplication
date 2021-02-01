package com.tm.demo.roomapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import android.app.Activity;
import android.os.Bundle;

import com.tm.demo.roomapplication.MyObserver;
import com.tm.demo.roomapplication.R;

/**
 * activity不继承AppCompatActivity继承Activity的方式，不能直接获得getLifecycle()，
 * 需要自己实现LifecycleOwner接口，并在具体的生命周期下通过 LifecycleRegistry 的 markState(...)方法来主动进行事件的分发。
 */
public class Main2Activity extends Activity implements LifecycleOwner {

    private static final String TAG = "Main2Activity";

    private LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //创建Lifecycle对象, LifecycleRegistry父类是Lifecycle
        lifecycleRegistry = new LifecycleRegistry(this);
        //添加观察者
        getLifecycle().addObserver(new MyObserver(TAG));
        //手动标记状态
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    //返回Lifecycle
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);//执行onResume
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);//执行onResume
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);//执行onPause
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }
}
