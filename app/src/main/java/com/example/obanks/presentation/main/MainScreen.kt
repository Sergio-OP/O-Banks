package com.example.obanks.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.obanks.presentation.components.AppSearchBar
import com.example.obanks.presentation.components.InfoCard

@Composable
fun MainScreen(
    onMoreInfoClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val viewModel: MainViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
    ) {
        AppSearchBar(
            query = uiState.value.query,
            onQueryChange = { viewModel.updateQuery(it) },
            banksFound = uiState.value.banks,
            onSearch = { viewModel.onSearch() },
            onActiveChange = { viewModel.onActiveChange() },
            onClear = { viewModel.updateQuery("") }
        )
        LazyColumn {
            items(items = uiState.value.banks) { bank ->
                InfoCard(
                    bank = bank,
                    onMoreInfoClicked = { onMoreInfoClicked() },
                    onFavoriteToggle = { viewModel.toggleFavoriteBank(bank) }
                )
            }
        }
    }

}