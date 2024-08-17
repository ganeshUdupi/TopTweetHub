package com.ganesh.toptweethub.api

import com.ganesh.toptweethub.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TopTweetHubApi {
    @GET("/v3/b/66c0b006ad19ca34f8975b11?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("/v3/b/66c0b006ad19ca34f8975b11?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories(): Response<List<String>>
}