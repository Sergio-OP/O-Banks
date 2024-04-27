package com.example.obanks.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import com.example.obanks.domain.use_cases.GetBankByIdUseCase
import com.example.obanks.domain.use_cases.GetBanksByNameUseCase
import com.example.obanks.domain.use_cases.GetBanksUseCase
import com.example.obanks.domain.use_cases.ToggleFavoriteBankUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBankByIdUseCase: GetBankByIdUseCase,
    private val toggleFavoriteBankUseCase: ToggleFavoriteBankUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val screenState: StateFlow<DetailsUiState> = _screenState.asStateFlow()

    fun getBankById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getBankByIdUseCase.invoke(id).collect { bank ->
                    _screenState.update {
                        DetailsUiState.Success(bank)
                    }
                }

            } catch (e: Exception) {
                _screenState.update { DetailsUiState.Error(e.message ?: "Error Unknown") }
            }

        }
    }

    fun toggleFavoriteBank(bank: Bank) {
        val bankUpdated = bank.copy(
            isFavorite = !bank.isFavorite
        )
        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoriteBankUseCase.invoke(bankUpdated)
        }
    }


}