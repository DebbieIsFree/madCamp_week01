package com.example.madcamp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class GridViewAdapter (val context: Context, val galleryList: ArrayList<GalleryItem>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.gallery_item, null)

        val title = view.findViewById<TextView>(R.id.titleTv)

        val info = galleryList[position]
        title.text = info.title

        return view
    }

    override fun getItem(position: Int): Any {
        return galleryList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return galleryList.size
    }
}