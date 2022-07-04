package com.example.madcamp

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class GalleryPopup(context: Context) {
    private val dialog = Dialog(context)

    fun showDialog(galleryItem: GalleryItem) {
        dialog.setContentView(R.layout.gallery_popup)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val title = dialog.findViewById<TextView>(R.id.title_popup)
        val closeBtn = dialog.findViewById<Button>(R.id.close_button)
        title.text = galleryItem.title
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

}