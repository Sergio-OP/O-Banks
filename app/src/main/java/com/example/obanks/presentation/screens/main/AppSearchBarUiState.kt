package com.example.obanks.presentation.screens.main

import com.example.obanks.domain.entities.Bank

data class AppSearchBarUiState(
    val banks: List<Bank> = emptyList()
)
