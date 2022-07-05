package com.example.madcamp

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView

class GalleryPopup(context: Context) {
    val context = context

    private val dialog = Dialog(context)

    fun showDialog(galleryItem: GalleryItem) {
        dialog.setContentView(R.layout.gallery_popup2)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val image = dialog.findViewById<ImageView>(R.id.popup_image)
        image.setImageDrawable(galleryItem.img)
    }

}

