package com.example.app_com_tam.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class Cart(
    @PrimaryKey(autoGenerate = true) val idCart: Int = 0,
    @NonNull @ColumnInfo(name = "idDish") val idDish: Int,
    @NonNull @ColumnInfo(name = "quantity") val quantity: Int,
    @NonNull @ColumnInfo(name = "idCustomer") val idCustomer: Int
)
