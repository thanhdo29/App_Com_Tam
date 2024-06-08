package com.example.app_com_tam.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_com_tam.model.TypeDish
import kotlinx.coroutines.flow.Flow


@Dao
interface TypeDishDAO{

    @Insert
    suspend fun addTypeDish(typeDish: TypeDish)

    @Query("SELECT * FROM TypeDish")
    fun getAllTypeDish():Flow<List<TypeDish>>

    @Delete
    suspend fun deleteTypeDish(typeDish: TypeDish)

    @Update
    suspend fun updateTypeDish(typeDish: TypeDish)
}