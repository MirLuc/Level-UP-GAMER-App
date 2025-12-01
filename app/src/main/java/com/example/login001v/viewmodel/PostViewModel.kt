package com.example.login001v.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login001v.data.model.Post
import com.example.login001v.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class PostViewModel(private val repository: PostRepository = PostRepository()) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState(isLoading = true))
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val result = repository.getPosts()
                _uiState.value = PostUiState(posts = result, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = PostUiState(
                    isLoading = false,
                    error = "Error al obtener posts: ${e.localizedMessage}"
                )
            }
        }
    }
}