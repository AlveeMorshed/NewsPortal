package com.moinul.newsportal.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.moinul.newsportal.R
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.viewModel.NewsViewModel

class NewsAdapter (val context: Context,
                   val viewModel: NewsViewModel
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList = emptyList<ArticleForRoomDB>()
    class NewsViewHolder(private val binding: View) :RecyclerView.ViewHolder(binding) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return NewsViewHolder(root)
    }

    override fun getItemCount(): Int {
        return this.newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        val imageView = holder.itemView.findViewById<ImageView>(R.id.newsImage)
        val titleView = holder.itemView.findViewById<MaterialTextView>(R.id.title)
        val authorView = holder.itemView.findViewById<MaterialTextView>(R.id.author)
        val publishDateView = holder.itemView.findViewById<MaterialTextView>(R.id.publishDate)
        val description = holder.itemView.findViewById<MaterialTextView>(R.id.description)
        val addBookmarkButton = holder.itemView.findViewById<ImageView>(R.id.addBookmarkButton)

        Log.d("in onBindViewHolder()", "############ in OnBindViewHolder()")
        Glide.with(context)
            .load(news.urlToImage)
            .centerCrop()
            .error(R.drawable.ic_connection_error)
            .into(imageView)


        titleView.text = news.title.toString()
        publishDateView.text = "📅 "+news.publishedAt.toString().substring(0, news.publishedAt.toString().indexOf("T"))
        description.text = news.description.toString()
        authorView.text = "🖋 "+news.author.toString()

        if(news.bookmarked==true){
            addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_added_24)
            addBookmarkButton.setOnClickListener {
                addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_add_24)
                viewModel.removeBookmark(news.id)
            }
        }else{
            addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_add_24)
            addBookmarkButton.setOnClickListener {
                addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_added_24)
                viewModel.addBookmark(news.id)
            }
        }



        imageView.setOnClickListener {
            Toast.makeText(context, news.url, Toast.LENGTH_SHORT).show()
        }


    }



    fun setDataset(newsList: List<ArticleForRoomDB>){
        this.newsList = newsList
        notifyDataSetChanged()
    }
}