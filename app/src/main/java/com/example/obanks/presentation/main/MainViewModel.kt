package com.example.obanks.presentation.main

import androidx.lifecycle.ViewModel
import com.example.obanks.domain.entities.Bank
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun updateQuery(input: String) = _uiState.update { it.copy(query = input) }

    fun onSearch() {/*TODO*/}

    fun onActiveChange() {
        //TODO("Not yet implemented")
    }

    fun toggleFavoriteBank(bank: Bank) {
        // TODO
    }


}