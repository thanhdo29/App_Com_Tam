package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TypeDish")
data class TypeDish(
    @PrimaryKey(autoGenerate = true) var idTypeDish:Int=0,
    @ColumnInfo(name = "nameType") var nameType:String,
)