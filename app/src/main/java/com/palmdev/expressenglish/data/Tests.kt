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
        }
        return array
    }

    fun getExamContainers(context: Context, level: String): ArrayList<ExamContainer> {
        val array = ArrayList<ExamContainer>()
        val numberOfQuestionsA1 = 20
        val numberOfQuestionsA2 = 30
        val numberOfQuestionsB1 = 25
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
}