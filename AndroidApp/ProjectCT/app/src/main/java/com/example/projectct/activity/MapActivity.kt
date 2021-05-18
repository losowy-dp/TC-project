package com.example.projectct.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.projectct.R
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager
import com.mapbox.mapboxsdk.style.expressions.Expression.stop

class MapActivity : AppCompatActivity(), PermissionsListener, OnMapReadyCallback, MapboxMap.OnMapClickListener {
    private var permissionManager: PermissionsManager = PermissionsManager(this)
    private lateinit var mapboxMap: MapboxMap
    private lateinit var mapView: MapView
    private lateinit var button: Button
    private lateinit var buttonStop: Button
    private lateinit var destionation: Point

    private var destinationMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this,getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_map)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        button = findViewById(R.id.accept_map_button)
        button.setText(R.string.start)
        buttonStop = findViewById(R.id.stop_map)
        buttonStop.setText(R.string.stop)
        button.setOnClickListener(buttonAcceptListener)
        buttonStop.setOnClickListener(buttonStopListener)
        buttonStop.isVisible = false
        buttonStop.isEnabled = false
    }

    private var buttonAcceptListener= View.OnClickListener { buttonAccept() }
    private var buttonStopListener = View.OnClickListener { buttonStopL() }

    private fun buttonStopL(){
        buttonStop.isVisible = false
        buttonStop.isEnabled = false
        button.setText(R.string.start)
    }
    private fun buttonAccept(){
        if(button.text.equals("Start")){
            buttonStop.isVisible = true
            buttonStop.isEnabled = true
            button.setText(R.string.accept)
        }
        else{
            //TODO
        }
    }
    override fun onMapReady(mapboxMap: MapboxMap) {
       this.mapboxMap = mapboxMap
/*        mapboxMap.setStyle(Style.Builder().fromUri(
                "mapbox://styles/texnari0/ckosr83s28kix17pbfy3k5hml"
        )){
            enableLocationComponent(it)
        }*/
        mapboxMap.setStyle(Style.MAPBOX_STREETS){
            enableLocationComponent(it)
        }
    }
    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        if(PermissionsManager.areLocationPermissionsGranted(this)){
            val customLocationComponentOption = LocationComponentOptions.builder(this).trackingGesturesManagement(true).accuracyColor(ContextCompat.getColor(this,R.color.menuText)).build()

            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(this,loadedMapStyle).locationComponentOptions(customLocationComponentOption).build()

            mapboxMap.locationComponent.apply {
                activateLocationComponent(locationComponentActivationOptions)
                isLocationComponentEnabled = true
                cameraMode = CameraMode.TRACKING
                renderMode = RenderMode.COMPASS
            }
        }
        else{
            permissionManager = PermissionsManager(this)
            permissionManager.requestLocationPermissions(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }
    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(this,R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if(granted){
            enableLocationComponent(mapboxMap.style!!)
        }else{
            Toast.makeText(this,R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show()
            finish()
        }
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

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapClick(point: LatLng): Boolean {
        destinationMarker?.let{
            mapboxMap.removeMarker(it)
        }
        destinationMarker = mapboxMap.addMarker(MarkerOptions().position(point))
        destionation = Point.fromLngLat(point.longitude,point.latitude)
        return true
    }


}