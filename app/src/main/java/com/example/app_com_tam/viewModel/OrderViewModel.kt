package com.example.app_com_tam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_com_tam.model.CartDishCrossRef
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class OrderViewModel (val repository: Repository):ViewModel(){
    fun addOrder(order: Order){
        viewModelScope.launch {
            repository.addOrdertoRoom(order)
        }
    }


    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    init {
        viewModelScope.launch {
            repository.getAllOrder().collect { orderList ->
                _orders.value = orderList
            }
        }
    }
    fun updateOrder(order: Order){
        viewModelScope.launch {
            repository.updateOrer(order)
        }
    }
    fun deleteOrder(order: Order){
        viewModelScope.launch {
            repository.deleteOrder(order)
        }
    }

    fun OrderUpdateStatur(order: Int,bo:Boolean){
        viewModelScope.launch {
            repository.updateOrderStatus(order,bo)
        }
    }
    fun getCartDishCrossRefsByCartId(cartId: Int, callback: (List<CartDishCrossRef>) -> Unit) {
        viewModelScope.launch {
            repository.getCartDishCrossRefsByCartId(cartId).collect { crossRefs ->
                callback(crossRefs)
            }
        }
    }

    fun getOrderByCustomerId(CustomerId: Int, callback: (Order?) -> Unit) {
        viewModelScope.launch {
            val order = repository.getOrderByCustomerId(CustomerId)
            callback(order)
        }
    }

    fun getOrderById(OrderById: Int, callback: (Order?) -> Unit) {
        viewModelScope.launch {
            val order = repository.getOrderById(OrderById)
            callback(order)
        }
    }
}