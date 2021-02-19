package com.example.androidroom.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidroom.R
import com.example.androidroom.databinding.ActivityMainBinding
import com.example.androidroom.room.DbCallback
import com.example.androidroom.room.DbProvider
import com.example.androidroom.entity.User

class MainActivity : AppCompatActivity(), DbCallback, View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DbProvider.initialize(this)
        binding.let {
            it.buttonAdd.setOnClickListener(this)
            it.buttonGetAll.setOnClickListener(this)
            it.buttonUpdate.setOnClickListener(this)
            it.buttonDelete.setOnClickListener(this)
        }
    }

    override fun onDestroy() {
        DbProvider.disposeAll()
        super.onDestroy()
    }

    override fun onUserAdded() = showToast("new User added!")

    override fun onUserUpdated() = showToast("user updated")

    override fun onUserDeleted() = showToast("user deleted")

    private fun add() {
        if (!TextUtils.isEmpty(binding.userName.text) && !TextUtils.isEmpty(binding.userSurname.text))
            DbProvider.addUser(
                    this,
                    User(binding.userName.text.toString(), binding.userSurname.text.toString())
            )
    }

    private fun update() {
        if (!TextUtils.isEmpty(binding.uid.text) && !TextUtils.isEmpty(binding.userName.text) &&
                !TextUtils.isEmpty(binding.userSurname.text))
            DbProvider.updateUser(this, binding.uid.text.toString().toInt(),
                    User(binding.userSurname.text.toString(), binding.userSurname.text.toString()))
    }

    private fun delete() {
        if (!TextUtils.isEmpty(binding.uid.text.toString()))
            DbProvider.deleteUser(this, binding.uid.text.toString().toInt())
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

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}