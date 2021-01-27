package com.tm.demo.roomapplication.dao;

import android.database.Cursor;

import com.tm.demo.roomapplication.entity.Pet;
import com.tm.demo.roomapplication.entity.User;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("select * from users where userId = :id")
    User getUserById(long id);

    @Query("select * from users where user_name = :name")
    User getUserByName(String name);

    //搜索地区子集下的所有用户
//    @Query("SELECT user_name FROM users WHERE address IN (:addressList)")
//    List<User> loadUsersFromAddress(List<String> addressList);

    /**
     * 可观察查询:
     * 在执行查询的时候, 经常想要在数据发生改变的时候自动更新UI. 要达到这个目的, 需要在查询方法描述中返回LiveData类型的值.
     * 在数据库更新的时候, Room生成所有必要的代码以更新LiveData.
     */
    @Query("SELECT * FROM users WHERE address IN (:addressList)")
    LiveData<List<User>> loadUsersFromAddressSync(List<String> addressList);

    //查询返回指针
    @Query("SELECT * FROM users WHERE age > :minAge LIMIT 5")
    public Cursor loadRawUsersOlderThan(int minAge);

    //按逻辑条件引用两个表
    @Query("SELECT users.user_name AS userName, pets.petId "
            + "FROM users, pets "
            + "WHERE users.user_name = pets.userName")
    public List<Pet> loadUserAndPetNames();

    //关联查找
    @Query("SELECT * FROM pets "
            + "INNER JOIN users ON pets.userName = users.user_name "
            + "WHERE users.user_name LIKE :userName")
    public List<Pet> findPetsFromUserName(String userName);

    @Query("SELECT * FROM users WHERE birthday BETWEEN :from AND :to")
    public List<User> findUsersBornBetweenDates(Date from, Date to);

    //插入的数据已经存在时候的处理逻辑，不指定则默认为ABORT终止插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    //使用主键来查找要删除的实体
    @Delete
    void deleteUser(User user);

    //和@Delete一样也是根据主键来查找要删除的实体。
    @Update
    void updateUser(User user);

    /**
     * 删除或者更新我们也可以使用 @Query 注解来使用SQL语句来直接执行，效果是完全一样的。
     */
    @Query("delete from users where userId = :id")
    void deleteUserById(long id);

    //更新使用 @Query 注解来使用SQL语句来直接执行
    @Query("update users set userId = :userName where userId = :id")
    void updateUserNameById(long id, String userName);

    @Query("delete from users")
    void deleteAll();

}
