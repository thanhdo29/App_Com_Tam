package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemCart")
data class ItemCart(
    @PrimaryKey(autoGenerate = true) var idItemCart:Int=0,
    @ColumnInfo(name = "idCart") var idCart:Int,

)