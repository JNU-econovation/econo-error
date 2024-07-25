package com.example.project.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project.ui.home.HomeScreen
import com.example.project.ui.login.LoginScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(context: Context, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavItem.Home.screenRoute) {
        composable(NavItem.Home.screenRoute) {
            HomeScreen(navController = navController)
        }
        composable(NavItem.Login.screenRoute) {
            LoginScreen(navController = navController)
        }
    }
}
