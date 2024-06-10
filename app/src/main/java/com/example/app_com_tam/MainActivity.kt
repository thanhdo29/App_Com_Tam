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
import com.example.app_com_tam.repository.Repository
import com.example.app_com_tam.room.DAO.CardDAO
import com.example.app_com_tam.screens.TestScreen
//import com.example.app_com_tam.database.DBApp
import com.example.app_com_tam.ui.theme.App_Com_TamTheme
import com.example.app_com_tam.viewModel.CartViewModel
import com.example.app_com_tam.viewModel.DishViewModel
import com.example.app_com_tam.viewModel.OrderViewModel
import com.example.app_com_tam.viewModel.TypeDishViewModel

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
        val repo=Repository(database)
        val typeDishViewModel=TypeDishViewModel(repo)
        val dishViewModel=DishViewModel(repo)
        val orderViewModel=OrderViewModel(repo)
        val cartViewModel=CartViewModel(repo)
        setContent {
            App_Com_TamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppCom(database)
//                    TestScreen(
//                        typeDishViewModel = typeDishViewModel,
//                        dishViewModel = dishViewModel,
//                        orderViewModel = orderViewModel,
//                        cartViewModel =cartViewModel
//                    )
                }
            }
        }
    }
}

@Composable
fun AppCom(dbApp: DBApp) {
    val navController= rememberNavController()
    val repository:Repository= Repository(dbApp =dbApp )
    AppNavHost(navHostController = navController,repository)
}