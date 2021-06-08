package com.example.googlebooksapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlebooksapp.R
import com.example.googlebooksapp.model.Book

class BookAdapter(private var context: Context, private var bookList: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return BookViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook: Book = bookList[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentBook.volumeInfo.infoLink))
            context.startActivity(intent)
        }

        holder.title.text = currentBook.volumeInfo.title
        if (currentBook.volumeInfo.authors != null)
            holder.author.text = "Authors: " + currentBook.volumeInfo.authors.toString()
                .replace("[", "")
                .replace("]", "")
        else
            holder.author.text = "Authors: N/A"

        if (currentBook.volumeInfo.averageRating != null)
            holder.rating.rating = currentBook.volumeInfo.averageRating

        if (currentBook.volumeInfo.categories != null)
            holder.category.text = "Categories: " + currentBook.volumeInfo.categories.toString()
            .replace("[", "")
            .replace("]", "")

        holder.pages.text = "Pages: " + currentBook.volumeInfo.pageCount.toString()
        holder.publishedDate.text = "Published Date: " + currentBook.volumeInfo.publishedDate
        Glide.with(context)
                .load(currentBook.volumeInfo.imageLinks.thumbnail.replace("http", "https"))
                .into(holder.image)

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

     class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var image: ImageView = itemView.findViewById(R.id.bookImage)
         var title: TextView = itemView.findViewById(R.id.bookTitle)
         var author: TextView = itemView.findViewById(R.id.bookAuthor)
         var category: TextView = itemView.findViewById(R.id.bookCategory)
         var publishedDate: TextView = itemView.findViewById(R.id.publishedDate)
         var pages: TextView = itemView.findViewById(R.id.bookPages)
         var rating: RatingBar = itemView.findViewById(R.id.bookRating)
     }
}