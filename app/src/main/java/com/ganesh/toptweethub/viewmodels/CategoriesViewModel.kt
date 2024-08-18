package com.ganesh.toptweethub.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ganesh.toptweethub.repository.TopTweetHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: TopTweetHubRepository):ViewModel() {

    val categories: StateFlow<List<String>>
        get() = repository.categories

    init {
       viewModelScope.launch {
           repository.getCategories()
       }
    }
}