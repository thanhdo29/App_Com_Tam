package com.example.app_com_tam.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_com_tam.model.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDAO{
    @Insert
    suspend fun addOrder(order: Order)

    @Query("SELECT * FROM orders")
    fun getAddOrder():Flow<List<Order>>

    @Delete
    suspend fun deleteOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)
}