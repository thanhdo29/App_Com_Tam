package com.example.app_com_tam.BottonNavigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_com_tam.screens.Login
import com.example.app_com_tam.screens.Welcom

enum class ROUTE_NAME{
    login,welcom,app
}

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = ROUTE_NAME.app.name ){
        composable(ROUTE_NAME.welcom.name){
            Welcom()
        }
        composable(ROUTE_NAME.login.name){
            Login()
        }
        composable(ROUTE_NAME.app.name){
            ScreenBottonNavigation()
        }
    }
}