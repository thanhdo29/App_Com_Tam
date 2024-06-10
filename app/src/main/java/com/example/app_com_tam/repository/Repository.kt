    package com.example.app_com_tam.repository


import com.example.app_com_tam.database.DBApp
import com.example.app_com_tam.model.Cart
import com.example.app_com_tam.model.CartDishCrossRef
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.room.DAO.OrderDAO
import kotlinx.coroutines.flow.Flow

    class Repository (val dbApp: DBApp){
        suspend fun addDishtoRoom(dish: Dish){
            dbApp.dishDAO().addDish(dish)
        }
        fun getAllDish()=dbApp.dishDAO().getAllDish()

//         fun getDishById(idDishUpdate:Int):Dish?{
//            dbApp.dishDAO().getDishById1(idDishUpdate)
//        }

        fun getDish(idDishUpdate:Int)=dbApp.dishDAO().getDishById1(idDishUpdate)
        suspend fun deleteDish(dish: Dish){
            dbApp.dishDAO().deleteDish(dish)
        }
        suspend fun updateDish(dish: Dish){
            dbApp.dishDAO().updateDish(dish)
        }

        suspend fun addTypeDishtoRoom(typeDish: TypeDish){
            dbApp.typeDishDAO().addTypeDish(typeDish)
        }
        fun getAllTypeDish()=dbApp.typeDishDAO().getAllTypeDish()
        suspend fun deleteTypeDish(typeDish: TypeDish){
            dbApp.typeDishDAO().deleteTypeDish(typeDish)
        }
        suspend fun updateTypeDish(typeDish: TypeDish){
            dbApp.typeDishDAO().updateTypeDish(typeDish)
        }


        suspend fun addOrdertoRoom(order: Order){
            dbApp.orderDAO().addOrder(order)
        }
        fun getAllOrder()=dbApp.orderDAO().getAllOrders()
        suspend fun deleteOrder(order: Order){
            dbApp.orderDAO().deleteOrder(order)
        }
        suspend fun updateOrer(order: Order){
            dbApp.orderDAO().updateOrder(order)
        }

        suspend fun getDishById(dishId: Int): Dish? {
            return dbApp.dishDAO().getDishById(dishId)
        }

        suspend fun getOrderById(orderId: Int): Order? {
            return dbApp.orderDAO().getOrderById(orderId)
        }


        suspend fun updateOrderStatus(order: Int,bo:Boolean){
            dbApp.orderDAO().updateOrderStatus(order,bo)
        }
        suspend fun addCarttoRoom(cart: Cart){
            dbApp.cardDao().addCart(cart)
        }
        fun getAllCart()=dbApp.cardDao().getAllCart()
        suspend fun deleteCart(cart: Cart){
            dbApp.cardDao().deleteCart(cart)
        }

        suspend fun updateCart(cart: Cart){
            dbApp.cardDao().updateCart(cart)
        }
        fun getCartDishCrossRefsByCartId(cartId: Int): Flow<List<CartDishCrossRef>> {
            return dbApp.orderDAO().getCartDishCrossRefsByCartId(cartId)
        }

        suspend fun addCartDishCrossRef(cartDishCrossRef: CartDishCrossRef){
            dbApp.cardDao().addCartDishCrossRef(cartDishCrossRef)
        }

        suspend fun getOrderByCustomerId(customerId: Int): Order? {
            return dbApp.orderDAO().getOrderByCustomerId(customerId)
        }

}
