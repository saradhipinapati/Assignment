package apps.saa.assignment.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import apps.saa.assignment.adapters.OrderItemsAdapter
import apps.saa.assignment.adapters.SetOnItemClickListener
import apps.saa.assignment.databinding.ActivityOrdersListBinding
import apps.saa.assignment.viewmodels.OrdersListViewModel
import apps.saa.networking.model.Order
import com.bumptech.glide.Glide

class OrdersListActivity : AppCompatActivity() {

    lateinit var binding: ActivityOrdersListBinding
    val viewModel: OrdersListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrdersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OrderItemsAdapter(object : SetOnItemClickListener{
            override fun onItemClicked(order: Order) {
                val intent = Intent(this@OrdersListActivity, MapsActivity::class.java)
                intent.putExtra("order", order)
                startActivity(intent)
            }
        })

        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@OrdersListActivity)
            this.adapter = adapter
        }

        viewModel.ordersInfo.observe(this, Observer {info ->
            adapter.submitList(info.orders)
            loadCompanyLogo(info.logoUrl)
        })

        viewModel.error.observe(this, Observer {
            showToast("Failed to connect")
        })
    }

    private fun loadCompanyLogo(logoUrl: String) {
        Glide.with(this).load(logoUrl).into(binding.companyLogo)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}