package com.example.merchplace.presentation.screens.auctions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Auction
import com.example.merchplace.domain.repository.AuctionRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuctionDetailViewModel(
    private val repository: AuctionRepository = RepositoryProvider.auctionRepository
) : ViewModel() {

    private val _auction = MutableStateFlow<Auction?>(null)
    val auction: StateFlow<Auction?> = _auction.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadAuction(auctionId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _auction.value = repository.getAuctionById(auctionId)
            } catch (e: Exception) {
                _auction.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}

