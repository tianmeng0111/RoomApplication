package com.tm.demo.roomapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
}
