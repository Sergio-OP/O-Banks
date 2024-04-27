package com.example.obanks.presentation.main

import com.example.obanks.domain.entities.Bank

data class MainUiState(
    val query: String = "",
    val banks: List<Bank> = emptyList()
)