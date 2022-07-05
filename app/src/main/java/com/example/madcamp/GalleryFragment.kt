package com.example.madcamp

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GalleryFragment: Fragment() {

    var totlist : ArrayList<GalleryItem>? = arrayListOf<GalleryItem>()

    private lateinit var mcontext : Context
    private lateinit var galleryAdapter: GalleryAdapter
    private val permissionAlbum = 100
    private val permissionCamera = 200
    private val requestStorage = 300



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
                        } else {
                            val source = ImageDecoder.createSource(mcontext.contentResolver, imageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            totlist?.add(
                                GalleryItem(bitmap.toDrawable(resources)),
                            )
                        }
                    }
                }
            }
            else {
                data?.data?.let {
                    if(Build.VERSION.SDK_INT < 28) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            mcontext.contentResolver,
                            it
                        )
                    } else {
                        val source = ImageDecoder.createSource(mcontext.contentResolver, it)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        totlist?.add(
                            GalleryItem(bitmap.toDrawable(resources)),
                        )
                    }
                }
            }
            galleryAdapter.notifyDataSetChanged()
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
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), permissionAlbum)

        var galleryBtn: View = view?.findViewById(R.id.open)
        val refreshBtn: View = view?.findViewById(R.id.refresh)
        galleryBtn?.setOnClickListener{
            openGallery()
        }
        refreshBtn?.setOnClickListener{
            totlist!!.clear()
            galleryAdapter.notifyDataSetChanged()
        }

        galleryAdapter = GalleryAdapter(totlist!!)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        recyclerView.adapter = galleryAdapter
        galleryAdapter.setItemClickListener(object: GalleryAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val dialog = GalleryPopup(view.context)
                dialog.showDialog(totlist!![position])
            }
        })

    }

}