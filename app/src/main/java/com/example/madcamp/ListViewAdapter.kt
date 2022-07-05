package com.example.madcamp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ListViewAdapter (val context: Context, val phoneList: ArrayList<PhoneNumber>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.phone_item, null)

        val name = view.findViewById<TextView>(R.id.nameTv)
        val phone = view.findViewById<TextView>(R.id.phoneTv)
        val firstname = view.findViewById<TextView>(R.id.firstName)
//        val image = view.findViewById<ImageView>(R.id.profileImage)

        val info = phoneList[position]
//        image.setImageDrawable(info.img)
        firstname.text = info.name[0].toString()
        name.text = info.name
        phone.text = info.phone

        return view
    }

    override fun getItem(position: Int): Any {
        return phoneList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return phoneList.size
    }
}