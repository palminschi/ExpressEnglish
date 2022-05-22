package com.palmdev.expressenglish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.palmdev.expressenglish.Ads
import com.palmdev.expressenglish.Dialogs
import com.palmdev.expressenglish.R
import com.palmdev.expressenglish.data.Books
import com.palmdev.expressenglish.data.SharedPref
import com.palmdev.expressenglish.data.User
import com.palmdev.expressenglish.databinding.ItemBookBinding
import com.palmdev.expressenglish.models.Book
import com.palmdev.expressenglish.utils.Network

private const val AD_TYPE = 0
private const val CONTENT_TYPE = 1
private const val AD_CONDITION = 4

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BookHolder>() {

    private val bookList = ArrayList<Book>()
    private var isPremiumUser = true
    private var isNetworkActive = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        isPremiumUser = User.getPremiumStatus(parent.context)
        isNetworkActive = Network.isNetworkAvailable(parent.context)
        val view = if (viewType == AD_TYPE) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_native_ad, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        }

        return BookHolder(view)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        if (isPremiumUser || !isNetworkActive) {
            holder.bind(bookList[position])
            return
        }

        if (getItemViewType(position) == AD_TYPE) {
            Ads.loadNativeAd(holder.itemView.context, holder.itemView.rootView as ViewGroup)
        } else {
            if (position > AD_CONDITION) {
                holder.bind(bookList[position - 1])
            } else {
                holder.bind(bookList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPremiumUser || !isNetworkActive) CONTENT_TYPE
        else {
            when (position) {
                AD_CONDITION -> AD_TYPE
                else -> CONTENT_TYPE
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isPremiumUser || bookList.size < AD_CONDITION || !isNetworkActive) bookList.size
        else bookList.size + 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addBooks(data: ArrayList<Book>) {
        if (bookList.isEmpty() && data.isNotEmpty()) {
            bookList.addAll(data)
        }
        notifyDataSetChanged()
    }

    fun clearData() {
        bookList.clear()
    }

    class BookHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(book: Book) {

            val binding = ItemBookBinding.bind(itemView)
            binding.apply {
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
                } else {
                    imgPremium.visibility = View.VISIBLE
                    cardFree.visibility = View.INVISIBLE
                }
                // Click Item Listener
                root.setOnClickListener {
                    val premiumUser = User.getPremiumStatus(root.context)

                    if (!book.bookAccess) {
                        if (premiumUser) {
                            Navigation.findNavController(it).navigate(
                                R.id.action_booksFragment_to_readBookFragment,
                                bundleOf(Books.BOOK_ID_KEY to book.bookID)
                            )
                        } else {
                            val dialog = Dialogs.dialogRestrictedContent(root.context)
                            dialog.show()
                        }
                    } else {
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
                    } else {
                        SharedPref.put(book.bookID, false)
                    }
                }
                toggleLike.setOnClickListener {
                    if (toggleLike.isChecked) {
                        User.addFavoriteBook()
                    } else {
                        User.removeFavoriteBook()
                    }
                }
                toggleLike.isChecked = SharedPref.get(book.bookID, false)
            }
        }

    }
}