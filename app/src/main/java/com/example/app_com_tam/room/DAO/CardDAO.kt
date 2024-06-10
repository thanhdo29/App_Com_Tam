package com.example.app_com_tam.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app_com_tam.model.Cart
import com.example.app_com_tam.model.CartDishCrossRef
import com.example.app_com_tam.model.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDAO {
    @Insert
    suspend fun addCart(cart: Cart)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartDishCrossRef(crossRef: CartDishCrossRef)


    @Query("Select * FROM cart")
    fun getAllCart(): Flow<List<Cart>>

    @Delete
    suspend fun deleteCart(cart: Cart)

    @Update
    suspend fun updateCart(cart: Cart)
}