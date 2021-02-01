# 导入依赖

Lifecycle 被包含在 support library 26.1.0 及之后的依赖包中，如果我们的项目依赖的支持库版本在 26.1.0及以上，那么不需要额外导入 Lifecycle 库，

例使用的支持库是 28.0.0 ：（推荐）

```
implementation 'com.android.support:appcompat-v7:28.0.0'
```

如果支持库版本小于 26.1.0 ，就需要单独导入 Lifecycle 库 ：

```
implementation "android.arch.lifecycle:runtime:1.1.1"
```

使用androidX，导入下面appcompat包含androidx.lifecycle：（推荐）

```
implementation 'androidx.appcompat:appcompat:1.0.0-alpha1'
```

当然， AndroidX，可以使用下面的方式单独引入 ：

```
implementation "androidx.lifecycle:lifecycle-runtime:2.0.0"
```

# 使用

 Lifecycle 的实现机制是观察者模式，意识到这点，再讲它的使用过程及原理就比较容易理解了。

##### 整体流程：

1. 构建一个 Lifecycle 对象（通过一个实现了 LifecycleOwner 接口的对象的 `getLifecycle()`方法返回），这个对象就是一个被观察者，具有生命周期感知能力
2. 构建一个 LifecycleObserver 对象，它对指定的 Lifecycle 对象进行监听
3. 通过将 Lifecycle 对象的 addObserver(…) 方法，将 Lifecycle 对象和 LifecycleObserver 对象进行绑定

`AppCompatActivity`和`Fragment`都实现了`LifecycleOwner`接口（Support Library 26.1.0之后的版本），所以可以直接拿来使用：

```
getLifecycle().addObserver(new MyObserver());
```

但是`Activity`类并没有实现`LifecycleOwner`接口，所以，如果我们需要去监听自定义`Activity`的话，需要自己手动去实现`LifecycleOwner`接口，重写getLifecycle()， 手动标记生命周期状态。

# Lifecycle状态和事件

Lifecycle内部 Event，State两个枚举类型，看源码。

<img src="https://img.jbzj.com/file_images/article/201807/201873191105698.png?201863191121" alt="img" style="zoom:100%;" />

state标识Activity或Fragment当前的状态；event感知的事件，看起来有7种，实际上也就是6种而已。`ON_ANY`表示所有的事件都会感知。

可以看到，像`Activity`的`onRestart()` ，`Fragment`的`onCreateView()`等等其他生命周期是无法感知的。





lifecycle 还提供了查询当前组件所处的生命周期状态的方法：

```
lifecycle.getCurrentState().isAtLeast(STARTED)
```

