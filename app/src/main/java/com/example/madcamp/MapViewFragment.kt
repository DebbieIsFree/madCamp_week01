package com.example.madcamp


import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import java.util.*


class MapViewFragment : Fragment(), OnMapReadyCallback {

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var mapView: MapView
    private lateinit var mcontext: Context

    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private lateinit var locationManager : LocationManager



    private var lat : Double = 0.0
    private var lon : Double = 0.0

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

//    private val locationRequest: LocationRequest? = null


//    private val permissions = arrayOf(
//        Manifest.permission.ACCESS_FINE_LOCATION,
//        Manifest.permission.ACCESS_COARSE_LOCATION
//    )

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mcontext = context as MainActivity
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        fusedLocationClient =
//            LocationServices.getFusedLocationProviderClient(requireContext())
//
//        locationSource =
//            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
//
//        locationManager = mcontext.getSystemService(LOCATION_SERVICE) as LocationManager
//
//        if (ActivityCompat.checkSelfPermission(
//                mcontext,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                mcontext,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//
//        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
//            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
//
//            override fun isCancellationRequested() = false
//        })
//            .addOnSuccessListener { location: Location? ->
//                if (location == null)
//                    Log.d("Failed","Cannot_get_location")
//                else {
//                    lat = location.latitude
//                    lon = location.longitude
//                    Log.d("location : ", "${lat}, $lon")
//                }
//
//                val geocoder  = Geocoder(mcontext, Locale.KOREA)
//                var addr = geocoder.getFromLocation(lat, lon, 1)
//
//                Log.d("주소", "${addr[0].countryName}")
//                Log.d("주소", "${addr[0].subLocality}")
//                Log.d("주소", "${addr[0].subAdminArea}")
//            }
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.frag_map, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.map_view)
//        mapView.onCreate(savedInstanceState)
        mcontext = context as MainActivity

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
//
//        naverMap.addOnLocationChangeListener { location ->
//            Log.d("lat, lon", "${location.latitude}, ${location.longitude}")
//        }


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mcontext)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                lat = location!!.latitude
                lon = location!!.longitude
                Log.d("location", lat.toString())
                Log.d("location", lon.toString())
//                val geocoder  = Geocoder(mcontext, Locale.KOREA)
//                val address = geocoder.getFromLocation(lat, lon, 1)
//                Log.d("address", address.toString())
            }

        val grant = intArrayOf(0)
        onRequestPermissionsResult(100, arrayOf("android.permission.ACCESS_FINE_LOCATION"), grant)
        if(grant[0] == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
        }

        mapView.onResume()
        mapView.getMapAsync(this)
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

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//
//        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults))
//        {
//            if (!locationSource.isActivated) { // 권한 거부됨
//                neverMap.locationTrackingMode = LocationTrackingMode.None
//                return
//            }
//            else{
//                neverMap.locationTrackingMode = LocationTrackingMode.Follow
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }


    override fun onMapReady(naverMap: NaverMap) {
        Log.d("ready", "hihihi")
        this.naverMap = naverMap
//        naverMap.locationSource = locationSource
//        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

}






