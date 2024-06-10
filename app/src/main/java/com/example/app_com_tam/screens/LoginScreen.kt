package com.example.app_com_tam.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.app_com_tam.R
import com.example.app_com_tam.bottonNavigation.ROUTE_NAME
import com.example.app_com_tam.viewModel.LoginViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_com_tam.repository.Repository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navHostController: NavHostController, repository: Repository) {

    val loginViewModel=LoginViewModel(repository.dbApp.userDao())
    loginViewModel.insertSampleAdminIfNeeded()

    val isAuthenticated by loginViewModel.isAuthenticated.observeAsState()

    val context= LocalContext.current
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

        val usernameState by loginViewModel.username.observeAsState("")
        val isShowPasswordState by loginViewModel.isShowPassword.observeAsState(false)

        var username by remember { mutableStateOf(usernameState) }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(isShowPasswordState) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Username",
                color = Color(0XFFFFFFFF),
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 12.dp, start = 10.dp),
                fontWeight = FontWeight.Bold,
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username =  it},
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0XFFD9D9D9),
                        RoundedCornerShape(6.dp)
                    ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Password",
                color = Color(0XFFFFFFFF),
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 12.dp, start = 10.dp),
                fontWeight = FontWeight.Bold,
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0XFFD9D9D9),
                        RoundedCornerShape(6.dp)
                    ),
            )

            Spacer(modifier = Modifier.height(26.dp))
            TextButton(
                onClick = {
                    if (username.length==0 || password.length==0){
                        Toast.makeText(context, "Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT ).show()
                        return@TextButton
                    }
                    loginViewModel.login(username, password)
                },
                modifier = Modifier.background(
                    color = Color(0XFFFE724C),
                    RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Đăng nhâp",
                    color = Color(0XFFFFFFFF),
                    fontSize = 20.sp,
                )
            }
            isAuthenticated?.let {it->
                LaunchedEffect(key1 = it) {
                    if (it){
                        Toast.makeText(context, "Đăng nhập thành công",Toast.LENGTH_SHORT ).show()
                        navHostController.navigate(ROUTE_NAME.app.name){

                        }
                    }else{
                        Toast.makeText(context, "Đăng nhập thất bại",Toast.LENGTH_SHORT ).show()
                    }
                }

            }

            Spacer(modifier = Modifier.height(56.dp))
            Row{
                Text(text = "Already have account?",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color(0xff808080)
                )
                TextButton(onClick = {
                    navHostController.navigate(ROUTE_NAME.signup.name)
                                     },
                ) {
                    Text(text = "Register", color = Color(0xff808080)
                        , fontWeight = FontWeight.Bold
                    )
                }
            }
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