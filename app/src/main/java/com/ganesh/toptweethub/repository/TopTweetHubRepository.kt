package com.ganesh.toptweethub.repository

import com.ganesh.toptweethub.api.TopTweetHubApi
import com.ganesh.toptweethub.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TopTweetHubRepository @Inject constructor(private val topTweetHubApi: TopTweetHubApi) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val  categories: StateFlow<List<String>>
        get() = _categories


    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = topTweetHubApi.getCategories()
        if (response.isSuccessful && response.body() != null) {
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = topTweetHubApi.getTweets(category)
        if (response.isSuccessful && response.body() != null) {
            _tweets.emit(response.body()!!)
        }
    }
}