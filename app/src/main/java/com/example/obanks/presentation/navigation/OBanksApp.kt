package com.example.obanks.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.obanks.R
import com.example.obanks.presentation.components.AppBar
import com.example.obanks.presentation.main.MainScreen

@Composable
fun OBanksApp(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { AppBar(title = stringResource(R.string.app_name)) },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.MAIN.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.MAIN.name) {
                MainScreen(
                    onMoreInfoClicked = {  },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
            }

        }
    }
}