package com.palmdev.expressenglish

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.ActivityMainBinding
import com.palmdev.expressenglish.utils.AllLanguages
import com.palmdev.expressenglish.utils.TinyDB

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var bottomNavigationView: BottomNavigationView
        @SuppressLint("StaticFieldLeak")
        lateinit var navController: NavController
        @SuppressLint("StaticFieldLeak")
        lateinit var tinyDB: TinyDB
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Init Shared Preferences
        SharedPref.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init BottomNavigationView and Navigation Component
        bottomNavigationView = binding.bottomNavigationView
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Set User Lang
        AllLanguages.initLanguages()
        val userLang = "Русский"
        SharedPref.put(SharedPref.USER_LANGUAGE_NAME, userLang)

        // Init util for Array Shared Pref
        tinyDB = TinyDB(this)
    }
}


