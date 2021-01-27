package com.tm.demo.roomapplication;

import android.content.Context;

import com.tm.demo.roomapplication.dao.UserDao;
import com.tm.demo.roomapplication.entity.Pet;
import com.tm.demo.roomapplication.entity.User;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * 警告: Schema export directory is not provided to the annotation processor so we cannot export the schema. You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
 * @Database(exportSchema = false) 处理警告
 * 或
 * 在gradle文件中添加
 *         javaCompileOptions {
 *             annotationProcessorOptions {
 *                 arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
 *             }
 *         }
 * 会在app下生成schemas文件
 */

@Database(entities = {User.class, Pet.class}, version = 1)
@TypeConverters(Converters.class)//添加@TypeConverters注解到AppDatabbase类上, 之后Room就能够在AppDatabase中定义的每一个实体和DAO上使用这个转换器.
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE Fruit ('id' INTEGER, 'name' TEXT, PRIMARY KEY('id'))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN user_code INTEGER");
        }
    };

    private static AppDatabase mInstance;

    //在实例化AppDatabase对象时，应遵循单例设计模式，因为每个Roomdatabase实例都相当消耗性能。
    //默认情况下Database是不可以在主线程中进行调用的。
    public static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            /**
             * Migration需要两个参数，startVersion表示的是升级开始的版本，endVersion表示要升级到的版本。
             * 同时需要将@Database注解中的version的值修改为和endVersion相同。
             */

            mInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "database_name.db")//数据库名称
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)//数据库的升级或者降级使用addMigrations
                    .allowMainThreadQueries()//允许在主线程中使用
                    .build();
        }
        return mInstance;
    }

//    public static final AppDatabase.Companion Companion = new AppDatabase.Companion();
//
//    public static final class Companion {
//
//        private AppDatabase instance = null;
//
//
//        public final AppDatabase aStaticFunction(Context context) {
//            if (instance == null) {
//                /**
//                 * Migration需要两个参数，startVersion表示的是升级开始的版本，endVersion表示要升级到的版本。
//                 * 同时需要将@Database注解中的version的值修改为和endVersion相同。
//                 */
//
//                instance = Room.databaseBuilder(
//                        context.getApplicationContext(),
//                        AppDatabase.class,
//                        "database_name.db")//数据库名称
//                        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)//数据库的升级或者降级使用addMigrations
//                        .allowMainThreadQueries()//允许在主线程中使用
//                        .build();
//            }
//            return instance;
//        }
//    }

}
