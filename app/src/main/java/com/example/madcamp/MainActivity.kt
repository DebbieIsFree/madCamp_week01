package com.example.madcamp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phoneBtn = findViewById<Button>(R.id.phone)
        val galleryBtn = findViewById<Button>(R.id.gallery)
        val mapBtn = findViewById<Button>(R.id.map)

        phoneBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, PhoneFragment())
                .addToBackStack(null)
                .commit()
        }
        galleryBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, GalleryFragment())
                .commit()
        }
        mapBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, MapViewFragment())
                .commit()
        }
    }
}