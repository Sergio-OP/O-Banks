package com.example.obanks.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.obanks.R
import com.example.obanks.presentation.components.AppSearchBar
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
    val appSearchBarUiState = viewModel.appSearchBarUiState.collectAsState()

    when (screenState.value) {
        is MainScreenState.Error -> {
            ErrorScreen(
                message = (screenState.value as MainScreenState.Error).message,
                onTryAgain = { viewModel.fetchBanksData() },
                modifier = modifier
            )
        }

        MainScreenState.Loading -> {
            LoadingScreen(modifier = modifier)
        }

        is MainScreenState.Success -> {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = modifier
            ) {

                AppSearchBar(
                    onSearch = { viewModel.getBanksByName(it) },
                    banksFound = appSearchBarUiState.value.banks,
                    onBankClicked = { onMoreInfoClicked(it) },
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(R.dimen.card_info_content_padding))
                        .fillMaxWidth()
                )

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