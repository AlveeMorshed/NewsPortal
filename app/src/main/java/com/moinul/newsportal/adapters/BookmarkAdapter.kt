package com.moinul.newsportal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.moinul.newsportal.R
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.util.Constants
import com.moinul.newsportal.viewModel.BookmarkViewModel
import java.util.*

class BookmarkAdapter(val context: Context,
                      val viewModel: BookmarkViewModel,
                      val listFromFragment:List<Bookmark>
): RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {
    private var bookmarkList = listFromFragment

    class BookmarkViewHolder(private val binding: View): RecyclerView.ViewHolder(binding) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return BookmarkViewHolder(root)
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = bookmarkList[position]
        val errorText = "Not Available"

        val imageView = holder.itemView.findViewById<ImageView>(R.id.newsImage)
        val titleView = holder.itemView.findViewById<MaterialTextView>(R.id.title)
        val authorView = holder.itemView.findViewById<MaterialTextView>(R.id.author)
        val publishDateView = holder.itemView.findViewById<MaterialTextView>(R.id.publishDate)
        val description = holder.itemView.findViewById<MaterialTextView>(R.id.description)
        val addBookmarkButton = holder.itemView.findViewById<ShapeableImageView>(R.id.addBookmarkButton)

        Glide.with(context)
            .load(bookmark.urlToImage)
            .centerCrop()
            .error(R.drawable.ic_connection_error)
            .into(imageView)


        titleView.text = bookmark.title.toString()
        if(bookmark.title==null || bookmark.title == ""){
            titleView.text = errorText
        }

        publishDateView.text = "📅 "+bookmark.publishedAt.toString().substring(0, bookmark.publishedAt.toString().indexOf("T"))
        if(publishDateView.text== null || publishDateView.text == ""){
            publishDateView.text = errorText
        }


        description.text = bookmark.description.toString()
        if(description.text==null || description.text==""){
            description.text = errorText
        }
        authorView.text = "🖋 "+bookmark.author.toString()
        if(authorView.text==null || authorView.text=="" || authorView.text=="null"){
            authorView.text = errorText
        }
        addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_added_24)


        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            val article = ArticleForRoomDB(bookmark.id, bookmark.author, bookmark.content, bookmark.description, bookmark.publishedAt, bookmark.title, bookmark.url, bookmark.urlToImage, bookmark.category.toString(), true)
            bundle.putParcelable(Constants.NEWS, article)
            //currentFragment.setHasOptionsMenu(false)
            holder.itemView.findNavController().navigate(R.id.detailedNewsFragment, bundle)

        }

        addBookmarkButton.setOnClickListener{
            viewModel.deleteBookmark(bookmark.id)
        }



        imageView.setOnClickListener {
            Toast.makeText(context, bookmark.url, Toast.LENGTH_SHORT).show()
        }
    }

    fun performSearch(text: String){
        val searchResults =  ArrayList<Bookmark>()
        for(bookmark in listFromFragment){
            Log.d("List e ki?", "$bookmarkList")
            if(bookmark.title?.lowercase(Locale.ROOT)?.contains(text.lowercase(Locale.ROOT)) == true)
            {
                searchResults.add(bookmark)
            }
        }
        showResults(searchResults)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun showResults(searchResults: List<Bookmark>){
        bookmarkList = searchResults
        notifyDataSetChanged()
    }

}

