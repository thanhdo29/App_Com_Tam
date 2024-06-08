package com.example.app_com_tam.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    var username:String,
    var password:String,
    var email:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)