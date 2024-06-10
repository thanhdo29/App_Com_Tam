package com.example.app_com_tam.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app_com_tam.model.Cart
import com.example.app_com_tam.model.CartDishCrossRef

import com.example.app_com_tam.model.Dish;
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.model.User
import com.example.app_com_tam.room.DAO.CardDAO
import com.example.app_com_tam.room.DAO.DishDAO
import com.example.app_com_tam.room.DAO.OrderDAO
import com.example.app_com_tam.room.DAO.TypeDishDAO
import com.example.app_com_tam.room.DAO.UserDAO

@Database(entities = [User::class, Dish::class, TypeDish::class, Order::class, Cart::class,CartDishCrossRef::class], version = 19)

abstract class DBApp : RoomDatabase() {
    abstract fun dishDAO(): DishDAO
    abstract fun typeDishDAO(): TypeDishDAO
    abstract fun orderDAO(): OrderDAO

    abstract fun userDao():UserDAO

    abstract fun cardDao():CardDAO

//    companion object {
//        @Volatile
//        private var INSTANCE: DBApp? = null
//
//        fun getInstance(context: Context): DBApp {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DBApp::class.java,
//                    "db1_app"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}
