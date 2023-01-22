package com.moinul.newsportal.adapters

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
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.moinul.newsportal.R
import com.moinul.newsportal.fragments.DetailedNewsFragment
import com.moinul.newsportal.fragments.HomeFragmentDirections
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.util.Constants
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_detailed_news.view.*
import kotlinx.android.synthetic.main.news_list.view.*

class NewsAdapter (val context: Context,
                   val viewModel: NewsViewModel,
                   private val lifecycleOwner: LifecycleOwner
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
        val addBookmarkButton = holder.itemView.findViewById<ImageFilterView>(R.id.addBookmarkButton)

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
            holder.itemView.findNavController().navigate(R.id.detailedNewsFragment, bundle)
        }

        /*cardView.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(Constants.NEWS, news)
            holder.itemView.findNavController().navigate(R.id.detailedNewsFragment, bundle)
        }*/
/*
        if(news.id == 141){
            Log.d("BOOKMARK KI?", "${getBookmarkStatus(news)}")
            println(news.bookmarked)
        }*/


        /*if(getBookmarkStatus(news)==true){
            addBookmarkButton.crossfade = 1.0F
        }else{
            addBookmarkButton.crossfade = 0.0F
        }*/

        /*viewModel.isBookmarked(news.id).observe(holder.itemView.context as LifecycleOwner, Observer {
            if(it){
                addBookmarkButton.crossfade = 1.0F
                if(news.id ==141 )
                    Log.d("BOOKMARK KI?", "getBookmarkStatus:= ${it}")
            }else{
                addBookmarkButton.crossfade = 0.0F
                if(news.id ==141 )
                    Log.d("BOOKMARK KI?", "getBookmarkStatus:= ${it}")
            }
        })*/

       addBookmarkButton.setOnClickListener{
           if(addBookmarkButton.crossfade==0.0F){
               addBookmarkButton.crossfade = 1.0F
               viewModel.addBookmark(news.id)
               notifyDataSetChanged()

               println(news.bookmarked)
           }else{
               addBookmarkButton.crossfade = 0.0F
               viewModel.removeBookmark(news.id)
               notifyDataSetChanged()

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

    /*fun getBookmarkStatus(news : ArticleForRoomDB): Boolean{
        var flag = true
        viewModel.isBookmarked(news.id).observe(lifecycleOwner){
            news.bookmarked = it
            flag = it

        }
        Log.d("BOOKMARK KI?", "${news.bookmarked}")
        println(news.bookmarked)
        Log.d("BOOKMARK KI?", "getBookmarkStatus: ${flag}")
        return flag
    }*/
}