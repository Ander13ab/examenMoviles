package com.example.appexamenapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.appexamenapi.data.model.Post
import perfetto.protos.UiState

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    var postList by mutableStateOf<List<Post>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                postList = repository.getPosts()
            } catch (e: Exception) {
                // Log error o manejar el estado
            } finally {
                isLoading = false
            }
        }
    }
}