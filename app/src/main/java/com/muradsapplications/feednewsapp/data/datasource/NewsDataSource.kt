package com.muradsapplications.feednewsapp.data.datasource

import com.muradsapplications.feednewsapp.response.NewsResponse
import com.muradsapplications.feednewsapp.retrofit.NewsDao
import retrofit2.Call

class NewsDataSource(var nd : NewsDao) {
    fun getTopHeadlines(apiKey : String , country : String) : Call<NewsResponse> = nd.getTopHeadLines(apiKey,country)

    fun searchNews(apiKey: String,q:String) : Call<NewsResponse> = nd.searchNews(apiKey, q)
}