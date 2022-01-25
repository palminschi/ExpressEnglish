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
        const val ID_BOOK_011 = "ID_BOOK_011"
        const val ID_BOOK_012 = "ID_BOOK_012"
        const val ID_BOOK_013 = "ID_BOOK_013"
        const val ID_BOOK_014 = "ID_BOOK_014"
        const val ID_BOOK_015 = "ID_BOOK_015"
        const val ID_BOOK_016 = "ID_BOOK_016"
        const val ID_BOOK_017 = "ID_BOOK_017"
        const val ID_BOOK_018 = "ID_BOOK_018"
        const val ID_BOOK_019 = "ID_BOOK_019"
        const val ID_BOOK_020 = "ID_BOOK_020"
        const val ID_BOOK_021 = "ID_BOOK_021"
        const val ID_BOOK_022 = "ID_BOOK_022"
        const val ID_BOOK_023 = "ID_BOOK_023"
        const val ID_BOOK_024 = "ID_BOOK_024"
        const val ID_BOOK_025 = "ID_BOOK_025"
        const val ID_BOOK_026 = "ID_BOOK_026"
        const val ID_BOOK_027 = "ID_BOOK_027"
        const val ID_BOOK_028 = "ID_BOOK_028"
        const val ID_BOOK_029 = "ID_BOOK_029"
        const val ID_BOOK_030 = "ID_BOOK_030"
        const val ID_BOOK_031 = "ID_BOOK_031"
        const val ID_BOOK_032 = "ID_BOOK_032"
        const val ID_BOOK_033 = "ID_BOOK_033"
        const val ID_BOOK_034 = "ID_BOOK_034"
        const val ID_BOOK_035 = "ID_BOOK_035"

        fun initAllBooks() {
            books.clear()
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

            val book008 = Book(
                ID_BOOK_008,
                R.drawable.img_book_008,
                "Interview with the Vampire",
                "Anne Rice",
                "B2",
                false,
                "vampires",
                "novella",
                "mystery"
            )

            val book009 = Book(
                ID_BOOK_009,
                R.drawable.img_book_009,
                "Climate Change",
                "Barnaby Newbolt",
                "A2",
                false,
                "documentary",
                "ecology",
                "future"
            )

            val book010 = Book(
                ID_BOOK_010,
                R.drawable.img_book_010,
                "Ivanhoe",
                "Walter Scott",
                "B1",
                false,
                "king",
                "past",
                "fight"
            )

            val book011 = Book(
                ID_BOOK_011,
                R.drawable.img_book_011,
                "Dinosaurs",
                "Tim Vicary",
                "B1",
                true,
                "documentary",
                "animals",
                "history"
            )

            val book012 = Book(
                ID_BOOK_012,
                R.drawable.img_book_012,
                "Muhammad Ali",
                "B. Smith",
                "A1",
                true,
                "biography",
                "work",
                "fight"
            )

            val book013 = Book(
                ID_BOOK_013,
                R.drawable.img_book_013,
                "Leonardo Da Vinci",
                "Clarke Georgia",
                "B2",
                false,
                "history",
                "biography",
                "invention"
            )

            val book014 = Book(
                ID_BOOK_014,
                R.drawable.img_book_014,
                "The Final Diagnosis",
                "Arthur Hailey",
                "C1",
                false,
                "novella",
                "doctor",
                "society"
            )

            val book015 = Book(
                ID_BOOK_015,
                R.drawable.img_book_015,
                "Les Miserables",
                "Victor Hugo",
                "C1",
                true,
                "classic",
                "history",
                "misery"
            )

            val book016 = Book(
                ID_BOOK_016,
                R.drawable.img_book_016,
                "The House",
                "Erica N. Robinson",
                "A2",
                true,
                "family",
                "drugs",
                "story"
            )

            val book017 = Book(
                ID_BOOK_017,
                R.drawable.img_book_017,
                "Famous British Criminals",
                "Victoria Spence",
                "B1",
                true,
                "crime",
                "prison",
                "robbery"
            )

            val book018 = Book(
                ID_BOOK_018,
                R.drawable.img_book_018,
                "Madame Bovary",
                "Gustave Flaubert",
                "C1",
                false,
                "love-story",
                "family",
                "novel"
            )

            val book019 = Book(
                ID_BOOK_019,
                R.drawable.img_book_019,
                "Sister Love",
                "John Escott",
                "A1",
                false,
                "family",
                "crime",
                "accident"
            )

            val book020 = Book(
                ID_BOOK_020,
                R.drawable.img_book_020,
                "Your Body",
                "Jennifer Gascoigne",
                "A1",
                false,
                "music",
                "work",
                "doctor"
            )

            val book021 = Book(
                ID_BOOK_021,
                R.drawable.img_book_021,
                "Remember Atita",
                "Jackee Budesta Batanda",
                "A2",
                false,
                "missing",
                "dream",
                "escape"
            )

            val book022 = Book(
                ID_BOOK_022,
                R.drawable.img_book_022,
                "A Walk in Amnesia",
                "O. Henry",
                "A2",
                false,
                "business",
                "family",
                "doctor"
            )

            val book023 = Book(
                ID_BOOK_023,
                R.drawable.img_book_023,
                "King Arthur",
                "George Gibson",
                "B1",
                false,
                "war",
                "magic",
                "jewels"
            )

            val book024 = Book(
                ID_BOOK_024,
                R.drawable.img_book_024,
                "A Good Marriage",
                "Stephen King",
                "B1",
                false,
                "family",
                "house",
                "horror"
            )

            val book025 = Book(
                ID_BOOK_025,
                R.drawable.img_book_025,
                "On the Beach",
                "Nevil Shute",
                "B2",
                true,
                "accident",
                "escape",
                "holiday"
            )

            val book026 = Book(
                ID_BOOK_026,
                R.drawable.img_book_026,
                "The Story of the Internet",
                "Stephen Bryant",
                "B2",
                false,
                "documentary",
                "computers",
                "society"
            )

            val book027 = Book(
                ID_BOOK_027,
                R.drawable.img_book_027,
                "The Notebook",
                "Nicholas Sparks",
                "C1",
                false,
                "tragedy",
                "dream",
                "love"
            )

            val book028 = Book(
                ID_BOOK_028,
                R.drawable.img_book_028,
                "Rebecca",
                "Daphne Du Maurier",
                "C1",
                false,
                "marriage",
                "novel",
                "mistress"
            )

            books.add(book001)
            books.add(book002)
            books.add(book003)
            books.add(book004)
            books.add(book005)
            books.add(book006)
            books.add(book007)
            books.add(book008)
            books.add(book009)
            books.add(book010)
            books.add(book011)
            books.add(book012)
            books.add(book013)
            books.add(book014)
            books.add(book015)
            books.add(book016)
            books.add(book017)
            books.add(book018)
            books.add(book019)
            books.add(book020)
            books.add(book021)
            books.add(book022)
            books.add(book023)
            books.add(book024)
            books.add(book025)
            books.add(book026)
            books.add(book027)
            books.add(book028)
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
                if (SharedPref.get(it.bookID,false)) booksLiked.add(it)
            }

            return booksLiked
        }


    }
}