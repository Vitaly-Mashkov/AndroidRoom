package com.example.androidroom.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidroom.R
import com.example.androidroom.adapters.MyAdapter
import com.example.androidroom.room.DbCallback
import com.example.androidroom.room.DbManager
import com.example.androidroom.entity.User
import kotlinx.android.synthetic.main.activity_main_recycler.*

class RecyclerMainActivity : AppCompatActivity(), DbCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler)
        recycler_id.layoutManager = LinearLayoutManager(this)
        DbManager.getInstance(this)?.getUsers(this)
    }

    override fun onUsersLoaded(users: List<User?>) {
        recycler_id.adapter = MyAdapter(users)
    }

}