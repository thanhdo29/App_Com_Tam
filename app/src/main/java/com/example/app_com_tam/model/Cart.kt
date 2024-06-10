package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName = "Cart")
    data class Cart(
        @PrimaryKey(autoGenerate = true) var idCart:Int=0,
        @ColumnInfo(name = "idCustomer") var idCustomer:Int,
        @ColumnInfo(name = "idDish") var idDish:Int,
        @ColumnInfo(name = "quantity") var quantity:Int
    )