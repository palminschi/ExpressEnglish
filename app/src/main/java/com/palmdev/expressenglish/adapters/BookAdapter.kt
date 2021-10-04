package com.palmdev.expressenglish.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.ReadBookFragment.Companion.BOOK_KEY
import com.palmdev.expressenglish.databinding.ItemBookBinding
import com.palmdev.expressenglish.model.Book
import kotlin.random.Random

class BookAdapter: RecyclerView.Adapter<BookAdapter.BookHolder>() {


    private val bookList = ArrayList<Book>()

    class BookHolder(item: View): RecyclerView.ViewHolder(item){

        private val binding = ItemBookBinding.bind(item)

        fun bind(book: Book) = with(binding) {
            bookTitle.text = book.bookTitle
            bookAuthor.text = book.bookAuthor
            bookLevel.text = book.bookLevel
            bookImg.setImageResource(book.bookImg)
            if (book.bookAccess) {
                imgPremium.visibility = View.INVISIBLE
                cardFree.visibility = View.VISIBLE
            }
            root.setOnClickListener {
               // val bundle = Bundle()
              //  bundle.putString("KEY","Book ID = ${book.bookID}")
                Navigation.findNavController(it).navigate(
                    R.id.readBookFragment,
                    bundleOf(BOOK_KEY to "Book ID = ${book.bookID}")
                )
            }

            bookCategory01.text = book.bookCategory01
            bookCategory02.text = book.bookCategory02
            bookCategory03.text = book.bookCategory03
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookHolder(view)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun addBook(book: Book){
        bookList.add(book)
        notifyDataSetChanged()
    }


}