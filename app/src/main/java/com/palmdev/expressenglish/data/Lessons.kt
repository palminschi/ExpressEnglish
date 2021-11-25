package com.palmdev.expressenglish.data

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.models.Lesson
import com.palmdev.expressenglish.utils.MyTextToSpeech

class Lessons {
    companion object {

        private val mLessonsA1 = ArrayList<Lesson>()
        private val mLessonsA2 = ArrayList<Lesson>()
        private val mLessonsB1 = ArrayList<Lesson>()

        fun getLessonsA1(context: Context): ArrayList<Lesson>{
            mLessonsA1.clear()
            val lessonsNumbers = listOf(
                LESSON_1_01, LESSON_1_02, LESSON_1_03, LESSON_1_04, LESSON_1_05, LESSON_1_06,
                LESSON_1_07, LESSON_1_08, LESSON_1_09, LESSON_1_10, LESSON_1_11, LESSON_1_12,
                LESSON_1_13, LESSON_1_14, LESSON_1_15, LESSON_1_16, LESSON_1_17, LESSON_1_18,
                LESSON_1_19
            )
            val lessonsTitle = listOf(
                R.string.lesson101_title, R.string.lesson102_title, R.string.lesson103_title,
                R.string.lesson104_title, R.string.lesson105_title, R.string.lesson106_title,
                R.string.lesson107_title, R.string.lesson108_title, R.string.lesson109_title,
                R.string.lesson110_title, R.string.lesson111_title, R.string.lesson112_title,
                R.string.lesson113_title, R.string.lesson114_title, R.string.lesson115_title,
                R.string.lesson116_title, R.string.lesson117_title, R.string.lesson118_title,
                R.string.lesson119_title
            )
            val practiceList = listOf(
                false, // lesson 1.01
                true, // lesson 1.02
                true, // lesson 1.03
                false, // lesson 1.04
                true, // lesson 1.05
                false, // lesson 1.06
                true, // lesson 1.07
                false, // lesson 1.08
                true, // lesson 1.09
                true, // lesson 1.10
                false, // lesson 1.11
                false, // lesson 1.12
                false, // lesson 1.13
                true, // lesson 1.14
                true, // lesson 1.15
                false, // lesson 1.16
                true, // lesson 1.17
                false, // lesson 1.18
                true, // lesson 1.19
            )
            for (i in lessonsNumbers.indices){
                val lesson = Lesson(
                        level = A1,
                        number = lessonsNumbers[i],
                        title = context.getString(lessonsTitle[i]),
                        status = SharedPref.get(lessonsNumbers[i], STATUS_NOT_READ)!!,
                        practice = practiceList[i],
                        forSearch = false
                    )
                mLessonsA1.add(lesson)
            }
            return mLessonsA1
        }

        fun setLessonView(lessonNumber: String, parent: LinearLayout) {
            when (lessonNumber) {
                LESSON_1_01 -> createLesson101(parent)
                LESSON_1_02 -> createLesson102(parent)
                LESSON_1_03 -> createLesson103(parent)

                else -> return
            }
        }


        // Lesson 1 (A1) - 1.01
        private fun createLesson101(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson101_text01)
            addCenterText(parent, R.string.example)
            addPhrase(parent,R.string.lesson101_phrase01, R.string.lesson101_translation01)
            addPhrase(parent,R.string.lesson101_phrase02, R.string.lesson101_translation02)
            addSimpleText(parent, R.string.lesson101_text02)
            addCenterText(parent, R.string.example)
            addCenterText(parent, R.string.lesson101_text03)
            addSimpleText(parent, R.string.lesson101_text04)
            // no practice
        }

