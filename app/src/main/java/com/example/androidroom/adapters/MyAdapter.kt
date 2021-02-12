package com.example.androidroom.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidroom.R
import com.example.androidroom.entity.User
import com.example.androidroom.adapters.MyAdapter.MyViewHolder

class MyAdapter(var users: List<User?>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        users[position]?.let { holder.bind(it) }
    }

    override fun getItemCount() = users.size


    class MyViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(user: User){

            itemView.findViewById<TextView>(R.id.id_item).text = "id: " + user.uid
            itemView.findViewById<TextView>(R.id.textName_item).text = "Name: " + user.firstName
            itemView.findViewById<TextView>(R.id.textLastName_item).text = "Surname: " + user.lastName
        }
    }

}