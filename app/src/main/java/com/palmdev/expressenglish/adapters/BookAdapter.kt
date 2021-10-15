package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.databinding.ItemBookBinding
import com.palmdev.expressenglish.fragments.BooksFragment
import com.palmdev.expressenglish.models.Book

class BookAdapter: RecyclerView.Adapter<BookAdapter.BookHolder>() {


    private val bookList = ArrayList<Book>()

    class BookHolder(item: View): RecyclerView.ViewHolder(item){

        private val binding = ItemBookBinding.bind(item)

        fun bind(book: Book) = with(binding) {
            bookTitle.text = book.bookTitle
            bookAuthor.text = book.bookAuthor
            bookLevel.text = book.bookLevel
            bookCategory01.text = book.bookCategory01
            bookCategory02.text = book.bookCategory02
            bookCategory03.text = book.bookCategory03
            bookImg.setImageResource(book.bookImg)
            // Free or Premium
            if (book.bookAccess) {
                imgPremium.visibility = View.INVISIBLE
                cardFree.visibility = View.VISIBLE
            }
            // Click Item Listener
            root.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.readBookFragment,
                    bundleOf(Books.BOOK_ID_KEY to book.bookID)
                )
            }
            // Button Like Listener

            toggleLike.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    SharedPref.write(book.bookID, true)
                }else {
                    SharedPref.write(book.bookID, false)
                }
            }
            toggleLike.setOnClickListener {
                var favoriteBooks = SharedPref.read(SharedPref.FAVORITE_BOOKS,0)
                if (toggleLike.isChecked) {
                    favoriteBooks++
                    SharedPref.write(
                        SharedPref.FAVORITE_BOOKS,
                        favoriteBooks)
                }else {
                    favoriteBooks--
                    SharedPref.write(SharedPref.FAVORITE_BOOKS,
                        favoriteBooks)
                }
            }
            toggleLike.isChecked = SharedPref.read(book.bookID, false)
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

    @SuppressLint("NotifyDataSetChanged")
    fun addNewBook(book: Book){
        bookList.add(book)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addBooks(data: ArrayList<Book>){
        bookList.addAll(data)
        notifyDataSetChanged()
    }
    fun clearData(){
        bookList.clear()
    }
}