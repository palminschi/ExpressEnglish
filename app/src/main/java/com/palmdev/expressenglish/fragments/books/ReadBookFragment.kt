package com.palmdev.expressenglish.fragments.books

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.mlkit.nl.translate.TranslateLanguage
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.MainActivity
import com.palmdev.expressenglish.R
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
                val darkMode = SharedPref.get(SharedPref.BOOK_DARK_MODE, false)
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
    }


    override fun onResume() {
        super.onResume()

        // Set the Theme
        val darkMode = SharedPref.get(SharedPref.BOOK_DARK_MODE,false)
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


                else -> R.raw.b_003_freckles
            }
        )
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
            bookContainer.setCardBackgroundColor(resources.getColor(R.color.cardview_light_background))
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


}