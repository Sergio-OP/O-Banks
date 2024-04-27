package com.example.obanks.presentation.screens.details

import com.example.obanks.domain.entities.Bank

sealed class DetailsUiState {
    data class Success(val bank: Bank) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
    data object Loading : DetailsUiState()
}