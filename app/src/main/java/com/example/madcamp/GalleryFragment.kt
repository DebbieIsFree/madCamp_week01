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
import kotlinx.android.synthetic.main.gallery_popup2.*
import java.io.IOException
import java.text.SimpleDateFormat


class GalleryFragment: Fragment() {

    var totlist : ArrayList<GalleryItem>? = arrayListOf<GalleryItem>()

    private lateinit var mcontext : Context
    private lateinit var galleryAdapter: GalleryAdapter
    private val permissionAlbum = 100
    private val permissionCamera = 200
    private val requestStorage = 300
    private val requestCamera = 400
    private val requestCancled = 500

    var realUri : Uri? = null
//    var cr : ContentResolver = mcontext.contentResolver



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
//            permissionAlbum -> openGallery()
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
        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, requestStorage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == requestStorage) {
            if(data?.clipData != null) {
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri

                    imageUri?.let {
                        if(Build.VERSION.SDK_INT < 28) {
                            val bitmap = MediaStore.Images.Media.getBitmap(
                                mcontext.contentResolver,
                                imageUri
                            )
                            galleryView?.setImageBitmap(bitmap)
                        } else {
                            val source = ImageDecoder.createSource(mcontext.contentResolver, imageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            galleryView?.setImageBitmap(bitmap)
                            totlist?.plus(
                                GalleryItem(view?.let { it1 -> ContextCompat.getDrawable(it1.context, R.drawable.img) }!!,"item${i}"),
                            )
                        }
                    }
                }
            }
            else {
                Log.d("test", "only one")
            }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if(requestCode == requestStorage) {
//            if(resultCode == RESULT_OK) {
//                var currentImageUri = data!!.data
//
//                try{
//                    currentImageUri?.let {
//                        if(Build.VERSION.SDK_INT < 28) {
//                            val bitmap = MediaStore.Images.Media.getBitmap(
//                                mcontext.contentResolver,
//                                currentImageUri
//                            )
//                            galleryView?.setImageBitmap(bitmap)
//                        } else {
//                            val source = ImageDecoder.createSource(mcontext.contentResolver, currentImageUri)
//                            val bitmap = ImageDecoder.decodeBitmap(source)
//                            galleryView?.setImageBitmap(bitmap)
//                            totlist?.plus(
//                                GalleryItem(view?.let { it1 -> ContextCompat.getDrawable(it1.context, R.drawable.img) }!!,"item${count++}"),
//                            )
//                        }
//                    }
//                }catch(e : IOException)
//                {
//                    e.printStackTrace()
//                }
//            }
//            else if(resultCode == requestCancled)
//            {
//                Toast.makeText(mcontext, "사진 선택 취소", Toast.LENGTH_LONG).show();
//            }
//        }
//    }


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
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), permissionAlbum)

        var galleryBtn = view?.findViewById<Button>(R.id.galleryBtn)
        galleryBtn?.setOnClickListener{
            openGallery()
        }

        galleryCloseBtn?.setOnClickListener{

        }

        galleryAdapter = GalleryAdapter(totlist!!)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        recyclerView.adapter = galleryAdapter

//        if (galleryAdapter != null) {
//            galleryAdapter.setItemClickListener(object: GalleryAdapter.OnItemClickListener {
//                override fun onClick(v: View, position: Int) {
//                    Log.d("test", position.toString())
//    //
//                    val dialog = GalleryPopup(view.context)
//                    dialog.showDialog(totlist!![position])
//                }
//            })
//        }
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

}