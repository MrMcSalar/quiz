package edu.farmingdale.quiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val questions = listOf(
        QuizQuestion("What is 5 + 20?", listOf("10", "20", "25"), listOf("25")),
        QuizQuestion("What course is this for?", listOf("CSC325", "BCS471", "BCS470"), listOf("BCS471")),
        QuizQuestion("Who made this app", listOf("Salar", "Joe", "Amongus"), listOf("Salar")),
        QuizQuestion("Which of the following are languages?", listOf("English", "French", "Spanish"), listOf("English, French, Spanish")),
        QuizQuestion("Is the 25 an even number?", listOf("Yes", "No"), listOf("No"))
    )

    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questionTextView: TextView
    private lateinit var answerCheckbox1: CheckBox
    private lateinit var answerCheckbox2: CheckBox
    private lateinit var answerCheckbox3: CheckBox
    private lateinit var submitAnswerButton: Button
    private lateinit var timerTextView: TextView
    private val timerDuration: Long = 30000 // 30 seconds
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)

        questionTextView = findViewById(R.id.question_text)
        answerCheckbox1 = findViewById(R.id.answer_checkbox1)
        answerCheckbox2 = findViewById(R.id.answer_checkbox2)
        answerCheckbox3 = findViewById(R.id.answer_checkbox3)
        submitAnswerButton = findViewById(R.id.submit_answer_btn)
        timerTextView = findViewById(R.id.timer_text)

        loadQuestion()
        submitAnswerButton.setOnClickListener { confirmAnswer() }
        startTimer()
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            questionTextView.text = question.question

            // Reset checkbox states
            answerCheckbox1.isChecked = false
            answerCheckbox2.isChecked = false
            answerCheckbox3.isChecked = false

            // Display options based on the number available
            answerCheckbox1.text = if (question.options.size > 0) question.options[0] else ""
            answerCheckbox2.text = if (question.options.size > 1) question.options[1] else ""
            answerCheckbox3.text = if (question.options.size > 2) question.options[2] else ""

            // Hide checkboxes if not enough options
            answerCheckbox1.visibility = if (question.options.size > 0) View.VISIBLE else View.GONE
            answerCheckbox2.visibility = if (question.options.size > 1) View.VISIBLE else View.GONE
            answerCheckbox3.visibility = if (question.options.size > 2) View.VISIBLE else View.GONE
        } else {
            finishQuiz()
        }
    }

    private fun confirmAnswer() {
        val selectedAnswers = mutableListOf<String>()
        if (answerCheckbox1.isChecked) selectedAnswers.add(answerCheckbox1.text.toString())
        if (answerCheckbox2.isChecked) selectedAnswers.add(answerCheckbox2.text.toString())
        if (answerCheckbox3.isChecked) selectedAnswers.add(answerCheckbox3.text.toString())

        val correctAnswers = questions[currentQuestionIndex].correctAnswer
        if (selectedAnswers.toSet() == correctAnswers.toSet()) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong! Correct answer: ${correctAnswers.joinToString(", ")}", Toast.LENGTH_SHORT).show()
        }

        currentQuestionIndex++
        loadQuestion()
    }

    private fun finishQuiz() {
        Log.d("QuizActivity", "Score: $score, Total Questions: ${questions.size}")

        val scoreIntent = Intent(this, ScoreActivity::class.java)
        scoreIntent.putExtra("SCORE", score)
        scoreIntent.putExtra("TOTAL_QUESTIONS", questions.size)

        startActivity(scoreIntent)
        finish() // Call finish to close the QuizActivity
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "Time left: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                finishQuiz()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}
