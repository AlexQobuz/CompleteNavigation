package com.example.completenavigation.user

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.completenavigation.R

class UserAdapter(val context: Context, val onClick:(User) -> Unit):
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    /**
     * J'initialise ma variable usersList
     * avec une MutableList
     */
    private val usersList: MutableList<User> = mutableListOf()

    /**
     * Le ViewHolder est une classe qui est responsable
     * de la création et de la gestion des vues individuelles
     * qui apparaissent dans la liste
     */
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name_user)
        var username: TextView = itemView.findViewById(R.id.username_user)
        var cardView = itemView.findViewById<CardView>(R.id.card_view_item_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_users_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        holder.name.text = user.name
        holder.username.text = user.username

        holder.cardView.setOnClickListener {
            Log.d("UserFragment", "Click sur un utilisateur de la liste")
            onClick(user)
        }

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