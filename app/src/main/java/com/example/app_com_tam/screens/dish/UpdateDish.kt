package com.example.app_com_tam.screens.dish

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.app_com_tam.R

data class MenuItem(val id: Int, val name: String, val price: String, val image: Int)

val items = listOf(
    MenuItem(1, "Sườn bì", "28K", R.drawable.comtam),
    MenuItem(2, "Bì chả", "25K", R.drawable.comtam),
    MenuItem(3, "Trứng chả", "25K", R.drawable.comtam),
    MenuItem(4, "Sườn chả", "28K", R.drawable.comtam),
    MenuItem(5, "Sườn bì chả", "35K", R.drawable.comtam),
    MenuItem(6, "Sườn cay", "35K", R.drawable.comtam),
    MenuItem(7, "Sườn trứng", "30K", R.drawable.comtam),
    MenuItem(8, "Sườn trứng", "30K", R.drawable.comtam),
    MenuItem(9, "Sườn trứng", "30K", R.drawable.comtam),
    MenuItem(10, "Sườn trứng", "30K", R.drawable.comtam),
)

//hàm quản lí dish
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerDish(navController: NavController){
    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        Row (modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically) {

                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription ="",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxWidth(0.12f)
                            )
                            Text(text = "Cum tứm đim")

                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xff252121),
                        titleContentColor = Color.White,
                    ),

                    )
                Divider(thickness = 2.dp, color = Color.Black)
            }

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("AddDish")
            }, contentColor = Color.White, containerColor = Color(0xFF2F2D2D)) {
                Icon(imageVector = Icons.Rounded.Add,
                    contentDescription = "Add new category")

            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252121))){
        ManegerDish(items, navigationController = navController)
    }
}
@Composable
fun ManegerDish(items: List<MenuItem>, navigationController: NavController) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            MenuItemCard(index + 1, item, navController = navigationController)
        }
    }



}
@Composable
fun MenuItemCard(order: Int, item: MenuItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2F2D2D))
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "$order",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = item.price, fontSize = 16.sp, color = Color(0xFFFE724C))
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { navController.navigate("UpdateDish") },
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_create_24),
                    contentDescription = "Edit",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { /* Handle delete action */ },
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "Delete",
                    tint = Color.White
                )
            }
        }
    }
}

//Hàm updateDish
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDish(navController: NavController){
    var price by remember { mutableStateOf(TextFieldValue("100k")) }
    var foodName by remember { mutableStateOf(TextFieldValue("Sườn")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SmallTopAppBar(
            title = { Text("Update", color = Color.White) },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF252121)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { /* Add image upload functionality */ },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.comtam),
                contentDescription = "Sample Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(150.dp))

        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Giá") },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("Tên món ăn") },
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle save action */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
        ) {
            Text("Sửa", color = Color.White, fontSize = 18.sp)
        }
    }

}