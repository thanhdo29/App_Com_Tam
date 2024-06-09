package com.example.app_com_tam.bottonNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app_com_tam.R
import com.example.app_com_tam.screens.Login
import com.example.app_com_tam.screens.Signup
import com.example.app_com_tam.screens.Welcom
import com.example.app_com_tam.ui.theme.Black_Medium
import com.example.app_com_tam.ui.theme.Dark_Charcoa
import com.example.app_com_tam.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

enum class ROUTE_NAME{
    login,welcom,app,signup
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navHostController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Dark_Charcoa
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false // Thay đổi thành true nếu bạn muốn biểu tượng tối trên thanh trạng thái
        )
    }
    val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route

    Surface {
        Scaffold(
            topBar = {
                if (currentRoute!= ROUTE_NAME.welcom.name && currentRoute!= ROUTE_NAME.login.name){
                    TopAppBar(title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painter = painterResource(id = R.drawable.logo), contentDescription ="", modifier = Modifier.size(57 .dp, 48.dp) )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Cum tứm đim", style = TextStyle(fontWeight = FontWeight(700), fontSize = 17 .sp, color = White))
                        }
                    },
                        navigationIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowLeft,
                                    contentDescription = "",
                                    tint = White,

                                    )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Dark_Charcoa))
                }

            }
        ) {
            paddingValues ->
            NavigationGraph(navHostController = navHostController, paddingValues = paddingValues)
        }
    }

}

@Composable
fun NavigationGraph(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navHostController, startDestination = ROUTE_NAME.welcom.name ){
        composable(ROUTE_NAME.welcom.name){
            Welcom(navHostController)
        }
        composable(ROUTE_NAME.login.name){
            Login(navHostController)
        }
        composable(ROUTE_NAME.app.name){
            ScreenBottonNavigation()
        }
        composable(ROUTE_NAME.signup.name){
            Signup()
        }
    }
}