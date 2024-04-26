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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.obanks.R
import com.example.obanks.domain.entities.Bank
import com.example.obanks.ui.theme.OBanksTheme

@Composable
fun AppSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    banksFound: List<Bank>,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
) {
    SearchBar(
        query = query,
        onQueryChange = { onQueryChange(it) },
        onSearch = { onSearch(it) },
        active = isActive,
        onActiveChange = { onActiveChange(it) },
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
            IconButton(onClick = { onClear() }) {
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
            query = "",
            onQueryChange = {},
            onSearch = {},
            onActiveChange = {},
            banksFound = emptyList(),
            onClear = {}
        )
    }
}