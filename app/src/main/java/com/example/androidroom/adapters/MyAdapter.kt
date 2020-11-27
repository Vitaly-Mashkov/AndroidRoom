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
        holder.textId.text = "id: " + users[position]?.uid
        holder.textName.text = "Name: " + users[position]?.firstName
        holder.textLastName.text = "Surname: " + users[position]?.lastName
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val textId: TextView = itemView.findViewById(R.id.id_item)
        val textName: TextView = itemView.findViewById(R.id.textName_item)
        val textLastName: TextView = itemView.findViewById(R.id.textLastName_item)
    }

}