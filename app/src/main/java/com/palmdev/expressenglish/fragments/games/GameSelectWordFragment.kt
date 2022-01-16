package com.palmdev.expressenglish.fragments.games

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.databinding.FragmentGameSelectWordBinding
import com.palmdev.expressenglish.fragments.GroupOfWordsFragment
import com.palmdev.expressenglish.utils.MyTextToSpeech
import kotlin.random.Random


class GameSelectWordFragment : Fragment(R.layout.fragment_game_select_word) {

    private lateinit var binding: FragmentGameSelectWordBinding
    private var mCurrentWordCounter = 0
    private val mArrayWords = ArrayList<String>()
    private val mArrayTranslatedWords = ArrayList<String>()
    private val mArrayPhrases = ArrayList<String>()
    private var mRightAnswer = 0
    private lateinit var mCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameSelectWordBinding.bind(view)

        // Get data from arguments
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_WORDS)?.let {
            mArrayWords.addAll(it)
        }
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_TRANSLATED_WORDS)?.let {
            mArrayTranslatedWords.addAll(it)
        }
        requireArguments().getStringArrayList(GroupOfWordsFragment.ARRAY_PHRASES)?.let {
            mArrayPhrases.addAll(it)
        }

        updateContent()

        binding.choice1.setOnClickListener { onSelectAnswer(it as CardView, binding.tvChoice1) }
        binding.choice2.setOnClickListener { onSelectAnswer(it as CardView, binding.tvChoice2) }
        binding.choice3.setOnClickListener { onSelectAnswer(it as CardView, binding.tvChoice3) }
        binding.btnNext.setOnClickListener {
            when (binding.tvBtnNext.text){
                getText(R.string.next) -> {
                    mCurrentWordCounter++
                    updateContent()
                }
                getText(R.string.done) -> {
                    findNavController().navigateUp()
                    findNavController().navigateUp()
                }
            }
        }
        binding.btnSound.setOnClickListener {
            MyTextToSpeech.play(binding.tvWord.text, requireContext())
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
        }
    }


    private fun updateContent() {
        binding.tvWord.text = mArrayWords[mCurrentWordCounter]
        MyTextToSpeech.play(mArrayWords[mCurrentWordCounter], requireContext())
        binding.btnNext.visibility = View.INVISIBLE
        binding.apply {
            choice1.isClickable = true
            choice2.isClickable = true
            choice3.isClickable = true
            choice1.animate().alpha(1f)
            choice2.animate().alpha(1f)
            choice3.animate().alpha(1f)
            choice1.visibility = View.VISIBLE
            choice2.visibility = View.VISIBLE
            choice3.visibility = View.VISIBLE
            choice1.setCardBackgroundColor(resources.getColor(R.color.button_white))
            choice2.setCardBackgroundColor(resources.getColor(R.color.button_white))
            choice3.setCardBackgroundColor(resources.getColor(R.color.button_white))
        }
        // if this is last word
        if (mCurrentWordCounter == mArrayWords.size - 1) {
            binding.tvBtnNext.text = getText(R.string.done)
        }

        // if the word has no sentence
        if (mArrayPhrases[mCurrentWordCounter] != "empty") {
            binding.tvPhrase.text = mArrayPhrases[mCurrentWordCounter]
        }
        // put right answer into a random position
        val randomPositionOfRightAnswer = Random.nextInt(1, 4)
        mRightAnswer = randomPositionOfRightAnswer
        // get 2 another words
        var randomPositionOfWordN1 = Random.nextInt(mArrayWords.size)
        while (randomPositionOfWordN1 == mCurrentWordCounter) {
            randomPositionOfWordN1 = Random.nextInt(mArrayWords.size)
        }
        var randomPositionOfWordN2 = Random.nextInt(mArrayWords.size)
        while (
            randomPositionOfWordN2 == mCurrentWordCounter ||
            randomPositionOfWordN2 == randomPositionOfWordN1
        ) {
            randomPositionOfWordN2 = Random.nextInt(mArrayWords.size)
        }

        when (mRightAnswer) {
            1 -> {
                binding.tvChoice1.text = mArrayTranslatedWords[mCurrentWordCounter]
                binding.tvChoice2.text = mArrayTranslatedWords[randomPositionOfWordN1]
                binding.tvChoice3.text = mArrayTranslatedWords[randomPositionOfWordN2]
            }
            2 -> {
                binding.tvChoice1.text = mArrayTranslatedWords[randomPositionOfWordN1]
                binding.tvChoice2.text = mArrayTranslatedWords[mCurrentWordCounter]
                binding.tvChoice3.text = mArrayTranslatedWords[randomPositionOfWordN2]
            }
            3 -> {
                binding.tvChoice1.text = mArrayTranslatedWords[randomPositionOfWordN1]
                binding.tvChoice2.text = mArrayTranslatedWords[randomPositionOfWordN2]
                binding.tvChoice3.text = mArrayTranslatedWords[mCurrentWordCounter]
            }
        }

    }

    private fun onSelectAnswer(cardView: CardView, textView: TextView){
        // if answer is right
        if (textView.text == mArrayTranslatedWords[mCurrentWordCounter]){
            val anim = ScaleAnimation(0f,1f,0f,1f,0.5f,0.5f)
            anim.duration = 200
            binding.btnNext.animation = anim
            binding.btnNext.visibility = View.VISIBLE

            // TODO -> Animation
            binding.apply {
                choice1.isClickable = false
                choice2.isClickable = false
                choice3.isClickable = false
            }
            cardView.setCardBackgroundColor(resources.getColor(R.color.green_3))
        }else {
            cardView.animate().setDuration(300).alpha(0f).withEndAction {
                cardView.visibility = View.INVISIBLE
            }
        }
    }

    private fun setOnBackPressedCallback(){
        mCallback = object :  OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(mCallback)
    }

    override fun onResume() {
        super.onResume()
        setOnBackPressedCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCallback.remove()
    }

}