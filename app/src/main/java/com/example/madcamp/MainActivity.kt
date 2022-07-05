package com.example.madcamp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.gms.location.LocationRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val grantResults = intArrayOf(0)
        checkPermission()

        var fragment1 = PhoneFragment()
        var fragment2 = GalleryFragment()
        var fragment3 = MapViewFragment()

        val  tabs = findViewById<TabLayout>(R.id.tabs)
//        tabs.addTab(tabs.newTab().setText("Phone"))
        tabs.addTab(tabs.newTab().setText("Gallery"))
        tabs.addTab(tabs.newTab().setText("Map"))

        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment1).commit()




        tabs.setOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                var position = tab.getPosition()
                lateinit var selected : Fragment
                if (position == 0)
                    selected = fragment1
                else if (position == 1)
                    selected = fragment2
                else if (position == 2)
                    selected = fragment3
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selected)
                    .commit()
            }
        })

    }

    fun checkPermission() {
        val contactPermission1 = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
        val contactPermission2 = ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION")
        if(contactPermission1 == PackageManager.PERMISSION_GRANTED) {
        }
        else {
            ActivityCompat.requestPermissions(this, arrayOf<String>("android.permission.READ_CONTACTS"), 100)
        }
        if(contactPermission2 == PackageManager.PERMISSION_GRANTED) {
        }
        else {
            ActivityCompat.requestPermissions(this, arrayOf<String>("android.permission.ACCESS_FINE_LOCATION"), 101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "permission granted")
        } else {
            Log.d("test", "permission denied")
        }
    }
}