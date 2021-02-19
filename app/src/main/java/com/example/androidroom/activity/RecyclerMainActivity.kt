package com.example.androidroom.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidroom.adapters.UserAdapter
import com.example.androidroom.databinding.ActivityMainRecyclerBinding
import com.example.androidroom.room.DbCallback
import com.example.androidroom.room.DbProvider
import com.example.androidroom.entity.User

class RecyclerMainActivity : AppCompatActivity(), DbCallback {
    lateinit var binding: ActivityMainRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerId.layoutManager = LinearLayoutManager(this)
        DbProvider.getUsers(this)
    }

    override fun onUsersLoaded(users: List<User?>) {
        binding.recyclerId.adapter = UserAdapter(users)
    }

}