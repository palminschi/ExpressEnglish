package com.palmdev.expressenglish.data

import android.content.Context
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.models.ExamContainer
import com.palmdev.expressenglish.models.Exercise
import com.palmdev.expressenglish.models.ExerciseN2

object Tests {

    fun getTest(context: Context, testID: String): ArrayList<ExerciseN2> {
        val array = ArrayList<ExerciseN2>()
        when (testID) {
            QUICK_TEST_N1 -> {
                array.add(getExerciseN2(context, R.array.quick_test_n1_q1))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q2))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q3))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q4))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q5))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q6))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q7))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q8))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q9))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q10))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q11))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q12))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q13))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q14))
                array.add(getExerciseN2(context, R.array.quick_test_n1_q15))
            }
            QUICK_TEST_N2 -> {
                array.add(getExerciseN2(context, R.array.quick_test_n2_q1))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q2))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q3))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q4))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q5))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q6))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q7))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q8))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q9))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q10))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q11))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q12))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q13))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q14))
                array.add(getExerciseN2(context, R.array.quick_test_n2_q15))
            }
            QUICK_TEST_N3 -> {
                array.add(getExerciseN2(context, R.array.quick_test_n3_q1))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q2))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q3))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q4))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q5))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q6))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q7))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q8))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q9))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q10))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q11))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q12))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q13))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q14))
                array.add(getExerciseN2(context, R.array.quick_test_n3_q15))
            }
            EXAM_A1_N1 -> {
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q1))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q2))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q3))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q4))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q5))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q6))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q7))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q8))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q9))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q10))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q11))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q12))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q13))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q14))
                array.add(getExerciseN2(context, R.array.exam_a1_n1_q15))
            }
            EXAM_A1_N2 -> {
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q1))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q2))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q3))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q4))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q5))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q6))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q7))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q8))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q9))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q10))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q11))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q12))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q13))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q14))
                array.add(getExerciseN2(context, R.array.exam_a1_n2_q15))
            }
            EXAM_A1_N3 -> {
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q1))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q2))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q3))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q4))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q5))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q6))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q7))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q8))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q9))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q10))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q11))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q12))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q13))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q14))
                array.add(getExerciseN2(context, R.array.exam_a1_n3_q15))
            }
            EXAM_A2_N1 -> {
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q1))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q2))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q3))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q4))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q5))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q6))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q7))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q8))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q9))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q10))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q11))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q12))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q13))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q14))
                array.add(getExerciseN2(context, R.array.exam_a2_n1_q15))
            }
            EXAM_A2_N2 -> {
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q1))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q2))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q3))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q4))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q5))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q6))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q7))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q8))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q9))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q10))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q11))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q12))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q13))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q14))
                array.add(getExerciseN2(context, R.array.exam_a2_n2_q15))
            }
            EXAM_A2_N3 -> {
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q1))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q2))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q3))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q4))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q5))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q6))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q7))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q8))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q9))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q10))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q11))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q12))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q13))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q14))
                array.add(getExerciseN2(context, R.array.exam_a2_n3_q15))
            }
            EXAM_B1_N1 -> {
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q1))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q2))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q3))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q4))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q5))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q6))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q7))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q8))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q9))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q10))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q11))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q12))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q13))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q14))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q15))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q16))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q17))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q18))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q19))
                array.add(getExerciseN2(context, R.array.exam_b1_n1_q20))
            }
            EXAM_B1_N2 -> {
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q1))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q2))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q3))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q4))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q5))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q6))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q7))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q8))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q9))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q10))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q11))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q12))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q13))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q14))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q15))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q16))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q17))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q18))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q19))
                array.add(getExerciseN2(context, R.array.exam_b1_n2_q20))
            }
            EXAM_B1_N3 -> {
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q1))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q2))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q3))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q4))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q5))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q6))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q7))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q8))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q9))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q10))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q11))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q12))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q13))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q14))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q15))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q16))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q17))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q18))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q19))
                array.add(getExerciseN2(context, R.array.exam_b1_n3_q20))
            }
            EXAM_B2_N1 -> {
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q1))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q2))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q3))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q4))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q5))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q6))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q7))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q8))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q9))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q10))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q11))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q12))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q13))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q14))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q15))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q16))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q17))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q18))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q19))
                array.add(getExerciseN2(context, R.array.exam_b2_n1_q20))
            }
            EXAM_B2_N2 -> {
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q1))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q2))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q3))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q4))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q5))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q6))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q7))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q8))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q9))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q10))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q11))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q12))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q13))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q14))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q15))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q16))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q17))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q18))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q19))
                array.add(getExerciseN2(context, R.array.exam_b2_n2_q20))
            }
            EXAM_B2_N3 -> {
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q1))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q2))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q3))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q4))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q5))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q6))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q7))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q8))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q9))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q10))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q11))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q12))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q13))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q14))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q15))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q16))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q17))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q18))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q19))
                array.add(getExerciseN2(context, R.array.exam_b2_n3_q20))
            }

        }
        return array
    }

    fun getExamContainers(context: Context, level: String): ArrayList<ExamContainer> {
        val array = ArrayList<ExamContainer>()
        val numberOfQuestionsA1 = 15
        val numberOfQuestionsA2 = 15
        val numberOfQuestionsB1 = 20
        val numberOfQuestionsB2 = 20
        when (level) {
            // A1
            Lessons.A1 -> {
                array.add(
                    ExamContainer(
                        id = EXAM_A1_N1,
                        title = context.getString(R.string.examN1),
                        numberOfQuestions = numberOfQuestionsA1,
                        level = context.getString(R.string.A1Lvl),
                        premium = false,
                        passed = SharedPref.get(EXAM_A1_N1, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_A1_N2,
                        title = context.getString(R.string.examN2),
                        numberOfQuestions = numberOfQuestionsA1,
                        level = context.getString(R.string.A1Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_A1_N2, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_A1_N3,
                        title = context.getString(R.string.examN3),
                        numberOfQuestions = numberOfQuestionsA1,
                        level = context.getString(R.string.A1Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_A1_N3, false)
                    )
                )
            }
            // A2
            Lessons.A2 -> {
                array.add(
                    ExamContainer(
                        id = EXAM_A2_N1,
                        title = context.getString(R.string.examN1),
                        numberOfQuestions = numberOfQuestionsA2,
                        level = context.getString(R.string.A2Lvl),
                        premium = false,
                        passed = SharedPref.get(EXAM_A2_N1, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_A2_N2,
                        title = context.getString(R.string.examN2),
                        numberOfQuestions = numberOfQuestionsA2,
                        level = context.getString(R.string.A2Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_A2_N2, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_A2_N3,
                        title = context.getString(R.string.examN3),
                        numberOfQuestions = numberOfQuestionsA2,
                        level = context.getString(R.string.A2Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_A2_N3, false)
                    )
                )
            }
            // B1
            Lessons.B1 -> {
                array.add(
                    ExamContainer(
                        id = EXAM_B1_N1,
                        title = context.getString(R.string.examN1),
                        numberOfQuestions = numberOfQuestionsB1,
                        level = context.getString(R.string.B1Lvl),
                        premium = false,
                        passed = SharedPref.get(EXAM_B1_N1, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_B1_N2,
                        title = context.getString(R.string.examN2),
                        numberOfQuestions = numberOfQuestionsB1,
                        level = context.getString(R.string.B1Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_B1_N2, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_B1_N3,
                        title = context.getString(R.string.examN3),
                        numberOfQuestions = numberOfQuestionsB1,
                        level = context.getString(R.string.B1Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_B1_N3, false)
                    )
                )
            }
            // B2
            Lessons.B2 -> {
                array.add(
                    ExamContainer(
                        id = EXAM_B2_N1,
                        title = context.getString(R.string.examN1),
                        numberOfQuestions = numberOfQuestionsB2,
                        level = context.getString(R.string.B2Lvl),
                        premium = false,
                        passed = SharedPref.get(EXAM_B2_N1, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_B2_N2,
                        title = context.getString(R.string.examN2),
                        numberOfQuestions = numberOfQuestionsB2,
                        level = context.getString(R.string.B2Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_B2_N2, false)
                    )
                )
                array.add(
                    ExamContainer(
                        id = EXAM_B2_N3,
                        title = context.getString(R.string.examN3),
                        numberOfQuestions = numberOfQuestionsB2,
                        level = context.getString(R.string.B2Lvl),
                        premium = true,
                        passed = SharedPref.get(EXAM_B2_N3, false)
                    )
                )
            }
        }

        return array
    }


    private fun getExerciseN2(context: Context, id: Int): ExerciseN2 {
        return ExerciseN2(
            context.resources.getStringArray(id)[0],
            context.resources.getStringArray(id)[1],
            context.resources.getStringArray(id)[2],
            context.resources.getStringArray(id)[3],
            context.resources.getStringArray(id)[4].toInt(),
            context.resources.getStringArray(id)[5]
        )
    }

    const val QUICK_TEST_N1 = "QUICK_TEST_N1"
    const val QUICK_TEST_N2 = "QUICK_TEST_N2"
    const val QUICK_TEST_N3 = "QUICK_TEST_N3"
    const val EXAM_A1_N1 = "EXAM_A1_N1"
    const val EXAM_A1_N2 = "EXAM_A1_N2"
    const val EXAM_A1_N3 = "EXAM_A1_N3"
    const val EXAM_A2_N1 = "EXAM_A2_N1"
    const val EXAM_A2_N2 = "EXAM_A2_N2"
    const val EXAM_A2_N3 = "EXAM_A2_N3"
    const val EXAM_B1_N1 = "EXAM_B1_N1"
    const val EXAM_B1_N2 = "EXAM_B1_N2"
    const val EXAM_B1_N3 = "EXAM_B1_N3"
    const val EXAM_B2_N1 = "EXAM_B2_N1"
    const val EXAM_B2_N2 = "EXAM_B2_N2"
    const val EXAM_B2_N3 = "EXAM_B2_N3"
    const val EXAM_OR_QUICK_TEST = "EXAM_OR_QUICK_TEST"
    const val EXAM = "EXAM"
    const val QUICK_TEST = "QUICK_TEST"
    const val LEVEL = "LEVEL"
}