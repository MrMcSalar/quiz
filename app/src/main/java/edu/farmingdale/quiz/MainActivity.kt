package edu.farmingdale.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Ensure this matches your main activity layout file name

        val loginButton: Button = findViewById(R.id.login_button) // Make sure this ID exists in your layout
        val registerButton: Button = findViewById(R.id.register_button) // Make sure this ID exists in your layout

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}