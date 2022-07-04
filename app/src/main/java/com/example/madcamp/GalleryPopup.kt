package com.example.madcamp

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GalleryPopup(context: Context) {
    val context = context

    private val dialog = Dialog(context)

    fun showDialog(galleryItem: GalleryItem) {

        MaterialAlertDialogBuilder(context)
            .setTitle(galleryItem.title)
            .setBackground(ContextCompat.getDrawable(context, R.drawable.popup)!!)
            .setNeutralButton("close") {dialog, which -> true}
            .show()
//        dialog.setContentView(R.layout.gallery_popup)
//        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.setCancelable(true)
//        dialog.show()
//
//        val title = dialog.findViewById<TextView>(R.id.title_popup)
//        val closeBtn = dialog.findViewById<Button>(R.id.close_button)
//        title.text = galleryItem.title
//        closeBtn.setOnClickListener {
//            dialog.dismiss()
//        }
    }

}

