package com.example.app_com_tam.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_com_tam.model.Cart
import com.example.app_com_tam.model.CartDishCrossRef
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.viewModel.CartViewModel
import com.example.app_com_tam.viewModel.DishViewModel
import com.example.app_com_tam.viewModel.OrderViewModel
import com.example.app_com_tam.viewModel.TypeDishViewModel

@Composable
fun TestScreen(
    typeDishViewModel: TypeDishViewModel,
    dishViewModel: DishViewModel,
    orderViewModel: OrderViewModel,
    cartViewModel: CartViewModel

) {
    Column {
        Button(onClick = {
            val typeDish1 = TypeDish(
                nameType = "Type 1",
            )
            val typeDish2 = TypeDish(
                nameType = "Type 2",
            )
            typeDishViewModel.addTypeDish(typeDish1)
            typeDishViewModel.addTypeDish(typeDish2)
        }) {
            Text(text = "AddLoai")
        }

        Button(onClick = {
            val dish1 = Dish(
                nameDish = "Dish 1",
                priceDish = 10.0,
                idTypeDish = 1,
                imgDish = "https://www.pinterest.com/pin/38491771809390578/",
                desDish = "ok",
                sales = 100
            )
            val dish2 = Dish(
                nameDish = "Dish 2",
                priceDish = 20.0,
                idTypeDish = 2,
                imgDish = "https://www.pinterest.com/pin/38491771809390578/",
                desDish = "ok",
                sales = 200
            )
            val dish3 = Dish(
                nameDish = "Dish 3",
                priceDish = 20.0,
                idTypeDish = 2,
                imgDish = "https://www.pinterest.com/pin/38491771809390578/",
                desDish = "ok",
                sales = 200
            )
            dishViewModel.addDish(dish1)
            dishViewModel.addDish(dish2)
            dishViewModel.addDish(dish3)


            val cart1 = Cart(idCustomer = 1, idDish = 1, quantity = 2)
            val cart2 = Cart(idCustomer = 1, idDish = 2, quantity = 3)

            val cartDish1 = CartDishCrossRef(1,cartId = cart1.idCart, dishId = cart1.idDish)
            val cartDish2 = CartDishCrossRef(2,cartId = cart2.idCart, dishId = cart2.idDish)
            val cartDish3 = CartDishCrossRef(3,cartId = cart2.idCart, dishId = dish3.idDish)

            cartViewModel.addCart(cart1)
            cartViewModel.addCart(cart2)
          cartViewModel.addCartDishCrossRef(cartDish1)
            cartViewModel.addCartDishCrossRef(cartDish2)
            cartViewModel.addCartDishCrossRef(cartDish3)
            val order1 = Order(
                idCustomer = 1,
                idCart = cart1.idCart,
                methodPay = 1,
                dateBook = "2024-06-08",
                totalAmount = 50000,
                statusOrder = true
            )
            val order2 = Order(
                idCustomer = 1,
                idCart = cart2.idCart,
                methodPay = 2,
                dateBook = "2024-06-09",
                totalAmount = 30000,
                statusOrder = false
            )
            orderViewModel.addOrder(order1)
            orderViewModel.addOrder(order2)



        }) {
            Text(text = "AddsanPham")
        }





    }

}


//@Preview(showBackground = true)
//@Composable
//fun TestScreenPreview() {
//    TestScreen(
//
//    )
//}