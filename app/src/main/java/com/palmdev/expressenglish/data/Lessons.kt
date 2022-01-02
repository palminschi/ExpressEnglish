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
import java.util.*
import kotlin.collections.ArrayList

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
                true, // lesson 1.08
                false, // lesson 1.09
                false, // lesson 1.10
                true, // lesson 1.11
                false, // lesson 1.12
                false, // lesson 1.13
                false, // lesson 1.14
                false, // lesson 1.15
                false, // lesson 1.16
                true, // lesson 1.17
                true, // lesson 1.18
                false, // lesson 1.19
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

        fun getLessonsA2(context: Context): ArrayList<Lesson>{
            mLessonsA2.clear()
            val lessonsNumbers = listOf(
                LESSON_2_01, LESSON_2_02, LESSON_2_03, LESSON_2_04, LESSON_2_05, LESSON_2_06,
                LESSON_2_07, LESSON_2_08, LESSON_2_09, LESSON_2_10, LESSON_2_11, LESSON_2_12,
                LESSON_2_13, LESSON_2_14, LESSON_2_15, LESSON_2_16, LESSON_2_17,
            )
            val lessonsTitle = listOf(
                R.string.lesson201_title, R.string.lesson202_title, R.string.lesson203_title,
                R.string.lesson204_title, R.string.lesson205_title, R.string.lesson206_title,
                R.string.lesson207_title, R.string.lesson208_title, R.string.lesson209_title,
                R.string.lesson210_title, R.string.lesson211_title, R.string.lesson212_title,
                R.string.lesson213_title, R.string.lesson214_title, R.string.lesson215_title,
                R.string.lesson216_title, R.string.lesson217_title
            )
            val practiceList = listOf(
                true, // lesson 2.01
                false, // lesson 2.02
                true, // lesson 2.03
                false, // lesson 2.04
                true, // lesson 2.05
                true, // lesson 2.06
                false, // lesson 2.07
                true, // lesson 2.08
                true, // lesson 2.09
                false, // lesson 2.10
                false, // lesson 2.11
                false, // lesson 2.12
                false, // lesson 2.13
                false, // lesson 2.14
                false, // lesson 2.15
                true, // lesson 2.16
                true, // lesson 2.17
            )
            for (i in lessonsNumbers.indices){
                val lesson = Lesson(
                    level = A2,
                    number = lessonsNumbers[i],
                    title = context.getString(lessonsTitle[i]),
                    status = SharedPref.get(lessonsNumbers[i], STATUS_NOT_READ)!!,
                    practice = practiceList[i],
                    forSearch = false
                )
                mLessonsA2.add(lesson)
            }
            return mLessonsA2
        }

        fun getLessonsB1(context: Context): ArrayList<Lesson>{
            mLessonsB1.clear()
            val lessonsNumbers = listOf(
                LESSON_3_01, LESSON_3_02, LESSON_3_03, LESSON_3_04, LESSON_3_05, LESSON_3_06,
                LESSON_3_07, LESSON_3_08, LESSON_3_09, LESSON_3_10, LESSON_3_11, LESSON_3_12,
                LESSON_3_13, LESSON_3_14
            )
            val lessonsTitle = listOf(
                R.string.lesson301_title, R.string.lesson302_title, R.string.lesson303_title,
                R.string.lesson304_title, R.string.lesson305_title, R.string.lesson306_title,
                R.string.lesson307_title, R.string.lesson308_title, R.string.lesson309_title,
                R.string.lesson310_title, R.string.lesson311_title, R.string.lesson312_title,
                R.string.lesson313_title, R.string.lesson314_title,
            )
            val practiceList = listOf(
                false, // lesson 3.01
                true, // lesson 3.02
                false, // lesson 3.03
                false, // lesson 3.04
                true, // lesson 3.05
                true, // lesson 3.06
                false, // lesson 3.07
                false, // lesson 3.08
                false, // lesson 3.09
                false, // lesson 3.10
                false, // lesson 3.11
                false, // lesson 3.12
                false, // lesson 3.13
                false, // lesson 3.14
            )
            for (i in lessonsNumbers.indices){
                val lesson = Lesson(
                    level = B1,
                    number = lessonsNumbers[i],
                    title = context.getString(lessonsTitle[i]),
                    status = SharedPref.get(lessonsNumbers[i], STATUS_NOT_READ)!!,
                    practice = practiceList[i],
                    forSearch = false
                )
                mLessonsB1.add(lesson)
            }
            return mLessonsB1
        }

        fun setLessonView(lessonNumber: String, parent: LinearLayout) {
            when (lessonNumber) {
                // A1
                LESSON_1_01 -> createLesson101(parent)
                LESSON_1_02 -> createLesson102(parent)
                LESSON_1_03 -> createLesson103(parent)
                LESSON_1_04 -> createLesson104(parent)
                LESSON_1_05 -> createLesson105(parent)
                LESSON_1_06 -> createLesson106(parent)
                LESSON_1_07 -> createLesson107(parent)
                LESSON_1_08 -> createLesson108(parent)
                LESSON_1_09 -> createLesson109(parent)
                LESSON_1_10 -> createLesson110(parent)
                LESSON_1_11 -> createLesson111(parent)
                LESSON_1_12 -> createLesson112(parent)
                LESSON_1_13 -> createLesson113(parent)
                LESSON_1_14 -> createLesson114(parent)
                LESSON_1_15 -> createLesson115(parent)
                LESSON_1_16 -> createLesson116(parent)
                LESSON_1_17 -> createLesson117(parent)
                LESSON_1_18 -> createLesson118(parent)
                LESSON_1_19 -> createLesson119(parent)
                // A2
                LESSON_2_01 -> createLesson201(parent)
                LESSON_2_02 -> createLesson202(parent)
                LESSON_2_03 -> createLesson203(parent)
                LESSON_2_04 -> createLesson204(parent)
                LESSON_2_05 -> createLesson205(parent)
                LESSON_2_06 -> createLesson206(parent)
                LESSON_2_07 -> createLesson207(parent)
                LESSON_2_08 -> createLesson208(parent)
                LESSON_2_09 -> createLesson209(parent)
                LESSON_2_10 -> createLesson210(parent)
                LESSON_2_11 -> createLesson211(parent)
                LESSON_2_12 -> createLesson212(parent)
                LESSON_2_13 -> createLesson213(parent)
                LESSON_2_14 -> createLesson214(parent)
                LESSON_2_15 -> createLesson215(parent)
                LESSON_2_16 -> createLesson216(parent)
                LESSON_2_17 -> createLesson217(parent)
                // B1
                LESSON_3_01 -> createLesson301(parent)
                LESSON_3_02 -> createLesson302(parent)
                LESSON_3_03 -> createLesson303(parent)
                LESSON_3_04 -> createLesson304(parent)
                LESSON_3_05 -> createLesson305(parent)
                LESSON_3_06 -> createLesson306(parent)
                LESSON_3_07 -> createLesson307(parent)
                LESSON_3_08 -> createLesson308(parent)
                LESSON_3_09 -> createLesson309(parent)
                LESSON_3_10 -> createLesson310(parent)
                LESSON_3_11 -> createLesson311(parent)
                LESSON_3_12 -> createLesson312(parent)
                LESSON_3_13 -> createLesson313(parent)
                LESSON_3_14 -> createLesson314(parent)


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
            addSimpleText(parent, R.string.lesson103_text01)
            addImage(parent, "table_01_pronouns.png")
            addSimpleText(parent, R.string.lesson103_text02)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson103_phrase01, R.string.lesson103_translation01)
            addPhrase(parent, R.string.lesson103_phrase02, R.string.lesson103_translation02)
            addPhrase(parent, R.string.lesson103_phrase03, R.string.lesson103_translation03)
            addPhrase(parent, R.string.lesson103_phrase04, R.string.lesson103_translation04)
            addPhrase(parent, R.string.lesson103_phrase05, R.string.lesson103_translation05)
            addSimpleText(parent, R.string.lesson103_text03)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson103_phrase06, R.string.lesson103_translation06)
            addSimpleText(parent, R.string.lesson103_text04)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson103_phrase07, R.string.lesson103_translation07)
            addCenterText(parent, R.string.lesson103_text05)
            addImage(parent, "table_02_pronouns.png")
            addCenterText(parent, R.string.lesson103_text06)
        }

        // Lesson 4 (A1) - 1.04
        private fun createLesson104(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson104_text01)
            addImage(parent,"table_03_simple_tense.png")
            addCenterText(parent, R.string.lesson104_text02)
            addCenterText(parent, R.string.lesson104_text03)
            addCenterText(parent, R.string.lesson104_text04)
            addPhrase(parent, R.string.lesson104_phrase01, R.string.lesson104_translation01)
            addPhrase(parent, R.string.lesson104_phrase02, R.string.lesson104_translation02)
            addCenterText(parent, R.string.lesson104_text_negative)
            addPhrase(parent, R.string.lesson104_phrase03, R.string.lesson104_translation03)
            addPhrase(parent, R.string.lesson104_phrase04, R.string.lesson104_translation04)
            addCenterText(parent, R.string.lesson104_text_question)
            addPhrase(parent, R.string.lesson104_phrase05, R.string.lesson104_translation05)
            addPhrase(parent, R.string.lesson104_phrase06, R.string.lesson104_translation06)
            addCenterText(parent, R.string.lesson104_text05)
            addPhrase(parent, R.string.lesson104_phrase07, R.string.lesson104_translation07)
            addPhrase(parent, R.string.lesson104_phrase08, R.string.lesson104_translation08)
            addCenterText(parent, R.string.lesson104_text_negative)
            addPhrase(parent, R.string.lesson104_phrase09, R.string.lesson104_translation09)
            addPhrase(parent, R.string.lesson104_phrase10, R.string.lesson104_translation10)
            addCenterText(parent, R.string.lesson104_text_question)
            addPhrase(parent, R.string.lesson104_phrase11, R.string.lesson104_translation11)
            addPhrase(parent, R.string.lesson104_phrase12, R.string.lesson104_translation12)
            addCenterText(parent, R.string.lesson104_text06)
            addPhrase(parent, R.string.lesson104_phrase13, R.string.lesson104_translation13)
            addPhrase(parent, R.string.lesson104_phrase14, R.string.lesson104_translation14)
            addCenterText(parent, R.string.lesson104_text_negative)
            addPhrase(parent, R.string.lesson104_phrase15, R.string.lesson104_translation15)
            addPhrase(parent, R.string.lesson104_phrase16, R.string.lesson104_translation16)
            addCenterText(parent, R.string.lesson104_text_question)
            addPhrase(parent, R.string.lesson104_phrase17, R.string.lesson104_translation17)
            addPhrase(parent, R.string.lesson104_phrase18, R.string.lesson104_translation18)
            addCenterText(parent, R.string.lesson104_text07)
        }

        // Lesson 5 (A1) - 1.05
        private fun createLesson105(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson105_phrase01, R.string.lesson105_translation01)
            addSimpleText(parent, R.string.lesson105_text01)
            addImage(parent, "table_04_present_simple.png")
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson105_phrase02, R.string.lesson105_translation02)
            addPhrase(parent, R.string.lesson105_phrase03, R.string.lesson105_translation03)
            addPhrase(parent, R.string.lesson105_phrase04, R.string.lesson105_translation04)
            addPhrase(parent, R.string.lesson105_phrase05, R.string.lesson105_translation05)
            addSimpleText(parent, R.string.lesson105_text02)
            addPhrase(parent, R.string.lesson105_phrase06, R.string.lesson105_translation06)
            addPhrase(parent, R.string.lesson105_phrase07, R.string.lesson105_translation07)
            addPhrase(parent, R.string.lesson105_phrase08, R.string.lesson105_translation08)
            addSimpleText(parent, R.string.lesson105_text03)
            addPhrase(parent, R.string.lesson105_phrase09, R.string.lesson105_translation09)
            addPhrase(parent, R.string.lesson105_phrase10, R.string.lesson105_translation10)
            addPhrase(parent, R.string.lesson105_phrase11, R.string.lesson105_translation11)
            addCenterText(parent, R.string.lesson105_text04)
        }

        // Lesson 6 (A1) - 1.06
        private fun createLesson106(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson106_phrase01, R.string.lesson106_translation01)
            addSimpleText(parent, R.string.lesson106_text01)
            addCenterText(parent,R.string.example)
            addPhrase(parent, R.string.lesson106_phrase02, R.string.lesson106_translation02)
            addPhrase(parent, R.string.lesson106_phrase03, R.string.lesson106_translation03)
            addSimpleText(parent, R.string.lesson106_text02)
            addPhrase(parent, R.string.lesson106_phrase04, R.string.lesson106_translation04)
            addSimpleText(parent, R.string.lesson106_text03)
            addPhrase(parent, R.string.lesson106_phrase05, R.string.lesson106_translation05)
            addImage(parent,"table_05_future_simple.png")
            addSimpleText(parent, R.string.lesson106_text04)
            addCenterText(parent,R.string.example)
            addPhrase(parent, R.string.lesson106_phrase06, R.string.lesson106_translation06)
            addPhrase(parent, R.string.lesson106_phrase07, R.string.lesson106_translation07)
            addPhrase(parent, R.string.lesson106_phrase08, R.string.lesson106_translation08)
        }

        // Lesson 7 (A1) - 1.7
        private fun createLesson107(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson107_phrase01, R.string.lesson107_translation01)
            addSimpleText(parent, R.string.lesson107_text01)
            addPhrase(parent, R.string.lesson107_phrase02, R.string.lesson107_translation02)
            addPhrase(parent, R.string.lesson107_phrase03, R.string.lesson107_translation03)
            addSimpleText(parent, R.string.lesson107_text02)
            addPhrase(parent, R.string.lesson107_phrase04, R.string.lesson107_translation04)
            addSimpleText(parent, R.string.lesson107_text03)
            addPhrase(parent, R.string.lesson107_phrase05, R.string.lesson107_translation05)
            addImage(parent, "table_05_past_simple.png")
            addSimpleText(parent, R.string.lesson107_text04)
            addPhrase(parent, R.string.lesson107_phrase06, R.string.lesson107_translation06)
            addPhrase(parent, R.string.lesson107_phrase07, R.string.lesson107_translation07)
            addPhrase(parent, R.string.lesson107_phrase08, R.string.lesson107_translation08)
            addPhrase(parent, R.string.lesson107_phrase09, R.string.lesson107_translation09)
            addSimpleText(parent, R.string.lesson107_text05)
        }

        // Lesson 8 (A1) - 1.8
        private fun createLesson108(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson108_phrase01, R.string.lesson108_translation01)
            addSimpleText(parent, R.string.lesson108_text01)
            addPhrase(parent, R.string.lesson108_phrase02, R.string.lesson108_translation02)
            addPhrase(parent, R.string.lesson108_phrase03, R.string.lesson108_translation03)
            addPhrase(parent, R.string.lesson108_phrase04, R.string.lesson108_translation04)
            addCenterText(parent, R.string.lesson108_text02)
            addPhrase(parent, R.string.lesson108_phrase05, R.string.lesson108_translation05)
            addPhrase(parent, R.string.lesson108_phrase06, R.string.lesson108_translation06)
            addPhrase(parent, R.string.lesson108_phrase07, R.string.lesson108_translation07)
            addSimpleText(parent, R.string.lesson108_text03)
            addPhrase(parent, R.string.lesson108_phrase08, R.string.lesson108_translation08)
            addPhrase(parent, R.string.lesson108_phrase09, R.string.lesson108_translation09)
            addImage(parent, "table_06_to_be.png")
            addSimpleText(parent, R.string.lesson108_text04)
            addPhrase(parent, R.string.lesson108_phrase10, R.string.lesson108_translation10)
            addSimpleText(parent, R.string.lesson108_text05)
            addPhrase(parent, R.string.lesson108_phrase11, R.string.lesson108_translation11)
            addPhrase(parent, R.string.lesson108_phrase12, R.string.lesson108_translation12)
            addPhrase(parent, R.string.lesson108_phrase13, R.string.lesson108_translation13)
        }

        // Lesson 9 (A1) - 1.9
        private fun createLesson109(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson109_phrase01, R.string.lesson109_translation01)
            addSimpleText(parent, R.string.lesson109_text01)
            addPhrase(parent, R.string.lesson109_phrase02, R.string.lesson109_translation02)
            addPhrase(parent, R.string.lesson109_phrase03, R.string.lesson109_translation03)
            addCenterText(parent, R.string.lesson109_text02)
            addImage(parent, "table_07_present_continuous.png")
            addCenterText(parent, R.string.example)
            addCenterText(parent, R.string.lesson109_text03)
            addPhrase(parent, R.string.lesson109_phrase04, R.string.lesson109_translation04)
            addPhrase(parent, R.string.lesson109_phrase05, R.string.lesson109_translation05)
            addPhrase(parent, R.string.lesson109_phrase06, R.string.lesson109_translation06)
            addPhrase(parent, R.string.lesson109_phrase07, R.string.lesson109_translation07)
            addPhrase(parent, R.string.lesson109_phrase08, R.string.lesson109_translation08)
        }

        // Lesson 10 (A1) - 1.10
        private fun createLesson110(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson110_phrase01, R.string.lesson110_translation01)
            addSimpleText(parent, R.string.lesson110_text01)
            addImage(parent, "table_08_be_going_to.png")
            addCenterText(parent, R.string.lesson110_text02)
            addSimpleText(parent, R.string.lesson110_text03)
            addPhrase(parent, R.string.lesson110_phrase02, R.string.lesson110_translation02)
            addPhrase(parent, R.string.lesson110_phrase03, R.string.lesson110_translation03)
            addPhrase(parent, R.string.lesson110_phrase04, R.string.lesson110_translation04)
            addSimpleText(parent, R.string.lesson110_text04)
            addPhrase(parent, R.string.lesson110_phrase05, R.string.lesson110_translation05)
            addPhrase(parent, R.string.lesson110_phrase06, R.string.lesson110_translation06)
            addSimpleText(parent, R.string.lesson110_text05)
            addPhrase(parent, R.string.lesson110_phrase07, R.string.lesson110_translation07)
            addPhrase(parent, R.string.lesson110_phrase08, R.string.lesson110_translation08)
            addSimpleText(parent, R.string.lesson110_text07)
            addPhrase(parent, R.string.lesson110_phrase09, R.string.lesson110_translation09)
            addPhrase(parent, R.string.lesson110_phrase10, R.string.lesson110_translation10)
            addSimpleText(parent, R.string.lesson110_text08)
            addCenterText(parent, R.string.lesson110_text09)
            addPhrase(parent, R.string.lesson110_phrase11, R.string.lesson110_translation11)
            addCenterText(parent, R.string.lesson110_text10)
            addPhrase(parent, R.string.lesson110_phrase12, R.string.lesson110_translation12)
            addSimpleText(parent, R.string.lesson110_text11)
            addCenterText(parent, R.string.lesson110_text12)
            addPhrase(parent, R.string.lesson110_phrase13, R.string.lesson110_translation13)
            addPhrase(parent, R.string.lesson110_phrase14, R.string.lesson110_translation14)
        }

        // Lesson 11 (A1) - 1.11
        private fun createLesson111(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson111_phrase01, R.string.lesson111_translation01)
            addSimpleText(parent, R.string.lesson111_text01)
            addCenterText(parent, R.string.lesson111_text02)
            addPhrase(parent, R.string.lesson111_phrase02, R.string.lesson111_translation02)
            addPhrase(parent, R.string.lesson111_phrase03, R.string.lesson111_translation03)
            addPhrase(parent, R.string.lesson111_phrase04, R.string.lesson111_translation04)
            addPhrase(parent, R.string.lesson111_phrase05, R.string.lesson111_translation05)
            addPhrase(parent, R.string.lesson111_phrase06, R.string.lesson111_translation06)
            addCenterText(parent, R.string.lesson111_text03)
            addPhrase(parent, R.string.lesson111_phrase07, R.string.lesson111_translation07)
            addPhrase(parent, R.string.lesson111_phrase08, R.string.lesson111_translation08)
            addPhrase(parent, R.string.lesson111_phrase09, R.string.lesson111_translation09)
            addPhrase(parent, R.string.lesson111_phrase10, R.string.lesson111_translation10)
            addSimpleText(parent, R.string.lesson111_text04)
            addPhrase(parent, R.string.lesson111_phrase11, R.string.lesson111_translation11)
            addPhrase(parent, R.string.lesson111_phrase12, R.string.lesson111_translation12)
            addSimpleText(parent, R.string.lesson111_text05)
            addPhrase(parent, R.string.lesson111_phrase13, R.string.lesson111_translation13)
            addPhrase(parent, R.string.lesson111_phrase14, R.string.lesson111_translation14)
            addSimpleText(parent, R.string.lesson111_text06)
            addPhrase(parent, R.string.lesson111_phrase15, R.string.lesson111_translation15)
            addPhrase(parent, R.string.lesson111_phrase16, R.string.lesson111_translation16)
            addSimpleText(parent, R.string.lesson111_text07)
            addPhrase(parent, R.string.lesson111_phrase17, R.string.lesson111_translation17)
            addPhrase(parent, R.string.lesson111_phrase18, R.string.lesson111_translation18)
            addCenterText(parent, R.string.lesson111_text08)
        }



        // Lesson 12 (A1) - 1.12
        private fun createLesson112(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson112_phrase01, R.string.lesson112_translation01)
            addSimpleText(parent, R.string.lesson112_text01)
            addCenterText(parent, R.string.lesson112_text02)
            addSimpleText(parent, R.string.lesson112_text03)
            addPhrase(parent, R.string.lesson112_phrase02, R.string.lesson112_translation02)
            addPhrase(parent, R.string.lesson112_phrase03, R.string.lesson112_translation03)
            addPhrase(parent, R.string.lesson112_phrase04, R.string.lesson112_translation04)
            addCenterText(parent, R.string.lesson112_text04)
            addSimpleText(parent, R.string.lesson112_text05)
            addPhrase(parent, R.string.lesson112_phrase05, R.string.lesson112_translation05)
            addCenterText(parent, R.string.lesson112_text06)
            addSimpleText(parent, R.string.lesson112_text07)
            addPhrase(parent, R.string.lesson112_phrase06, R.string.lesson112_translation06)
            addPhrase(parent, R.string.lesson112_phrase07, R.string.lesson112_translation07)
            addCenterText(parent, R.string.lesson112_text08)
            addSimpleText(parent, R.string.lesson112_text09)
            addPhrase(parent, R.string.lesson112_phrase08, R.string.lesson112_translation08)
            addPhrase(parent, R.string.lesson112_phrase09, R.string.lesson112_translation09)
            addPhrase(parent, R.string.lesson112_phrase10, R.string.lesson112_translation10)
        }


        // Lesson 13 (A1) - 1.13
        private fun createLesson113(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson113_phrase01, R.string.lesson113_translation01)
            addSimpleText(parent, R.string.lesson113_text01)
            addPhrase(parent, R.string.lesson113_phrase02, R.string.lesson113_translation02)
            addSimpleText(parent, R.string.lesson113_text02)
            addCenterText(parent, R.string.lesson113_text03)
            addSimpleText(parent, R.string.lesson113_text04)
            addCenterText(parent, R.string.lesson113_text05)
            addSimpleText(parent, R.string.lesson113_text06)
            addCenterText(parent, R.string.lesson113_text07)
        }


        // Lesson 14 (A1) - 1.14
        private fun createLesson114(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson114_phrase01, R.string.lesson114_translation01)
            addSimpleText(parent, R.string.lesson114_text01)
            addPhrase(parent, R.string.lesson114_phrase02, R.string.lesson114_translation02)
            addPhrase(parent, R.string.lesson114_phrase03, R.string.lesson114_translation03)
            addPhrase(parent, R.string.lesson114_phrase04, R.string.lesson114_translation04)
            addSimpleText(parent, R.string.lesson114_text02)
            addPhrase(parent, R.string.lesson114_phrase05, R.string.lesson114_translation05)
            addPhrase(parent, R.string.lesson114_phrase06, R.string.lesson114_translation06)
            addSimpleText(parent, R.string.lesson114_text03)
            addPhrase(parent, R.string.lesson114_phrase07, R.string.lesson114_translation07)
            addSimpleText(parent, R.string.lesson114_text04)
            addPhrase(parent, R.string.lesson114_phrase08, R.string.lesson114_translation08)
            addPhrase(parent, R.string.lesson114_phrase09, R.string.lesson114_translation09)
            addPhrase(parent, R.string.lesson114_phrase10, R.string.lesson114_translation10)
            addPhrase(parent, R.string.lesson114_phrase11, R.string.lesson114_translation11)
            addPhrase(parent, R.string.lesson114_phrase12, R.string.lesson114_translation12)
            addSimpleText(parent, R.string.lesson114_text05)
            addPhrase(parent, R.string.lesson114_phrase13, R.string.lesson114_translation13)
            addPhrase(parent, R.string.lesson114_phrase14, R.string.lesson114_translation14)
            addCenterText(parent, R.string.lesson114_text06)
        }


        // Lesson 15 (A1) - 1.15
        private fun createLesson115(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson115_text01)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson115_phrase01, R.string.lesson115_translation01)
            addPhrase(parent, R.string.lesson115_phrase02, R.string.lesson115_translation02)
            addPhrase(parent, R.string.lesson115_phrase03, R.string.lesson115_translation03)
            addPhrase(parent, R.string.lesson115_phrase04, R.string.lesson115_translation04)
            addCenterText(parent, R.string.lesson115_text02)
            addPhrase(parent, R.string.lesson115_phrase05, R.string.lesson115_translation05)
            addPhrase(parent, R.string.lesson115_phrase06, R.string.lesson115_translation06)
            addSimpleText(parent, R.string.lesson115_text03)
            addPhrase(parent, R.string.lesson115_phrase07, R.string.lesson115_translation07)
            addPhrase(parent, R.string.lesson115_phrase08, R.string.lesson115_translation08)
            addCenterText(parent, R.string.lesson115_text04)
            addSimpleText(parent, R.string.lesson115_text05)
            addPhrase(parent, R.string.lesson115_phrase09, R.string.lesson115_translation09)
            addSimpleText(parent, R.string.lesson115_text06)
            addPhrase(parent, R.string.lesson115_phrase10, R.string.lesson115_translation10)
            addSimpleText(parent, R.string.lesson115_text07)
            addPhrase(parent, R.string.lesson115_phrase11, R.string.lesson115_translation11)
            addCenterText(parent, R.string.lesson115_text08)
            addSimpleText(parent, R.string.lesson115_text09)
            addPhrase(parent, R.string.lesson115_phrase12, R.string.lesson115_translation12)
            addSimpleText(parent, R.string.lesson115_text10)
            addPhrase(parent, R.string.lesson115_phrase13, R.string.lesson115_translation13)
        }

        // Lesson 16 (A1) - 1.16
        private fun createLesson116(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson116_phrase01, R.string.lesson116_translation01)
            addSimpleText(parent, R.string.lesson116_text01)
            addPhrase(parent, R.string.lesson116_phrase02, R.string.lesson116_translation02)
            addSimpleText(parent, R.string.lesson116_text02)
            addPhrase(parent, R.string.lesson116_phrase03, R.string.lesson116_translation03)
            addPhrase(parent, R.string.lesson116_phrase04, R.string.lesson116_translation04)
            addSimpleText(parent, R.string.lesson116_text03)
            addPhrase(parent, R.string.lesson116_phrase05, R.string.lesson116_translation05)
            addPhrase(parent, R.string.lesson116_phrase06, R.string.lesson116_translation06)
            addSimpleText(parent, R.string.lesson116_text04)
            addPhrase(parent, R.string.lesson116_phrase07, R.string.lesson116_translation07)
            addPhrase(parent, R.string.lesson116_phrase08, R.string.lesson116_translation08)
            addSimpleText(parent, R.string.lesson116_text05)
            addPhrase(parent, R.string.lesson116_phrase09, R.string.lesson116_translation09)
        }

        // Lesson 17 (A1) - 1.17
        private fun createLesson117(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson117_phrase01, R.string.lesson117_translation01)
            addSimpleText(parent, R.string.lesson117_text01)
            addPhrase(parent, R.string.lesson117_phrase002, R.string.lesson117_translation002)
            addPhrase(parent, R.string.lesson117_phrase003, R.string.lesson117_translation003)
            addPhrase(parent, R.string.lesson117_phrase004, R.string.lesson117_translation004)
            addPhrase(parent, R.string.lesson117_phrase005, R.string.lesson117_translation005)
            addSimpleText(parent, R.string.lesson117_text03)
            addPhrase(parent, R.string.lesson117_phrase02, R.string.lesson117_translation02)
            addPhrase(parent, R.string.lesson117_phrase03, R.string.lesson117_translation03)
            addSimpleText(parent, R.string.lesson117_text04)
            addPhrase(parent, R.string.lesson117_phrase04, R.string.lesson117_translation04)
            addSimpleText(parent, R.string.lesson117_text05)
            addPhrase(parent, R.string.lesson117_phrase05, R.string.lesson117_translation05)
            addPhrase(parent, R.string.lesson117_phrase06, R.string.lesson117_translation06)
            addSimpleText(parent, R.string.lesson117_text06)
            addPhrase(parent, R.string.lesson117_phrase07, R.string.lesson117_translation07)
            addPhrase(parent, R.string.lesson117_phrase08, R.string.lesson117_translation08)
            addSimpleText(parent, R.string.lesson117_text07)
            addPhrase(parent, R.string.lesson117_phrase09, R.string.lesson117_translation09)
            addSimpleText(parent, R.string.lesson117_text08)
            addPhrase(parent, R.string.lesson117_phrase10, R.string.lesson117_translation10)
            addPhrase(parent, R.string.lesson117_phrase11, R.string.lesson117_translation11)
        }


        // Lesson 18 (A1) - 1.18
        private fun createLesson118(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson118_phrase01, R.string.lesson118_translation01)
            addSimpleText(parent, R.string.lesson118_text01)
            addPhrase(parent, R.string.lesson118_phrase02, R.string.lesson118_translation02)
            addPhrase(parent, R.string.lesson118_phrase03, R.string.lesson118_translation03)
            addPhrase(parent, R.string.lesson118_phrase04, R.string.lesson118_translation04)
            addPhrase(parent, R.string.lesson118_phrase05, R.string.lesson118_translation05)
            addSimpleText(parent, R.string.lesson118_text02)
            addCenterText(parent, R.string.lesson118_text03)
            addSimpleText(parent, R.string.lesson118_text04)
            addCenterText(parent, R.string.lesson118_text05)
            addImage(parent, "table_09_much_many.png")
            addSimpleText(parent, R.string.lesson118_text06)
            addPhrase(parent, R.string.lesson118_phrase06, R.string.lesson118_translation06)
            addSimpleText(parent, R.string.lesson118_text07)
            addPhrase(parent, R.string.lesson118_phrase07, R.string.lesson118_translation07)
            addSimpleText(parent, R.string.lesson118_text08)
            addPhrase(parent, R.string.lesson118_phrase08, R.string.lesson118_translation08)
            addSimpleText(parent, R.string.lesson118_text09)
            addPhrase(parent, R.string.lesson118_phrase09, R.string.lesson118_translation09)
            addCenterText(parent, R.string.lesson118_text10)
        }


        // Lesson 19 (A1) - 1.19
        private fun createLesson119(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson119_phrase01, R.string.lesson119_translation01)
            addSimpleText(parent, R.string.lesson119_text01)
            addPhrase(parent, R.string.lesson119_phrase02, R.string.lesson119_translation02)
            addPhrase(parent, R.string.lesson119_phrase03, R.string.lesson119_translation03)
            addPhrase(parent, R.string.lesson119_phrase04, R.string.lesson119_translation04)
            addSimpleText(parent, R.string.lesson119_text02)
            addPhrase(parent, R.string.lesson119_phrase05, R.string.lesson119_translation05)
            addPhrase(parent, R.string.lesson119_phrase06, R.string.lesson119_translation06)
            addPhrase(parent, R.string.lesson119_phrase07, R.string.lesson119_translation07)
            addSimpleText(parent, R.string.lesson119_text03)
            addPhrase(parent, R.string.lesson119_phrase08, R.string.lesson119_translation08)
            addPhrase(parent, R.string.lesson119_phrase09, R.string.lesson119_translation09)
            addPhrase(parent, R.string.lesson119_phrase10, R.string.lesson119_translation10)
            addPhrase(parent, R.string.lesson119_phrase11, R.string.lesson119_translation11)
            addSimpleText(parent, R.string.lesson119_text04)
            addPhrase(parent, R.string.lesson119_phrase12, R.string.lesson119_translation12)
            addPhrase(parent, R.string.lesson119_phrase13, R.string.lesson119_translation13)
            addPhrase(parent, R.string.lesson119_phrase14, R.string.lesson119_translation14)
            addSimpleText(parent, R.string.lesson119_text05)
            addPhrase(parent, R.string.lesson119_phrase15, R.string.lesson119_translation15)
            addPhrase(parent, R.string.lesson119_phrase16, R.string.lesson119_translation16)
            addPhrase(parent, R.string.lesson119_phrase17, R.string.lesson119_translation17)
            addSimpleText(parent, R.string.lesson119_text06)
            addPhrase(parent, R.string.lesson119_phrase18, R.string.lesson119_translation18)
            addPhrase(parent, R.string.lesson119_phrase19, R.string.lesson119_translation19)
            addPhrase(parent, R.string.lesson119_phrase20, R.string.lesson119_translation20)
        }

        // A2
        // Lesson 01 (A2) - 2.01
        private fun createLesson201(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson201_text01)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson201_phrase01, R.string.lesson201_translation01)
            addPhrase(parent, R.string.lesson201_phrase02, R.string.lesson201_translation02)
            addSimpleText(parent, R.string.lesson201_text02)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson201_phrase03, R.string.lesson201_translation03)
            addPhrase(parent, R.string.lesson201_phrase04, R.string.lesson201_translation04)
            addSimpleText(parent, R.string.lesson201_text03)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson201_phrase05, R.string.lesson201_translation05)
            addPhrase(parent, R.string.lesson201_phrase06, R.string.lesson201_translation06)
        }


        // Lesson 02 (A2) - 2.02
        private fun createLesson202(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson202_text01)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson202_phrase01, R.string.lesson202_translation01)
            addPhrase(parent, R.string.lesson202_phrase02, R.string.lesson202_translation02)
            addPhrase(parent, R.string.lesson202_phrase03, R.string.lesson202_translation03)
            addSimpleText(parent, R.string.lesson202_text02)
            addCenterText(parent, R.string.lesson202_text03)
            addSimpleText(parent, R.string.lesson202_text04)
            addCenterText(parent, R.string.example)
            addPhrase(parent, R.string.lesson202_phrase04, R.string.lesson202_translation04)
            addPhrase(parent, R.string.lesson202_phrase05, R.string.lesson202_translation05)
        }

        // Lesson 03 (A2) - 2.03
        private fun createLesson203(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson203_phrase01, R.string.lesson203_translation01)
            addSimpleText(parent, R.string.lesson203_text01)
            addCenterText(parent, R.string.lesson203_text02)
            addPhrase(parent, R.string.lesson203_phrase02, R.string.lesson203_translation02)
            addPhrase(parent, R.string.lesson203_phrase03, R.string.lesson203_translation03)
            addCenterText(parent, R.string.lesson203_text03)
            addPhrase(parent, R.string.lesson203_phrase04, R.string.lesson203_translation04)
            addPhrase(parent, R.string.lesson203_phrase05, R.string.lesson203_translation05)
            addCenterText(parent, R.string.lesson203_text04)
            addPhrase(parent, R.string.lesson203_phrase06, R.string.lesson203_translation06)
            addPhrase(parent, R.string.lesson203_phrase07, R.string.lesson203_translation07)
            addSimpleText(parent, R.string.lesson203_text05)
            addPhrase(parent, R.string.lesson203_phrase08, R.string.lesson203_translation08)
            addSimpleText(parent, R.string.lesson203_text06)
            addPhrase(parent, R.string.lesson203_phrase09, R.string.lesson203_translation09)
            addPhrase(parent, R.string.lesson203_phrase10, R.string.lesson203_translation10)
            addSimpleText(parent, R.string.lesson203_text07)
            addPhrase(parent, R.string.lesson203_phrase11, R.string.lesson203_translation11)
            addPhrase(parent, R.string.lesson203_phrase12, R.string.lesson203_translation12)
            addPhrase(parent, R.string.lesson203_phrase13, R.string.lesson203_translation13)
            addPhrase(parent, R.string.lesson203_phrase14, R.string.lesson203_translation14)
            addCenterText(parent, R.string.lesson203_text08)
        }

        // Lesson 04 (A2) - 2.04
        private fun createLesson204(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson204_text01)
            addPhrase(parent, R.string.lesson204_phrase01, R.string.lesson204_translation01)
            addSimpleText(parent, R.string.lesson204_text02)
            addPhrase(parent, R.string.lesson204_phrase02, R.string.lesson204_translation02)
            addSimpleText(parent, R.string.lesson204_text03)
            addPhrase(parent, R.string.lesson204_phrase03, R.string.lesson204_translation03)
            addPhrase(parent, R.string.lesson204_phrase04, R.string.lesson204_translation04)
            addSimpleText(parent, R.string.lesson204_text04)
            addPhrase(parent, R.string.lesson204_phrase05, R.string.lesson204_translation05)
            addPhrase(parent, R.string.lesson204_phrase06, R.string.lesson204_translation06)
            addSimpleText(parent, R.string.lesson204_text05)
            addPhrase(parent, R.string.lesson204_phrase07, R.string.lesson204_translation07)
            addPhrase(parent, R.string.lesson204_phrase08, R.string.lesson204_translation08)
        }

        // Lesson 05 (A2) - 2.05
        private fun createLesson205(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson205_text01)
            addPhrase(parent, R.string.lesson205_phrase01, R.string.lesson205_translation01)
            addSimpleText(parent, R.string.lesson205_text02)
            addCenterText(parent, R.string.lesson205_text03)
            addSimpleText(parent, R.string.lesson205_text04)
            addImage(parent, "table_10_present_perfect.png")
            addSimpleText(parent, R.string.lesson205_text05)
            addPhrase(parent, R.string.lesson205_phrase02, R.string.lesson205_translation02)
            addPhrase(parent, R.string.lesson205_phrase03, R.string.lesson205_translation03)
            addSimpleText(parent, R.string.lesson205_text06)
            addPhrase(parent, R.string.lesson205_phrase04, R.string.lesson205_translation04)
        }

        // Lesson 06 (A2) - 2.06
        private fun createLesson206(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson206_text01)
            addCenterText(parent, R.string.lesson206_text02)
            addPhrase(parent, R.string.lesson206_phrase01, R.string.lesson206_translation01)
            addPhrase(parent, R.string.lesson206_phrase02, R.string.lesson206_translation02)
            addPhrase(parent, R.string.lesson206_phrase03, R.string.lesson206_translation03)
            addCenterText(parent, R.string.lesson206_text03)
            addPhrase(parent, R.string.lesson206_phrase04, R.string.lesson206_translation04)
            addPhrase(parent, R.string.lesson206_phrase05, R.string.lesson206_translation05)
            addPhrase(parent, R.string.lesson206_phrase06, R.string.lesson206_translation06)
            addCenterText(parent, R.string.lesson206_text04)
            addPhrase(parent, R.string.lesson206_phrase07, R.string.lesson206_translation07)
            addPhrase(parent, R.string.lesson206_phrase08, R.string.lesson206_translation08)
        }

        // Lesson 07 (A2) - 2.07
        private fun createLesson207(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson207_text01)
            addCenterText(parent, R.string.lesson207_text02)
            addSimpleText(parent, R.string.lesson207_text03)
            addPhrase(parent, R.string.lesson207_phrase01, R.string.lesson207_translation01)
            addPhrase(parent, R.string.lesson207_phrase02, R.string.lesson207_translation02)
            addPhrase(parent, R.string.lesson207_phrase03, R.string.lesson207_translation03)
            addPhrase(parent, R.string.lesson207_phrase04, R.string.lesson207_translation04)
            addSimpleText(parent, R.string.lesson207_text04)
            addPhrase(parent, R.string.lesson207_phrase05, R.string.lesson207_translation05)
            addCenterText(parent, R.string.lesson207_text05)
            addSimpleText(parent, R.string.lesson207_text06)
            addPhrase(parent, R.string.lesson207_phrase06, R.string.lesson207_translation06)
            addPhrase(parent, R.string.lesson207_phrase07, R.string.lesson207_translation07)
            addPhrase(parent, R.string.lesson207_phrase08, R.string.lesson207_translation08)
            addCenterText(parent, R.string.lesson207_text07)
            addSimpleText(parent, R.string.lesson207_text08)
            addPhrase(parent, R.string.lesson207_phrase09, R.string.lesson207_translation09)
            addSimpleText(parent, R.string.lesson207_text09)
            addPhrase(parent, R.string.lesson207_phrase10, R.string.lesson207_translation10)
            addPhrase(parent, R.string.lesson207_phrase11, R.string.lesson207_translation11)
            addPhrase(parent, R.string.lesson207_phrase12, R.string.lesson207_translation12)
            addPhrase(parent, R.string.lesson207_phrase13, R.string.lesson207_translation13)
        }

        // Lesson 08 (A2) - 2.08
        private fun createLesson208(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson208_text01)
            addImage(parent, "table_11_prepositions_of_place.png")
            addSimpleText(parent, R.string.lesson208_text02)
            addPhrase(parent, R.string.lesson208_phrase01, R.string.lesson208_translation01)
            addSimpleText(parent, R.string.lesson208_text03)
            addPhrase(parent, R.string.lesson208_phrase02, R.string.lesson208_translation02)
            addSimpleText(parent, R.string.lesson208_text04)
            addPhrase(parent, R.string.lesson208_phrase03, R.string.lesson208_translation03)
            addSimpleText(parent, R.string.lesson208_text05)
            addPhrase(parent, R.string.lesson208_phrase04, R.string.lesson208_translation04)
            addSimpleText(parent, R.string.lesson208_text06)
            addPhrase(parent, R.string.lesson208_phrase05, R.string.lesson208_translation05)
            addSimpleText(parent, R.string.lesson208_text07)
            addPhrase(parent, R.string.lesson208_phrase06, R.string.lesson208_translation06)
            addSimpleText(parent, R.string.lesson208_text08)
            addPhrase(parent, R.string.lesson208_phrase07, R.string.lesson208_translation07)
            addSimpleText(parent, R.string.lesson208_text09)
            addPhrase(parent, R.string.lesson208_phrase08, R.string.lesson208_translation08)
            addSimpleText(parent, R.string.lesson208_text10)
            addPhrase(parent, R.string.lesson208_phrase09, R.string.lesson208_translation09)
        }

        // Lesson 09 (A2) - 2.09
        private fun createLesson209(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson209_text01)
            addImage(parent, "table_12_prepositions_of_motion.png")
            addSimpleText(parent, R.string.lesson209_text02)
            addPhrase(parent, R.string.lesson209_phrase01, R.string.lesson209_translation01)
            addSimpleText(parent, R.string.lesson209_text03)
            addPhrase(parent, R.string.lesson209_phrase02, R.string.lesson209_translation02)
            addSimpleText(parent, R.string.lesson209_text04)
            addPhrase(parent, R.string.lesson209_phrase03, R.string.lesson209_translation03)
            addSimpleText(parent, R.string.lesson209_text05)
            addPhrase(parent, R.string.lesson209_phrase04, R.string.lesson209_translation04)
            addSimpleText(parent, R.string.lesson209_text06)
            addPhrase(parent, R.string.lesson209_phrase05, R.string.lesson209_translation05)
            addSimpleText(parent, R.string.lesson209_text07)
            addPhrase(parent, R.string.lesson209_phrase06, R.string.lesson209_translation06)
            addSimpleText(parent, R.string.lesson209_text08)
            addPhrase(parent, R.string.lesson209_phrase07, R.string.lesson209_translation07)
            addSimpleText(parent, R.string.lesson209_text09)
            addPhrase(parent, R.string.lesson209_phrase08, R.string.lesson209_translation08)
            addSimpleText(parent, R.string.lesson209_text10)
            addPhrase(parent, R.string.lesson209_phrase09, R.string.lesson209_translation09)
        }

        // Lesson 10 (A2) - 2.10
        private fun createLesson210(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson210_text01)
            addSimpleText(parent, R.string.lesson210_text02)
            addPhrase(parent, R.string.lesson210_phrase01, R.string.lesson210_translation01)
            addSimpleText(parent, R.string.lesson210_text03)
            addPhrase(parent, R.string.lesson210_phrase02, R.string.lesson210_translation02)
            addSimpleText(parent, R.string.lesson210_text04)
            addPhrase(parent, R.string.lesson210_phrase03, R.string.lesson210_translation03)
            addSimpleText(parent, R.string.lesson210_text05)
            addPhrase(parent, R.string.lesson210_phrase04, R.string.lesson210_translation04)
            addSimpleText(parent, R.string.lesson210_text06)
            addPhrase(parent, R.string.lesson210_phrase05, R.string.lesson210_translation05)
            addSimpleText(parent, R.string.lesson210_text07)
            addPhrase(parent, R.string.lesson210_phrase06, R.string.lesson210_translation06)
            addSimpleText(parent, R.string.lesson210_text08)
            addPhrase(parent, R.string.lesson210_phrase07, R.string.lesson210_translation07)
        }

        // Lesson 11 (A2) - 2.11
        private fun createLesson211(parent: LinearLayout) {
            addPhrase(parent, R.string.lesson211_phrase01, R.string.lesson211_translation01)
            addSimpleText(parent, R.string.lesson211_text01)
            addImage(parent, "table_13_past_continuous.png")
            addPhrase(parent, R.string.lesson211_phrase02, R.string.lesson211_translation02)
            addPhrase(parent, R.string.lesson211_phrase03, R.string.lesson211_translation03)
            addPhrase(parent, R.string.lesson211_phrase04, R.string.lesson211_translation04)
            addSimpleText(parent, R.string.lesson211_text02)
            addPhrase(parent, R.string.lesson211_phrase05, R.string.lesson211_translation05)
            addPhrase(parent, R.string.lesson211_phrase06, R.string.lesson211_translation06)
        }


        // Lesson 12 (A2) - 2.12
        private fun createLesson212(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson212_text01)
            addPhrase(parent, R.string.lesson212_phrase01, R.string.lesson212_translation01)
            addPhrase(parent, R.string.lesson212_phrase02, R.string.lesson212_translation02)
            addSimpleText(parent, R.string.lesson212_text02)
            addPhrase(parent, R.string.lesson212_phrase03, R.string.lesson212_translation03)
            addPhrase(parent, R.string.lesson212_phrase04, R.string.lesson212_translation04)
            addSimpleText(parent, R.string.lesson212_text03)
            addPhrase(parent, R.string.lesson212_phrase05, R.string.lesson212_translation05)
            addPhrase(parent, R.string.lesson212_phrase06, R.string.lesson212_translation06)
            addCenterText(parent, R.string.lesson212_text04)
            addSimpleText(parent, R.string.lesson212_text05)
            addPhrase(parent, R.string.lesson212_phrase07, R.string.lesson212_translation07)
            addPhrase(parent, R.string.lesson212_phrase08, R.string.lesson212_translation08)
            addPhrase(parent, R.string.lesson212_phrase09, R.string.lesson212_translation09)
        }

        // Lesson 13 (A2) - 2.13
        private fun createLesson213(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson213_text01)
            addPhrase(parent, R.string.lesson213_phrase01, R.string.lesson213_translation01)
            addSimpleText(parent, R.string.lesson213_text02)
            addPhrase(parent, R.string.lesson213_phrase02, R.string.lesson213_translation03)
            addSimpleText(parent, R.string.lesson213_text03)
            addPhrase(parent, R.string.lesson213_phrase03, R.string.lesson213_translation03)
            addSimpleText(parent, R.string.lesson213_text04)
        }


        // Lesson 14 (A2) - 2.14
        private fun createLesson214(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson214_text01)
            addCenterText(parent, R.string.lesson214_text02)
            addPhrase(parent, R.string.lesson214_phrase01, R.string.lesson214_translation01)
            addPhrase(parent, R.string.lesson214_phrase02, R.string.lesson214_translation02)
            addPhrase(parent, R.string.lesson214_phrase03, R.string.lesson214_translation03)
            addPhrase(parent, R.string.lesson214_phrase04, R.string.lesson214_translation04)
            addPhrase(parent, R.string.lesson214_phrase05, R.string.lesson214_translation05)
            addPhrase(parent, R.string.lesson214_phrase06, R.string.lesson214_translation06)
            addPhrase(parent, R.string.lesson214_phrase07, R.string.lesson214_translation07)
        }

        // Lesson 15 (A2) - 2.15
        private fun createLesson215(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson215_text01)
            addCenterText(parent, R.string.lesson215_text02)
            addSimpleText(parent, R.string.lesson215_text03)
            addPhrase(parent, R.string.lesson215_phrase01, R.string.lesson215_translation01)
            addPhrase(parent, R.string.lesson215_phrase02, R.string.lesson215_translation02)
            addSimpleText(parent, R.string.lesson215_text04)
            addPhrase(parent, R.string.lesson215_phrase03, R.string.lesson215_translation03)
            addSimpleText(parent, R.string.lesson215_text05)
            addPhrase(parent, R.string.lesson215_phrase04, R.string.lesson215_translation04)
            addPhrase(parent, R.string.lesson215_phrase05, R.string.lesson215_translation05)
            addPhrase(parent, R.string.lesson215_phrase06, R.string.lesson215_translation06)
            addSimpleText(parent, R.string.lesson215_text06)
            addPhrase(parent, R.string.lesson215_phrase07, R.string.lesson215_translation07)
            addSimpleText(parent, R.string.lesson215_text07)
            addPhrase(parent, R.string.lesson215_phrase08, R.string.lesson215_translation08)
            addCenterText(parent, R.string.lesson215_text08)
            addPhrase(parent, R.string.lesson215_phrase09, R.string.lesson215_translation09)
        }


        // Lesson 16 (A2) - 2.16
        private fun createLesson216(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson216_text01)
            addSimpleText(parent, R.string.lesson216_text02)
            addPhrase(parent, R.string.lesson216_phrase01, R.string.lesson216_translation01)
            addSimpleText(parent, R.string.lesson216_text03)
            addPhrase(parent, R.string.lesson216_phrase02, R.string.lesson216_translation02)
            addSimpleText(parent, R.string.lesson216_text05)
            addPhrase(parent, R.string.lesson216_phrase04, R.string.lesson216_translation04)
            addSimpleText(parent, R.string.lesson216_text06)
            addPhrase(parent, R.string.lesson216_phrase05, R.string.lesson216_translation05)
            addSimpleText(parent, R.string.lesson216_text07)
            addPhrase(parent, R.string.lesson216_phrase06, R.string.lesson216_translation06)
            addSimpleText(parent, R.string.lesson216_text08)
            addPhrase(parent, R.string.lesson216_phrase07, R.string.lesson216_translation07)
            addSimpleText(parent, R.string.lesson216_text09)
            addPhrase(parent, R.string.lesson216_phrase08, R.string.lesson216_translation08)
            addSimpleText(parent, R.string.lesson216_text10)
            addPhrase(parent, R.string.lesson216_phrase09, R.string.lesson216_translation09)
            addSimpleText(parent, R.string.lesson216_text11)
            addPhrase(parent, R.string.lesson216_phrase10, R.string.lesson216_translation10)
            addSimpleText(parent, R.string.lesson216_text12)
            addPhrase(parent, R.string.lesson216_phrase11, R.string.lesson216_translation11)
            addSimpleText(parent, R.string.lesson216_text13)
            addPhrase(parent, R.string.lesson216_phrase12, R.string.lesson216_translation12)


        }

        // Lesson 17 (A2) - 2.17
        private fun createLesson217(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson217_text01)
            addPhrase(parent, R.string.lesson217_phrase01, R.string.lesson217_translation01)
            addPhrase(parent, R.string.lesson217_phrase02, R.string.lesson217_translation02)
            addPhrase(parent, R.string.lesson217_phrase03, R.string.lesson217_translation03)
            addSimpleText(parent, R.string.lesson217_text02)
            addPhrase(parent, R.string.lesson217_phrase04, R.string.lesson217_translation04)
            addPhrase(parent, R.string.lesson217_phrase05, R.string.lesson217_translation05)
            addPhrase(parent, R.string.lesson217_phrase06, R.string.lesson217_translation06)
            addSimpleText(parent, R.string.lesson217_text03)
            addPhrase(parent, R.string.lesson217_phrase07, R.string.lesson217_translation07)
            addPhrase(parent, R.string.lesson217_phrase08, R.string.lesson217_translation08)
            addPhrase(parent, R.string.lesson217_phrase09, R.string.lesson217_translation09)
            addSimpleText(parent, R.string.lesson217_text04)
            addPhrase(parent, R.string.lesson217_phrase10, R.string.lesson217_translation10)
            addPhrase(parent, R.string.lesson217_phrase11, R.string.lesson217_translation11)
            addPhrase(parent, R.string.lesson217_phrase12, R.string.lesson217_translation12)
            addSimpleText(parent, R.string.lesson217_text05)
            addCenterText(parent, R.string.lesson217_text06)
        }

        // Lesson 01 (B1) - 3.01
        private fun createLesson301(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson301_text01)
            addPhrase(parent, R.string.lesson301_phrase01, R.string.lesson301_translation01)
            addSimpleText(parent, R.string.lesson301_text02)
            addPhrase(parent, R.string.lesson301_phrase02, R.string.lesson301_translation02)
            addSimpleText(parent, R.string.lesson301_text03)
            addPhrase(parent, R.string.lesson301_phrase03, R.string.lesson301_translation03)
            addSimpleText(parent, R.string.lesson301_text04)
        }

        // Lesson 02 (B1) - 3.02
        private fun createLesson302(parent: LinearLayout) {
            addCenterText(parent, R.string.lesson302_text01)
            addPhrase(parent, R.string.lesson302_phrase01, R.string.lesson302_translation01)
            addPhrase(parent, R.string.lesson302_phrase02, R.string.lesson302_translation02)
            addSimpleText(parent, R.string.lesson302_text02)
            addPhrase(parent, R.string.lesson302_phrase03, R.string.lesson302_translation03)
            addPhrase(parent, R.string.lesson302_phrase04, R.string.lesson302_translation04)
            addSimpleText(parent, R.string.lesson302_text03)
            addPhrase(parent, R.string.lesson302_phrase05, R.string.lesson302_translation05)
            addPhrase(parent, R.string.lesson302_phrase06, R.string.lesson302_translation06)
            addSimpleText(parent, R.string.lesson302_text04)
            addPhrase(parent, R.string.lesson302_phrase07, R.string.lesson302_translation07)
            addPhrase(parent, R.string.lesson302_phrase08, R.string.lesson302_translation08)
            addSimpleText(parent, R.string.lesson302_text05)
            addPhrase(parent, R.string.lesson302_phrase09, R.string.lesson302_translation09)
            addPhrase(parent, R.string.lesson302_phrase10, R.string.lesson302_translation10)
            addSimpleText(parent, R.string.lesson302_text06)
            addPhrase(parent, R.string.lesson302_phrase11, R.string.lesson302_translation11)
            addPhrase(parent, R.string.lesson302_phrase12, R.string.lesson302_translation12)
        }

        // Lesson 03 (B1) - 3.03
        private fun createLesson303(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson303_text01)
            addPhrase(parent, R.string.lesson303_phrase01, R.string.lesson303_translation01)
            addPhrase(parent, R.string.lesson303_phrase02, R.string.lesson303_translation02)
            addSimpleText(parent, R.string.lesson303_text02)
            addPhrase(parent, R.string.lesson303_phrase03, R.string.lesson303_translation03)
            addSimpleText(parent, R.string.lesson303_text03)
            addPhrase(parent, R.string.lesson303_phrase04, R.string.lesson303_translation04)
        }

        // Lesson 04 (B1) - 3.04
        private fun createLesson304(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson304_text01)
            addPhrase(parent, R.string.lesson304_phrase01, R.string.lesson304_translation01)
            addSimpleText(parent, R.string.lesson304_text02)
            addPhrase(parent, R.string.lesson304_phrase02, R.string.lesson304_translation02)
            addPhrase(parent, R.string.lesson304_phrase03, R.string.lesson304_translation03)
            addCenterText(parent, R.string.lesson304_text03)
            addSimpleText(parent, R.string.lesson304_text04)
            addPhrase(parent, R.string.lesson304_phrase04, R.string.lesson304_translation04)
            addSimpleText(parent, R.string.lesson304_text05)
            addPhrase(parent, R.string.lesson304_phrase05, R.string.lesson304_translation05)
            addCenterText(parent, R.string.lesson304_text06)
            addSimpleText(parent, R.string.lesson304_text07)
            addPhrase(parent, R.string.lesson304_phrase06, R.string.lesson304_translation06)
            addPhrase(parent, R.string.lesson304_phrase07, R.string.lesson304_translation07)
        }

        // Lesson 05 (B1) - 3.05
        private fun createLesson305(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson305_text01)
            addPhrase(parent, R.string.lesson305_phrase01, R.string.lesson305_translation01)
            addPhrase(parent, R.string.lesson305_phrase02, R.string.lesson305_translation02)
            addSimpleText(parent, R.string.lesson305_text02)
            addPhrase(parent, R.string.lesson305_phrase03, R.string.lesson305_translation03)
            addPhrase(parent, R.string.lesson305_phrase04, R.string.lesson305_translation04)
            addSimpleText(parent, R.string.lesson305_text03)
            addPhrase(parent, R.string.lesson305_phrase05, R.string.lesson305_translation05)
            addPhrase(parent, R.string.lesson305_phrase06, R.string.lesson305_translation06)
            addCenterText(parent, R.string.lesson305_text04)
        }
        // Lesson 06 (B1) - 3.06
        private fun createLesson306(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson306_text01)
            addCenterText(parent, R.string.lesson306_text02)
            addPhrase(parent, R.string.lesson306_phrase01, R.string.lesson306_translation01)
            addSimpleText(parent, R.string.lesson306_text03)
            addPhrase(parent, R.string.lesson306_phrase02, R.string.lesson306_translation02)
            addCenterText(parent, R.string.lesson306_text04)
            addSimpleText(parent, R.string.lesson306_text05)
            addPhrase(parent, R.string.lesson306_phrase03, R.string.lesson306_translation03)
            addSimpleText(parent, R.string.lesson306_text06)
            addPhrase(parent, R.string.lesson306_phrase04, R.string.lesson306_translation04)
            addSimpleText(parent, R.string.lesson306_text07)
            addPhrase(parent, R.string.lesson306_phrase05, R.string.lesson306_translation05)
            addPhrase(parent, R.string.lesson306_phrase06, R.string.lesson306_translation06)
            addPhrase(parent, R.string.lesson306_phrase07, R.string.lesson306_translation07)
            addPhrase(parent, R.string.lesson306_phrase08, R.string.lesson306_translation08)
            addCenterText(parent, R.string.lesson306_text08)
            addSimpleText(parent, R.string.lesson306_text09)
            addPhrase(parent, R.string.lesson306_phrase09, R.string.lesson306_translation09)
            addSimpleText(parent, R.string.lesson306_text10)
            addPhrase(parent, R.string.lesson306_phrase10, R.string.lesson306_translation10)
            addSimpleText(parent, R.string.lesson306_text11)
            addPhrase(parent, R.string.lesson306_phrase11, R.string.lesson306_translation11)
        }

        // Lesson 07 (B1) - 3.07
        private fun createLesson307(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson307_text01)
            addCenterText(parent, R.string.lesson307_text02)
            addCenterText(parent, R.string.lesson307_text03)
            addPhrase(parent, R.string.lesson307_phrase01, R.string.lesson307_translation01)
            addCenterText(parent, R.string.lesson307_text04)
            addPhrase(parent, R.string.lesson307_phrase02, R.string.lesson307_translation02)
            addCenterText(parent, R.string.lesson307_text05)
            addPhrase(parent, R.string.lesson307_phrase03, R.string.lesson307_translation03)
            addCenterText(parent, R.string.lesson307_text06)
            addCenterText(parent, R.string.lesson307_text07)
            addPhrase(parent, R.string.lesson307_phrase04, R.string.lesson307_translation04)
            addCenterText(parent, R.string.lesson307_text08)
            addPhrase(parent, R.string.lesson307_phrase05, R.string.lesson307_translation05)
            addCenterText(parent, R.string.lesson307_text09)
            addPhrase(parent, R.string.lesson307_phrase06, R.string.lesson307_translation06)
            addCenterText(parent, R.string.lesson307_text10)
            addCenterText(parent, R.string.lesson307_text11)
            addPhrase(parent, R.string.lesson307_phrase07, R.string.lesson307_translation07)
            addCenterText(parent, R.string.lesson307_text12)
            addPhrase(parent, R.string.lesson307_phrase08, R.string.lesson307_translation08)
            addCenterText(parent, R.string.lesson307_text13)
            addPhrase(parent, R.string.lesson307_phrase09, R.string.lesson307_translation09)
            addCenterText(parent, R.string.lesson307_text14)
            addCenterText(parent, R.string.lesson307_text15)
            addPhrase(parent, R.string.lesson307_phrase10, R.string.lesson307_translation10)
            addCenterText(parent, R.string.lesson307_text16)
            addPhrase(parent, R.string.lesson307_phrase11, R.string.lesson307_translation11)
            addCenterText(parent, R.string.lesson307_text17)
            addPhrase(parent, R.string.lesson307_phrase12, R.string.lesson307_translation12)
        }

        // Lesson 08 (B1) - 3.08
        private fun createLesson308(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson308_text01)
            addPhrase(parent, R.string.lesson308_phrase01, R.string.lesson308_translation01)
            addSimpleText(parent, R.string.lesson308_text02)
            addPhrase(parent, R.string.lesson308_phrase02, R.string.lesson308_translation02)
            addSimpleText(parent, R.string.lesson308_text03)
            addPhrase(parent, R.string.lesson308_phrase03, R.string.lesson308_translation03)
            addSimpleText(parent, R.string.lesson308_text04)
            addPhrase(parent, R.string.lesson308_phrase04, R.string.lesson308_translation04)
            addSimpleText(parent, R.string.lesson308_text05)
            addPhrase(parent, R.string.lesson308_phrase05, R.string.lesson308_translation05)
            addPhrase(parent, R.string.lesson308_phrase06, R.string.lesson308_translation06)
        }

        // Lesson 09 (B1) - 3.09
        private fun createLesson309(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson309_text01)
            addPhrase(parent, R.string.lesson309_phrase01, R.string.lesson309_translation01)
            addPhrase(parent, R.string.lesson309_phrase02, R.string.lesson309_translation02)
            addSimpleText(parent, R.string.lesson309_text02)
            addPhrase(parent, R.string.lesson309_phrase03, R.string.lesson309_translation03)
            addSimpleText(parent, R.string.lesson309_text03)
            addPhrase(parent, R.string.lesson309_phrase04, R.string.lesson309_translation04)
            addSimpleText(parent, R.string.lesson309_text04)
            addCenterText(parent, R.string.lesson309_text05)
            addSimpleText(parent, R.string.lesson309_text06)
            addPhrase(parent, R.string.lesson309_phrase05, R.string.lesson309_translation05)
            addCenterText(parent, R.string.lesson309_text07)
            addPhrase(parent, R.string.lesson309_phrase06, R.string.lesson309_translation06)
            addSimpleText(parent, R.string.lesson309_text08)
        }

        // Lesson 10 (B1) - 3.10
        private fun createLesson310(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson310_text01)
            addPhrase(parent, R.string.lesson310_phrase01, R.string.lesson310_translation01)
            addPhrase(parent, R.string.lesson310_phrase02, R.string.lesson310_translation02)
            addPhrase(parent, R.string.lesson310_phrase03, R.string.lesson310_translation03)
            addSimpleText(parent, R.string.lesson310_text02)
            addPhrase(parent, R.string.lesson310_phrase04, R.string.lesson310_translation04)
            addPhrase(parent, R.string.lesson310_phrase05, R.string.lesson310_translation05)
            addSimpleText(parent, R.string.lesson310_text03)
        }

        // Lesson 11 (B1) - 3.11
        private fun createLesson311(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson311_text01)
            addPhrase(parent, R.string.lesson311_phrase01, R.string.lesson311_translation01)
            addPhrase(parent, R.string.lesson311_phrase02, R.string.lesson311_translation02)
            addSimpleText(parent, R.string.lesson311_text02)
            addCenterText(parent, R.string.lesson311_text03)
            addSimpleText(parent, R.string.lesson311_text04)
            addPhrase(parent, R.string.lesson311_phrase03, R.string.lesson311_translation03)
            addPhrase(parent, R.string.lesson311_phrase04, R.string.lesson311_translation04)
            addSimpleText(parent, R.string.lesson311_text05)
            addPhrase(parent, R.string.lesson311_phrase05, R.string.lesson311_translation05)
            addSimpleText(parent, R.string.lesson311_text06)
        }

        // Lesson 12 (B1) - 3.12
        private fun createLesson312(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson312_text01)
            addPhrase(parent, R.string.lesson312_phrase01, R.string.lesson312_translation01)
            addPhrase(parent, R.string.lesson312_phrase02, R.string.lesson312_translation02)
            addSimpleText(parent, R.string.lesson312_text02)
            addPhrase(parent, R.string.lesson312_phrase03, R.string.lesson312_translation03)
        }

        // Lesson 13 (B1) - 3.13
        private fun createLesson313(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson313_text01)
            addPhrase(parent, R.string.lesson313_phrase01, R.string.lesson313_translation01)
            addSimpleText(parent, R.string.lesson313_text02)
            addPhrase(parent, R.string.lesson313_phrase02, R.string.lesson313_translation02)
            addSimpleText(parent, R.string.lesson313_text03)
        }

        // Lesson 14 (B1) - 3.14
        private fun createLesson314(parent: LinearLayout) {
            addSimpleText(parent, R.string.lesson314_text01)
            addPhrase(parent, R.string.lesson314_phrase01, R.string.lesson314_translation01)
            addSimpleText(parent, R.string.lesson314_text02)
            addPhrase(parent, R.string.lesson314_phrase02, R.string.lesson314_translation02)
            addSimpleText(parent, R.string.lesson314_text03)
            addCenterText(parent, R.string.lesson314_text04)
            addPhrase(parent, R.string.lesson314_phrase03, R.string.lesson314_translation03)
            addCenterText(parent, R.string.lesson314_text05)
            addPhrase(parent, R.string.lesson314_phrase04, R.string.lesson314_translation04)
            addCenterText(parent, R.string.lesson314_text06)
            addPhrase(parent, R.string.lesson314_phrase05, R.string.lesson314_translation05)
            addPhrase(parent, R.string.lesson314_phrase06, R.string.lesson314_translation06)
            addCenterText(parent, R.string.lesson314_text07)
            addPhrase(parent, R.string.lesson314_phrase07, R.string.lesson314_translation07)
            addPhrase(parent, R.string.lesson314_phrase08, R.string.lesson314_translation08)
            addCenterText(parent, R.string.lesson314_text08)
            addPhrase(parent, R.string.lesson314_phrase09, R.string.lesson314_translation09)
            addPhrase(parent, R.string.lesson314_phrase10, R.string.lesson314_translation10)
            addCenterText(parent, R.string.lesson314_text09)
            addPhrase(parent, R.string.lesson314_phrase11, R.string.lesson314_translation11)
            addPhrase(parent, R.string.lesson314_phrase12, R.string.lesson314_translation12)
            addCenterText(parent, R.string.lesson314_text10)
            addPhrase(parent, R.string.lesson314_phrase13, R.string.lesson314_translation13)
            addPhrase(parent, R.string.lesson314_phrase14, R.string.lesson314_translation14)
            addCenterText(parent, R.string.lesson314_text11)
            addPhrase(parent, R.string.lesson314_phrase15, R.string.lesson314_translation15)
            addPhrase(parent, R.string.lesson314_phrase16, R.string.lesson314_translation16)
            addCenterText(parent, R.string.lesson314_text12)
            addPhrase(parent, R.string.lesson314_phrase17, R.string.lesson314_translation17)
            addPhrase(parent, R.string.lesson314_phrase18, R.string.lesson314_translation18)
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
            // assets for different languages
            val appLanguage = Locale.getDefault().language
            val path = if (appLanguage == "ru") "tables-ru/" else "tables/"

            val stub = ViewStub(parent.context)
            parent.addView(stub)
            stub.layoutResource = R.layout.include_image
            stub.setOnInflateListener { _, inflated ->
                // Set Content
                val imageView = inflated.findViewById<ImageView>(R.id.imageView)
                val ims = parent.context.assets.open(path + fileName)
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
        const val LESSON_2_01 = "2.01"
        const val LESSON_2_02 = "2.02"
        const val LESSON_2_03 = "2.03"
        const val LESSON_2_04 = "2.04"
        const val LESSON_2_05 = "2.05"
        const val LESSON_2_06 = "2.06"
        const val LESSON_2_07 = "2.07"
        const val LESSON_2_08 = "2.08"
        const val LESSON_2_09 = "2.09"
        const val LESSON_2_10 = "2.10"
        const val LESSON_2_11 = "2.11"
        const val LESSON_2_12 = "2.12"
        const val LESSON_2_13 = "2.13"
        const val LESSON_2_14 = "2.14"
        const val LESSON_2_15 = "2.15"
        const val LESSON_2_16 = "2.16"
        const val LESSON_2_17 = "2.17"
        //B1 :
        const val LESSON_3_01 = "3.01"
        const val LESSON_3_02 = "3.02"
        const val LESSON_3_03 = "3.03"
        const val LESSON_3_04 = "3.04"
        const val LESSON_3_05 = "3.05"
        const val LESSON_3_06 = "3.06"
        const val LESSON_3_07 = "3.07"
        const val LESSON_3_08 = "3.08"
        const val LESSON_3_09 = "3.09"
        const val LESSON_3_10 = "3.10"
        const val LESSON_3_11 = "3.11"
        const val LESSON_3_12 = "3.12"
        const val LESSON_3_13 = "3.13"
        const val LESSON_3_14 = "3.14"
    }
}