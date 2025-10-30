package com.example.merchplace.presentation.screens.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.FeedPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Временный репозиторий для Feed, пока не создан в domain
interface FeedRepository {
    fun getFeedPosts(): kotlinx.coroutines.flow.Flow<List<FeedPost>>
}

class FeedViewModel(
    private val feedRepository: FeedRepository? = null
) : ViewModel() {
    
    private val _posts = MutableStateFlow<List<FeedPost>>(emptyList())
    val posts: StateFlow<List<FeedPost>> = _posts.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        // Пока используем mock данные напрямую, так как FeedRepository еще не создан
        // В будущем: loadFeedPosts()
    }
}

