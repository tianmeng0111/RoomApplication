package com.tm.demo.roomapplication.roomdb;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * 数据库使用自定义类型：
 * 因为数据库可以持久存储Long类型，用这个转换器储存Date类型。
 * 使用方法：
 * @TypeConverters(Converters.class) 添加到RoomDatabase类上
 */
public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
