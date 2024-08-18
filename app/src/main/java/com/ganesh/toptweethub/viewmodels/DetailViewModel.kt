package com.ganesh.toptweethub.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ganesh.toptweethub.models.TweetListItem
import com.ganesh.toptweethub.repository.TopTweetHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TopTweetHubRepository,private val savedStateHandle: SavedStateHandle):ViewModel() {
    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets

    init {
       viewModelScope.launch {
           val category = savedStateHandle.get<String>("category")?:"Android"
           repository.getTweets(category)
       }
    }
}