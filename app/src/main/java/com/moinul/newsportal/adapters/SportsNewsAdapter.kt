package com.moinul.newsportal.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moinul.newsportal.R
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.viewModel.NewsViewModel

class SportsNewsAdapter (val context: Context,
                         val viewModel: NewsViewModel,
                         private val newsList: ArrayList<Article>
): RecyclerView.Adapter<SportsNewsAdapter.NewsViewHolder>()  {
    class NewsViewHolder(private val binding: View) :RecyclerView.ViewHolder(binding) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return NewsViewHolder(root)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        val imageView = holder.itemView.findViewById<ImageView>(R.id.newsImage)

        Log.d("in onBindViewHolder()", "############ in OnBindViewHolder()")
        Glide.with(context)
            .load(news.urlToImage)
            .into(imageView)



        imageView.setOnClickListener {
            Toast.makeText(context, news.url, Toast.LENGTH_SHORT).show()
        }
    }
}