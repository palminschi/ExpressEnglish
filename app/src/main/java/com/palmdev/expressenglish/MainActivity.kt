package com.palmdev.expressenglish

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var bottomNavigationView: BottomNavigationView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Init Shared Preferences
        SharedPref.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init BottomNavigationView and Navigation Component
        bottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)





    }
}


