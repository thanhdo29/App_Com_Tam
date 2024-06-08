package com.example.app_com_tam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_com_tam.R
import com.example.app_com_tam.ui.theme.Black_Medium
import com.example.app_com_tam.ui.theme.Dark_Charcoa


data class ClassHelp(
    val img:Int,
    val title:String
)
val listHelp= listOf(
    ClassHelp(R.drawable.icon_zalo, "0879175310"),
    ClassHelp(R.drawable.icon_gmail, "vuthanhnam2102@gmail.com"),
    ClassHelp(R.drawable.icon_phone, "0879175310")
)

@Composable
fun Help() {
    Column(modifier = Modifier.fillMaxSize().background(color = Dark_Charcoa), verticalArrangement = Arrangement.Center){
        LazyColumn(modifier = Modifier.padding(horizontal = 25.dp)) {
            items(listHelp){
                    item ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 52.dp)) {
                    Image(painter = painterResource(id =item.img), contentDescription = "", modifier = Modifier.size(58.dp))
                    Spacer(modifier = Modifier.width(35.dp))
                    Text(text = item.title, style = TextStyle(fontSize = 20 .sp, fontWeight = FontWeight(400), color = Color.White))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelpPreview() {
    Help()
}