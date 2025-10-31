package com.example.merchplace.presentation.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchplace.domain.entities.Booth
import com.example.merchplace.domain.repository.IBoothRepository
import com.example.merchplace.shared.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MapViewModel(
    private val boothRepository: IBoothRepository = RepositoryProvider.boothRepository
) : ViewModel() {
    
    private val _booths = MutableStateFlow<List<Booth>>(emptyList())
    val booths: StateFlow<List<Booth>> = _booths.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _selectedBooth = MutableStateFlow<Booth?>(null)
    val selectedBooth: StateFlow<Booth?> = _selectedBooth.asStateFlow()
    
    init {
        loadBooths()
    }
    
    private fun loadBooths() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                boothRepository.getBooths().collect { boothList ->
                    _booths.value = boothList
                }
            } catch (e: Exception) {
                _booths.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun selectBooth(booth: Booth?) {
        _selectedBooth.value = booth
    }
}

