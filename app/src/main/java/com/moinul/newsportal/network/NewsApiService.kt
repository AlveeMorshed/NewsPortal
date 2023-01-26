package com.moinul.newsportal.network

import com.moinul.newsportal.model.News
import com.moinul.newsportal.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface NewsApiService {

   /* @GET("v2/top-headlines@country")
    suspend fun getBreakingNews(@Query("country") countryCode: String = "us",@Query("page") pageNumber: Int = 1,@Query("apiKey") apiKey: String = SyncStateContract.Constants.Constants.API_KEY): Response<NewsResponse>*/
    @GET("top-headlines")
    suspend fun getAllNews(@Query("category") category: String?, @Query("country") countryCode: String = "us", @Query("apiKey") apiKey: String = Constants.API_KEY): News
}

object NewsApi{
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}