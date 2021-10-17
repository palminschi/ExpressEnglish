package com.palmdev.expressenglish.data

import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.models.Book

class Books {

    companion object {

        private val books: ArrayList<Book> = arrayListOf()

        const val BOOK_ID_KEY = "BOOK_KEY"
        const val ID_BOOK_001 = "ID_BOOK_001"
        const val ID_BOOK_002 = "ID_BOOK_002"
        const val ID_BOOK_003 = "ID_BOOK_003"
        const val ID_BOOK_004 = "ID_BOOK_004"
        const val ID_BOOK_005 = "ID_BOOK_005"
        const val ID_BOOK_006 = "ID_BOOK_006"
        const val ID_BOOK_007 = "ID_BOOK_007"
        const val ID_BOOK_008 = "ID_BOOK_008"
        const val ID_BOOK_009 = "ID_BOOK_009"
        const val ID_BOOK_010 = "ID_BOOK_010"

        fun initAllBooks() {
            val book001 = Book(
                ID_BOOK_001,
                R.drawable.img_book_001,
                "The Cat",
                "John Escott",
                "A1",
                true,
                "detective",
                "thieves",
                "police")

            val book002 = Book(
                ID_BOOK_002,
                R.drawable.img_book_002,
                "The Boy Who Couldn't Sleep",
                "Foreman Peter",
                "A2",
                true,
                "family",
                "doctor",
                "mystery")

            val book003 = Book(
                ID_BOOK_003,
                R.drawable.img_book_003,
                "Freckles",
                "Andrew Matthews",
                "A1",
                true,
                "beauty",
                "teenager",
                "girl")

            val book004 = Book(
                ID_BOOK_004,
                R.drawable.img_book_004,
                "The Man with Three Names",
                "John Escott",
                "A2",
                false,
                "drama",
                "crime",
                "missing")

            val book005 = Book(
                ID_BOOK_005,
                R.drawable.img_book_005,
                "Me before You",
                "Jojo Moyes",
                "B1",
                true,
                "love",
                "family",
                "accident")

            val book006 = Book(
                ID_BOOK_006,
                R.drawable.img_book_006,
                "Private",
                "James Patterson",
                "B1",
                false,
                "detective",
                "story",
                "killer")

            val book007 = Book(
                ID_BOOK_007,
                R.drawable.img_book_007,
                "The Hobbit",
                "J. R. R. Tolkien",
                "C1",
                false,
                "adventure",
                "magic",
                "escape")

            books.add(book001)
            books.add(book002)
            books.add(book003)
            books.add(book004)
            books.add(book005)
            books.add(book006)
            books.add(book007)
        }



        fun getBooks(): ArrayList<Book> {
            return books
        }
        fun getA1Books(): ArrayList<Book> {
            val booksA1: ArrayList<Book> = ArrayList()
            books.forEach{
                if (it.bookLevel == "A1") booksA1.add(it)
            }

            return booksA1
        }
        fun getA2Books(): ArrayList<Book> {
            val booksA2: ArrayList<Book> = ArrayList()
            books.forEach{
                if (it.bookLevel == "A2") booksA2.add(it)
            }

            return booksA2
        }
        fun getB1Books(): ArrayList<Book> {
            val booksB1: ArrayList<Book> = ArrayList()
            books.forEach{
                if (it.bookLevel == "B1") booksB1.add(it)
            }

            return booksB1
        }
        fun getB2Books(): ArrayList<Book> {
            val booksB2: ArrayList<Book> = ArrayList()
            books.forEach{
                if (it.bookLevel == "B2") booksB2.add(it)
            }

            return booksB2
        }
        fun getC1Books(): ArrayList<Book> {
            val booksC1: ArrayList<Book> = ArrayList()
            books.forEach{
                if (it.bookLevel == "C1") booksC1.add(it)
            }

            return booksC1
        }
        fun getLikedBooks(): ArrayList<Book> {
            val booksLiked: ArrayList<Book> = ArrayList()
            books.forEach{
                if (SharedPref.read(it.bookID,false)) booksLiked.add(it)
            }

            return booksLiked
        }


    }
}