package com.muradsapplications.feednewsapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.muradsapplications.feednewsapp.R
import com.muradsapplications.feednewsapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        val news = bundle.news

        binding.topicText.text = news.title


        // Check if URL can be displayed in webview
        if (URLUtil.isValidUrl(news.url)) {
            binding.webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(news.url)
                visibility = View.VISIBLE
            }
            binding.clickHereText.visibility = View.GONE
        } else {
            binding.webView.visibility = View.GONE
            binding.clickHereText.visibility = View.VISIBLE
            binding.clickHereText.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                startActivity(intent)
            }
        }

        setImageWithGlide(news.urlToImage, binding.imageNews)

        return binding.root
    }

    private fun setImageWithGlide(imageUrl: String?, imageView: ImageView) {
        Glide.with(requireContext())
            .load(imageUrl)
            .override(300, 150)
            .into(imageView)
    }
}
