package com.palmdev.expressenglish.data

import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.ReadBookFragment
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_001
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_002
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_003
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_004
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_005
import com.palmdev.expressenglish.ReadBookFragment.Companion.ID_BOOK_006
import com.palmdev.expressenglish.adapters.BookAdapter
import com.palmdev.expressenglish.model.Book

class Books {

    companion object {

        fun getBooks(adapter: BookAdapter){
            val book001 = Book(
                ID_BOOK_001,
                R.drawable.img_book_ex,
                "I Feel Bad About My Neck",
                "Nora Ephron",
                "B2",
                true,
                "love",
                "drama",
                "action")
            adapter.addBook(book001)

            val book002 = Book(
                ID_BOOK_002,
                R.drawable.img_book_ex_2,
                "The Boy Who Couldn't Sleep",
                "Stieg Larsson",
                "A2",
                false,
                "family",
                "roman",
                "horror")
            adapter.addBook(book002)

            val book003 = Book(
                ID_BOOK_003,
                R.drawable.img_book_ex_3,
                "Harry Potter and the Goblet",
                "JK Rowling",
                "A1",
                true,
                "fantasy",
                "horror",
                "story")
            adapter.addBook(book003)

            val book004 = Book(
                ID_BOOK_004,
                R.drawable.img_book_ex,
                "The Siege",
                "Helen Dunmore",
                "A1",
                true,
                "drama",
                "crime",
                "story")
            adapter.addBook(book004)

            val book005 = Book(
                ID_BOOK_005,
                R.drawable.img_book_ex_2,
                "Chronicles: Volume One",
                "Bob Dylan",
                "B2+",
                false,
                "fantasy",
                "horror",
                "criminal")
            adapter.addBook(book005)

            val book006 = Book(
                ID_BOOK_006,
                R.drawable.img_book_ex_3,
                "Bad Blood",
                "Lorna Sage",
                "B1",
                false,
                "biography",
                "story",
                "crime")
            adapter.addBook(book006)
        }
    }
}