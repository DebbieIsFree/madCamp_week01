package com.example.madcamp

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class PhoneAddPopup(context: Context) {
    val context = context

    private val dialog = Dialog(context)

    fun showDialog(totlist: ArrayList<PhoneNumber>, lv: ListView) {
        dialog.setContentView(R.layout.phone_add_popup)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val name = dialog.findViewById<TextView>(R.id.name)
        val phone = dialog.findViewById<TextView>(R.id.phone)

        val addBtn = dialog.findViewById<Button>(R.id.add)
        addBtn.setOnClickListener {
            val newName = name.getText().toString()
            val newPhone = phone.getText().toString()
            totlist.add(PhoneNumber(newName, newPhone))
            val adapter = ListViewAdapter(context, totlist)
            lv.adapter = adapter
            dialog.dismiss()
        }
    }
}