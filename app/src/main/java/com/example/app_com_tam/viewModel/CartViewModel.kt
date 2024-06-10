package com.example.app_com_tam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_com_tam.model.Cart
import com.example.app_com_tam.model.CartDishCrossRef
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.repository.Repository
import com.example.app_com_tam.room.DAO.CardDAO
import com.example.app_com_tam.room.DAO.DishDAO
import kotlinx.coroutines.launch

class CartViewModel(val repository: Repository) : ViewModel() {
    fun addCart(cart: Cart) {
        viewModelScope.launch {
            repository.addCarttoRoom(cart)
        }
    }

    val carts =repository.getAllCart()

    fun updateCart(cart: Cart) {
        viewModelScope.launch {
            repository.updateCart(cart)
        }

    }

    fun deleteCart(cart: Cart) {
        viewModelScope.launch {
            repository.deleteCart(cart)
        }
    }

    fun addCartDishCrossRef(cartDishCrossRef: CartDishCrossRef) {
        viewModelScope.launch {
            repository.addCartDishCrossRef(cartDishCrossRef)
        }

    }

//    fun getCartDishCrossRefsByCartId(){
//        viewModelScope.launch {
//        }
//
//    }


    fun getlist(x: Int) {
        viewModelScope.launch {
            val cartDishCrossRefs = repository.getCartDishCrossRefsByCartId(x)
        }
    }


}