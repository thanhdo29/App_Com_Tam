package com.example.app_com_tam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Database
import androidx.room.Room
import com.example.app_com_tam.bottonNavigation.AppNavHost
import com.example.app_com_tam.database.DBApp
//import com.example.app_com_tam.database.DBApp
import com.example.app_com_tam.ui.theme.App_Com_TamTheme

lateinit var DATABASE_INSTANCE : DBApp
class MainActivity : ComponentActivity() {

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            DBApp::class.java,"comtam.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DATABASE_INSTANCE=database
        setContent {
            App_Com_TamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppCom()
                }
            }
        }
    }
}

@Composable
fun AppCom() {
    val navController= rememberNavController()
    AppNavHost(navHostController = navController)
}