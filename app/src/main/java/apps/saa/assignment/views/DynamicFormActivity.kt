package apps.saa.assignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import apps.saa.assignment.databinding.ActivityDynamicFormBinding
import apps.saa.assignment.helper.UIHelper
import apps.saa.assignment.viewmodels.DynamicFormViewModel
import apps.saa.networking.model.UIInfo
import com.bumptech.glide.Glide

class DynamicFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityDynamicFormBinding
    val viewModel: DynamicFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDynamicFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.uiInfo.observe(this, Observer {
            Glide.with(this).load(it.logoUrl).into(binding.companyLogo)
            renderView(it)
        })

        viewModel.error.observe(this, Observer {
            showToast("Failed to connect")
        })

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun renderView(uiInfo: UIInfo?) {
        val uiHelper = UIHelper(this)
        for(element in uiInfo!!.uidata) {
            val view = uiHelper.getUIElement(element)

            binding.parent.addView(view)
        }
    }
}