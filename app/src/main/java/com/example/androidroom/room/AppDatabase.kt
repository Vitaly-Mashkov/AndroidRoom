package com.example.androidroom.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidroom.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}