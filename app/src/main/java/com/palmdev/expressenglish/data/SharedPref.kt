package com.palmdev.expressenglish.data

import android.content.SharedPreferences

import android.app.Activity
import android.content.Context
import com.palmdev.expressenglish.MainActivity


object SharedPref {
    private var mSharedPref: SharedPreferences? = null

    const val USER_NAME = "USER_NAME"
    const val USER_LEVEL = "USER_LEVEL"
    const val USER_GENDER = "USER_GENDER"
    const val USER_LANGUAGE_NAME = "USER_LANGUAGE_NAME"
    const val USER_TRANSLATOR_LANGUAGE_CODE = "USER_TRANSLATOR_LANGUAGE_CODE"
    const val FAVORITE_BOOKS = "FAVORITE_BOOKS"
    const val SELECTED_WORDS = "SELECTED_WORDS"
    const val LEARNED_TOPICS = "LEARNED_TOPICS"
    const val EXAMS_PASSED = "EXAMS_PASSED"

    const val WORDS_ARRAY = "WORDS_ARRAY"
    const val TRANSLATED_WORDS_ARRAY = "TRANSLATED_WORDS_ARRAY"
    const val PHRASES_ARRAY = "PHRASES_ARRAY"
    const val BOOK_DARK_MODE = "BOOK_DARK_MODE"
    const val BOOK_FONT_SIZE = "BOOK_FONT_SIZE"
    const val BOOK_PAGE = "BOOK_PAGE"

    fun init(context: Context) {
        if (mSharedPref == null) mSharedPref =
            context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
    }

    fun get(key: String?, defValue: String?): String? {
        return mSharedPref!!.getString(key, defValue)
    }

    fun put(key: String?, value: String?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun get(key: String?, defValue: Boolean): Boolean {
        return mSharedPref!!.getBoolean(key, defValue)
    }

    fun put(key: String?, value: Boolean) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    fun get(key: String?, defValue: Int): Int {
        return mSharedPref!!.getInt(key, defValue)
    }

    fun put(key: String?, value: Int?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putInt(key, value!!)
            .apply()
    }

    fun putArray(key: String?, value: ArrayList<String>?) {
        MainActivity.tinyDB.putListString(key, value)
    }
    fun getArray(key: String?): ArrayList<String> {
        return MainActivity.tinyDB.getListString(key)
    }

    fun addToArray(ArrayKey: String?, value: String?) {
        val array = MainActivity.tinyDB.getListString(ArrayKey)
        array.add(value)
        MainActivity.tinyDB.putListString(ArrayKey, array)
    }
}