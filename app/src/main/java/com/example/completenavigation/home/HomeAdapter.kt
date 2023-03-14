package com.example.completenavigation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.completenavigation.R

class HomeAdapter(val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val photosList: MutableList<Photo> = mutableListOf()

    /**
     * Le ViewHolder est une classe qui est responsable
     * de la création et de la gestion des vues individuelles
     * qui apparaissent dans la liste
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var photos: ImageView = itemView.findViewById(R.id.img_item_recyclerview_home)
            ?: throw IllegalArgumentException("ItemView must containt img_item_recyclerview_home")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.home_img_item_recyclerview, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photosList[position]
        Glide.with(holder.itemView.context)
            .load(photo.url)
            .into(holder.photos)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    fun setPhotosHomeAdapter(photos: List<Photo>) {
        photosList.clear()
        photosList.addAll(photos)
        notifyDataSetChanged()
    }

}