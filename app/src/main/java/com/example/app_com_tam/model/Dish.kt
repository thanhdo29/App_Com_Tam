package com.example.app_com_tam.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishs")
data class Dish(
    @NonNull @PrimaryKey(autoGenerate = true) val idDish: Int = 0,
    @NonNull @ColumnInfo(name = "nameDish") var nameDish: String,
    @NonNull @ColumnInfo(name = "priceDish") var priceDish: Double,
    @NonNull @ColumnInfo(name = "idTypeDish") var idTypeDish: Int,
    @NonNull @ColumnInfo(name = "imgDish") var imgDish: String,
    @NonNull @ColumnInfo(name = "desDish") var desDish: String,
)
