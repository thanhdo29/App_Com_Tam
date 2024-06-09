package com.example.app_com_tam.bottonNavigation

import com.example.app_com_tam.ui.theme.Amber

import CategoryScreen2
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_com_tam.R
import com.example.app_com_tam.repository.Repository
import com.example.app_com_tam.screens.category.AddCategory
import com.example.app_com_tam.screens.category.CategoryScreen
import com.example.app_com_tam.screens.dish.AddDish
import com.example.app_com_tam.screens.dish.DishScreen
import com.example.app_com_tam.screens.dish.ManagerDish
import com.example.app_com_tam.screens.dish.UpdateDish
import com.example.app_com_tam.viewModel.DishViewModel
import com.example.app_com_tam.viewModel.TypeDishViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBottonNavigation(repository: Repository) {

    val navController= rememberNavController()
    var selectItem by remember {
        mutableStateOf(0)
    }


    val listItem = listOf(
        BottonNavigationItem(TitleBottonNavigation.Home.name, R.drawable.icon_home1_selected, R.drawable.icon_home_selected),
        BottonNavigationItem(TitleBottonNavigation.Statistical.name, R.drawable.icon_statistial,R.drawable.icon_tk_selecte ),
        BottonNavigationItem(TitleBottonNavigation.Manage.name, R.drawable.icon_manage,R.drawable.icon_manage_selected ),
        BottonNavigationItem(TitleBottonNavigation.Help.name, R.drawable.icon_help,R.drawable.icon_help_selecte )
    )

    Surface {
        Scaffold(
            bottomBar = {
                BottonNavigationBar(items = listItem, selectedItemIndex = selectItem, onItemSelected = {
                    index->
                    selectItem=index
                    navController.navigate(listItem[index].title)
                })
            },
            topBar = {

            }
        ) {
            paddingValue->
            BottonNavigationGraph(navHostController = navController, paddingValues = paddingValue,repository)
        }
    }
}

@Composable
fun BottonNavigationBar(items:List<BottonNavigationItem>,
                        selectedItemIndex:Int,
                        onItemSelected:(Int)->Unit){
    val interactionSource = remember { MutableInteractionSource() }
    NavigationBar(containerColor = Black_Medium) {
        items.forEachIndexed { index, bottonNavigationItem ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {onItemSelected(index) },
                modifier = Modifier,
                icon = {
                    val painter=if(selectedItemIndex== index) {
                        painterResource(id = bottonNavigationItem.unSnSelectItem)
                    }else{
                        painterResource(id = bottonNavigationItem.selectItem)
                    }
                    Image(painter =  painter,
                        contentDescription ="",
                        modifier = Modifier
                            .size(23.dp))
                },
                label = {
                    Text(
                        text = bottonNavigationItem.title,
                        color = if (selectedItemIndex == index) Amber else White
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = Color.DarkGray
                ))

        }
    }
}



@Composable
fun BottonNavigationGraph(navHostController: NavHostController, paddingValues: PaddingValues, repository: Repository) {


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
            AddCategory(navHostController, typeDishViewModel = TypeDishViewModel(repository))
        }
        composable("UpdateCategory"){
            CategoryScreen2(navHostController,typeDishViewModel = TypeDishViewModel(repository))
        }

        composable("ManageDish"){
            DishScreen(navHostController)
        }

        composable("ManagerDish"){
            ManagerDish(navHostController, dishViewModel = DishViewModel(repository))
        }
        composable("UpdateDish"){
            UpdateDish(navHostController)
        }
        composable(ROUTE_NAME.AddDish.name){
            AddDish(navHostController, typeDishViewModel = TypeDishViewModel(repository), dishViewModel = DishViewModel(repository))
        }
    }
}