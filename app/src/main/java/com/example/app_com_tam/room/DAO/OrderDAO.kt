package com.example.app_com_tam.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_com_tam.model.CartDishCrossRef
import com.example.app_com_tam.model.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDAO {
    @Insert
    suspend fun addOrder(order: Order)

    @Query("SELECT * FROM orders WHERE idCustomer = :customerId")
    suspend fun getOrderByCustomerId(customerId: Int): Order?

    @Query("SELECT * FROM orders WHERE idOrder = :orderId")
    suspend fun getOrderById(orderId: Int): Order?

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<Order>>

    @Delete
    suspend fun deleteOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)
    @Query("UPDATE orders SET statusOrder = :newStatus WHERE idOrder = :orderId")
    suspend fun updateOrderStatus(orderId: Int, newStatus: Boolean)

    @Query("SELECT * FROM CartDishCrossRef WHERE cartId = :cartId")
    fun getCartDishCrossRefsByCartId(cartId: Int): Flow<List<CartDishCrossRef>>

}