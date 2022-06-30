package com.example.madcamp

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray


class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        val listViewAdapter = ListViewAdapter(this, getPhoneNumbers())
        val phoneListView = findViewById<ListView>(R.id.phoneListView)
        phoneListView.adapter = listViewAdapter
    }

    private fun getPhoneNumbers(): ArrayList<PhoneNumber> {
        val jsonString = assets.open("data.json").reader().readText()
        val jsonArray = JSONArray(jsonString)

        var phoneNumbers = arrayListOf<PhoneNumber>()

        for (index in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(index)

            val name = jsonObject.getString("name")
            val phone = jsonObject.getString("phone")

            Log.d("jsonObject", jsonObject.toString())
            Log.d("json_name_phone", "$name $phone")

            phoneNumbers.add(PhoneNumber(name, phone))
        }

        return phoneNumbers
    }
}