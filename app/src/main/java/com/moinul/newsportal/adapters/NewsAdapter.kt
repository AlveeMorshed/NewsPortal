package com.moinul.newsportal.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.moinul.newsportal.R
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.util.Constants
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_detailed_news.view.*
import kotlinx.android.synthetic.main.news_list.view.*
import java.util.*

class NewsAdapter(
    val context: Context, val viewModel: NewsViewModel, val listFromFragment: List<ArticleForRoomDB>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsList = listFromFragment

    class NewsViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        return NewsViewHolder(root)
    }

    override fun getItemCount(): Int {
        return this.newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        val errorText = "Not Available"
        val imageView = holder.itemView.findViewById<ImageView>(R.id.newsImage)
        val titleView = holder.itemView.findViewById<MaterialTextView>(R.id.title)
        val authorView = holder.itemView.findViewById<MaterialTextView>(R.id.author)
        val publishDateView = holder.itemView.findViewById<MaterialTextView>(R.id.publishDate)
        val description = holder.itemView.findViewById<MaterialTextView>(R.id.description)
        val addBookmarkButton =
            holder.itemView.findViewById<ShapeableImageView>(R.id.addBookmarkButton)

        Glide.with(context).load(news.urlToImage).centerCrop().error(R.drawable.ic_connection_error)
            .into(imageView)

        if((news.title == null) || (news.title == "") || (news.title.toString() == "null")){

            titleView.text = errorText
        }else {
            titleView.text = news.title.toString()
        }

        if(news.publishedAt == null || news.publishedAt.toString() == "" || news.publishedAt.toString()=="null"){
            publishDateView.text = "📅 "+errorText
        }else{
            publishDateView.text = "📅 "+news.publishedAt.toString().substring(0, news.publishedAt.toString().indexOf("T"))
        }

        if(news.description ==null || news.description.toString()=="" || news.description.toString()=="null"){
            description.text = errorText
        }else{
            description.text = news.description.toString()
        }

        if(news.author == null || news.author =="" || news.author.toString()=="null"){
            authorView.text = "🖋 "+errorText
        }else{
            authorView.text = "🖋 "+news.author.toString()
        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            val currentArticle = ArticleForRoomDB(news.id, authorView.text.toString(), news.content, description.text.toString(), publishDateView.text.toString(), titleView.text.toString(), news.url, news.urlToImage, news.category, news.bookmarked)
            bundle.putParcelable(Constants.NEWS, currentArticle)
            //currentFragment.setHasOptionsMenu(false)
            holder.itemView.findNavController().navigate(R.id.detailedNewsFragment, bundle)

        }



        if (news.bookmarked) {
            addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_added_24)
        } else {
            addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_add_24)
        }


        addBookmarkButton.setOnClickListener {
            if (!news.bookmarked) {
                news.bookmarked = true
                viewModel.updateBookmark(news)
                viewModel.addBookmark(
                    Bookmark(
                        news.id,
                        news.author,
                        news.content,
                        news.description,
                        news.publishedAt,
                        news.title,
                        news.url,
                        news.urlToImage,
                        news.category
                    )
                )
                addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_added_24)
                println(news.bookmarked)
            } else {
                news.bookmarked = false
                viewModel.updateBookmark(news)
                viewModel.deleteBookmark(news.id)
                addBookmarkButton.setImageResource(R.drawable.baseline_bookmark_add_24)
                println(news.bookmarked)
            }
        }
    }

    fun setDataset(newsList: List<ArticleForRoomDB>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    fun performSearch(text: String) {
        val searchResults = ArrayList<ArticleForRoomDB>()
        for (article in listFromFragment) {
            Log.d("List e ki?", "$newsList")
            if (article.title?.lowercase(Locale.ROOT)
                    ?.contains(text.lowercase(Locale.ROOT)) == true
            ) {
                searchResults.add(article)
            }
        }
        showResults(searchResults)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun showResults(searchResults: List<ArticleForRoomDB>) {
        newsList = searchResults
        notifyDataSetChanged()
    }

}