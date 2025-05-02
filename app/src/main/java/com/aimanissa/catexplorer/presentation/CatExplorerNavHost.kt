package com.aimanissa.catexplorer.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aimanissa.catexplorer.presentation.ui.random.RandomImageViewModel
import com.aimanissa.catexplorer.presentation.ui.random.screen.RandomImageScreen

sealed class NavigationDestination(val destination: String) {
    data object RandomImage : NavigationDestination("RandomImage")
}

@Composable
fun CatExplorerNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.RandomImage.destination,
        modifier = modifier
    ) {
        composable(route = NavigationDestination.RandomImage.destination) {
            val randomImageViewModel = hiltViewModel<RandomImageViewModel>()
            RandomImageScreen(modifier = modifier, viewModel = randomImageViewModel)
        }
    }
}