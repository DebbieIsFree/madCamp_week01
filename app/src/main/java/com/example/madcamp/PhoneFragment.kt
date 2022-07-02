package com.example.madcamp

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


class PhoneFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var totList = arrayListOf<PhoneNumber>()
        val grant = intArrayOf(0)
        onRequestPermissionsResult(100, arrayOf("android.permission.READ_CONTACTS"), grant)
        if(grant[0] == PackageManager.PERMISSION_GRANTED) {
            totList = getContacts(view)
        }

        val context = context as MainActivity
        val search = context.findViewById(R.id.searchView) as SearchView
        val lv = context.findViewById(R.id.phoneListView) as ListView

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                return true
            }
        })

        val adapter = ListViewAdapter(context, totList)
        lv.adapter = adapter
    }

//    private fun getPhoneNumbers(): ArrayList<PhoneNumber> {
//        val assetManager = resources.assets
//        val inputStream = assetManager.open("data.json")
//        val jsonString = inputStream.reader().readText()
//        val jsonArray = JSONArray(jsonString)
//
//        var phoneNumbers = arrayListOf<PhoneNumber>()
//
//        for (index in 0 until jsonArray.length()){
//            val jsonObject = jsonArray.getJSONObject(index)
//
//            val name = jsonObject.getString("name")
//            val phone = jsonObject.getString("phone")
//
////            Log.d("jsonObject", jsonObject.toString())
////            Log.d("json_name_phone", "$name $phone")
//
//            phoneNumbers.add(PhoneNumber(name, phone))
//        }
//
//        return phoneNumbers
//    }

    fun getContacts(view: View): ArrayList<PhoneNumber> {
        val proj = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val contacts = arrayListOf<PhoneNumber>()
        val resolver = context?.getContentResolver()

        val cursor = resolver!!.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            proj,
            null,
            null,
            null
        )

        while (cursor?.moveToNext()?:false) {
            val id = cursor?.getString(0)
            val name = cursor?.getString(1)
            val number = cursor?.getString(2)

            val phone = PhoneNumber(ContextCompat.getDrawable(view.context, R.drawable.img)!!, name!!, number!!)
            Log.d("test", "$name $number")

            contacts.add(phone)
        }

        return contacts
    }

}