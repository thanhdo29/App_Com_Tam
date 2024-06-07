package com.example.app_com_tam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.room.DAO.DishDAO
import com.example.app_com_tam.room.DAO.OrderDAO
import com.example.app_com_tam.room.DAO.TypeDishDAO

@Database(entities = [Dish::class, TypeDish::class, Order::class], version = 1, exportSchema = false)
abstract class DBApp :RoomDatabase(){
    abstract fun dishDAO():DishDAO
    abstract fun typeDishDAO():TypeDishDAO
    abstract fun orderDAO():OrderDAO

    companion object{
        @Volatile
        private var INTANCE:DBApp?= null

        fun getIntance(context: Context):DBApp{
            synchronized(this){
                var instance= INTANCE
                if (instance== null){
                    instance=Room.databaseBuilder(context.applicationContext, DBApp::class.java, "db_app").build()
                }
                return instance
            }
        }
    }
}