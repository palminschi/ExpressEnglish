package com.palmdev.expressenglish.data

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
            for (i in lessonsNumbers.indices){
                val lesson = Lesson(
                        level = A1,
                        number = lessonsNumbers[i],
                        title = context.getString(lessonsTitle[i]),
                        status = SharedPref.get(lessonsNumbers[i], STATUS_NOT_READ)!!,
                        forSearch = false
                    )
                mLessonsA1.add(lesson)
            }
            return mLessonsA1
        }

        fun setLessonView(lessonNumber: String, parent: LinearLayout, buttonPractice: Button) {
            when (lessonNumber) {
                LESSON_1_01 -> createLesson101(parent, buttonPractice)
                LESSON_1_02 -> createLesson102(parent, buttonPractice)
                LESSON_1_03 -> createLesson103(parent, buttonPractice)

                else -> return
            }
        }


        // Lesson 1 (A1) - 1.01
        private fun createLesson101(parent: LinearLayout, buttonPractice: Button) {
            addNewPhrase( parent, R.string.lesson101_phrase01, R.string.lesson101_translation01)
            addNewText(parent, R.string.lesson101_text01)
            addNewPhrase( parent, R.string.lesson101_phrase02, R.string.lesson101_translation02)
            addNewPhrase( parent, R.string.lesson101_phrase03, R.string.lesson101_translation03)
            addNewImage( parent, "tables/table_07_present_continuous.png")

            addNewPhrase( parent, R.string.lesson101_phrase01, R.string.lesson101_translation01)
            addNewText(parent, R.string.lesson101_text01)
            addNewPhrase( parent, R.string.lesson101_phrase02, R.string.lesson101_translation02)
            addNewPhrase( parent, R.string.lesson101_phrase03, R.string.lesson101_translation03)
            addNewImage( parent, "tables/table_09_much_many.png")


        }

        // Lesson 2 (A1) - 1.02
        private fun createLesson102(parent: LinearLayout, buttonPractice: Button) {

        }

        // Lesson 3 (A1) - 1.03
        private fun createLesson103(parent: LinearLayout, buttonPractice: Button) {

        }

        private fun addNewPhrase(
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

        private fun addNewText(parent: LinearLayout, textResource: Int) {
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

        private fun addNewImage(parent: LinearLayout, fileName: String) {
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