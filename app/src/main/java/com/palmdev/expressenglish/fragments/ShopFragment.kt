package com.palmdev.expressenglish.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentShopBinding
import java.util.*
import kotlin.system.exitProcess


class ShopFragment : Fragment(R.layout.fragment_shop) {

    private lateinit var binding: FragmentShopBinding
    private val mPrice = "4.99$"
    private val mHandler = Handler()
    private lateinit var mCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShopBinding.bind(view)

        binding.tvOldPrice.text = Html.fromHtml(getString(R.string.oldPrice))
        binding.tvNewPrice.text = mPrice

        // Init Countdown Timer
        mHandler.post(object : Runnable {
            override fun run() {
                Handler().postDelayed(this, 1000)
                updateTimer()
            }
        })


    }

    private fun updateTimer(){
        val currentDate = Calendar.getInstance()
        var nextMonth = currentDate[Calendar.MONTH] + 1
        var neededYear = currentDate[Calendar.YEAR]
        // if it's the last month of the year
        if (nextMonth == 12) {
            nextMonth = 0
            neededYear++
        }

        // set event date to the 3th of the next month
        val eventDate = Calendar.getInstance()
        eventDate[Calendar.YEAR] = neededYear
        eventDate[Calendar.MONTH] = nextMonth
        eventDate[Calendar.DAY_OF_MONTH] = 3
        eventDate[Calendar.MINUTE] = 0
        eventDate[Calendar.SECOND] = 0
        eventDate.timeZone = TimeZone.getTimeZone("GMT")

        // find how many milliseconds until the event
        val diff = eventDate.timeInMillis - currentDate.timeInMillis

        // change the milliseconds to days, hours, minutes and seconds
        val days = diff / (24 * 60 * 60 * 1000)
        val hours = diff / (1000 * 60 * 60) % 24
        val minutes = diff / (1000 * 60) % 60
        val seconds = (diff / 1000) % 60

        val text =
            "$days ${getString(R.string.days)} - $hours ${getString(R.string.hours)} - " +
                    "$minutes ${getString(R.string.min)} - $seconds ${getString(R.string.sec)}"

        binding.tvTimer.text = text
    }

    private fun setOnBackPressedCallback(){
        mCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeFragment)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }


    override fun onPause() {
        mCallback.remove()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        setOnBackPressedCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(null)
    }

}