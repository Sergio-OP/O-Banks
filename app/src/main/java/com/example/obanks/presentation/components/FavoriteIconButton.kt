package com.example.obanks.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.obanks.R
import com.example.obanks.domain.entities.Bank

@Composable
fun FavoriteIconButton(
    bank: Bank,
    onFavoriteToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    IconToggleButton(
        checked = bank.isFavorite,
        onCheckedChange = { onFavoriteToggle(it) },
        modifier = modifier
    ) {
        if (bank.isFavorite) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.remove_from_favorites)
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.add_to_favorites)
            )
        }
    }
}