        // Lesson 2 (A1) - 1.02
        private fun createLesson102(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson102_phrase01, R.string.lesson102_translation01)
            addSimpleText(parent, R.string.lesson102_text01)
            addCenterText(parent, R.string.lesson102_text02)
            addSimpleText(parent, R.string.lesson102_text03)
            addCenterText(parent, R.string.lesson102_text04)
            addSimpleText(parent, R.string.lesson102_text05)
            addCenterText(parent, R.string.lesson102_text06)
            addSimpleText(parent, R.string.lesson102_text07)
            addCenterText(parent, R.string.lesson102_text08)
            addSimpleText(parent, R.string.lesson102_text09)
            addCenterText(parent, R.string.lesson102_text10)
        }

        // Lesson 3 (A1) - 1.03
        private fun createLesson103(parent: LinearLayout) {

        }

        private fun addPhrase(
            parent: LinearLayout, phraseResource: Int, translationResource: Int
        ) {
            val stub = ViewStub(parent.context)
            parent.addView(stub)
            stub.layoutResource = R.layout.include_phrase

            stub.setOnInflateListener { _, inflated ->
                // Set Content
                val phrase = inflated.context.getText(phraseResource)
                val translation = inflated.context.getText(translationResource)
                inflated.findViewById<TextView>(R.id.phrase).text = phrase
                inflated.findViewById<ImageView>(R.id.btnSound).setOnClickListener {
                    MyTextToSpeech.play(phrase, parent.context)
                }
                if (translation != "") {
                    inflated.findViewById<TextView>(R.id.translation).text = translation
                } else {
                    inflated.findViewById<TextView>(R.id.translation).visibility = View.GONE
                }
            }
            stub.inflate()
        }

        private fun addSimpleText(parent: LinearLayout, textResource: Int) {
            val stub = ViewStub(parent.context)
            parent.addView(stub)
            stub.layoutResource = R.layout.include_text
            stub.setOnInflateListener { _, inflated ->
                // Set Content
                val text = inflated.context.getText(textResource)
                inflated.findViewById<TextView>(R.id.textView).text = text
            }
            stub.inflate()
        }

        private fun addCenterText(parent: LinearLayout, textResource: Int) {
            val stub = ViewStub(parent.context)
            parent.addView(stub)
            stub.layoutResource = R.layout.include_text
            stub.setOnInflateListener { _, inflated ->
                // Set Content
                val text = inflated.context.getText(textResource)
                val textView =  inflated.findViewById<TextView>(R.id.textView)
                textView.text = text
                textView.gravity = Gravity.CENTER
            }
            stub.inflate()
        }

        private fun addImage(parent: LinearLayout, fileName: String) {
            val stub = ViewStub(parent.context)
            parent.addView(stub)
            stub.layoutResource = R.layout.include_image
            stub.setOnInflateListener { _, inflated ->
                // Set Content
                val imageView = inflated.findViewById<ImageView>(R.id.imageView)
                val ims = parent.context.assets.open(fileName)
                val drawable = Drawable.createFromStream(ims, null)
                imageView.setImageDrawable(drawable)
            }
            stub.inflate()
        }

        // Lessons Levels
        const val A1 = "A1"
        const val A2 = "A2"
        const val B1 = "A1"
        // Lessons Status
        const val STATUS_LEARNED = "STATUS_LEARNED"
        const val STATUS_READ = "STATUS_READ"
        const val STATUS_NOT_READ = "STATUS_NOT_READ"
        // Lessons Numbers
        //A1 :
        const val LESSON_1_01 = "1.01"
        const val LESSON_1_02 = "1.02"
        const val LESSON_1_03 = "1.03"
        const val LESSON_1_04 = "1.04"
        const val LESSON_1_05 = "1.05"
        const val LESSON_1_06 = "1.06"
        const val LESSON_1_07 = "1.07"
        const val LESSON_1_08 = "1.08"
        const val LESSON_1_09 = "1.09"
        const val LESSON_1_10 = "1.10"
        const val LESSON_1_11 = "1.11"
        const val LESSON_1_12 = "1.12"
        const val LESSON_1_13 = "1.13"
        const val LESSON_1_14 = "1.14"
        const val LESSON_1_15 = "1.15"
        const val LESSON_1_16 = "1.16"
        const val LESSON_1_17 = "1.17"
        const val LESSON_1_18 = "1.18"
        const val LESSON_1_19 = "1.19"
        //A2 :
    }
}