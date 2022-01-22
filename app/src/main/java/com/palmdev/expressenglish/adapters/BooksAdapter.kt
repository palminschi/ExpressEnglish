package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.ItemBookBinding
import com.palmdev.expressenglish.models.Book

class BooksAdapter: RecyclerView.Adapter<BooksAdapter.BookHolder>() {


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
                val premiumUser = User.getPremiumStatus(root.context)

                if (!book.bookAccess){
                    if (premiumUser){
                        Navigation.findNavController(it).navigate(
                            R.id.action_booksFragment_to_readBookFragment,
                            bundleOf(Books.BOOK_ID_KEY to book.bookID)
                        )
                    }else {
                        val dialog = Dialogs.dialogRestrictedContent(root.context)
                        dialog.show()
                    }
                }else{
                    Navigation.findNavController(it).navigate(
                        R.id.action_booksFragment_to_readBookFragment,
                        bundleOf(Books.BOOK_ID_KEY to book.bookID)
                    )
                }
            }
            // Button Like Listener

            toggleLike.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    SharedPref.put(book.bookID, true)
                }else {
                    SharedPref.put(book.bookID, false)
                }
            }
            toggleLike.setOnClickListener {
                if (toggleLike.isChecked) {
                    User.addFavoriteBook()
                }else {
                    User.removeFavoriteBook()
                }
            }
            toggleLike.isChecked = SharedPref.get(book.bookID, false)
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
    fun addBooks(data: ArrayList<Book>){
        if (bookList.isEmpty() && data.isNotEmpty()) {
            bookList.addAll(data)
            notifyDataSetChanged()
        }
    }
    fun clearData(){
        bookList.clear()
    }
}