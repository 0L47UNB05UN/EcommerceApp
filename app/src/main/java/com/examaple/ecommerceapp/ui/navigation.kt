package com.examaple.ecommerceapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.examaple.ecommerceapp.ui.screen.CartScreen
import com.examaple.ecommerceapp.ui.screen.HomeScreen
import com.examaple.ecommerceapp.ui.screen.LoginScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Cart : Screen("cart")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val appViewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onAddToCart = { navController.navigate(Screen.Login.route) },
                appViewModel = appViewModel,
                appUiState = appViewModel.uiState,
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.popBackStack() // return to Home after login
            })
        }
        composable(Screen.Cart.route) {
            CartScreen()
        }
    }
}
