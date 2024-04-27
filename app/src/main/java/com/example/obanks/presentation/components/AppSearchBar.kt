@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.obanks.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.obanks.R
import com.example.obanks.domain.entities.Bank
import com.example.obanks.ui.theme.OBanksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    banksFound: List<Bank>,
    onSearch: (String) -> Unit,
    onBankClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var query by rememberSaveable { mutableStateOf("") }
    var isActive by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange = {
            query = it
            onSearch(it)
        },
        onSearch = { onSearch(it) },
        active = isActive,
        onActiveChange = { isActive = it },
        shape = RoundedCornerShape(50),
        leadingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { query = "" }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.clear)
                )
            }
        },
        modifier = modifier
    ) {
        if (isActive) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.card_info_content_padding)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_info_content_padding))
            ) {
                items(banksFound) { bank ->
                    BankElement(
                        name = bank.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onBankClicked(bank.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun BankElement(
    name: String,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = name,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppSearchBarPreview() {
    OBanksTheme {
        AppSearchBar(
            onSearch = {},
            banksFound = emptyList(),
            onBankClicked = {}
        )
    }
}