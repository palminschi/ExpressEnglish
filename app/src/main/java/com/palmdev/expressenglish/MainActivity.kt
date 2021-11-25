package com.palmdev.expressenglish

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.ActivityMainBinding
import com.palmdev.expressenglish.utils.AllLanguages
import com.palmdev.expressenglish.utils.TinyDB
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.ui.navigateUp

import com.google.android.material.navigation.NavigationBarView
import com.palmdev.expressenglish.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init Shared Preferences
        SharedPref.init(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Init Navigation
        drawerLayout = binding.drawerLayout
        bottomNavigationView = binding.bottomNavigationView
        navController = findNavController(R.id.nav_host_fragment)
        binding.navigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        initBottomNavigationButtons()



        // Set User Lang

        val userLang = "Русский"
        SharedPref.put(SharedPref.USER_LANGUAGE_NAME, userLang)

        // Init util for Array Shared Pref
        tinyDB = TinyDB(this)



    }

    private fun initBottomNavigationButtons(){
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemMore -> {
                    drawerLayout.openDrawer(GravityCompat.END)
                    return@setOnItemSelectedListener false
                }
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.booksFragment -> navController.navigate(R.id.booksFragment)
                R.id.grammarFragment -> navController.navigate(R.id.grammarFragment)
                R.id.translatorFragment -> navController.navigate(R.id.translatorFragment)
            }
            true
        }
    }

    override fun onBackPressed() {
        // If Drawer is Open
        if (drawerLayout.isDrawerOpen(binding.navigationView)) {
            drawerLayout.closeDrawer(GravityCompat.END)
            return
        }
        super.onBackPressed()
    }

    companion object {
        lateinit var bottomNavigationView: BottomNavigationView

        @SuppressLint("StaticFieldLeak")
        lateinit var navController: NavController

        @SuppressLint("StaticFieldLeak")
        lateinit var tinyDB: TinyDB

    }
}


