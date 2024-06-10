package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) var idOrder:Int=0,
    @ColumnInfo(name = "idCustomer") var idCustomer:Int,
    @ColumnInfo(name = "idCart") var idCart:Int,
    @ColumnInfo(name = "methodPay") var methodPay:Int,
    @ColumnInfo(name = "dateBook") var dateBook:String,
    @ColumnInfo(name = "totalAmount") var totalAmount: Int,
    @ColumnInfo(name = "statusOrder") var statusOrder:Boolean
)

//val order1 = Order(
//    id = 1,
//    ngayGioPhut = Date(),
//    idKhachHang = 1001,
//    danhSachProductWantToBuy = listOf(productWantToBuy1, productWantToBuy2),
//    trangThai = true,
//    tongTien = productWantToBuy1.gia * productWantToBuy1.soLuong + productWantToBuy2.gia * productWantToBuy2.soLuong
//)