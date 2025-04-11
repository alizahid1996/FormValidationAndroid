package com.example.formvalidationdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formvalidation.ValidationResult
import com.example.formvalidation.ValidationRules
import com.example.formvalidation.Validator
import com.example.formvalidationdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        validations()
    }

    private fun validations() {
        with(binding) {
            btnCheck.setOnClickListener {
                val email = email.text.toString()
                val password = password.text.toString()
                val conPassword = conPassword.text.toString()

                val validator = Validator()
                    .addRule { ValidationRules.isNotEmpty(email) }
                    .addRule { ValidationRules.isEmail(email) }
                    .addRule { ValidationRules.isNotEmpty(password) }
                    .addRule { ValidationRules.maxLength(password, 10) }
                    .addRule { ValidationRules.minLength(password, 8) }
                    .addRule { ValidationRules.matches(password, conPassword) }
                    .addRule { ValidationRules.isChecked(checkBox) }

                when (val result = validator.validate()) {
                    is ValidationResult.Error -> {
                        showToast("Error: ${result.message}")
                    }

                    is ValidationResult.Success -> {
                        showToast("Success")
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}