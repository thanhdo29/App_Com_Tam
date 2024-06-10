package com.example.app_com_tam.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_com_tam.model.Dish
import kotlinx.coroutines.flow.Flow

    @Dao
    interface DishDAO{
        @Insert
        suspend fun addDish(dish: Dish)


    @Query("Select * FROM dishs")
     fun getAllDish(): Flow<List<Dish>>

    @Query("Select * FROM dishs Where idDish= :idDishUpdate LIMIT 1")
    fun getDishById1(idDishUpdate:Int):Flow<Dish?>


        @Query("SELECT * FROM dishs WHERE idDish = :dishId")
        suspend fun getDishById(dishId: Int): Dish?



        @Delete
        suspend fun deleteDish(dish: Dish)

        @Update
        suspend fun updateDish(dish: Dish)
    }