package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer(
    @PrimaryKey(autoGenerate = true) var idCustomer:Int=0,
    @ColumnInfo(name = "nameCustomer") var nameCustomer:String,
    @ColumnInfo(name="phoneCustomer") var phoneCustomer:String,
    @ColumnInfo(name="username") var username:String,
    @ColumnInfo(name="password") var password:String,
)