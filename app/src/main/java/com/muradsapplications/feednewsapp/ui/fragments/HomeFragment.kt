package com.muradsapplications.feednewsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.muradsapplications.feednewsapp.R
import com.muradsapplications.feednewsapp.databinding.FragmentHomeBinding
import com.muradsapplications.feednewsapp.ui.adapter.NewsAdapter
import com.muradsapplications.feednewsapp.ui.viewmodel.NewsViewModel
import com.muradsapplications.feednewsapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter(requireContext(), emptyList(), viewModel)
        binding.rv.adapter = adapter



        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchNews(Constants.API_KEY, newText ?: "")
                return true
            }
        })


        viewModel.searchList.observe(viewLifecycleOwner) { newsResponse ->
            adapter.newsList = newsResponse.articles
            adapter.notifyDataSetChanged()
            binding.refreshLayout.isRefreshing = false // Stop the refreshing animation
        }
        viewModel.newsList.observe(viewLifecycleOwner) { newsResponse ->
            adapter.newsList = newsResponse.articles
            adapter.notifyDataSetChanged()
            binding.refreshLayout.isRefreshing = false // Stop the refreshing animation
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.getNews()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempviewModel  : NewsViewModel by viewModels()
        viewModel = tempviewModel
    }
}