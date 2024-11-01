package edu.farmingdale.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RulesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rules)

        val startQuizButton: Button = findViewById(R.id.start_quiz_button)
        startQuizButton.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
            finish()
        }
    }
}
