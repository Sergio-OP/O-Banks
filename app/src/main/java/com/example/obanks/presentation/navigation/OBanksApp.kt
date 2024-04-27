package com.example.obanks.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.obanks.R
import com.example.obanks.presentation.components.AppBar
import com.example.obanks.presentation.screens.main.MainScreen
import com.example.obanks.presentation.screens.details.DetailsScreen


@Composable
fun OBanksApp(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.app_name),
                canNavigateBack = navController.previousBackStackEntry != null,
                onNavigateBack = { navController.popBackStack() }
            ) },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.MAIN.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.MAIN.name) {
                MainScreen(
                    onMoreInfoClicked = { navController.navigate("bank/${it}") },
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
            }
            composable(
                route = "bank/{bankId}",
                arguments = listOf(navArgument("bankId") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailsScreen(
                    bankId = backStackEntry.arguments?.getInt("bankId") ?: 0,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
            }

        }
    }
}