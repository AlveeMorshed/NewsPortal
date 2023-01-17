package com.moinul.newsportal.model

data class SportsNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)