package com.example.obanks.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.use_cases.GetBanksUseCase
import com.example.obanks.domain.use_cases.SaveBanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBanksUseCase: GetBanksUseCase,
    private val saveBanksUseCase: SaveBanksUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val screenState: StateFlow<MainScreenState> = _screenState.asStateFlow()

    private val _screenUiState = MutableStateFlow(MainScreenUiState())
    val screenUiState: StateFlow<MainScreenUiState> = _screenUiState.asStateFlow()


    init {
        viewModelScope.launch {
            fetchBanksData()
        }
    }

    fun fetchBanksData() {
        if (_screenState.value is MainScreenState.Success) return
        _screenState.value = MainScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            try {
                val banksData = getBanksUseCase.invoke()
                if (banksData.isEmpty()) throw Exception("Something went wrong. Please try again later.")
                _screenState.update {
                    MainScreenState.Success(data = banksData)
                }
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
        // TODO
    }


}