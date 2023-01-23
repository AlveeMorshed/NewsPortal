package com.moinul.newsportal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.media.tv.TvContract.Programs.Genres.NEWS
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.utils.widget.ImageFilterButton
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.moinul.newsportal.R


import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.util.Constants
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_detailed_news.view.*
import kotlinx.android.synthetic.main.news_list.view.*
import java.util.*

class NewsAdapter (val context: Context,
                   val viewModel: NewsViewModel,
                   val currentFragment: Fragment
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
        val cardView = holder.itemView.cardView

        val imageView = holder.itemView.findViewById<ImageView>(R.id.newsImage)
        val titleView = holder.itemView.findViewById<MaterialTextView>(R.id.title)
        val authorView = holder.itemView.findViewById<MaterialTextView>(R.id.author)
        val publishDateView = holder.itemView.findViewById<MaterialTextView>(R.id.publishDate)
        val description = holder.itemView.findViewById<MaterialTextView>(R.id.description)
        val addBookmarkButton = holder.itemView.findViewById<ShapeableImageView>(R.id.addBookmarkButton)

        Log.d("in onBindViewHolder()", "############ in OnBindViewHolder()")

        //addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_add_24)

        Glide.with(context)
            .load(news.urlToImage)
            .centerCrop()
            .error(R.drawable.ic_connection_error)
            .into(imageView)


        titleView.text = news.title.toString()
        publishDateView.text = "📅 "+news.publishedAt.toString().substring(0, news.publishedAt.toString().indexOf("T"))
        description.text = news.description.toString()
        authorView.text = "🖋 "+news.author.toString()


        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(Constants.NEWS, news)
            //currentFragment.setHasOptionsMenu(false)
            holder.itemView.findNavController().navigate(R.id.detailedNewsFragment, bundle)

        }



        if(news.bookmarked == true){
            addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_added_24)
        }else{
            addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_add_24)
        }


       addBookmarkButton.setOnClickListener{
           if(!news.bookmarked){
               news.bookmarked=true
               viewModel.updateBookmark(news)
               addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_added_24)
               println(news.bookmarked)
           }else{
               news.bookmarked=false
               viewModel.updateBookmark(news)
               addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_add_24)
               println(news.bookmarked)
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

    fun performSearch(text: String){
        val searchResults =  ArrayList<ArticleForRoomDB>()
        for(article in newsList){
            Log.d("List e ki?", "${newsList}")
            if(article.title?.lowercase(Locale.ROOT)?.contains(text.lowercase(Locale.ROOT)) == true)
            {
                searchResults.add(article)
            }
        }
        showResults(searchResults)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun showResults(searchResults: List<ArticleForRoomDB>){
        newsList = searchResults
        notifyDataSetChanged()
    }

}