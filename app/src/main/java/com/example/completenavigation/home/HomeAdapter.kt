package com.example.completenavigation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.completenavigation.R
import com.example.completenavigation.post.Post
import com.example.completenavigation.user.User

class HomeAdapter(val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val photosList: MutableList<Photo> = mutableListOf()
    private val usersList: MutableList<User> = mutableListOf()
    private val postsList: MutableList<Post> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var albumId: TextView
        //var id: TextView
        //var title: TextView
        val url: ImageView

        init {
            //albumId = itemView.findViewById(R.id.albumId_photo)
            //id = itemView.findViewById(R.id.albumId_photo)
            //title = itemView.findViewById(R.id.albumId_photo)
            url = itemView.findViewById(R.id.img_item_recyclerview_home)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.fragment_home, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photosList[position]

        //holder.albumId.text = photo.albumId.toString()
        //holder.id.text = photo.id.toString()
        //holder.title.text = photo.title
        //holder.url = photo.url
        Glide.with(holder.itemView.context)
            .load(photo.url)
            .into(holder.url)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    fun setPhotosHomeAdapter(photos: List<Photo>) {
        photosList.clear()
        photosList.addAll(photos)
        notifyDataSetChanged()
    }

    fun setUsersHomeAdapter(users: List<User>) {
        usersList.clear()
        usersList.addAll(users)
        notifyDataSetChanged()
    }

    fun setPostsHomeAdapter(posts: List<Post>) {
        postsList.clear()
        postsList.addAll(posts)
        notifyDataSetChanged()
    }

}