package com.palmdev.expressenglish.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import com.palmdev.expressenglish.MainActivity
import java.util.*

class MyTextToSpeech {
    companion object {
        var mTextToSpeech: TextToSpeech? = null

        fun play(text: CharSequence, context: Context) {
            mTextToSpeech = TextToSpeech(context) {
                mTextToSpeech?.language = Locale.UK
                mTextToSpeech!!.setSpeechRate(0.85f)
                mTextToSpeech!!.speak(text,TextToSpeech.QUEUE_FLUSH, null, null)
            }

        }
    }
}