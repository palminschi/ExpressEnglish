package com.palmdev.expressenglish.models

data class ExamContainer (
    val id: String,
    val title: String,
    val numberOfQuestions: Int,
    val level: String,
    val premium: Boolean,
    val passed: Boolean
    )