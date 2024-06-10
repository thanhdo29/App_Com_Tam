package com.example.app_com_tam.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "CartDishCrossRef")
data class CartDishCrossRef(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cartId: Int,
    val dishId: Int
)