package com.example.madcamp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import org.json.JSONArray


class PhoneFragment: Fragment() {
//    lateinit var mainActivity: MainActivity
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        // 2. Context를 액티비티로 형변환해서 할당
//        mainActivity = context as MainActivity
//    }

//    @SuppressLint("MissingPermission", "HardwareIds")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
////        var telManager  : TelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE)
//        val telManager = requireContext().applicationContext
//            .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        var phoneNum = telManager.getLine1Number()
//
//        if(phoneNum.startsWith("+82")){
//            phoneNum = phoneNum.replace("+82", "0")
//            Log.d("phone number", phoneNum)
//        }
//    }

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