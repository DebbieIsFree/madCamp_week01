package com.example.madcamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity: AppCompatActivity() {

    var galleryItems = arrayListOf<GalleryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
    }
}