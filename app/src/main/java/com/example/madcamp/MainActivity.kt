package com.example.madcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phoneBtn = findViewById<Button>(R.id.phone)
        val galleryBtn = findViewById<Button>(R.id.gallery)

        phoneBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, PhoneFragment())
                .commit()
        }
        galleryBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, GalleryFragment())
                .commit()
        }
    }
}