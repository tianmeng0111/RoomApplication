package com.tm.demo.roomapplication.entity;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long userId;

    @ColumnInfo(name = "user_name")
    private String userName;

    private int age;

    @ColumnInfo(name = "address", defaultValue = "china")
    @Nullable
    public String address;

    /**
     @Ignore 忽略这个字段，使用了这个注解的字段将不会在数据库中生成对应的列信息
     也可以使用@Entity注解中的 ignoredColumns 参数来指定，效果是一样的。
     */
    @Ignore
    private boolean sex;

    //自定义类型，用Converters.class转成Long
    private Date birthday;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}
