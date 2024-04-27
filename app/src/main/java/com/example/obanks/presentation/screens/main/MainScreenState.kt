package com.example.obanks.presentation.screens.main

import com.example.obanks.domain.entities.Bank

sealed class MainScreenState {
    data class Success(val data: List<Bank>) : MainScreenState()
    data class Error(val message: String) : MainScreenState()
    data object Loading : MainScreenState()
}

data class MainScreenUiState(
    val query: String = "",
)