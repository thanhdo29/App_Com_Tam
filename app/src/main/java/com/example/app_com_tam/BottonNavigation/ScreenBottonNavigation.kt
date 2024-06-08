package com.example.app_com_tam.BottonNavigation

import CategoryScreen2
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_com_tam.screens.Help
import com.example.app_com_tam.screens.Home
import com.example.app_com_tam.screens.manager.Manage
import com.example.app_com_tam.screens.Statistical
import com.example.app_com_tam.ui.theme.Black_Medium
import com.example.app_com_tam.ui.theme.White
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.app_com_tam.R
import com.example.app_com_tam.screens.category.AddCategory
import com.example.app_com_tam.screens.category.CategoryScreen
import com.example.app_com_tam.screens.dish.AddDish
import com.example.app_com_tam.screens.dish.DeleteDish
import com.example.app_com_tam.screens.dish.DishScreen
import com.example.app_com_tam.screens.dish.ManagerDish
import com.example.app_com_tam.screens.dish.UpdateDish

enum class TitleBottonNavigation{
    Home,
    Statistical,
    Manage,
    Help
}

data class BottonNavigationItem(
    val title:String,
    val selectItem:Int,
    val unSnSelectItem:Int
)

@Composable
fun ScreenBottonNavigation() {

    val navController= rememberNavController()
    var selectItem by remember {
        mutableStateOf(0)
    }
    val listItem = listOf(
        BottonNavigationItem(TitleBottonNavigation.Home.name, R.drawable.icon_home, R.drawable.icon_home),
        BottonNavigationItem(TitleBottonNavigation.Statistical.name, R.drawable.icon_statistial,R.drawable.icon_statistial ),
        BottonNavigationItem(TitleBottonNavigation.Manage.name, R.drawable.icon_manage,R.drawable.icon_manage ),
        BottonNavigationItem(TitleBottonNavigation.Help.name, R.drawable.icon_help,R.drawable.icon_help )
    )

    Surface {
        Scaffold(
            bottomBar = {
                BottonNavigationBar(items = listItem, selectedItemIndex = selectItem, onItemSelected = {
                    index->
                    selectItem=index
                    navController.navigate(listItem[index].title)
                })
            }
        ) {
            paddingValue->
            BottonNavigationGraph(navHostController = navController, paddingValues = paddingValue)
        }
    }
}

@Composable
fun BottonNavigationBar(items:List<BottonNavigationItem>,
                        selectedItemIndex:Int,
                        onItemSelected:(Int)->Unit){
    NavigationBar(containerColor = Black_Medium) {
        items.forEachIndexed { index, bottonNavigationItem ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {onItemSelected(index) },
                icon = {
                    Image(painter =  painterResource(id = bottonNavigationItem.selectItem), contentDescription ="", modifier = Modifier.size(23 .dp) )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = White
                ))

        }
    }
}



@Composable
fun BottonNavigationGraph(navHostController: NavHostController, paddingValues: PaddingValues) {


    NavHost(navController =navHostController , startDestination = TitleBottonNavigation.Home.name, modifier = Modifier.padding(paddingValues) ){
        composable(TitleBottonNavigation.Home.name){
            Home()
        }
        composable(TitleBottonNavigation.Statistical.name){
            Statistical()
        }
        composable(TitleBottonNavigation.Manage.name){
            Manage(navHostController)
        }
        composable(TitleBottonNavigation.Help.name){
            Help()
        }
        composable("ManageCategory"){
            CategoryScreen(navHostController)
        }
        composable("AddCategory"){
            AddCategory(navHostController)
        }
        composable("UpdateCategory"){
            CategoryScreen2(navHostController)
        }

        composable("ManageDish"){
            DishScreen(navHostController)
        }
        composable("AddDish"){
            AddDish(navHostController)
        }
        composable("ManagerDish"){
            ManagerDish(navHostController)
        }
        composable("UpdateDish"){
            UpdateDish(navHostController)
        }
    }
}