package edu.farmingdale.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val emailField: EditText = findViewById(R.id.email)
        val passwordField: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.login_btn)

        loginButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("QuizApp", Context.MODE_PRIVATE)
            val storedEmail = sharedPreferences.getString("email", null)
            val storedPassword = sharedPreferences.getString("password", null)

            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            if (email == storedEmail && password == storedPassword) {
                startActivity(Intent(this, RulesActivity::class.java))
                finish() // Close LoginActivity
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
