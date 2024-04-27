package com.example.obanks.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.obanks.R
import com.example.obanks.presentation.components.InfoCard
import com.example.obanks.presentation.screens.ErrorScreen
import com.example.obanks.presentation.screens.LoadingScreen

@Composable
fun MainScreen(
    onMoreInfoClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val viewModel: MainViewModel = hiltViewModel()
    val screenState = viewModel.screenState.collectAsState()
    val uiState = viewModel.screenUiState.collectAsState()

    Column(
        modifier = modifier
    ) {

        when (screenState.value) {
            is MainScreenState.Error -> {
                ErrorScreen(
                    message = (screenState.value as MainScreenState.Error).message,
                    onTryAgain = { viewModel.fetchBanksData() },
                    modifier = Modifier.fillMaxSize()
                )
            }

            MainScreenState.Loading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }

            is MainScreenState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.card_info_content_padding))
                ) {
                    items(items = (screenState.value as MainScreenState.Success).data) { bank ->
                        InfoCard(
                            bank = bank,
                            onMoreInfoClicked = { onMoreInfoClicked(bank.id) },
                            onFavoriteToggle = { viewModel.toggleFavoriteBank(bank) },
                            modifier = Modifier.padding(top = dimensionResource(R.dimen.card_info_content_padding))
                        )
                    }
                }
            }

        }


    }

}