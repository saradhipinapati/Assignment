package apps.saa.assignment.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import apps.saa.assignment.R
import apps.saa.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val PREFERENCES_FILE = "credentials"
    val USER_NAME = "username"
    val PASSWORD = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCredentials()

        binding.btnLogin.setOnClickListener {
            saveCredentials()
            onLoginClicked()
        }
    }

    private fun initCredentials() {
        val sharedPreferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
        if(sharedPreferences.contains(USER_NAME)) {
            val userName = sharedPreferences.getString(USER_NAME, "username")
            val password = sharedPreferences.getString(PASSWORD, "password")
            binding.userName.setText(userName)
            binding.password.setText(password)
        }

    }

    private fun saveCredentials() {
        if(binding.checkBox.isChecked) {
            val sharedPreferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(USER_NAME, binding.userName.text.toString())
            editor.putString(PASSWORD, binding.password.text.toString())
            editor.apply()
            editor.commit()
        }
    }

    private fun onLoginClicked() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

        finish()
    }
}