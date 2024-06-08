package com.example.app_com_tam.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_com_tam.R
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.ui.theme.Amber
import com.example.app_com_tam.ui.theme.Dark_Charcoa
import java.util.Date

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Dark_Charcoa)

    ) {
        Column(
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today: 19-05-2023",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp)) // Adding space between texts
            Text(
                text = "Số lượng đơn: 2",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp)) // Adding space between texts
            Row {
                Text(
                    text = "Doanh thu :",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(4.dp)) // Adding space between texts
                Text(
                    text = "320.000 đ",
                    color = Amber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

    }
}


@Composable
fun OrderList(orders: List<Order>) {
    LazyColumn {
        items(orders) { order ->
            OrderItem(order = order)
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .padding(8.dp)
            .height(100.dp)
            .background(Color(0xFF363131))
            .clickable {
            },
        shape = RoundedCornerShape(16.dp), // Thiết lập góc bo tròn
        border = BorderStroke(2.dp, Color.Black) // Thiết lập đường viền
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), // Để Box chiếm toàn bộ kích thước của Card
            contentAlignment = Alignment.Center // Căn giữa nội dung bên trong Box theo cả hai chiều
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), // Để Row chiếm toàn bộ chiều ngang của Box
                verticalAlignment = Alignment.CenterVertically, // Căn giữa các phần tử theo chiều dọc
            ) {
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = "Đơn hàng: ${order.idOrder}",
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "Trạng thái",
                        color = Color.White,
                        fontSize = 15.sp,
                    )
                }

                Text(
                    text = "//",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(0.5f)
                )

                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        color = Color.White,
                        fontSize = 18.sp,
                        text = "${order.totalAmount}",
                    )
                    Text(
                        color = Color.Red,
                        fontSize = 18.sp,
                        text = if (order.statusOrder) "Chấp nhận" else "Từ chối",
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    Home()
//}
//
//
//val category1 = Category(
//    id = 1,
//    tenLoai = "Đồ điện tử",
//    anhLoai = R.drawable.img
//)
//
//val category2 = Category(
//    id = 2,
//    tenLoai = "Thời trang",
//    anhLoai = R.drawable.img
//)
//
//val category3 = Category(
//    id = 3,
//    tenLoai = "Đồ gia dụng",
//    anhLoai = R.drawable.img
//)
//
//
//val product1 = Product(
//    id = 4,
//    ten = "Điện thoại thông minh",
//    danhSachAnhMon = listOf(
//        R.drawable.img,
//
//        ),
//    gia = 12000000.0,
//    luotBan = 150,
//    idCategory = 1
//)
//
//val product2 = Product(
//    id = 5,
//    ten = "Laptop",
//    danhSachAnhMon = listOf(
//        R.drawable.img,
//    ),
//    gia = 20000000.0,
//    luotBan = 75,
//    idCategory = 1
//)
//
//val product3 = Product(
//    id = 6,
//    ten = "Máy giặt",
//    danhSachAnhMon = listOf(
//        R.drawable.img,
//    ),
//    gia = 7000000.0,
//    luotBan = 50,
//    idCategory = 3 // Giả sử 3 là id của category "Đồ gia dụng"
//)
//
//val product4 = Product(
//    id = 610,
//    ten = "Máy giặt",
//    danhSachAnhMon = listOf(
//        R.drawable.img,
//    ),
//    gia = 7000000.0,
//    luotBan = 50,
//    idCategory = 3 // Giả sử 3 là id của category "Đồ gia dụng"
//)
//
//
//val productWantToBuy1 = ProductWantToBuy(
//    id = 7,
//    idSanPham = 1,
//    gia = 12000000.0,
//    soLuong = 2
//)
//
//val productWantToBuy2 = ProductWantToBuy(
//    id = 8,
//    idSanPham = 2,
//    gia = 20000000.0,
//    soLuong = 1
//)
//
//val productWantToBuy3 = ProductWantToBuy(
//    id = 9,
//    idSanPham = 3,
//    gia = 7000000.0,
//    soLuong = 3
//)
//
//val productWantToBuy4 = ProductWantToBuy(
//    id = 10,
//    idSanPham = 610,
//    gia = 7000000.0,
//    soLuong = 3
//)
//
//
//val order1 = Order(
//    id = 1,
//    ngayGioPhut = Date(),
//    idKhachHang = 1001,
//    danhSachProductWantToBuy = listOf(productWantToBuy1, productWantToBuy2),
//    trangThai = true,
//    tongTien = productWantToBuy1.gia * productWantToBuy1.soLuong + productWantToBuy2.gia * productWantToBuy2.soLuong
//)
//
//
//val order2 = Order(
//    id = 2,
//    ngayGioPhut = Date(),
//    idKhachHang = 1002,
//    danhSachProductWantToBuy = listOf(productWantToBuy3, productWantToBuy4),
//    trangThai = false,
//    tongTien = productWantToBuy3.gia * productWantToBuy3.soLuong + productWantToBuy4.gia * productWantToBuy4.soLuong
//)
