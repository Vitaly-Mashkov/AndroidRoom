package com.example.androidroom.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidroom.R
import com.example.androidroom.adapters.UserAdapter.MyViewHolder
import com.example.androidroom.databinding.RvItemBinding
import com.example.androidroom.entity.User

class UserAdapter(var users: List<User?>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                RvItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        users[position]?.let { holder.bind(it) }
    }

    override fun getItemCount() = users.size

    class MyViewHolder(private val binding: RvItemBinding) : ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.idItem.text = binding.root.context.getString(R.string.id_placeholder, user.uid)
            binding.textNameItem.text = binding.root.context.getString(R.string.name_placeholder, user.firstName)
            binding.textLastNameItem.text = binding.root.context.getString(R.string.surname_placeholder, user.lastName)
        }
    }

}