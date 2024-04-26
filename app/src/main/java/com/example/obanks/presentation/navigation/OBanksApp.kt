package com.example.obanks.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.obanks.R
import com.example.obanks.presentation.components.AppBar
import com.example.obanks.presentation.main.MainScreen

@Composable
fun OBanksApp(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { AppBar(title = stringResource(R.string.app_name)) },
        modifier = modifier
    ) { paddingValues ->
        MainScreen(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}