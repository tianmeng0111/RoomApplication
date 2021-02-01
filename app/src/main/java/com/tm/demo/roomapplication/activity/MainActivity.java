package com.tm.demo.roomapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tm.demo.roomapplication.roomdb.AppDatabase;
import com.tm.demo.roomapplication.MyObserver;
import com.tm.demo.roomapplication.R;
import com.tm.demo.roomapplication.dao.UserDao;
import com.tm.demo.roomapplication.entity.User;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  完成 Lifecycle 和LifecycleObserver 的绑定
         * 因为 MainActivity继承了 AppCompatActivity，所以直接使用 getLifecycle() 方法来获取 Lifecycle，只此一行代码
         */
        getLifecycle().addObserver(new MyObserver(TAG));

//        AppDatabase appDatabase = AppDatabase.Companion.aStaticFunction(this);
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        UserDao userDao = appDatabase.userDao();

//        userDao.deleteAll();

        //insert 10条
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("name" + i);
            user.setBirthday(new Date(1980 + i, 1, 1));
            userDao.insertUser(user);
        }

        //update 数据
        User user1 = userDao.getUserByName("name1");
        user1.setUserName("updateName");
        userDao.updateUser(user1);

        //delete 数据
        userDao.deleteUser(userDao.getUserByName("name0"));

        //query所有
        List<User> all = userDao.getAll();
        for (User user: all) {
            Log.v(TAG, "userName: " + user.getUserName()
                    + ",address:" + user.address
                    + ",id:" + user.getUserId());
        }

//        List<User> findUsersBornBetween = userDao.findUsersBornBetweenDates(new Date(1985, 1, 1), new Date(1987, 1, 1));
//        for (User user: findUsersBornBetween) {
//            Log.v(TAG, "userName: " + user.getUserName()
//                    + ",address:" + user.getAddress()
//                    + ",id:" + user.getUserId()
//                    + ",birthday-year:" + user.getBirthday().getYear());
//        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_2:
                startActivity(new Intent(this, Main2Activity.class));
                break;
            case R.id.activity_fragment:
                startActivity(new Intent(this, HaveFragmentActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
