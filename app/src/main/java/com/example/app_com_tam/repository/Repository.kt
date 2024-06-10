    package com.example.app_com_tam.repository

    import com.example.app_com_tam.database.DBApp
    import com.example.app_com_tam.model.Dish
    import com.example.app_com_tam.model.Order
    import com.example.app_com_tam.model.TypeDish

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
        fun getAllOrder()=dbApp.orderDAO().getAddOrder()
        suspend fun deleteOrder(order: Order){
            dbApp.orderDAO().deleteOrder(order)
        }
        suspend fun updateOrer(order: Order){
            dbApp.orderDAO().updateOrder(order)
        }
    }