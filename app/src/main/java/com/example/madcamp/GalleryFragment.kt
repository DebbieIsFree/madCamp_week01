package com.example.madcamp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.frag_gallery.*
import kotlinx.android.synthetic.main.gallery_popup.*
import java.io.IOException
import java.text.SimpleDateFormat


class GalleryFragment: Fragment() {

    private var list : ArrayList<GalleryItem>? = null
    var count : Int = 0

    private lateinit var mcontext : Context
    private val permissionAlbum = 100
    private val permissionCamera = 200
    private val requestStorage = 300
    private val requestCamera = 400
    private val requestCancled = 500

    var galleryBtn = view?.findViewById<Button>(R.id.galleryBtn)
    var realUri : Uri? = null
//    var cr : ContentResolver = mcontext.contentResolver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), permissionAlbum)

        galleryBtn?.setOnClickListener{
            openGallery()
        }

        galleryCloseBtn?.setOnClickListener{

        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            permissionGranted(requestCode)
        } else {
            permissionDenied(requestCode)
        }
    }

    private fun permissionGranted(requestCode: Int) {
        when (requestCode) {
//            permissionCamera -> openCamera()
            permissionAlbum -> openGallery()
        }
    }

    private fun permissionDenied(requestCode: Int) {
        when (requestCode) {
            permissionCamera -> Toast.makeText(
                mcontext,
                "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",
                Toast.LENGTH_LONG
            ).show()

            permissionAlbum -> Toast.makeText(
                mcontext,
                "저장소 권한을 승인해야 앨범에서 이미지를 불러올 수 있습니다.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.type = "image/*"
        startActivityForResult(intent,requestStorage)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == RESULT_OK) {
//            when (requestCode) {
//                requestCamera -> {
//                    realUri?.let { uri ->
//                        imageView.setImageURI(uri)
//                    }
//                }
//                requestStorage -> {
//                    data?.data?.let { uri ->
//                        imageView.setImageURI(uri)
//                    }
//                }
//            }
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == requestStorage) {
            if(resultCode == RESULT_OK) {
                var currentImageUri = data?.data

                try{
                    currentImageUri?.let {
                        if(Build.VERSION.SDK_INT < 28) {
                            val bitmap = MediaStore.Images.Media.getBitmap(
                                mcontext.contentResolver,
                                currentImageUri
                            )
                            galleryView?.setImageBitmap(bitmap)
                        } else {
                            val source = ImageDecoder.createSource(mcontext.contentResolver, currentImageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            galleryView?.setImageBitmap(bitmap)
                            list?.plus(
                                GalleryItem(view?.let { it1 -> ContextCompat.getDrawable(it1.context, R.drawable.img) }!!,"item${count++}"),
                            )
                        }
                    }
                }catch(e : IOException)
                {
                    e.printStackTrace()
                }
            }
            else if(resultCode == requestCancled)
            {
                Toast.makeText(mcontext, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var list = arrayListOf(
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item1"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item2"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item3"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item4"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item5"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item6")
//        )

        val galleryAdapter = list?.let { GalleryAdapter(it) }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = galleryAdapter

        if (galleryAdapter != null) {
            galleryAdapter.setItemClickListener(object: GalleryAdapter.OnItemClickListener {
                @SuppressLint("ResourceType")
                override fun onClick(v: View, position: Int) {
                    Log.d("test", position.toString())
    //
    //                var fname  = "item$position"
    //
    //                var dialog = Dialog(view.context)
    //
    //                dialog.setContentView(R.layout.dialog)
    ////                dialog.setTitle(fname)
    //                dialog.show()
    ////                dialog.showDialog(list[position])
                }
            })
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        var list = arrayListOf(
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item1"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item2"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item3"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item4"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item5"),
//            GalleryItem(ContextCompat.getDrawable(view.context, R.drawable.img)!!,"item6")
//        )
//
//        val galleryAdapter = GalleryAdapter(list)
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
//
//        recyclerView.layoutManager = GridLayoutManager(activity, 2)
//        recyclerView.adapter = galleryAdapter
//
//        galleryAdapter.setItemClickListener(object: GalleryAdapter.OnItemClickListener {
//            @SuppressLint("ResourceType")
//            override fun onClick(v: View, position: Int) {
//                Log.d("test", position.toString())
//
//                var fname  = "item$position"
//
//                var dialog = Dialog(view.context)
//
//                dialog.setContentView(R.layout.dialog)
////                dialog.setTitle(fname)
//                dialog.show()
////                dialog.showDialog(list[position])
//            }
//        })
//    }

//    override fun onStart() {
//        super.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onStop() {
//        super.onStop()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//    }


}