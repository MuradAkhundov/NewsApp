package com.muradsapplications.feednewsapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.muradsapplications.feednewsapp.entity.News

data class NewsResponse(
@SerializedName("status")
@Expose
val status : String,
@SerializedName("totalResults")
@Expose
val totalResults : Int,
@SerializedName("articles")
@Expose
val articles : List<News>
) : java.io.Serializable