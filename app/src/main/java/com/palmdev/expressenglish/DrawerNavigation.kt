package com.palmdev.expressenglish

import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ToggleButton
import com.google.android.material.navigation.NavigationView
import com.palmdev.expressenglish.data.SharedPref
import android.content.ComponentName

import android.app.ActivityManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import java.lang.Exception


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
                val email = Intent(Intent.ACTION_SENDTO)
                email.data = Uri.parse("mailto:" + it.context.getString(R.string.support_email))
                email.putExtra(Intent.EXTRA_SUBJECT, "Support Learn German")
                it.context.startActivity(email)
            }

            btnPrivacyPolicy.setOnClickListener {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(it.context.getString(R.string.privacy_policy_address)))
                it.context.startActivity(browserIntent)
            }

            btnShare.setOnClickListener {
                try {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Express English")
                    var shareMessage = "\nLearn English fast with this app!\n\n"
                    shareMessage =
                        "${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    it.context.startActivity(Intent.createChooser(shareIntent, "choose one"))
                } catch (e: Exception) {
                    //e.toString();
                }
            }

            btnBuyPremium.setOnClickListener {
                MainActivity.navController.navigate(R.id.shopFragment)
            }

            btnOurApps.setOnClickListener {
                try {
                    it.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://developer?id=DevPalm")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    it.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/developer?id=DevPalm")
                        )
                    )
                }
            }

            btnChangeLanguage.setOnClickListener {
                val dialog = Dialogs.dialogAppLanguages(navigationView.context)
                dialog.show()
            }

            btnRateUs.setOnClickListener {
                it.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    )
                )
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
                it.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    )
                )
            }
            star5.setOnClickListener {
                SharedPref.put(SharedPref.USER_RATING, 5)
                updateRating()
                it.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    )
                )
            }

        }
    }
}