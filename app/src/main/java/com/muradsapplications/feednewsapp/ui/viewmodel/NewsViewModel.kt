package com.muradsapplications.feednewsapp.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muradsapplications.feednewsapp.data.repo.NewsRepository
import com.muradsapplications.feednewsapp.entity.News
import com.muradsapplications.feednewsapp.response.NewsResponse
import com.muradsapplications.feednewsapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(var nrepo : NewsRepository) : ViewModel(){

    val newsList = MutableLiveData<NewsResponse>()
    val searchList = MutableLiveData<NewsResponse>()
    init {
        getNews()
    }
    fun getNews(){
        val responseCall = nrepo.getTopHeadlines(Constants.API_KEY,"us")
        responseCall.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
               if (response.code() == 200){
                   newsList.value = response.body()
               }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

        })
    }


    fun searchNews(apiKey : String ,q: String){
        val responseCall = nrepo.searchNews(apiKey , q)
        responseCall.enqueue(object  : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.code() == 200){
                    searchList.value = response.body()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

        })
    }
}