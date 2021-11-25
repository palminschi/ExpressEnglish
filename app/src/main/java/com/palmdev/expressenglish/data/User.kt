package com.palmdev.expressenglish.data

import android.content.Context
import com.palmdev.expressenglish.R

class User {

    companion object{

        fun getLevel(context: Context): String {
            return SharedPref.get(SharedPref.USER_LEVEL, context.getString(R.string.unknownLvl))!!
        }
        fun setLevel(level: String){
            SharedPref.put(SharedPref.USER_LEVEL, level)
        }

        fun getName(context: Context): String {
            return SharedPref.get(SharedPref.USER_NAME, context.getString(R.string.learner))!!
        }
        fun setName(name: String){
            SharedPref.put(SharedPref.USER_LEVEL, name)
        }

        fun getGender(context: Context): String{
            return SharedPref.get(SharedPref.USER_GENDER, context.getString(R.string.man))!!
        }
        fun setGender(gender: String){
            SharedPref.put(SharedPref.USER_GENDER, gender)
        }

        fun getFavoriteBooks(): Int {
            return SharedPref.get(SharedPref.FAVORITE_BOOKS, 0)
        }
        fun addFavoriteBook() {
            var favoriteBooks = getFavoriteBooks()
            favoriteBooks++
            SharedPref.put(SharedPref.FAVORITE_BOOKS, favoriteBooks)
        }
        fun removeFavoriteBook(){
            var favoriteBooks = getFavoriteBooks()
            favoriteBooks--
            SharedPref.put(SharedPref.FAVORITE_BOOKS, favoriteBooks)
        }

        fun getSelectedWords(): Int {
            return SharedPref.get(SharedPref.SELECTED_WORDS, 0)
        }
        fun addSelectedWord() {
            var selectedWords = getSelectedWords()
            selectedWords++
            SharedPref.put(SharedPref.SELECTED_WORDS, selectedWords)
        }
        fun removeSelectedWord() {
            var selectedWords = getSelectedWords()
            selectedWords--
            SharedPref.put(SharedPref.SELECTED_WORDS, selectedWords)
        }

        fun getLearnedTopics(): Int {
            return SharedPref.get(SharedPref.LEARNED_TOPICS, 0)
        }
        fun addLearnedTopics() {
            var learnedTopics = getLearnedTopics()
            learnedTopics++
            SharedPref.put(SharedPref.LEARNED_TOPICS, learnedTopics)
        }
        fun removeLearnedTopics() {
            var learnedTopics = getLearnedTopics()
            learnedTopics--
            SharedPref.put(SharedPref.LEARNED_TOPICS, learnedTopics)
        }

        fun getExamsPassed(): Int {
            return SharedPref.get(SharedPref.EXAMS_PASSED, 0)
        }
        fun addExamsPassed() {
            var examsPassed = getExamsPassed()
            examsPassed++
            SharedPref.put(SharedPref.EXAMS_PASSED, examsPassed)
        }
        fun removeExamsPassed() {
            var examsPassed = getExamsPassed()
            examsPassed--
            SharedPref.put(SharedPref.EXAMS_PASSED, examsPassed)
        }

        fun getAvatar(context: Context): Int{

            val userGender = this.getGender(context)
            val userLevel = this.getLevel(context)

            if (userGender == context.getString(R.string.woman)){
                return when (userLevel) {
                    context.getString(R.string.A1Lvl) -> R.drawable.avatar_w_a1
                    context.getString(R.string.A2Lvl) -> R.drawable.avatar_w_a2
                    context.getString(R.string.B1Lvl) -> R.drawable.avatar_w_b1
                    context.getString(R.string.B2Lvl) -> R.drawable.avatar_w_b2
                    context.getString(R.string.C1Lvl) -> R.drawable.avatar_w_c1
                    context.getString(R.string.C2Lvl) -> R.drawable.avatar_w_c2
                    else -> R.drawable.avatar_w_b1
                }
            }else{
                return when (userLevel) {
                    context.getString(R.string.A1Lvl) -> R.drawable.avatar_m_a1
                    context.getString(R.string.A2Lvl) -> R.drawable.avatar_m_a2
                    context.getString(R.string.B1Lvl) -> R.drawable.avatar_m_b1
                    context.getString(R.string.B2Lvl) -> R.drawable.avatar_m_b2
                    context.getString(R.string.C1Lvl) -> R.drawable.avatar_m_c1
                    context.getString(R.string.C2Lvl) -> R.drawable.avatar_m_c2
                    else -> R.drawable.avatar_m_b1
                }
            }
        }
    }
}