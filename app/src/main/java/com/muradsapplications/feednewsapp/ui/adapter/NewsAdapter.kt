package com.muradsapplications.feednewsapp.ui.adapter

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import com.muradsapplications.feednewsapp.databinding.CardNewsBinding
import com.muradsapplications.feednewsapp.entity.News
import com.muradsapplications.feednewsapp.ui.fragments.HomeFragmentDirections
import com.muradsapplications.feednewsapp.ui.viewmodel.NewsViewModel
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(var mContext : Context ,var newsList: List<News> , val viewModel : NewsViewModel) :RecyclerView.Adapter<NewsAdapter.CardDesignHolder>() {


    inner class CardDesignHolder(val binding: CardNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardNewsBinding.inflate(LayoutInflater.from(mContext) ,parent,false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val b = holder.binding
        val news = newsList.get(position)
        b.textViewTitle.text = news.title
        news.urlToImage?.let { setImageWithGlide(it, b.imageNews)}
        b.sourceText.text = news.source.name


        // Convert published date to relative time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse(news.publishedAt)
        val relativeTime = TimeAgo.using(date.time, TimeAgoMessages.Builder().withLocale(Locale.ENGLISH).build())



        b.root.setOnClickListener{
            try {
                val action = HomeFragmentDirections.toDetail(news)
                Navigation.findNavController(it).navigate(action)
            }
            catch (e : Exception){
//                Toast.makeText(mContext,"This information is not reachable , please try other ones :(" , Toast.LENGTH_SHORT).show()
            }
        }
        // Set relative time to date text view
        b.dateText.text = relativeTime
    }

    override fun getItemCount(): Int {
       return newsList.size
    }

    fun setImageWithGlide(imageUrl: String, imageView: ImageView) {
        Glide.with(mContext)
            .load(imageUrl)
            .override(300, 150)
            .centerCrop()
            .into(imageView)
    }
}