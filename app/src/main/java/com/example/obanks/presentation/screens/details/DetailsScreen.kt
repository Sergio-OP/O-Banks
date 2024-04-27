package com.example.obanks.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.obanks.R
import com.example.obanks.domain.entities.Bank
import com.example.obanks.presentation.components.FavoriteIconButton
import com.example.obanks.presentation.screens.ErrorScreen
import com.example.obanks.presentation.screens.LoadingScreen
import com.example.obanks.ui.theme.OBanksTheme

@Composable
fun DetailsScreen(
    bankId: Int,
    modifier: Modifier = Modifier,
) {

    val viewModel: DetailsViewModel = hiltViewModel()
    val screenState = viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBankById(bankId)
    }


    when(screenState.value) {
        is DetailsUiState.Error -> {
            ErrorScreen(
                message = (screenState.value as DetailsUiState.Error).message,
                onTryAgain = { /*TODO*/ }
            )
        }
        DetailsUiState.Loading -> {
            LoadingScreen(Modifier.fillMaxSize())
        }
        is DetailsUiState.Success -> {

            val bank = (screenState.value as DetailsUiState.Success).bank

            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_info_content_padding)),
                modifier = modifier.padding(dimensionResource(R.dimen.card_info_content_padding))
            ) {
                Box {
                    AsyncImage(
                        model = bank.photoUrl,
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_bank),
                        modifier = Modifier
                            .height(240.dp)
                            .clip(RoundedCornerShape(20))
                            .fillMaxWidth()
                    )
                    FavoriteIconButton(
                        bank = bank,
                        onFavoriteToggle = { viewModel.toggleFavoriteBank(bank) },
                        modifier = Modifier
                            .scale(1.5F)
                            .align(alignment = Alignment.BottomEnd)
                    )
                }
                InfoField(
                    label = stringResource(R.string.name),
                    value = bank.name,
                )
                InfoField(
                    label = stringResource(R.string.description),
                    value = bank.description,
                )
                InfoField(
                    label = "Age",
                    value = bank.age.toString(),
                )

            }
        }
    }



}

@Composable
fun InfoField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    OBanksTheme {
        DetailsScreen(
            bankId = 0,
            modifier = Modifier.fillMaxSize()
        )
    }
}