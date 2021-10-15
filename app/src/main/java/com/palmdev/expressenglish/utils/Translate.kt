package com.palmdev.expressenglish.utils

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class Translate {
    companion object {
        private var translator: Translator? = null

        fun getTranslator(): Translator? {
            return translator
        }

        fun createTranslator( sourceLanguage: String, targetLanguage: String ){
            val options = TranslatorOptions.Builder()
                .setSourceLanguage( sourceLanguage )
                .setTargetLanguage( targetLanguage )
                .build()
            translator = Translation.getClient(options)
        }

        fun downloadModel() {
            val condition = DownloadConditions.Builder().requireWifi().build()
            translator?.downloadModelIfNeeded(condition)
        }


    }
}