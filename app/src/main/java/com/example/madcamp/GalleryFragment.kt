package com.example.madcamp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GalleryFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = arrayListOf(
            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item1"),
            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item2"),
            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item3"),
            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item4"),
            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item5"),
            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item6")
        )

        val galleryAdapter = GalleryAdapter(list)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = galleryAdapter
    }
}