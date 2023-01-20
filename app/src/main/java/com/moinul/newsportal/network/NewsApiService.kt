package com.moinul.newsportal.network

import android.provider.SyncStateContract
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.model.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/v2/"
private const val API_KEY = "e9431cea1d5441c8b77bb40918d08745"
//private const val API_KEY = "e923cfa02e8949ac9341a172e7874d35"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {

   /* @GET("v2/top-headlines@country")
    suspend fun getBreakingNews(@Query("country") countryCode: String = "us",@Query("page") pageNumber: Int = 1,@Query("apiKey") apiKey: String = SyncStateContract.Constants.API_KEY): Response<NewsResponse>*/
    @GET("top-headlines")
    suspend fun getAllNews(@Query("category") category: String?, @Query("country") countryCode: String = "us", @Query("apiKey") apiKey: String = API_KEY): News
}

object NewsApi{
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}