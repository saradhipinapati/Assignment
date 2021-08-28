package apps.saa.assignment.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apps.saa.assignment.R
import apps.saa.assignment.databinding.ActivityMapsBinding
import apps.saa.networking.model.Order
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.let {
            order = intent.getSerializableExtra("order") as Order
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.navigate.setOnClickListener {
            onNavigationClicked()
        }
    }

    private fun onNavigationClicked() {
        val str = "google.navigation:q=" + order.shopLat + "," + order.shopLang
        val gmmIntentUri: Uri = Uri.parse(str)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val customer = LatLng(order.customerLat, order.customerLang)
        val shop = LatLng(order.shopLat, order.shopLang)
        mMap.addMarker(MarkerOptions().position(customer).title("Customer").icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker_icon_red))).showInfoWindow()
        mMap.addMarker(MarkerOptions().position(shop).title("Shop").icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker_icon))).showInfoWindow()
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shop, 17f))
    }
}