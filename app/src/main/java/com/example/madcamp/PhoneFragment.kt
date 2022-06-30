package com.example.madcamp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import org.json.JSONArray


class PhoneFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context as MainActivity
        val list = getPhoneNumbers()

        val lv = context.findViewById(R.id.phoneListView) as ListView
        val adapter = ListViewAdapter(context, list)
        lv.adapter = adapter
    }

    private fun getPhoneNumbers(): ArrayList<PhoneNumber> {
        val assetManager = resources.assets
        val inputStream = assetManager.open("data.json")
//        val jsonString = assets.open("data.json").reader().readText()
        val jsonString = inputStream.reader().readText()
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