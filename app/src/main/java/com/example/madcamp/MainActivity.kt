package com.example.madcamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.gms.location.LocationRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val phoneBtn = findViewById<Button>(R.id.phone)
//        val galleryBtn = findViewById<Button>(R.id.gallery)
//        val mapBtn = findViewById<Button>(R.id.map)

//        phoneBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frame, PhoneFragment())
//                .addToBackStack(null)
//                .commit()
//        }
//        galleryBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frame, GalleryFragment())
//                .commit()
//        }
//        mapBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frame, MapViewFragment())
//                .commit()
//        }




        /////


        var fragment1 = PhoneFragment()
        var fragment2 = GalleryFragment()
        var fragment3 = MapViewFragment()

        val  tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("친구"))
        tabs.addTab(tabs.newTab().setText("채팅"))
        tabs.addTab(tabs.newTab().setText("더보기"))

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
}