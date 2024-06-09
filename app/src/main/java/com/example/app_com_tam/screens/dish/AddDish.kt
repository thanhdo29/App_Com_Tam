package com.example.app_com_tam.screens.dish

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.ui.theme.App_Com_TamTheme
import com.example.app_com_tam.viewModel.DishViewModel
import com.example.app_com_tam.viewModel.TypeDishViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDish(navController: NavController, typeDishViewModel: TypeDishViewModel, dishViewModel: DishViewModel) {
    var selectedDishType by remember { mutableStateOf<TypeDish?>(null) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var foodName by remember { mutableStateOf(TextFieldValue("")) }
    val scrollState = rememberScrollState()

    val listCate by typeDishViewModel.typeDishs.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .verticalScroll(scrollState)
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SmallTopAppBar(
            title = { Text("Add", color = Color.White) },
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
                .size(150.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .clickable { /* Add image upload functionality */ },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Thêm hình ảnh", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenuBox(
            selectedOption = selectedDishType?.nameType ?: "Chọn loại món",
            options = listCate.map { it.nameType }
        ) { selectedName ->
            selectedDishType = listCate.find { it.nameType == selectedName }
        }


        Spacer(modifier = Modifier.height(8.dp))

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
            onClick = {
                selectedDishType?.let {
                    val dish = Dish(
                        nameDish = foodName.text,
                        priceDish = price.text.toDouble(),
                        idTypeDish = it.idTypeDish,
                        imgDish = "https://assets.tronhouse.vn/59185068-4c44-404a-a5b6-493d1d50d13d/derived/p_l/chup-anh-mon-an-4.jpeg",
                        desDish = "ok"
                    )
                    dishViewModel.addDish(dish)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
        ) {
            Text("Thêm", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Composable
fun DropdownMenuBox(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.Gray, shape = RoundedCornerShape(4.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = selectedOption, color = Color.White)
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(text = option) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddDishScreen() {
    App_Com_TamTheme {
    }
}
