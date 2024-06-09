package com.example.app_com_tam.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_com_tam.R

@Composable
fun CategoryScreen(navController: NavController
                   ){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff252121))
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ){
        Row (
            modifier = Modifier.padding(top = 40.dp).clickable { navController.navigate("AddCategory") },
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(0.15f)
            )
            Text(
                text = "Thêm loại món ăn",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Row (
            modifier = Modifier.clickable { navController.navigate("UpdateCategory") },
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(0.15f)
            )
            Text(
                text = "Sửa/Xóa loại món ăn",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
//        Row (
//
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "",
//                modifier = Modifier.fillMaxWidth(0.15f)
//            )
//            Text(
//                text = "Xóa loại món ăn",
//                color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                modifier = Modifier.padding(start = 10.dp)
//            )
//        }
    }
}

