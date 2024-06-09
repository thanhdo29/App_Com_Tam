package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AddressCustomer")
data class AddressCustomer (
    @PrimaryKey(autoGenerate = true) var idAddressCustomer:Int=0,
    @ColumnInfo(name = "idCustomer") var idCustomer:Int,
    @ColumnInfo(name = "duong") var duong:String,
    @ColumnInfo(name = "phuong") var phuong:String,
    @ColumnInfo(name = "quan") var quan:String,
)