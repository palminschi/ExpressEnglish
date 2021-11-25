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
            )
        }

    }
}