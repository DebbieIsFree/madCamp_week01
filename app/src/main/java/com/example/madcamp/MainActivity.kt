package com.example.madcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // phone 버튼 누르면 연락처 보여주는 페이지로 이동
        binding.phone.setOnClickListener(){

        }
    }
}