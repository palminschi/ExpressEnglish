package com.palmdev.expressenglish.data

import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.fragments.BooksFragment.Companion.ID_BOOK_001
import com.palmdev.expressenglish.fragments.BooksFragment.Companion.ID_BOOK_002
import com.palmdev.expressenglish.fragments.BooksFragment.Companion.ID_BOOK_003
import com.palmdev.expressenglish.fragments.BooksFragment.Companion.ID_BOOK_004
import com.palmdev.expressenglish.fragments.BooksFragment.Companion.ID_BOOK_005
import com.palmdev.expressenglish.fragments.BooksFragment.Companion.ID_BOOK_006
import com.palmdev.expressenglish.models.Book

class Books {

    companion object {

        private val books: ArrayList<Book> = arrayListOf(
        )

        fun getBooks(): ArrayList<Book> {
            return books
        }

        fun initBooks() {
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

            books.add(book001)
            books.add(book002)
            books.add(book003)
            books.add(book004)
            books.add(book005)
            books.add(book006)
        }
    }
}