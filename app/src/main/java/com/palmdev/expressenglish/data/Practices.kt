package com.palmdev.expressenglish.data

import android.content.Context
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.models.Exercise

class Practices {

    companion object{

        fun getExercisesArray(context: Context, lessonID: String): ArrayList<Exercise> {
            val array = ArrayList<Exercise>()
            when(lessonID){
                // Lesson A1 02
                Lessons.LESSON_1_02 -> {
                    array.add(getExercise(context, R.array.p102_question1))
                    array.add(getExercise(context, R.array.p102_question2))
                    array.add(getExercise(context, R.array.p102_question3))
                    array.add(getExercise(context, R.array.p102_question4))
                    array.add(getExercise(context, R.array.p102_question5))
                    array.add(getExercise(context, R.array.p102_question6))
                    array.add(getExercise(context, R.array.p102_question7))
                    array.add(getExercise(context, R.array.p102_question8))
                    array.add(getExercise(context, R.array.p102_question9))
                    array.add(getExercise(context, R.array.p102_question10))
                }
                // Lesson A1 03
                Lessons.LESSON_1_03 -> {
                    array.add(getExercise(context, R.array.p103_question1))
                    array.add(getExercise(context, R.array.p103_question2))
                    array.add(getExercise(context, R.array.p103_question3))
                    array.add(getExercise(context, R.array.p103_question4))
                    array.add(getExercise(context, R.array.p103_question5))
                    array.add(getExercise(context, R.array.p103_question6))
                    array.add(getExercise(context, R.array.p103_question7))
                    array.add(getExercise(context, R.array.p103_question8))
                    array.add(getExercise(context, R.array.p103_question9))
                    array.add(getExercise(context, R.array.p103_question10))
                }
                // Lesson A1 05
                Lessons.LESSON_1_05 -> {
                    array.add(getExercise(context, R.array.p105_question1))
                    array.add(getExercise(context, R.array.p105_question2))
                    array.add(getExercise(context, R.array.p105_question3))
                    array.add(getExercise(context, R.array.p105_question4))
                    array.add(getExercise(context, R.array.p105_question5))
                }
                // Lesson A1 07
                Lessons.LESSON_1_07 -> {
                    array.add(getExercise(context, R.array.p107_question1))
                    array.add(getExercise(context, R.array.p107_question2))
                    array.add(getExercise(context, R.array.p107_question3))
                    array.add(getExercise(context, R.array.p107_question4))
                    array.add(getExercise(context, R.array.p107_question5))
                    array.add(getExercise(context, R.array.p107_question6))
                    array.add(getExercise(context, R.array.p107_question7))
                    array.add(getExercise(context, R.array.p107_question8))
                    array.add(getExercise(context, R.array.p107_question9))
                    array.add(getExercise(context, R.array.p107_question10))
                }
                // Lesson A1 08
                Lessons.LESSON_1_08 -> {
                    array.add(getExercise(context, R.array.p108_question1))
                    array.add(getExercise(context, R.array.p108_question2))
                    array.add(getExercise(context, R.array.p108_question3))
                    array.add(getExercise(context, R.array.p108_question4))
                    array.add(getExercise(context, R.array.p108_question5))
                    array.add(getExercise(context, R.array.p108_question6))
                    array.add(getExercise(context, R.array.p108_question7))
                    array.add(getExercise(context, R.array.p108_question8))
                    array.add(getExercise(context, R.array.p108_question9))
                    array.add(getExercise(context, R.array.p108_question10))
                }
                // Lesson A1 11
                Lessons.LESSON_1_11 -> {
                    array.add(getExercise(context, R.array.p111_question1))
                    array.add(getExercise(context, R.array.p111_question2))
                    array.add(getExercise(context, R.array.p111_question3))
                    array.add(getExercise(context, R.array.p111_question4))
                    array.add(getExercise(context, R.array.p111_question5))
                    array.add(getExercise(context, R.array.p111_question6))
                    array.add(getExercise(context, R.array.p111_question7))
                    array.add(getExercise(context, R.array.p111_question8))
                    array.add(getExercise(context, R.array.p111_question9))
                    array.add(getExercise(context, R.array.p111_question10))
                }
                // Lesson A1 17
                Lessons.LESSON_1_17 -> {
                    array.add(getExercise(context, R.array.p117_question1))
                    array.add(getExercise(context, R.array.p117_question2))
                    array.add(getExercise(context, R.array.p117_question3))
                    array.add(getExercise(context, R.array.p117_question4))
                    array.add(getExercise(context, R.array.p117_question5))
                    array.add(getExercise(context, R.array.p117_question6))
                    array.add(getExercise(context, R.array.p117_question7))
                    array.add(getExercise(context, R.array.p117_question8))
                    array.add(getExercise(context, R.array.p117_question9))
                    array.add(getExercise(context, R.array.p117_question10))
                }
                // Lesson A1 18
                Lessons.LESSON_1_18 -> {
                    array.add(getExercise(context, R.array.p118_question1))
                    array.add(getExercise(context, R.array.p118_question2))
                    array.add(getExercise(context, R.array.p118_question3))
                    array.add(getExercise(context, R.array.p118_question4))
                    array.add(getExercise(context, R.array.p118_question5))
                    array.add(getExercise(context, R.array.p118_question6))
                    array.add(getExercise(context, R.array.p118_question7))
                    array.add(getExercise(context, R.array.p118_question8))
                    array.add(getExercise(context, R.array.p118_question9))
                    array.add(getExercise(context, R.array.p118_question10))
                }
                // Lesson A1 19
                Lessons.LESSON_1_19 -> {
                    array.add(getExercise(context, R.array.p119_question1))
                    array.add(getExercise(context, R.array.p119_question2))
                    array.add(getExercise(context, R.array.p119_question3))
                    array.add(getExercise(context, R.array.p119_question4))
                    array.add(getExercise(context, R.array.p119_question5))
                    array.add(getExercise(context, R.array.p119_question6))
                    array.add(getExercise(context, R.array.p119_question7))
                    array.add(getExercise(context, R.array.p119_question8))
                    array.add(getExercise(context, R.array.p119_question9))
                    array.add(getExercise(context, R.array.p119_question10))
                }
                // A2
                // Lesson A2 01
                Lessons.LESSON_2_01 -> {
                    array.add(getExercise(context, R.array.p201_question1))
                    array.add(getExercise(context, R.array.p201_question2))
                    array.add(getExercise(context, R.array.p201_question3))
                    array.add(getExercise(context, R.array.p201_question4))
                    array.add(getExercise(context, R.array.p201_question5))
                }
                // Lesson A2 03
                Lessons.LESSON_2_03 -> {
                    array.add(getExercise(context, R.array.p203_question1))
                    array.add(getExercise(context, R.array.p203_question2))
                    array.add(getExercise(context, R.array.p203_question3))
                    array.add(getExercise(context, R.array.p203_question4))
                    array.add(getExercise(context, R.array.p203_question5))
                    array.add(getExercise(context, R.array.p203_question6))
                    array.add(getExercise(context, R.array.p203_question7))
                    array.add(getExercise(context, R.array.p203_question8))
                    array.add(getExercise(context, R.array.p203_question9))
                    array.add(getExercise(context, R.array.p203_question10))
                }
                // Lesson A2 05
                Lessons.LESSON_2_05 -> {
                    array.add(getExercise(context, R.array.p205_question1))
                    array.add(getExercise(context, R.array.p205_question2))
                    array.add(getExercise(context, R.array.p205_question3))
                    array.add(getExercise(context, R.array.p205_question4))
                    array.add(getExercise(context, R.array.p205_question5))
                }
                // Lesson A2 06
                Lessons.LESSON_2_06 -> {
                    array.add(getExercise(context, R.array.p206_question1))
                    array.add(getExercise(context, R.array.p206_question2))
                    array.add(getExercise(context, R.array.p206_question3))
                    array.add(getExercise(context, R.array.p206_question4))
                    array.add(getExercise(context, R.array.p206_question5))
                }
                // Lesson A2 08
                Lessons.LESSON_2_08 -> {
                    array.add(getExercise(context, R.array.p208_question1))
                    array.add(getExercise(context, R.array.p208_question2))
                    array.add(getExercise(context, R.array.p208_question3))
                    array.add(getExercise(context, R.array.p208_question4))
                    array.add(getExercise(context, R.array.p208_question5))
                }
                // Lesson A2 09
                Lessons.LESSON_2_09 -> {
                    array.add(getExercise(context, R.array.p209_question1))
                    array.add(getExercise(context, R.array.p209_question2))
                    array.add(getExercise(context, R.array.p209_question3))
                    array.add(getExercise(context, R.array.p209_question4))
                    array.add(getExercise(context, R.array.p209_question5))
                }
                // Lesson A2 16
                Lessons.LESSON_2_16 -> {
                    array.add(getExercise(context, R.array.p216_question1))
                    array.add(getExercise(context, R.array.p216_question2))
                    array.add(getExercise(context, R.array.p216_question3))
                    array.add(getExercise(context, R.array.p216_question4))
                    array.add(getExercise(context, R.array.p216_question5))
                }
                // Lesson A2 17
                Lessons.LESSON_2_17 -> {
                    array.add(getExercise(context, R.array.p217_question1))
                    array.add(getExercise(context, R.array.p217_question2))
                    array.add(getExercise(context, R.array.p217_question3))
                    array.add(getExercise(context, R.array.p217_question4))
                    array.add(getExercise(context, R.array.p217_question5))
                    array.add(getExercise(context, R.array.p217_question6))
                }
                // Lesson B1 02
                Lessons.LESSON_3_02 -> {
                    array.add(getExercise(context, R.array.p302_question1))
                    array.add(getExercise(context, R.array.p302_question2))
                    array.add(getExercise(context, R.array.p302_question3))
                    array.add(getExercise(context, R.array.p302_question4))
                    array.add(getExercise(context, R.array.p302_question5))
                }
                // Lesson B1 05
                Lessons.LESSON_3_05 -> {
                    array.add(getExercise(context, R.array.p305_question1))
                    array.add(getExercise(context, R.array.p305_question2))
                    array.add(getExercise(context, R.array.p305_question3))
                    array.add(getExercise(context, R.array.p305_question4))
                    array.add(getExercise(context, R.array.p305_question5))
                }
                // Lesson B1 06
                Lessons.LESSON_3_06 -> {
                    array.add(getExercise(context, R.array.p306_question1))
                    array.add(getExercise(context, R.array.p306_question2))
                    array.add(getExercise(context, R.array.p306_question3))
                    array.add(getExercise(context, R.array.p306_question4))
                    array.add(getExercise(context, R.array.p306_question5))
                }
            }

            return array
        }


        private fun getExercise(context: Context, id: Int): Exercise{
            return Exercise(
                context.resources.getStringArray(id)[0],
                context.resources.getStringArray(id)[1],
                context.resources.getStringArray(id)[2],
                context.resources.getStringArray(id)[3],
                context.resources.getStringArray(id)[4].toInt(),
                context.resources.getStringArray(id)[5]
            )
        }

    }
}