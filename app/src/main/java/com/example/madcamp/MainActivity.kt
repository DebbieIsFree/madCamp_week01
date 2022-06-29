package com.example.madcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonString = assets.open("data.json").reader().readText()
        val jsonArray = JSONArray(jsonString)

        for (index in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(index)

            val id = jsonObject.getString("name")
            val language = jsonObject.getString("phone")

            Log.d("jsonObject", jsonObject.toString())
            Log.d("json_id_language", "$id $language")
        }

        setContentView(R.layout.activity_main)


    }
}