package com.palmdev.expressenglish

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.ActivityMainBinding
import com.palmdev.expressenglish.utils.TinyDB

import com.palmdev.expressenglish.utils.LocaleHelper
import java.util.*
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.ump.*
import com.google.android.ump.ConsentForm.OnConsentFormDismissedListener
import com.google.android.ump.ConsentInformation.OnConsentInfoUpdateFailureListener
import com.google.android.ump.ConsentInformation.OnConsentInfoUpdateSuccessListener
import com.google.android.ump.UserMessagingPlatform.OnConsentFormLoadFailureListener
import com.google.android.ump.UserMessagingPlatform.OnConsentFormLoadSuccessListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    // Funding Choice
    private var consentInformation: ConsentInformation? = null
    private var consentForm: ConsentForm? = null

    override fun attachBaseContext(base: Context?) {
        val locale = Locale.getDefault().language
        super.attachBaseContext(LocaleHelper.onAttach(base, locale))
    }

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
        DrawerNavigation.initDrawerNavigation(binding.navigationView)

        // Init util for Array Shared Pref
        tinyDB = TinyDB(this)

        // Init Ads
        MobileAds.initialize(this)
        Ads.loadRewardedAd(this)

        // Init Firebase
        FirebaseApp.initializeApp(this)

        // Init Firebase Messaging
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task: Task<String> ->
                if (!task.isSuccessful) {
                    return@addOnCompleteListener
                }
                val token = task.result
                Log.d("TAG", "Token ->$token")
            }

        // Init Funding Choice
        val params = ConsentRequestParameters.Builder()
            .setTagForUnderAgeOfConsent(false)
            .build()
        consentInformation = UserMessagingPlatform.getConsentInformation(this)
        consentInformation?.requestConsentInfoUpdate(
            this,
            params,
            { // The consent information state was updated.
                // You are now ready to check if a form is available.
                if (consentInformation?.isConsentFormAvailable == true) {
                    loadForm()
                }
            },
            {
                // Handle the error.
            })

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


    // Funding Choice Form
    private fun loadForm() {
        UserMessagingPlatform.loadConsentForm(
            this,
            { consentForm ->
                this.consentForm = consentForm
                if (consentInformation!!.consentStatus == ConsentInformation.ConsentStatus.REQUIRED) {
                    consentForm.show(
                        this@MainActivity
                    ) { // Handle dismissal by reloading form.
                        loadForm()
                    }
                }
            }
        ) {
            // Handle the error
        }
    }

    companion object {
        lateinit var bottomNavigationView: BottomNavigationView

        @SuppressLint("StaticFieldLeak")
        lateinit var navController: NavController

        @SuppressLint("StaticFieldLeak")
        lateinit var tinyDB: TinyDB

        // Ads
        //private var mRewardedAd: RewardedAd? = null

    }
}


