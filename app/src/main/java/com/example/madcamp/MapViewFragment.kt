package com.example.madcamp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.annotation.UiThread
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker

class MapViewFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapView: MapView

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        NaverMapSdk.getInstance(this).client =
//            NaverMapSdk.NaverCloudPlatformClient("zsmhzox1fe")
//    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.frag_map, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        // ...
    }






    /////

//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    val permission_request = 99
//    private lateinit var naverMap: NaverMap
//    var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//    //권한 가져오기
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        //activity가 최초 실행 되면 이곳을 수행
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.frag_map)
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//
//        if (isPermitted()) {
//            startProcess()
//        } else {
//            ActivityCompat.requestPermissions(this, permissions, permission_request)
//        }//권한 확인
//    }
//
//    fun isPermitted(): Boolean {
//        for (perm in permissions) {
//            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
//                return false
//            }
//        }
//        return true
//    }//권한을 허락 받아야함
//
//    fun startProcess(){
//        val fm = supportFragmentManager
//        val mapFragment = fm.findFragmentById(R.id.map_view) as MapFragment?
//            ?: MapFragment.newInstance().also {
//                fm.beginTransaction().add(R.id.map_view, it).commit()
//            } //권한
//        mapFragment.getMapAsync(this)
//    } //권한이 있다면 onMapReady연결
//
//
//    @UiThread
//    override fun onMapReady(naverMap: NaverMap){
//
//        val cameraPosition = CameraPosition(
//            LatLng(37.5666102, 126.9783881),  // 위치 지정
//            16.0 // 줌 레벨
//        )
//        naverMap.cameraPosition = cameraPosition
//        this.naverMap = naverMap
//
//        fusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(this) //gps 자동으로 받아오기
//        setUpdateLocationListner() //내위치를 가져오는 코드
//    }
//
//    //내 위치를 가져오는 코드
//    lateinit var fusedLocationProviderClient: FusedLocationProviderClient //자동으로 gps값을 받아온다.
//    lateinit var locationCallback: LocationCallback //gps응답 값을 가져온다.
//
//    @SuppressLint("MissingPermission")
//    fun setUpdateLocationListner() {
//        val locationRequest = LocationRequest.create()
//        locationRequest.run {
//            var priority = LocationRequest.PRIORITY_HIGH_ACCURACY //높은 정확도
//            var interval = 1000 //1초에 한번씩 GPS 요청
//        }
//
//        locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult?) {
//                locationResult ?: return
//                for ((i, location) in locationResult.locations.withIndex()) {
//                    Log.d("location: ", "${location.latitude}, ${location.longitude}")
//                    setLastLocation(location)
//                }
//            }
//        }
//        //location 요청 함수 호출 (locationRequest, locationCallback)
//
//        fusedLocationProviderClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            Looper.myLooper()
//        )
//    }//좌표계를 주기적으로 갱신
//
//    fun setLastLocation(location: Location) {
//        val myLocation = LatLng(location.latitude, location.longitude)
//        val marker = Marker()
//        marker.position = myLocation
//
//        marker.map = naverMap
//        //마커
//        val cameraUpdate = CameraUpdate.scrollTo(myLocation)
//        naverMap.moveCamera(cameraUpdate)
//        naverMap.maxZoom = 18.0
//        naverMap.minZoom = 5.0
//
//        //marker.map = null
//    }


}