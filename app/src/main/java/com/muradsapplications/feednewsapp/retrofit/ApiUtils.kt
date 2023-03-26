package com.muradsapplications.feednewsapp.retrofit

import com.muradsapplications.feednewsapp.util.Constants

class ApiUtils {

    companion object{
        fun getNewsDao() :NewsDao{
            return RetrofitClient.getClient(Constants.BASE_URL).create(NewsDao::class.java)
        }
    }
}