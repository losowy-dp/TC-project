package com.example.projectct.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.projectct.R
import com.example.projectct.helpClass.Dane.DaneOrder
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.Symbol
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.style.layers.Layer
import com.mapbox.mapboxsdk.style.layers.Property.NONE
import com.mapbox.mapboxsdk.style.layers.Property.VISIBLE
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature
import com.mapbox.api.geocoding.v5.models.GeocodingResponse
import com.mapbox.core.exceptions.ServicesException
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MapActivity : AppCompatActivity(), PermissionsListener, OnMapReadyCallback{
    private var permissionManager: PermissionsManager = PermissionsManager(this)
    private lateinit var mapboxMap: MapboxMap
    private lateinit var mapView: MapView
    private lateinit var button: Button
    private lateinit var buttonStop: Button
    private  var name: String = "null"
    private lateinit var hoveringMarker: ImageView
    private var DROPPED_MARKER_LAYER_ID = "DROPPED_MARKER_LAYER_ID"
    private lateinit var droppedMarkerLayer: Layer
    private lateinit var  mapTargetLatLng: LatLng
    private lateinit var type: String
    private lateinit var daneOrder: DaneOrder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_map)
        mapView = findViewById(R.id.mapView)
        type = intent.extras!!.getString("type").toString()
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        button = findViewById(R.id.accept_map_button)
        button.setText(R.string.start)
        buttonStop = findViewById(R.id.stop_map)
        buttonStop.setText(R.string.stop)
        buttonStop.setOnClickListener(buttonStopListener)
        buttonStop.isVisible = false
        buttonStop.isEnabled = false

    }

    private var buttonStopListener = View.OnClickListener { buttonStopL() }

    private fun buttonStopL(){
        buttonStop.isVisible = false
        buttonStop.isEnabled = false
        button.setText(R.string.start)
    }


    override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap
//      mapboxMap.setStyle(Style.Builder().fromUri(
//                "mapbox://styles/texnari0/ckosr83s28kix17pbfy3k5hml"
//        )){
//            enableLocationComponent(it)
//        }
        mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
            enableLocationComponent(style)
            hoveringMarker = ImageView(this)
            hoveringMarker.setImageResource(R.drawable.red_marker)
           val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER)
            hoveringMarker.layoutParams = params
            mapView.addView(hoveringMarker)
           initDroppedMarker(style)
            button.setOnClickListener {
                if(hoveringMarker.visibility == View.VISIBLE){
                   mapTargetLatLng = mapboxMap.cameraPosition.target
                    hoveringMarker.visibility = View.INVISIBLE
                    button.text = getString(R.string.accept)
                    buttonStop.isVisible = true
                    buttonStop.isEnabled = true
                    if(style.getLayer(DROPPED_MARKER_LAYER_ID)!=null){
                        val source = style.getSourceAs<GeoJsonSource>("dropped-marker-source-id")
                        source?.setGeoJson(Point.fromLngLat(mapTargetLatLng.longitude,mapTargetLatLng.latitude))

                        droppedMarkerLayer = style.getLayer(DROPPED_MARKER_LAYER_ID)!!
                        if(droppedMarkerLayer!=null){
                            droppedMarkerLayer.setProperties(visibility(VISIBLE))
                        }
                    }
                    reverseGeocode(Point.fromLngLat(mapTargetLatLng.longitude,mapTargetLatLng.latitude))
                    buttonStop.setOnClickListener{
                        buttonStop.isVisible = false
                        buttonStop.isEnabled = false
                        button.setText(R.string.start)
                        hoveringMarker.visibility = View.VISIBLE
                        droppedMarkerLayer = style.getLayer(DROPPED_MARKER_LAYER_ID)!!
                        if(droppedMarkerLayer!=null){
                            droppedMarkerLayer.setProperties(visibility(NONE))
                        }
                    }
               }
                else{
                    val intent = Intent(this,HomeActivity::class.java)
                    intent.putExtra("fragment","add")
                    intent.putExtra("type",type)
                    daneOrder = DaneOrder(this)
                    if(type=="From"){
                        daneOrder.saveFrom(name,mapTargetLatLng.latitude.toString(),mapTargetLatLng.longitude.toString())
                    }else {
                        daneOrder.saveWhere(name, mapTargetLatLng.latitude.toString(), mapTargetLatLng.longitude.toString())
                    }
                    startActivity(intent)
                    }
                }
            }
        }


    private fun initDroppedMarker(loadedMapStyle: Style){
        loadedMapStyle.addImage("dropped-icon-image",BitmapFactory.decodeResource(resources,R.drawable.red_marker))
        loadedMapStyle.addSource(GeoJsonSource("dropped-marker-source-id"))
        loadedMapStyle.addLayer(SymbolLayer(DROPPED_MARKER_LAYER_ID,"dropped-marker-source-id").withProperties(iconImage("dropped-icon-image"), visibility(NONE), iconAllowOverlap(true), iconIgnorePlacement(true)))
    }

    private fun reverseGeocode(point: Point) {
        try {
            val client: MapboxGeocoding = MapboxGeocoding.builder()
                    .accessToken(getString(R.string.mapbox_access_token))
                    .query(Point.fromLngLat(point.longitude(), point.latitude()))
                    .build()
            client.enqueueCall(object :  retrofit2.Callback<GeocodingResponse> {
                override fun onResponse(call: Call<GeocodingResponse?>?, response: Response<GeocodingResponse?>) {
                    if (response.body() != null) {
                        val results: List<CarmenFeature> = response.body()!!.features()
                        if (results.size > 0) {
                            val feature: CarmenFeature = results[0]

// If the geocoder returns a result, we take the first in the list and show a Toast with the place name.
                            mapboxMap.getStyle { style ->
                                if (style.getLayer(DROPPED_MARKER_LAYER_ID) != null) {
                                    name = feature.placeName().toString()
                                    Toast.makeText(this@MapActivity,
                                            feature.placeName(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(this@MapActivity,
                                    "No Result", Toast.LENGTH_SHORT).show()
                            name = "null"
                        }
                    }
                }

                override fun onFailure(call: Call<GeocodingResponse?>?, throwable: Throwable) {

                }
            })
        } catch (servicesException: ServicesException) {
            servicesException.printStackTrace()
        }
    }
    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        if(PermissionsManager.areLocationPermissionsGranted(this)){
            val customLocationComponentOption = LocationComponentOptions.builder(this).trackingGesturesManagement(true).accuracyColor(ContextCompat.getColor(this, R.color.menuText)).build()

            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(this, loadedMapStyle).locationComponentOptions(customLocationComponentOption).build()

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
        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if(granted){
            enableLocationComponent(mapboxMap.style!!)
        }else{
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show()
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




}