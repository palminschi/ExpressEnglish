package com.palmdev.expressenglish

import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ToggleButton
import com.google.android.material.navigation.NavigationView
import com.palmdev.expressenglish.data.SharedPref

class DrawerNavigation {
    companion object{
        fun initDrawerNavigation(navigationView: NavigationView){
            val header = navigationView.getHeaderView(0)

            val btnHelp = header.findViewById<FrameLayout>(R.id.btnHelp)
            val btnPrivacyPolicy = header.findViewById<FrameLayout>(R.id.btnPrivacyPolicy)
            val btnShare = header.findViewById<FrameLayout>(R.id.btnShare)
            val btnBuyPremium = header.findViewById<LinearLayout>(R.id.btnBuyPremium)
            val btnOurApps = header.findViewById<FrameLayout>(R.id.btnOurApps)
            val btnChangeLanguage = header.findViewById<FrameLayout>(R.id.btnChangeLanguage)
            val btnRateUs = header.findViewById<FrameLayout>(R.id.btnRateUs)
            val star1 = header.findViewById<ToggleButton>(R.id.star1)
            val star2 = header.findViewById<ToggleButton>(R.id.star2)
            val star3 = header.findViewById<ToggleButton>(R.id.star3)
            val star4 = header.findViewById<ToggleButton>(R.id.star4)
            val star5 = header.findViewById<ToggleButton>(R.id.star5)
            val starsList = listOf(star1,star2,star3,star4,star5)

            btnHelp.setOnClickListener {

            }

            btnPrivacyPolicy.setOnClickListener {

            }

            btnShare.setOnClickListener {

            }

            btnBuyPremium.setOnClickListener {

            }

            btnOurApps.setOnClickListener {

            }

            btnChangeLanguage.setOnClickListener {

            }

            btnRateUs.setOnClickListener {

            }
            // Set Rating
            fun updateRating(){
                when(SharedPref.get(SharedPref.USER_RATING, 0)){
                    1 -> {
                        star1.isChecked = true
                        star2.isChecked = false
                        star3.isChecked = false
                        star4.isChecked = false
                        star5.isChecked = false
                    }
                    2 -> {
                        star1.isChecked = true
                        star2.isChecked = true
                        star3.isChecked = false
                        star4.isChecked = false
                        star5.isChecked = false
                    }
                    3 -> {
                        star1.isChecked = true
                        star2.isChecked = true
                        star3.isChecked = true
                        star4.isChecked = false
                        star5.isChecked = false
                    }
                    4 -> {
                        star1.isChecked = true
                        star2.isChecked = true
                        star3.isChecked = true
                        star4.isChecked = true
                        star5.isChecked = false
                    }
                    5 -> {
                        star1.isChecked = true
                        star2.isChecked = true
                        star3.isChecked = true
                        star4.isChecked = true
                        star5.isChecked = true
                    }
                }
            }
            updateRating()
            star1.setOnClickListener {
                SharedPref.put(SharedPref.USER_RATING, 1)
                updateRating()
            }
            star2.setOnClickListener {
                SharedPref.put(SharedPref.USER_RATING, 2)
                updateRating()
            }
            star3.setOnClickListener {
                SharedPref.put(SharedPref.USER_RATING, 3)
                updateRating()
            }
            star4.setOnClickListener {
                SharedPref.put(SharedPref.USER_RATING, 4)
                updateRating()
            }
            star5.setOnClickListener {
                SharedPref.put(SharedPref.USER_RATING, 5)
                updateRating()
            }

        }
    }
}