package com.moinul.newsportal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moinul.newsportal.R
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.model.SportsNews
import com.moinul.newsportal.viewModel.NewsViewModel

class NewsAdapter: ListAdapter<Article, NewsAdapter.NewsViewHolder>(NewsItemDiffCallback())  {
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindTo(article: Article){
            // bind views with data
        }
    }

    class NewsItemDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldNews: Article, newNews: Article): Boolean{
            return oldNews.url == newNews.url
        }

        override fun areContentsTheSame(oldNews: Article, newNews: Article): Boolean{
            return oldNews == newNews
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_sports, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        return when (count) {
            0 -> 1
            else -> count
        }
    }

}