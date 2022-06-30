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
    }
}