package edu.farmingdale.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var totalQuestionsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Initialize views
        scoreTextView = findViewById(R.id.score_text_view)
        totalQuestionsTextView = findViewById(R.id.total_questions_text_view)

        // Get data from Intent
        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)

        // Set the text views with the score and total questions
        scoreTextView.text = "Score: $score"
        totalQuestionsTextView.text = "Total Questions: $totalQuestions"
    }
    fun restartQuiz(view: View) {
        // Create an Intent to restart the QuizActivity
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
        finish() // Finish the ScoreActivity
    }

}
