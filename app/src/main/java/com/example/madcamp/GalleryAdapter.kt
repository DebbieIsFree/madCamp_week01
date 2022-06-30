package com.example.madcamp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GalleryAdapter(private val galleryList: ArrayList<GalleryItem>): RecyclerView.Adapter<GalleryAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        val title: TextView = itemView!!.findViewById(R.id.titleTv)
        fun bind(galleryItem: GalleryItem, position: Int) {
            title.text = galleryItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(galleryList[position], position)
    }
}