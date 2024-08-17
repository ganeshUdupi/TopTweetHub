package com.ganesh.toptweethub.di

import android.util.Log
import com.ganesh.toptweethub.api.TopTweetHubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesTopTweetHubApi(retrofit: Retrofit): TopTweetHubApi {
        Log.d("NetworkModule", "Providing TopTweetHubApi instance")
        return retrofit.create(TopTweetHubApi::class.java)
    }

}