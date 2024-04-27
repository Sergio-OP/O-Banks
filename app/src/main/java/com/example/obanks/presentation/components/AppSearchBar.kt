@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.obanks.presentation.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.obanks.R
import com.example.obanks.domain.entities.Bank
import com.example.obanks.ui.theme.OBanksTheme

@Composable
fun AppSearchBar(
    banksFound: List<Bank>,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    var query by rememberSaveable { mutableStateOf("") }
    var isActive by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange = { query = it },
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
        banksFound.forEach { bank ->
            Text(bank.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppSearchBarPreview() {
    OBanksTheme {
        AppSearchBar(
            onSearch = {},
            banksFound = emptyList(),
        )
    }
}