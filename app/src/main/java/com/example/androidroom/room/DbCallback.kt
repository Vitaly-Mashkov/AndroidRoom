package com.example.androidroom.room

import com.example.androidroom.entity.User

interface DbCallback {
    fun onUsersLoaded(users: List<User?>){}
    fun onUserAdded(){}
    fun onUserUpdated(){}
    fun onUserDeleted(){}
}