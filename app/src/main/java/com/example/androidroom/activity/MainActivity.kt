package com.example.androidroom.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidroom.R
import com.example.androidroom.room.DbCallback
import com.example.androidroom.room.DbManager
import com.example.androidroom.entity.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DbCallback, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add.setOnClickListener(this)
        button_get_all.setOnClickListener(this)
        button_update.setOnClickListener(this)
        button_delete.setOnClickListener(this)
    }

    override fun onDestroy() {
        DbManager.getInstance(this)?.disposeAll()
        super.onDestroy()
    }

    override fun onUserAdded() = showToast("new User added!")

    override fun onUserUpdated() = showToast("user updated")

    override fun onUserDeleted() = showToast("user deleted")

    private fun add() {
        if (!TextUtils.isEmpty(user_name.text) && !TextUtils.isEmpty(user_surname.text))
            DbManager.getInstance(this)?.addUser(
                    this,
                    User(user_name.text.toString(), user_surname.text.toString())
            )
    }

    private fun update() {
        if (!TextUtils.isEmpty(uid.text) && !TextUtils.isEmpty(user_name.text) &&
                !TextUtils.isEmpty(user_surname.text))
            DbManager.getInstance(this)?.updateUser(this, uid.text.toString().toInt(),
                    User(user_name.text.toString(), user_surname.text.toString()))
    }

    private fun delete() {
        if (!TextUtils.isEmpty(uid.text.toString()))
            DbManager.getInstance(this)?.deleteUser(this, uid.text.toString().toInt())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_add -> add()
            R.id.button_get_all ->
                startActivity(Intent(this, RecyclerMainActivity::class.java))
            R.id.button_update -> update()
            R.id.button_delete -> delete()
        }
    }

    private fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }
}