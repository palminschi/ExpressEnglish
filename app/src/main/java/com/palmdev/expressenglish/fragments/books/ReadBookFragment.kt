package com.palmdev.expressenglish.fragments.books

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.mlkit.nl.translate.TranslateLanguage
import com.palmdev.expressenglish.*
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.FragmentBookReadBinding
import com.palmdev.expressenglish.utils.Pagination
import com.palmdev.expressenglish.utils.TextToClickable
import com.palmdev.expressenglish.utils.Translate
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class ReadBookFragment: Fragment(R.layout.fragment_book_read) {


    lateinit var binding: FragmentBookReadBinding
    private lateinit var mTextView: TextView
    private lateinit var mPagination: Pagination
    private lateinit var mText: CharSequence
    private var mCurrentPage = 0
    private var mReader: BufferedReader? = null
    private lateinit var mBookStringBuilder : StringBuilder
    private lateinit var mBook: InputStream
    private lateinit var mBookID: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookReadBinding.bind(view)

        //Load Ad
        Ads.loadInterstitialAd(requireContext())

        // Init content
        mBookID = requireArguments().getString(Books.BOOK_ID_KEY, "0")
        setCurrentPage(SharedPref.get(mBookID + SharedPref.BOOK_PAGE, 0))
        setBook(mBookID)
        makeTextClickable(mBook)
        setPagination()

        // Init buttons
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
                Ads.showInterstitialAd(requireContext(), requireActivity())
            }
            btnSettings.setOnClickListener {
                findNavController().navigate(
                    R.id.action_readBookFragment_to_bottomSheetFragment,
                    bundleOf(Books.BOOK_ID_KEY to mBookID)
                )
            }
        }

        // Change the Data (from Bottom Sheet) if it has been changed
        val dataChanged = findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<Boolean>(BottomSheetFragment.DATA_CHANGED)
        dataChanged?.observe(viewLifecycleOwner) {
            if (it) {
                // Change the Theme
                val darkMode = SharedPref.get(SharedPref.BOOK_DARK_MODE, true)
                if (darkMode) setDarkMode() else setLightMode()
                // Change Font Size
                val fontSize = SharedPref.get(
                    SharedPref.BOOK_FONT_SIZE,
                    BottomSheetFragment.FONT_SIZE_NORMAL
                )
                when (fontSize) {
                    BottomSheetFragment.FONT_SIZE_SMALL -> {
                        binding.bookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                    }
                    BottomSheetFragment.FONT_SIZE_NORMAL -> {
                        binding.bookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                    }
                    BottomSheetFragment.FONT_SIZE_LARGE -> {
                        binding.bookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                    }
                }

            }
            setPagination()
        }

        // Send Coordinate of Click to onClickListener
        binding.bookContent.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {

                TextToClickable.setCoordinate(
                    event.x.toInt(),
                    event.y.toInt()
                )
                false
            } else false
        }

        // Init Translator or Dialog for Select Language
        val userLangPref = SharedPref.get(SharedPref.USER_TRANSLATOR_LANGUAGE_CODE, "?")
        if (userLangPref != "?") userLangPref?.let {
            Translate.createTranslator(
                TranslateLanguage.ENGLISH,
                it
            )
        }else {
            val dialog = Dialogs.dialogTranslatorLanguages(requireContext())
            dialog.show()
        }

        // Set OnBackPressed Callback
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
            // Show ad
            Ads.showInterstitialAd(requireContext(), requireActivity())
        }
    }


    override fun onResume() {
        super.onResume()

        // Set the Theme
        val darkMode = SharedPref.get(SharedPref.BOOK_DARK_MODE,true)
        if (darkMode) setDarkMode() else setLightMode()
        // Set Font Size
        val fontSize = SharedPref.get(
            SharedPref.BOOK_FONT_SIZE,
            BottomSheetFragment.FONT_SIZE_NORMAL
        )
        when (fontSize) {
            BottomSheetFragment.FONT_SIZE_SMALL -> {
                binding.bookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            }
            BottomSheetFragment.FONT_SIZE_NORMAL -> {
                binding.bookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            }
            BottomSheetFragment.FONT_SIZE_LARGE -> {
                binding.bookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            }
        }

        // Firebase Event
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,
            ReadBookFragment().javaClass.simpleName + mBookID)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,
            ReadBookFragment().javaClass.simpleName + mBookID)
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun onStop() {
        super.onStop()
        // Return Light Theme
        MainActivity.bottomNavigationView.visibility = View.VISIBLE
        val mainLayout = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        mainLayout?.setBackgroundColor(resources.getColor(R.color.background_color))
        activity?.window?.navigationBarColor = resources.getColor(R.color.gray_03)
        // Save Current Page
        SharedPref.put(mBookID + SharedPref.BOOK_PAGE,getCurrentPage())
    }



    private fun setDarkMode(){
        MainActivity.bottomNavigationView.visibility = View.GONE
        activity?.window?.navigationBarColor = resources.getColor(R.color.night_background)
        val mainLayout = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        mainLayout?.setBackgroundColor(resources.getColor(R.color.night_background))
        binding.apply {
            bookContainer.setCardBackgroundColor(resources.getColor(R.color.night_main))
            bookContent.setTextColor(resources.getColor(R.color.night_text))
            bookContent.setLinkTextColor(resources.getColor(R.color.night_text))
            bookContent.highlightColor = resources.getColor(R.color.black)
        }
    }
    private fun setLightMode(){
        MainActivity.bottomNavigationView.visibility = View.GONE
        activity?.window?.navigationBarColor = resources.getColor(R.color.gray_03)
        val mainLayout = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
        mainLayout?.setBackgroundColor(resources.getColor(R.color.background_color))
        binding.apply {
            bookContainer.setCardBackgroundColor(resources.getColor(R.color.white))
            bookContent.setTextColor(resources.getColor(R.color.black))
            bookContent.setLinkTextColor(resources.getColor(R.color.black))
            bookContent.highlightColor = resources.getColor(R.color.gray_02)
        }
    }


    private fun makeTextClickable(book: InputStream){
        mBookStringBuilder = StringBuilder()

        try {
            mReader = BufferedReader(InputStreamReader(book))
            var line: String?
            while (mReader!!.readLine().also { line = it } != null) {
                mBookStringBuilder.append(line)
                mBookStringBuilder.append('\n')
            }
        } catch (e: IOException) {
            Toast.makeText(requireContext(), "Error reading file!", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        } finally {
            if (mReader != null) {
                try {
                    mReader!!.close()
                } catch (e: IOException) {
                    // empty
                }
            }
        }
    }

    private fun setPagination(){
        mText = mBookStringBuilder
        mTextView = binding.bookContent

        mTextView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Removing layout listener to avoid multiple calls
                mTextView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                mPagination = Pagination(
                    mText,
                    mTextView.width,
                    mTextView.height,
                    mTextView.paint,
                    mTextView.lineSpacingMultiplier,
                    mTextView.lineSpacingExtra,
                    mTextView.includeFontPadding
                )
                update()
            }
        })

        binding.btnPageBack.setOnClickListener {
            mCurrentPage = if (mCurrentPage > 0) mCurrentPage - 1 else 0
            update()
        }
        binding.btnPageNext.setOnClickListener {
            mCurrentPage =
                if (mCurrentPage < mPagination.size() - 1) mCurrentPage + 1 else mPagination.size() - 1
            update()
        }
    }

    private fun setCurrentPage(page: Int){
        mCurrentPage = page
    }
    private fun getCurrentPage(): Int{
        return mCurrentPage
    }


    @SuppressLint("SetTextI18n")
    private fun update() {
        val text = mPagination[mCurrentPage]
        if (text != null) {
            mTextView.text = text
            TextToClickable.initSelectableWord(text, mTextView)
        }

        binding.currentPage.text = "${mCurrentPage + 1} / ${(mPagination.size())}"

        if (mCurrentPage == 3) {
            AppReview.rateApp(requireActivity())
        }

        if (getCurrentPage() == 0) {
            binding.btnPageBack.visibility = View.INVISIBLE
        } else {
            binding.btnPageBack.visibility = View.VISIBLE
        }
        if (getCurrentPage() == mPagination.size() - 1){
            binding.btnPageNext.visibility = View.INVISIBLE
        } else {
            binding.btnPageNext.visibility = View.VISIBLE
        }
    }

    private fun setBook(bookID: String){
        mBook = resources.openRawResource(
            when (bookID) {
                Books.ID_BOOK_001 -> R.raw.b_001_the_cat
                Books.ID_BOOK_002 -> R.raw.b_002_the_boy_who_couldnt_sleep
                Books.ID_BOOK_003 -> R.raw.b_003_freckles
                Books.ID_BOOK_004 -> R.raw.b_004_the_man_with_three_names
                Books.ID_BOOK_005 -> R.raw.b_005_me_before_you
                Books.ID_BOOK_006 -> R.raw.b_006_private
                Books.ID_BOOK_007 -> R.raw.b_007_the_hobbit
                Books.ID_BOOK_008 -> R.raw.b_008_interview_with_the_vampire
                Books.ID_BOOK_009 -> R.raw.b_009_climate_change
                Books.ID_BOOK_010 -> R.raw.b_010_ivanhoe
                Books.ID_BOOK_011 -> R.raw.b_011_dinosaurs
                Books.ID_BOOK_012 -> R.raw.b_012_muhammad_ali
                Books.ID_BOOK_013 -> R.raw.b_013_leonardo_da_vinci
                Books.ID_BOOK_014 -> R.raw.b_014_the_final_diagnosis
                Books.ID_BOOK_015 -> R.raw.b_015_les_miserables
                Books.ID_BOOK_016 -> R.raw.b_016_the_house
                Books.ID_BOOK_017 -> R.raw.b_017_famous_british_criminals
                Books.ID_BOOK_018 -> R.raw.b_018_madame_bovary
                Books.ID_BOOK_019 -> R.raw.b_019_sister_love
                Books.ID_BOOK_020 -> R.raw.b_020_your_body
                Books.ID_BOOK_021 -> R.raw.b_021_remember_atita
                Books.ID_BOOK_022 -> R.raw.b_022_a_walk_in_amnesia
                Books.ID_BOOK_023 -> R.raw.b_023_king_arthur
                Books.ID_BOOK_024 -> R.raw.b_024_a_good_marriage
                Books.ID_BOOK_025 -> R.raw.b_025_on_the_beach
                Books.ID_BOOK_026 -> R.raw.b_026_the_story_of_the_internet
                Books.ID_BOOK_027 -> R.raw.b_027_the_notebook
                Books.ID_BOOK_028 -> R.raw.b_028_rebecca


                else -> R.raw.b_003_freckles
            }
        )
    }
}