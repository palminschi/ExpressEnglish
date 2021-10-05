package com.palmdev.expressenglish

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.palmdev.expressenglish.databinding.FragmentBookReadBinding
import com.palmdev.expressenglish.helpers.TextToClickable
import com.palmdev.expressenglish.helpers.Pagination
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class ReadBookFragment: Fragment(R.layout.fragment_book_read) {


    private lateinit var binding: FragmentBookReadBinding
    private lateinit var mTextView: TextView
    private lateinit var mPagination: Pagination
    private lateinit var mText: CharSequence
    private var mCurrentIndex = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookReadBinding.bind(view)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

      //  binding.bookID.text = requireArguments().getString(BOOK_KEY)

        /*val txt = resources.openRawResource(R.raw.fleckers)
        var text = ""
        var reader: BufferedReader? = null

        try {
            reader = BufferedReader(InputStreamReader(txt))
            text = reader.readLines().joinToString("\n")
        }catch (e: IOException) {
            Toast.makeText(requireContext(), "Error reading license file!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (e: IOException) {
                //log the exception
                e.printStackTrace()
            }
            binding.textView.text = text
        }*/
        mTextView = binding.tv

        val txt = resources.openRawResource(R.raw.fleckers)
        val text = StringBuilder()
        var reader: BufferedReader? = null

        try {
            reader = BufferedReader(InputStreamReader(txt))
            var mLine: String?
            while (reader.readLine().also { mLine = it } != null) {
                text.append(mLine)
                text.append('\n')
            }
        } catch (e: IOException) {
            Toast.makeText(requireContext(), "Error reading file!", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    //log the exception
                }
            }
        }



        mText = text

        mTextView.getViewTreeObserver().addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Removing layout listener to avoid multiple calls
                mTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                mPagination = Pagination(
                    mText,
                    mTextView.getWidth(),
                    mTextView.getHeight(),
                    mTextView.getPaint(),
                    mTextView.getLineSpacingMultiplier(),
                    mTextView.getLineSpacingExtra(),
                    mTextView.getIncludeFontPadding()
                )
                update()
            }
        })

        binding.btnBack.setOnClickListener {
            mCurrentIndex = if (mCurrentIndex > 0) mCurrentIndex - 1 else 0
            update()
        }
        binding.btnForward.setOnClickListener {
            mCurrentIndex =
                if (mCurrentIndex < mPagination!!.size() - 1) mCurrentIndex + 1 else mPagination!!.size() - 1
            update()
        }


    }

    private fun update() {
        val text = mPagination!![mCurrentIndex]
        if (text != null) {
            mTextView.text = text
            TextToClickable.initSelectableWord(text, mTextView)
        }
    }

    companion object {
        const val BOOK_KEY = "BOOK_KEY"
        const val ID_BOOK_001 = "001"
        const val ID_BOOK_002 = "002"
        const val ID_BOOK_003 = "003"
        const val ID_BOOK_004 = "004"
        const val ID_BOOK_005 = "005"
        const val ID_BOOK_006 = "006"
        const val ID_BOOK_007 = "007"
        const val ID_BOOK_008 = "008"
        const val ID_BOOK_009 = "009"
        const val ID_BOOK_010 = "010"
    }
}