package com.example.app_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishs")
data class Dish(
    @PrimaryKey(autoGenerate = true) val idDish: Int = 0,
    @ColumnInfo(name = "nameDish") var nameDish: String,
    @ColumnInfo(name = "priceDish") var priceDish: Double,
    @ColumnInfo(name = "idTypeDish") var idTypeDish: Int,
    @ColumnInfo(name = "imgDish") var imgDish: Int,
    @ColumnInfo(name = "desDish") var desDish: Int,
)
