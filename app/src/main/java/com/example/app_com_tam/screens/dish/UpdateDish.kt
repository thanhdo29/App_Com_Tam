package com.example.app_com_tam.screens.dish

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.app_com_tam.R
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.repository.Repository
import com.example.app_com_tam.screens.category.DeleteDish
import com.example.app_com_tam.ui.theme.Black_Medium
import com.example.app_com_tam.ui.theme.Dark_Charcoa
import com.example.app_com_tam.viewModel.DishViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerDish(navController: NavController, dishViewModel: DishViewModel) {
    val dishs by dishViewModel.dishes.collectAsState(initial = emptyList())
    var showDialogDelete by remember { mutableStateOf(false) }
    var selectedDishDelete by remember { mutableStateOf<Dish?>(null) }
    var selectedDishUpdate by remember { mutableStateOf<Dish?>(null) }

    ManegerDish(dishs, navController, dishViewModel, showDialogDelete, selectedDishDelete,selectedDishUpdate)
    if (showDialogDelete && selectedDishDelete != null) {
        DeleteDish(
            onConfirmation = {
                dishViewModel.deleteDish(selectedDishDelete!!)
                showDialogDelete = false
                selectedDishDelete = null
            },
            onDismiss = {
                showDialogDelete = false
                selectedDishDelete = null
            }
        )
    }
}

@Composable
fun ManegerDish(
    items: List<Dish>,
    navController: NavController,
    dishViewModel: DishViewModel,
    showDialogDelete: Boolean,
    selectedDishDelete: Dish?,
    selectedDishUpdate:Dish?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Dark_Charcoa)
            .padding(top = 80.dp)
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(items) { index, item ->
            MenuItemCard(index + 1, item, navController, dishViewModel, showDialogDelete, selectedDishDelete,selectedDishUpdate)
        }
    }
}

@Composable
fun MenuItemCard(
    order: Int,
    item: Dish,
    navController: NavController,
    dishViewModel: DishViewModel,
    showDialogDelete: Boolean,
    selectedDishDelete: Dish?,
    selectedDishUpdate: Dish?
) {
    var showDialogDeleteState by remember { mutableStateOf(showDialogDelete) }
    var selectedDishDeleteState by remember { mutableStateOf(selectedDishDelete) }
    var selectedDishUpdateState by remember { mutableStateOf(selectedDishUpdate) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Black_Medium)
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
            AsyncImage(
                model = item.imgDish,
                contentDescription = "",
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
                Text(
                    text = item.nameDish,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = item.priceDish.toString(),
                    fontSize = 16.sp,
                    color = Color(0xFFFE724C)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    navController.navigate("UpdateDish}"){

                    }
                          },
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
                onClick = {
                    showDialogDeleteState = true
                    selectedDishDeleteState = item
                },
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

    if (showDialogDeleteState && selectedDishDeleteState != null) {
        DeleteDish(
            onConfirmation = {
                dishViewModel.deleteDish(selectedDishDeleteState!!)
                showDialogDeleteState = false
                selectedDishDeleteState = null
            },
            onDismiss = {
                showDialogDeleteState = false
                selectedDishDeleteState = null
            }
        )
    }
}

// Hàm updateDish
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDish(navController: NavController, dish: Dish, dishViewModel: DishViewModel) {
    val selectDish by dishViewModel.selectedDish.observeAsState()

    var imgDishUpdate by remember { mutableStateOf<Uri?>(null) }
    var price by remember { mutableStateOf("") }
    var foodName by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imgDishUpdate = uri
    }






    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Dark_Charcoa),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable {
                    launcher.launch("image/*")
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberImagePainter(data = ""),
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
            onClick = {Log.e("TeSt", dish.toString()) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
        ) {
            Text("Sửa", color = Color.White, fontSize = 18.sp)
        }
    }
}
