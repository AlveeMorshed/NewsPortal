package com.moinul.newsportal.network

import com.moinul.newsportal.model.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://newsapi.org/v2/"
private const val API_KEY = "e9431cea1d5441c8b77bb40918d08745"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("top-headlines?country=us&apiKey=$API_KEY")
    suspend fun getTopNews():News
    @GET("top-headlines?category=sports&apiKey=$API_KEY")
    suspend fun getSportsNews():News

    @GET("top-headlines?category=business&apiKey=$API_KEY")
    suspend fun getBusinessNews():News

    @GET("top-headlines?category=entertainment&apiKey=$API_KEY")
    suspend fun getEntertainmentNews():News

    @GET("top-headlines?category=general&apiKey=$API_KEY")
    suspend fun getGeneralNews():News

    @GET("top-headlines?category=health&apiKey=$API_KEY")
    suspend fun getHealthNews():News

    @GET("top-headlines?category=science&apiKey=$API_KEY")
    suspend fun getScienceNews():News

    @GET("top-headlines?category=technology&apiKey=$API_KEY")
    suspend fun getTechnologyNews():News
}

object NewsApi{
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}