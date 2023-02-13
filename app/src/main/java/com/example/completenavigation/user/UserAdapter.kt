package com.example.completenavigation.user

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.completenavigation.R

class UserAdapter(val context: Context):
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private val usersList: MutableList<User> = mutableListOf()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //var userId: TextView
        var name: TextView
        var username: TextView
        //var email: TextView
        //val cardView = itemView.findViewById<CardView>(R.id.card_view_item_user)

        init {
            name = itemView.findViewById(R.id.name_user)
            username = itemView.findViewById(R.id.username_user)
            //userId = itemView.findViewById(R.id.id_fragment_user_detail)
            //email = itemView.findViewById(R.id.email_fragment_user_detail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_users_list, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        //holder.cardView.tag = position
        //holder.userId.text = user.id.toString()
        holder.name.text = user.name
        holder.username.text = user.username
        //holder.email.text = user.email

        //holder.cardView.setOnClickListener(itemClickListener)


    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun setItems(items: List<User>) {
        usersList.clear()
        usersList.addAll(items)
        notifyDataSetChanged()
    }

}