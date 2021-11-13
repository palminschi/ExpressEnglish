package com.palmdev.expressenglish

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.mlkit.nl.translate.TranslateLanguage
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.DialogAddWordBinding
import com.palmdev.expressenglish.utils.AllLanguages
import com.palmdev.expressenglish.utils.MyTextToSpeech
import com.palmdev.expressenglish.utils.Translate
import androidx.core.content.ContextCompat.getSystemService




class Dialogs {
    companion object {
        fun dialogSelectLanguage(context: Context): Dialog {

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

            val userLang = SharedPref.get(SharedPref.USER_LANGUAGE_NAME, "?")
            val userTranslatorLang = SharedPref.get(SharedPref.USER_TRANSLATOR_LANGUAGE_CODE, "?")
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
                val translateToLang = allLanguagesCode[spinner.selectedItemId.toInt()]
                Translate.createTranslator(TranslateLanguage.ENGLISH, translateToLang)
                Translate.getTranslator()
                    ?.downloadModelIfNeeded()
                    ?.addOnSuccessListener {
                        progressBar.visibility = View.GONE
                        btnDownload.visibility = View.VISIBLE
                        // Save User Languages for Translator
                        SharedPref.put(
                            SharedPref.USER_TRANSLATOR_LANGUAGE_CODE,
                            allLanguagesCode[spinner.selectedItemId.toInt()]
                        )
                    }
                    ?.addOnFailureListener {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    ?.addOnCanceledListener {
                        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show()
                    }
                    ?.addOnCompleteListener { dialogLanguages.dismiss() }

                // If No Internet
                val tvNoInternet = dialogLanguages.findViewById<TextView>(R.id.tvNoInternet)
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    tvNoInternet.visibility = View.GONE
                    btnDownload.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                } else {
                    tvNoInternet.visibility = View.VISIBLE
                    btnDownload.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
            btnBack.setOnClickListener {
                dialogLanguages.dismiss()
                MainActivity.navController.popBackStack()

            }

           // dialogLanguages.show()
            return dialogLanguages
        }

        fun dialogAddWord(context: Context, word: String, translation: String ): Dialog {
            // Create Dialog
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_add_word)
            val binding = DialogAddWordBinding.bind(dialog.findViewById(R.id.cardView))
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))


            binding.apply {
                dialogWord.setText(word)
                dialogTranslatedWord.setText(translation)
                val tvWord = dialogWord.text.toString()
                val tvTranslatedWord = dialogTranslatedWord.text.toString()
                val tvPhrase = dialogPhrase.text.toString()

                btnExample.setOnClickListener {
                    dialogPhrase.visibility = View.VISIBLE
                    btnExample.visibility = View.GONE
                    dialogPhrase.requestFocus()
                    val imm: InputMethodManager? =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm?.showSoftInput(dialogPhrase, InputMethodManager.SHOW_IMPLICIT)
                }

                btnCancel.setOnClickListener {
                    dialog.dismiss()
                }

                btnAdd.setOnClickListener {
                    if (dialogWord.text.isNotEmpty() && dialogTranslatedWord.text.isNotEmpty()) {
                        SharedPref.addToArray(SharedPref.WORDS_ARRAY,
                            dialogWord.text.toString())
                        SharedPref.addToArray(SharedPref.TRANSLATED_WORDS_ARRAY,
                            dialogTranslatedWord.text.toString())
                        SharedPref.addToArray(SharedPref.PHRASES_ARRAY,
                            dialogPhrase.text.toString())
                        val numberOfSelectedWords =
                            SharedPref.get(SharedPref.SELECTED_WORDS, 0)
                        SharedPref.put(SharedPref.SELECTED_WORDS, numberOfSelectedWords + 1)

                        dialog.dismiss()
                    } else {
                        this.dialogWord.setHintTextColor(context.getColor(R.color.red))
                        this.dialogTranslatedWord.setHintTextColor(context.getColor(R.color.red))
                    }
                }

                btnSound.setOnClickListener {
                    MyTextToSpeech.play(dialogWord.text,context)
                }

            }
            //dialog.show()
            return dialog
        }
    }
}