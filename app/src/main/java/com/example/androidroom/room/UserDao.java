package com.example.androidroom.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidroom.entity.User;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE uid = :id")
    Maybe<User> findById(int id);

    @Query("SELECT * FROM users")
    Maybe<List<User>> getAll();

    @Insert
    void insertAll(User... users);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
