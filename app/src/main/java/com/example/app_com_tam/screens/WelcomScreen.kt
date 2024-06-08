package com.example.app_com_tam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.app_com_tam.R
import com.example.app_com_tam.bottonNavigation.ROUTE_NAME
import kotlinx.coroutines.delay

@Composable
fun Welcom(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010))
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = null,
            modifier = Modifier
                .size(414.dp, 380.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )
    }
    LaunchedEffect(Unit) {
        delay(3000L) // Delay for 3 seconds
        navHostController.navigate((ROUTE_NAME.login.name))
    }
}