package com.palmdev.expressenglish.data

import android.content.SharedPreferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences.Editor


object SharedPref {
    private var mSharedPref: SharedPreferences? = null
    const val BOOK_DARK_MODE = "BOOK_DARK_MODE"
    const val BOOK_FONT_SIZE = "BOOK_FONT_SIZE"
    const val BOOK_PAGE = "BOOK_PAGE"
    const val FAVORITE_BOOKS = "FAVORITE_BOOKS"
    const val SELECTED_WORDS = "SELECTED_WORDS"
    const val LEARNED_TOPICS = "LEARNED_TOPICS"
    const val EXAMS_PASSED = "EXAMS_PASSED"
    const val USER_LANGUAGE_NAME = "USER_LANGUAGE_NAME"
    const val USER_TRANSLATOR_LANGUAGE_CODE = "USER_TRANSLATOR_LANGUAGE_CODE"

    fun init(context: Context) {
        if (mSharedPref == null) mSharedPref =
            context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
    }

    fun read(key: String?, defValue: String?): String? {
        return mSharedPref!!.getString(key, defValue)
    }

    fun write(key: String?, value: String?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun read(key: String?, defValue: Boolean): Boolean {
        return mSharedPref!!.getBoolean(key, defValue)
    }

    fun write(key: String?, value: Boolean) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    fun read(key: String?, defValue: Int): Int {
        return mSharedPref!!.getInt(key, defValue)
    }

    fun write(key: String?, value: Int?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putInt(key, value!!).apply()
    }
}