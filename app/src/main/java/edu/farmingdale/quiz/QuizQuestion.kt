package edu.farmingdale.quiz

data class QuizQuestion(
    val question: String,        // The quiz question
    val options: List<String>,   // List of answer options
    val correctAnswer: List<String> // List of correct answers
)
