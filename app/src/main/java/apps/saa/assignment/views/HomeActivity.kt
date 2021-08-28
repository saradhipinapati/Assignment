package apps.saa.assignment.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import apps.saa.assignment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dynamicForm.setOnClickListener {
            onDynamicFormClicked()
        }

        binding.ordersList.setOnClickListener {
            onOrdersListClicked()
        }

    }

    private fun onOrdersListClicked() {
        val intent = Intent(this, OrdersListActivity::class.java)
        startActivity(intent)
    }

    private fun onDynamicFormClicked() {
        val intent = Intent(this, DynamicFormActivity::class.java)
        startActivity(intent)
    }
}