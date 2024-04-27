package com.example.obanks.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.use_cases.GetBanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBanksUseCase: GetBanksUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            val banks = getBanksUseCase.invoke()
            _uiState.update { it.copy(banks = banks) }
        }
    }

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun updateQuery(input: String) = _uiState.update { it.copy(query = input) }

    fun onSearch() {/*TODO*/
    }

    fun onActiveChange() {
        //TODO("Not yet implemented")
    }

    fun toggleFavoriteBank(bank: Bank) {
        // TODO
    }


}