package com.example.app_com_tam.screens

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.app_com_tam.R
import com.example.app_com_tam.model.CartDishCrossRef
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.viewModel.DishViewModel
import com.example.app_com_tam.viewModel.OrderViewModel
import java.time.format.TextStyle
import coil.compose.rememberImagePainter
@Composable
fun OrderDetails(navHostController: NavHostController, OrderId: Int,viewModel: OrderViewModel,dishViewModel: DishViewModel) {
    var order by remember { mutableStateOf<Order?>(null) }
    var cartDishCrossRefs by remember { mutableStateOf<List<CartDishCrossRef>>(emptyList()) }
    var dishes by remember { mutableStateOf<List<Dish>>(emptyList()) }
      val context = LocalContext.current

    // Trong hàm OrderDetails
    LaunchedEffect(OrderId) {
        viewModel.getOrderById(OrderId) { result ->
            order = result
            result?.let {
                viewModel.getCartDishCrossRefsByCartId(it.idCart) { crossRefs ->
                    cartDishCrossRefs = crossRefs
                    val dishIds = crossRefs.map { it.dishId }
                    dishIds.forEach { dishId ->
                        dishViewModel.getDishById(dishId) { dish ->
                            dish?.let {
                                dishes += dish
//                                Log.d("DISH_LOG", "Dish: $dish")
                            }
                        }
                    }
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF231F1F))
            .padding(top = 50.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp)
                .background(Color.Black)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF231F1F)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(text = "Xác nhận", onClick = {
                viewModel.OrderUpdateStatur(OrderId, true)
                Toast.makeText(context, "Đã xác nhận", Toast.LENGTH_SHORT).show()            })
            CustomButton(text = "Hủy", onClick = {
                viewModel.OrderUpdateStatur(OrderId, false)
                Toast.makeText(context, "Đã hủy", Toast.LENGTH_SHORT).show()
            })

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val groupedProducts: Map<Int, List<Dish>> = dishes.groupBy { it.idTypeDish }

            OrderDetailsList(groupedProducts)
        }

    }



}

@Composable
fun OrderDetailsList(groupedProducts: Map<Int, List<Dish>>) {
    LazyColumn {
        items(groupedProducts.size) { index ->
            val category = groupedProducts.keys.elementAt(index)
            val products = groupedProducts.getValue(category)
            Log.d("ORDER_DETAILS", "Category: $category, Products: $products") // Log giá trị của category và products
            CategoryItem(category, products)
        }
    }
}


@Composable
fun CategoryItem(category: Int, products: List<Dish>) {
    val productCounts = remember { mutableMapOf<Int, Int>() } // Map để lưu trữ số lượng sản phẩm theo ID của chúng
    val processedProducts = remember { mutableSetOf<Int>() } // Set để lưu trữ các sản phẩm đã được xử lý
    var productIndex = 0

    Column {
        // Hiển thị tên loại
        Text(
            text = "Category ID: $category",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        // Hiển thị danh sách sản phẩm
        products.forEach { product ->
            // Tăng chỉ số sản phẩm
            productIndex++

            // Kiểm tra xem sản phẩm đã được hiển thị trước đó hay chưa
            if (productCounts.containsKey(product.idDish)) {
                // Nếu đã được hiển thị, chỉ cập nhật số lượng sản phẩm
                productCounts[product.idDish] = (productCounts[product.idDish] ?: 0) + 1
            } else {
//                 Nếu chưa được hiển thị, hiển thị sản phẩm và đếm số lượng
                ProductItem(product, productCounts, productIndex)

            }
        }
    }
}


@Composable
fun ProductItem(product: Dish, productCounts: Map<Int, Int>, productIndex: Int) {

    val productCount = productCounts[product.idDish] ?: 1

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp) // Khoảng cách giữa các ProductItem
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.Gray)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = productIndex.toString(), // Hiển thị số thứ tự
                color = Color.White,
                modifier = Modifier.weight(0.1f)
            )
            // Sử dụng ảnh của sản phẩm hoặc ảnh mặc định nếu không có ảnh


            Image(
                painter = painterResource( R.drawable.logo_black),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize() // Lấp đầy toàn bộ không gian của weight
                    .clip(RoundedCornerShape(10.dp)) // Chỉ bo tròn các góc của ảnh
                    .weight(0.4f)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(0.5f)// Khoảng cách giữa các phần tử bên trong mỗi ProductItem
            ) {
                Text(
                    text = product.nameDish,
                    color = Color.White,
                )
                Text(
                    text = formatPrice(product.priceDish),
                    color = Color.White,
                )
            }
            Text(
                text = "$productCount",
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp), // Cách phải 20.dp
                textAlign = TextAlign.End // Căn chỉnh văn bản sang bên phải
            )


        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


private fun formatPrice(price: Double): String {
    return when {
        price >= 1_000_000_000 -> "${(price / 1_000_000_000).toInt()}b"
        price >= 1_000_000 -> "${(price / 1_000_000).toInt()}m"
        price >= 1_000 -> "${(price / 1_000).toInt()}k"
        else -> price.toInt().toString()
    }
}


@Composable
fun RemoteImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    imageSize: IntSize? = null, // Kích thước của ảnh
    contentScale: ContentScale = ContentScale.Crop // Tùy chỉnh tỷ lệ nội dung
) {
    val painter: Painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            // Cấu hình tùy chọn tải ảnh (nếu cần)
        }
    )

    // Áp dụng kích thước nếu được chỉ định, nếu không, sử dụng Modifier từ argument
    Box(
        modifier = if (imageSize != null) {
            modifier
                .size(imageSize.width.dp, imageSize.height.dp)
        } else {
            modifier
        }
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(), // Lấp đầy toàn bộ không gian của Box
            contentScale = contentScale
        )
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
            .width(100.dp)
            .height(45.dp)
            .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = text,
            color = Color.White,

            )
    }
}

