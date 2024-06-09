package com.example.app_com_tam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_com_tam.model.Order
import com.example.app_com_tam.repository.Repository
import kotlinx.coroutines.launch

class OrderViewModel (val repository: Repository):ViewModel(){
    fun addOrder(order: Order){
        viewModelScope.launch {
            repository.addOrdertoRoom(order)
        }
    }

    val orders=repository.getAllOrder()

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
}