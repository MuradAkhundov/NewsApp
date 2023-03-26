package com.muradsapplications.feednewsapp.di

import com.muradsapplications.feednewsapp.data.datasource.NewsDataSource
import com.muradsapplications.feednewsapp.data.repo.NewsRepository
import com.muradsapplications.feednewsapp.retrofit.ApiUtils
import com.muradsapplications.feednewsapp.retrofit.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(nds : NewsDataSource) : NewsRepository{
        return NewsRepository(nds)
    }

    @Provides
    @Singleton
    fun provideNewsDataSource(nd : NewsDao) : NewsDataSource{
        return NewsDataSource(nd)
    }

    @Provides
    @Singleton
    fun provideNewsDao() : NewsDao{
        return ApiUtils.getNewsDao()
    }
}