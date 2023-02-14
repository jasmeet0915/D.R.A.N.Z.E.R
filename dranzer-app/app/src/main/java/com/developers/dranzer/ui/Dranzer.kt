package com.developers.dranzer.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developers.dranzer.ui.devices.Devices

@Composable
fun DranzerMainScreen() {
    val navController = rememberNavController()
    val colors = MaterialTheme.colors

    var statusBarColor by remember { mutableStateOf(colors.primaryVariant) }
    var navigationBarColor by remember { mutableStateOf(colors.primaryVariant) }

    val animatedStatusBarColor by animateColorAsState(
        targetValue = statusBarColor,
        animationSpec = tween()
    )
    val animatedNavigationBarColor by animateColorAsState(
        targetValue = navigationBarColor,
        animationSpec = tween()
    )
    
    NavHost(navController = navController, startDestination = NavScreen.Devices.route) {
        composable(NavScreen.Devices.route) {
            Devices(viewModel = hiltViewModel())

            LaunchedEffect(Unit) {
                statusBarColor = colors.primaryVariant
                navigationBarColor = colors.primaryVariant
            }
        }
    }
}

sealed class NavScreen(val route: String) {
    object Devices: NavScreen("Devices")

    object HealthStats: NavScreen(
        "HealthStats"
    )
}