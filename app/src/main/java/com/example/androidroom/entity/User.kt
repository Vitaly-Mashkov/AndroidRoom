package com.example.androidroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@field:ColumnInfo(name = "first_name") var firstName: String,
                @field:ColumnInfo(name = "last_name") var lastName: String) {
    @PrimaryKey(autoGenerate = true)
    var uid = 0
}