package com.palmdev.expressenglish

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.mlkit.nl.translate.TranslateLanguage
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.DialogAddWordBinding
import com.palmdev.expressenglish.utils.AllLanguages
import com.palmdev.expressenglish.utils.MyTextToSpeech
import com.palmdev.expressenglish.utils.Translate
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.utils.LocaleHelper
import android.content.Intent.getIntent
import android.app.Activity
import android.graphics.Color
import java.util.*


class Dialogs {
    companion object {
        fun dialogTranslatorLanguages(context: Context): Dialog {

            // init available languages
            AllLanguages.initLanguages()
            val allLanguagesName = AllLanguages.allLanguagesName
            val allLanguagesCode = AllLanguages.allLanguagesCode

            // create dialog
            val dialogLanguages = Dialog(context)
            dialogLanguages.setContentView(R.layout.dialog_translator_languages)
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

            return dialogLanguages
        }

        fun dialogAddWord(context: Context, word: String, translation: String ): Dialog {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_add_word)
            val binding = DialogAddWordBinding.bind(dialog.findViewById(R.id.cardView))
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            binding.apply {
                dialogWord.setText(word)
                dialogTranslatedWord.setText(translation)

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
                        User.addSelectedWord()

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
            return dialog
        }

        fun dialogEndPractice(context: Context, rightAnswers: String): Dialog{
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_end_practice)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val tvRightAnswers = dialog.findViewById<TextView>(R.id.tvRightAnswers)
            val btnDone = dialog.findViewById<TextView>(R.id.btnDone)
            val imageView = dialog.findViewById<ImageView>(R.id.imageView)

            tvRightAnswers.text = rightAnswers

            btnDone.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setOnDismissListener {
                MainActivity.navController.popBackStack()
            }

            val imgRes =
                if (User.getGender(context) == context.getString(R.string.woman)){
                    R.drawable.avatar_w_b1
                }else {
                    R.drawable.avatar_m_b1
                }
            imageView.setImageResource(imgRes)

            return dialog
        }

        fun dialogAppLanguages(context: Context): Dialog {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_app_languages)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val btnEnglish = dialog.findViewById<TextView>(R.id.btnEnglish)
            val btnRussian = dialog.findViewById<TextView>(R.id.btnRussian)

            val currentAppLanguage = SharedPref.get(
                SharedPref.CURRENT_APP_LANGUAGE,
                Locale.getDefault().language
            )
            if (currentAppLanguage == "ru"){
                btnRussian.setBackgroundResource(R.drawable.style_selected_language)
                btnEnglish.setBackgroundColor(Color.WHITE)
            }else {
                btnEnglish.setBackgroundResource(R.drawable.style_selected_language)
                btnRussian.setBackgroundColor(Color.WHITE)
            }

            btnEnglish.setOnClickListener {
                LocaleHelper.setLocale(context, "en")
                context.startActivity(Intent(context,MainActivity::class.java))
                btnEnglish.setBackgroundResource(R.drawable.style_selected_language)
                btnRussian.setBackgroundColor(Color.WHITE)
                SharedPref.put(SharedPref.CURRENT_APP_LANGUAGE, "en")
            }
            btnRussian.setOnClickListener {
                LocaleHelper.setLocale(context, "ru")
                context.startActivity(Intent(context,MainActivity::class.java))
                btnRussian.setBackgroundResource(R.drawable.style_selected_language)
                btnEnglish.setBackgroundColor(Color.WHITE)
                SharedPref.put(SharedPref.CURRENT_APP_LANGUAGE, "ru")
            }
            return dialog
        }
    }
}