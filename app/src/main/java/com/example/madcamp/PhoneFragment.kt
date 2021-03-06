package com.example.madcamp

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment


class PhoneFragment: Fragment() {
    var totList: ArrayList<PhoneNumber> = arrayListOf()
    var filterList: ArrayList<PhoneNumber> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val grant = intArrayOf(0)
        onRequestPermissionsResult(100, arrayOf("android.permission.READ_CONTACTS"), grant)
        if(grant[0] == PackageManager.PERMISSION_GRANTED) {
            totList = getContacts(view)
            filterList = getContacts(view)
        }

        val context = context as MainActivity
        val search = context.findViewById(R.id.search) as SearchView
        val lv = context.findViewById(R.id.phoneListView) as ListView
        val addBtn: View = context.findViewById(R.id.fab)

        var adapter = ListViewAdapter(context, filterList)
        lv.adapter = adapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                filterList = totList.filter {
                    it.name.contains(p0)
                } as ArrayList<PhoneNumber>
                adapter = ListViewAdapter(context, filterList)
                lv.adapter = adapter
                return true
            }
        })

        addBtn.setOnClickListener { view ->
            val dialog = PhoneAddPopup(view.context)
            dialog.showDialog(totList, lv)
        }

    }

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

            val phone = PhoneNumber(name!!, number!!)

            contacts.add(phone)
        }

        return contacts
    }

}