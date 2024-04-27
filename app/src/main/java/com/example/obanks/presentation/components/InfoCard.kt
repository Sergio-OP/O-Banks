package com.example.obanks.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.obanks.R
import com.example.obanks.domain.entities.Bank
import com.example.obanks.ui.theme.OBanksTheme

@Composable
fun InfoCard(
    bank: Bank,
    onMoreInfoClicked: () -> Unit,
    onFavoriteToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.card_info_content_padding))
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = bank.photoUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_bank),
                modifier = Modifier.size(dimensionResource(R.dimen.card_info_image_size))
            )

            Spacer(Modifier.weight(1F))

            Column {
                Text(
                    text = bank.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(dimensionResource(R.dimen.card_info_content_padding)))
                Button(onClick = { onMoreInfoClicked() }) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = stringResource(R.string.more_information)
                        )
                        Spacer(Modifier.width(dimensionResource(R.dimen.card_info_small_spacer)))
                        Text(text = stringResource(R.string.more_information))
                    }
                }
            }

            Spacer(Modifier.weight(2F))

            FavoriteIconButton(
                bank = bank,
                onFavoriteToggle = onFavoriteToggle
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun InfoCardPreview() {
    OBanksTheme {
        InfoCard(
            bank = Bank(
                name = "Paga Todo",
                photoUrl = "https://firebasestorage.googleapis.com/v0/b/stagingpagatodo-286214.appspot.com/o/Challenge%2Flogo-pagatodo.jpeg?alt=media&token=38b6ac4d-85ac-4288-bada-88eb5a0dec20",
                isFavorite = true
            ),
            onMoreInfoClicked = {},
            onFavoriteToggle = {}
        )
    }
}