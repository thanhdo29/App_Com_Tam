package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Order")
data class Order(
    @PrimaryKey(autoGenerate = true) var idOrder:Int=0,
    @ColumnInfo(name = "idCustomer") var idCustomer:Int,
    @ColumnInfo(name = "idCart") var idCart:Int,
    @ColumnInfo(name = "methodPay") var methodPay:Int,
    @ColumnInfo(name = "dateBook") var dateBook:Date,
    @ColumnInfo(name = "totalAmount") var totalAmount:Double,
)