package com.palmdev.expressenglish.fragments

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.mlkit.nl.translate.Translator
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.FragmentTranslatorBinding
import com.palmdev.expressenglish.utils.AllLanguages
import com.palmdev.expressenglish.utils.MyTextToSpeech
import com.palmdev.expressenglish.utils.Translate

class TranslatorFragment : Fragment(R.layout.fragment_translator) {

    private lateinit var binding: FragmentTranslatorBinding
    private lateinit var mAllLanguagesCode: ArrayList<String>
    private lateinit var mAllLanguagesName: ArrayList<String>
    private lateinit var mUserLangName: String
    private lateinit var mUserTranslatorLangCode: String
    private lateinit var mDialogSelectLanguage: Dialog
    private lateinit var mTranslator: Translator
    private lateinit var mCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTranslatorBinding.bind(view)

        AllLanguages.initLanguages()
        mAllLanguagesCode = AllLanguages.allLanguagesCode
        mAllLanguagesName = AllLanguages.allLanguagesName
        mUserLangName = SharedPref.get(SharedPref.USER_LANGUAGE_NAME, "?").toString()
        mUserTranslatorLangCode =
            SharedPref.get(SharedPref.USER_TRANSLATOR_LANGUAGE_CODE, "?").toString()

        // show dialog for select lang if is first open
        initDialogSelectLang()
        // Init Translator
        init()
        // Init All Buttons
        initButtons()

