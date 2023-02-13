package com.example.completenavigation.post

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.completenavigation.R

class PostAdapter(val context: Context) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val postsList: MutableList<Post> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userId: TextView
        var id: TextView
        var title: TextView
        var body: TextView

        init {
            userId = itemView.findViewById(R.id.user_id_post)
            id = itemView.findViewById(R.id.id_post)
            title = itemView.findViewById(R.id.title_post)
            body = itemView.findViewById(R.id.body_post)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_posts_list, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postsList[position]
        holder.userId.text = post.userId.toString()
        holder.id.text = post.id.toString()
        holder.title.text = post.title
        holder.body.text = post.body
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    fun setItems(items: List<Post>) {
        postsList.clear()
        postsList.addAll(items)
        notifyDataSetChanged()
    }

}