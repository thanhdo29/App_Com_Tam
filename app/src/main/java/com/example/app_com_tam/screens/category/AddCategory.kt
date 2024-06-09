package com.example.app_com_tam.screens.category

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.*
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.viewModel.TypeDishViewModel
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch


@Composable
fun AddCategory (
    navController: NavController,
    typeDishViewModel: TypeDishViewModel
){
    var nameCate by remember { mutableStateOf("") }
    var context = LocalContext.current
    val cates=typeDishViewModel.typeDishs.collectAsState(initial = emptyList())


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        shape = RoundedCornerShape(20.dp)
    ){
        Column(
            modifier = Modifier
                .padding(top=90.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Thêm loại món ăn",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.SemiBold
            )
            OutlinedTextField(value =nameCate , onValueChange = {nameCate= it})
            Spacer(modifier = Modifier.height(20.dp))

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    val cateNew=TypeDish(nameType = nameCate)
                    typeDishViewModel.addTypeDish(cateNew)
                          Log.e("DATA", cates.toString())

                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFB703),
                    contentColor = Color.White
                )
            ) {
                Text("THÊM")
            }
        }
    }

}