        val numberOfSelectedWords = User.getSelectedWords()
        binding.numberOfSelectedWords.text = numberOfSelectedWords.toString()
        if (numberOfSelectedWords > 0) binding.cardNumberOfWords.visibility = View.VISIBLE
        else binding.cardNumberOfWords.visibility = View.GONE
    }

    private fun init() = with(binding) {
        tvTranslateFrom.text = getString(R.string.english)
        // If is first open (Translator Language is not selected)
        if (mUserTranslatorLangCode == "?") {
            mDialogSelectLanguage.show()
            btnSelectLang.text = mUserLangName
        } else {
            btnSelectLang.text =
                mAllLanguagesName[mAllLanguagesCode.indexOf(mUserTranslatorLangCode)]
            tvTranslateTo.text =
                mAllLanguagesName[mAllLanguagesCode.indexOf(mUserTranslatorLangCode)]
            setUpTranslator()
        }

    }

    private fun initDialogSelectLang() = with(binding) {
        mDialogSelectLanguage = Dialogs.dialogTranslatorLanguages(requireContext())

        mDialogSelectLanguage.setOnDismissListener {
            mUserTranslatorLangCode =
                SharedPref.get(SharedPref.USER_TRANSLATOR_LANGUAGE_CODE, "?").toString()
            tvTranslateFrom.text = mAllLanguagesName[mAllLanguagesName.indexOf("English")]
            if (mUserTranslatorLangCode != "?") {
                tvTranslateTo.text =
                    mAllLanguagesName[mAllLanguagesCode.indexOf(mUserTranslatorLangCode)]
                btnSelectLang.text =
                    mAllLanguagesName[mAllLanguagesCode.indexOf(mUserTranslatorLangCode)]
                // Set up translator
                setUpTranslator()
            }
        }
    }

    private fun setUpTranslator() {
        val sourceLanguage =
            mAllLanguagesCode[mAllLanguagesName.indexOf(binding.tvTranslateFrom.text)]
        val targetLanguage =
            mAllLanguagesCode[mAllLanguagesName.indexOf(binding.tvTranslateTo.text)]
        Translate.createTranslator(sourceLanguage, targetLanguage)
        mTranslator = Translate.getTranslator()!!
    }

    private fun initButtons(){
        binding.btnSelectLang.setOnClickListener {
            mDialogSelectLanguage.setCancelable(true)
            val dialogBtnBack = mDialogSelectLanguage.findViewById<TextView>(R.id.btnBack)
            dialogBtnBack.setOnClickListener {
                mDialogSelectLanguage.dismiss()
            }
            mDialogSelectLanguage.show()
        }

        binding.btnTranslate.setOnClickListener {
            val textForTranslate = binding.editText.text.toString()
            mTranslator.translate(textForTranslate)
                .addOnCompleteListener { binding.tvTranslatedText.text = it.result }
                .addOnFailureListener { binding.tvTranslatedText.text = getString(R.string.error) }
            it.animate()
                .scaleX(0.7f).scaleY(0.7f)
                .setDuration(50).withEndAction {
                    it.animate()
                        .scaleX(1f).scaleY(1f)
                        .duration = 100
                }
        }

        binding.btnSwapLanguages.setOnClickListener {
            binding.apply {
                val languageOne = tvTranslateFrom.text.toString()
                val languageTwo = tvTranslateTo.text.toString()
                editText.text.clear()
                tvTranslatedText.text = ""
                if (tvTranslateFrom.text == getString(R.string.english)) {
                    tvTranslateFrom.text = languageTwo
                    tvTranslateTo.text = languageOne
                    btnSoundTop.visibility = View.GONE
                    btnSoundBottom.visibility = View.VISIBLE
                    setUpTranslator()
                } else {
                    tvTranslateFrom.text = languageTwo
                    tvTranslateTo.text = languageOne
                    btnSoundTop.visibility = View.VISIBLE
                    btnSoundBottom.visibility = View.GONE
                    setUpTranslator()
                }

                val animTopToBottom = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_top_to_bottom)
                val animBottomToTop = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_bottom_to_top)
                val animRotate = AnimationUtils.loadAnimation(requireContext(),R.anim.anim_rotate)
                topCardView.startAnimation(animTopToBottom)
                bottomCardView.startAnimation(animBottomToTop)
                btnSwapLanguages.startAnimation(animRotate)
            }
        }

        binding.btnSoundTop.setOnClickListener {
            MyTextToSpeech.play(
                binding.editText.text,
                requireContext()
            )
        }

        binding.btnSoundBottom.setOnClickListener {
            MyTextToSpeech.play(
                binding.tvTranslatedText.text,
                requireContext()
            )
        }

        binding.btnCopyTop.setOnClickListener {
            val textForCopy = binding.editText.text
            if (textForCopy.isEmpty()) return@setOnClickListener

            val myClipboard =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val myClip = ClipData.newPlainText("Label", textForCopy)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(requireContext(), getString(R.string.toastCopied), Toast.LENGTH_SHORT)
                .show()
        }

        binding.btnCopyBottom.setOnClickListener {
            val textForCopy = binding.tvTranslatedText.text
            if (textForCopy.isEmpty()) return@setOnClickListener

            val myClipboard =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val myClip = ClipData.newPlainText("Label", textForCopy)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(requireContext(), getString(R.string.toastCopied), Toast.LENGTH_SHORT)
                .show()
        }

        binding.btnSaveWord.setOnClickListener {
            val word: String
            val translatedWord: String

            if (binding.tvTranslateFrom.text == getString(R.string.english)){
                word = binding.editText.text.toString()
                translatedWord = binding.tvTranslatedText.text.toString()
            }else {
                word = binding.tvTranslatedText.text.toString()
                translatedWord = binding.editText.text.toString()
            }

            if (word.isEmpty() || translatedWord.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.toastNoWords),
                    Toast.LENGTH_SHORT)
                    .show()
            }else {
                if (word.length >= 23 || translatedWord.length >= 23 ) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.toastTooLong),
                        Toast.LENGTH_SHORT)
                        .show()
                }else {
                    val dialogAddWord = Dialogs.dialogAddWord(
                        context = requireContext(),
                        word = word,
                        translation = translatedWord
                    )
                    dialogAddWord.setOnDismissListener {
                        val numberOfWords = User.getSelectedWords()

                        binding.numberOfSelectedWords.text = numberOfWords.toString()

                        if (numberOfWords > 0) {
                            binding.cardNumberOfWords.visibility = View.VISIBLE
                        }
                    }
                    dialogAddWord.show()
                }
            }
        }

        binding.cardNumberOfWords.setOnClickListener {
            findNavController().navigate(R.id.action_translatorFragment_to_wordsFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        mCallback.remove()
    }

    override fun onResume() {
        super.onResume()

        setOnBackPressedCallback()

        // Firebase Event
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, TranslatorFragment().javaClass.simpleName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, TranslatorFragment().javaClass.simpleName)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    private fun setOnBackPressedCallback(){
        mCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeFragment)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }


}