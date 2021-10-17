package com.palmdev.expressenglish

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.*
import com.google.mlkit.nl.translate.TranslateLanguage
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.utils.AllLanguages
import com.palmdev.expressenglish.utils.Translate

class Dialogs {
    companion object {
        fun showDialogLanguages(context: Context) {

            // init available languages
            AllLanguages.initLanguages()
            val allLanguagesName = AllLanguages.allLanguagesName
            val allLanguagesCode = AllLanguages.allLanguagesCode

            // create dialog
            val dialogLanguages = Dialog(context)
            dialogLanguages.setContentView(R.layout.dialog_languages)
            dialogLanguages.setCancelable(false)

            // create spinner
            val spinner = dialogLanguages.findViewById<Spinner>(R.id.spinnerLanguages)
            val adapter = ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item,
                allLanguagesName
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            val userLang = SharedPref.read(SharedPref.USER_LANGUAGE_NAME, "?")
            val userTranslatorLang = SharedPref.read(SharedPref.USER_TRANSLATOR_LANGUAGE_CODE, "?")
            if (userLang != "?") {
                spinner.setSelection(allLanguagesName.indexOf(userLang))
            }
            if (userTranslatorLang != "?") {
                spinner.setSelection(allLanguagesCode.indexOf(userTranslatorLang))
            }

            // init buttons
            val btnDownload = dialogLanguages.findViewById<Button>(R.id.btnDownload)
            val btnBack = dialogLanguages.findViewById<TextView>(R.id.btnBack)
            val progressBar = dialogLanguages.findViewById<ProgressBar>(R.id.progressBar)
            btnDownload.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                btnDownload.visibility = View.INVISIBLE
                val translateToLang =  allLanguagesCode[spinner.selectedItemId.toInt()]
                Translate.createTranslator( TranslateLanguage.ENGLISH, translateToLang)
                Translate.getTranslator()
                    ?.downloadModelIfNeeded()
                    ?.addOnSuccessListener {
                        progressBar.visibility = View.GONE
                        btnDownload.visibility = View.VISIBLE
                        // Save User Languages for Translator
                        SharedPref.write(
                            SharedPref.USER_TRANSLATOR_LANGUAGE_CODE,
                            allLanguagesCode[spinner.selectedItemId.toInt()]
                        )
                    }
                    ?.addOnFailureListener {
                        Toast.makeText( context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    ?.addOnCanceledListener {
                        Toast.makeText( context, "Canceled", Toast.LENGTH_SHORT).show()
                    }
                    ?.addOnCompleteListener { dialogLanguages.dismiss() }

                // If No Internet
                val tvNoInternet = dialogLanguages.findViewById<TextView>(R.id.tvNoInternet)
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null)  {
                    tvNoInternet.visibility = View.GONE
                    btnDownload.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                }else {
                    tvNoInternet.visibility = View.VISIBLE
                    btnDownload.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
            btnBack.setOnClickListener {
                dialogLanguages.dismiss()
                MainActivity.navController.popBackStack()

            }

            dialogLanguages.show()
        }
    }
}