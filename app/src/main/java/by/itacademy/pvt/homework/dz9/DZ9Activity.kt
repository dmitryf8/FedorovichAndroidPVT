package by.itacademy.pvt.homework.dz9

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.itacademy.pvt.homework.R
import by.itacademy.pvt.myapplication.dz9.CarRepository
import by.itacademy.pvt.myapplication.dz9.CarRepositoryResultDZ9
import by.itacademy.pvt.myapplication.dz9.entity.Coordinate
import by.itacademy.pvt.myapplication.dz9.entity.CoordinateParameters
import by.itacademy.pvt.myapplication.dz9.entity.Poi
import by.itacademy.pvt.myapplication.dz9.provideCarRepository
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_dz9.*

class DZ9Activity : AppCompatActivity(), DZ9Adapter.ClickListenerDZ9, CarRepositoryResultDZ9, OnMapReadyCallback {

    private lateinit var gMap: GoogleMap
    private lateinit var mapView: MapView
    private val carRepository: CarRepository = provideCarRepository()
    private var listPoi: MutableList<Poi> = mutableListOf()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        mapView = findViewById(by.itacademy.pvt.homework.R.id.mapViewDZ9)
        var mapViewBundle: Bundle = Bundle()
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        carRepository.getCarByCoord(CoordinateParameters(Coordinate(0.0, 0.0), Coordinate(60.0, 60.0)), this)
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet_dz9))

        recyclerViewDZ9.setHasFixedSize(true)
        recyclerViewDZ9.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        recyclerViewDZ9.isNestedScrollingEnabled = false
        recyclerViewDZ9.adapter = DZ9Adapter(emptyList(), this)
    }

    override fun onItemClicked(item: Poi) {
        gMap.clear()
        listPoi.clear()
        listPoi.add(item)
        drawCarsOnMap()
        gMap.setMaxZoomPreference(14f)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onSucces(list: List<Poi>) {
        (recyclerViewDZ9.adapter as DZ9Adapter).showList(list)
        listPoi.addAll(list)
        if (gMap != null) drawCarsOnMap()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, resources.getString(by.itacademy.pvt.homework.R.string.loading_error), Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        gMap = googleMap!!
        if (listPoi.isNotEmpty()) drawCarsOnMap()
    }

    private fun drawCarsOnMap() {
        val builder = LatLngBounds.builder()

        listPoi.forEach {
            val coord = LatLng(it.coordinate?.latitude!!, it.coordinate.longitude)
            gMap.addMarker(
                MarkerOptions().position(coord).icon(
                    bitmapDescriptorFromVector(
                        this,
                        R.drawable.ic_location_on_black_24dp
                    )
                )
            )
            builder.include(coord)
        }

        val bounds = builder.build()

        gMap.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                resources.displayMetrics.widthPixels / 5
            )
        )
    }

    private fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor {

        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap =
            Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}