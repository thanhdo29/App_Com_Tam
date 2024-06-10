package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(primaryKeys = ["id"])
data class CartDishCrossRef(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "cartId") var cartId: Int,
    @ColumnInfo(name = "dishId") var dishId: Int
)
