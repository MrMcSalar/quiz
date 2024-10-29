package edu.farmingdale.quiz

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Patterns

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val firstNameField: EditText = findViewById(R.id.first_name)
        val lastNameField: EditText = findViewById(R.id.last_name)
        val dobField: EditText = findViewById(R.id.dob)
        val emailField: EditText = findViewById(R.id.email)
        val passwordField: EditText = findViewById(R.id.password)
        val registerButton: Button = findViewById(R.id.register_btn)

        registerButton.setOnClickListener {
            val firstName = firstNameField.text.toString().trim()
            val lastName = lastNameField.text.toString().trim()
            val dob = dobField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Data validation
            if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            } else if (firstName.length < 3 || firstName.length > 30) {
                Toast.makeText(this, "First name must be between 3 and 30 characters", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show()
            } else {
                // Save user data in SharedPreferences
                val sharedPreferences = getSharedPreferences("QuizApp", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
