package com.example.merchplace.presentation.screens.auctions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Auction
import com.example.merchplace.domain.repository.AuctionRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuctionsViewModel(
    private val repository: AuctionRepository = RepositoryProvider.auctionRepository
) : ViewModel() {
    
    private val _auctions = MutableStateFlow<List<Auction>>(emptyList())
    val auctions: StateFlow<List<Auction>> = _auctions.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private var loadJob: Job? = null
    
    init {
        loadAuctions()
    }
    
    fun loadAuctions(category: String? = null) {
        // Cancel previous loading job if exists
        loadJob?.cancel()
        
        loadJob = viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.getAuctions(category).collect { auctionList ->
                    _auctions.value = auctionList
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        loadJob?.cancel()
    }
}

