package com.example.obanks.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.use_cases.FetchBanksUseCase
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
) : ViewModel() {

    private val _screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val screenState: StateFlow<MainScreenState> = _screenState.asStateFlow()

    private val _screenUiState = MutableStateFlow(MainScreenUiState())
    val screenUiState: StateFlow<MainScreenUiState> = _screenUiState.asStateFlow()


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

    fun updateQuery(input: String) {
        if (_screenState.value is MainScreenState.Success) {
            _screenUiState.update { it.copy(query = input) }
        }
    }

    fun onSearch() {/*TODO*/
    }

    fun onActiveChange() {
        //TODO("Not yet implemented")
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