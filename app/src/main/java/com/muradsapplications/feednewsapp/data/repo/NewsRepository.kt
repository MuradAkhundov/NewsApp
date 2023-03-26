package com.muradsapplications.feednewsapp.data.repo

import com.muradsapplications.feednewsapp.data.datasource.NewsDataSource
import com.muradsapplications.feednewsapp.response.NewsResponse
import retrofit2.Call

class NewsRepository(var nds : NewsDataSource) {
    fun getTopHeadlines(apiKey : String , country : String) : Call<NewsResponse> = nds.getTopHeadlines(apiKey, country)

    fun searchNews(apiKey: String,q:String) : Call<NewsResponse> = nds.searchNews(apiKey, q)
}