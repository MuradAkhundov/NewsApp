package com.muradsapplications.feednewsapp.retrofit


import com.muradsapplications.feednewsapp.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsDao {

    @GET("top-headlines")
    fun getTopHeadLines(
        @Query("apiKey") apiKey : String,
        @Query("country") country : String) : Call<NewsResponse>


    @GET("everything")
    fun searchNews(
        @Query("apiKey") apiKey: String,
        @Query("q") q: String
    ) : Call<NewsResponse>

}