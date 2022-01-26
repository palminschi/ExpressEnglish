package com.palmdev.expressenglish.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentFirstOpenBinding
import com.palmdev.expressenglish.utils.AllLanguages
import java.util.*

class FirstOpenFragment : Fragment(R.layout.fragment_first_open) {

    private lateinit var binding: FragmentFirstOpenBinding
    private var mUserName: String = ""
    private var mUserGender: String = ""
    private var mUserLanguageName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstOpenBinding.bind(view)

        setSpinnerData()
        initSelectGender()
        initBtnDone()
    }

    private fun initBtnDone() {
        binding.btnDone.setOnClickListener {
            mUserName = binding.editText.text.toString()
            mUserLanguageName = binding.spinnerLanguages.selectedItem.toString()

            if (
                mUserName.isNotEmpty() &&
                mUserLanguageName.isNotEmpty() &&
                mUserGender.isNotEmpty()
            ) {
                // Save Data
                User.setLanguageName(mUserLanguageName)
                User.setName(mUserName)
                User.setGender(mUserGender)
                SharedPref.put(SharedPref.FIRST_OPEN, false)
                findNavController().navigate(R.id.action_firstOpenFragment_to_homeFragment)
            }
            if (mUserName.isEmpty()) binding.tvYourName.setTextColor(Color.RED)
            if (mUserGender.isEmpty()) binding.tvYourGender.setTextColor(Color.RED)
            if (mUserLanguageName.isEmpty()) binding.tvYourLang.setTextColor(Color.RED)
        }
    }

    private fun initSelectGender() {
        binding.toggleBtnMale.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    binding.toggleBtnFemale.isChecked = false
                    binding.btnMaleContainer.setCardBackgroundColor(resources.getColor(R.color.male))
                    mUserGender = getString(R.string.man)
                }
                false -> {
                    binding.btnMaleContainer.setCardBackgroundColor(resources.getColor(R.color.white))
                    mUserGender = ""
                }
            }
        }
        binding.toggleBtnFemale.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    binding.toggleBtnMale.isChecked = false
                    binding.btnFemaleContainer.setCardBackgroundColor(resources.getColor(R.color.female))
                    mUserGender = getString(R.string.woman)
                }
                false -> {
                    binding.btnFemaleContainer.setCardBackgroundColor(resources.getColor(R.color.white))
                    mUserGender = ""
                }
            }
        }
    }

    private fun setSpinnerData() {
        // init available languages
        AllLanguages.initLanguages()
        val allLanguagesName = AllLanguages.allLanguagesName
        val allLanguagesCode = AllLanguages.allLanguagesCode

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            allLanguagesName
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLanguages.adapter = adapter

        val deviceLanguage = Locale.getDefault().language
        if (deviceLanguage != "en") {
            binding.spinnerLanguages.setSelection(allLanguagesCode.indexOf(deviceLanguage))
        } else {
            binding.spinnerLanguages.setSelection(0)
        }
    }


    private fun hideBottomNav() {
        MainActivity.bottomNavigationView.visibility = View.GONE
    }

    private fun showBottomNav() {
        MainActivity.bottomNavigationView.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        hideBottomNav()

        // Firebase Event
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, FirstOpenFragment().javaClass.simpleName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, FirstOpenFragment().javaClass.simpleName)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun onStop() {
        super.onStop()
        showBottomNav()
    }
}