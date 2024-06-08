package com.example.app_com_tam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.app_com_tam.R
import com.example.app_com_tam.bottonNavigation.ROUTE_NAME

@Composable
fun Login(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            // Add content for the first box here
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Text(
                    text = "Đăng nhập",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )

                Box(
                    modifier = Modifier
                        .padding(top = 70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(305.dp, 294.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }

        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFF373232)),
            contentAlignment = Alignment.Center
        ) {
            // Add content for the second box here
            ImageBackgroundButton(width = 300.dp,
                height = 80.dp, onClick = {navHostController.navigate(ROUTE_NAME.app.name)})
        }
    }

}

    @Composable
    fun ImageBackgroundButton(
        width: Dp,
        height: Dp,
        onClick: () -> Unit
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .size(width, height)
                .clickable(onClick = onClick)
                .background(
                    color = Color(0xFF363131),
                    shape = RoundedCornerShape(25.dp)
                )

        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(45.dp)), // Thêm góc bo tròn 8dp
                contentScale = ContentScale.Crop
            )
        }
    }