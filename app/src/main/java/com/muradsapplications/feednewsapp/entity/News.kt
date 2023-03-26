package com.muradsapplications.feednewsapp.entity

data class News(
    val title : String,
    val description : String,
    val url : String,
    val publishedAt : String,
    val source: Source,
    val author : String,
    val urlToImage : String?

) : java.io.Serializable