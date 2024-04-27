package com.example.obanks.presentation.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.use_cases.FetchBanksUseCase
import com.example.obanks.domain.use_cases.GetBanksByNameUseCase
import com.example.obanks.domain.use_cases.GetBanksUseCase
import com.example.obanks.domain.use_cases.SaveBanksUseCase
import com.example.obanks.domain.use_cases.ToggleFavoriteBankUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchBanksUseCase: FetchBanksUseCase,
    private val saveBanksUseCase: SaveBanksUseCase,
    private val toggleFavoriteBankUseCase: ToggleFavoriteBankUseCase,
    private val getBanksUseCase: GetBanksUseCase,
    private val getBanksByNameUseCase: GetBanksByNameUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val screenState: StateFlow<MainScreenState> = _screenState.asStateFlow()

    private val _appSearchBarUiState = MutableStateFlow(AppSearchBarUiState())
    val appSearchBarUiState: StateFlow<AppSearchBarUiState> = _appSearchBarUiState.asStateFlow()


    init {
        viewModelScope.launch {
            fetchBanksData()
            getBanksData()
        }
    }

    fun fetchBanksData() {
        if (_screenState.value is MainScreenState.Success) return
        _screenState.value = MainScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            try {
                val banksData = fetchBanksUseCase.invoke()
                saveBanksUseCase.invoke(banksData)
            } catch (e: Exception) {
                _screenState.update {
                    MainScreenState.Error(
                        e.message ?: "Something went wrong. Please try again later."
                    )
                }
            }
        }
    }

    private fun getBanksData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getBanksUseCase.invoke().collect { banks ->
                    _screenState.update {
                        MainScreenState.Success(data = banks)
                    }
                }
            } catch (e: Exception) {
                _screenState.update {
                    MainScreenState.Error(
                        e.message ?: "Something went wrong. Please try again later."
                    )
                }
            }
        }
    }


    fun getBanksByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(TAG, "Searching Banks with $name")
            try {
                getBanksByNameUseCase.invoke(name).collect { banks ->
                    _appSearchBarUiState.update { it.copy(banks = banks) }
                    Log.i(TAG, "Banks Found:  ${banks.size}")
                }
            } catch (e: Exception) {
                _appSearchBarUiState.update { it.copy(banks = emptyList()) }
            }
        }
    }


    fun toggleFavoriteBank(bank: Bank) {
        val bankUpdated = bank.copy(
            isFavorite = !bank.isFavorite
        )
        Log.i(TAG, "Bank Id: ${bankUpdated.id} is Favorite ${bankUpdated.isFavorite}")
        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoriteBankUseCase.invoke(bankUpdated)
        }
    }


}