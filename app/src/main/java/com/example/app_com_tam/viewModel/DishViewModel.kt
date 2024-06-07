package com.example.app_com_tam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.repository.Repository
import kotlinx.coroutines.launch

class DishViewModel (val repository: Repository):ViewModel(){
    fun addDish(dish: Dish){
        viewModelScope.launch {
            repository.addDishtoRoom(dish)
        }
    }

    val dishs=repository.getAllDish()

    fun updateDish(dish: Dish){
        viewModelScope.launch {
            repository.updateDish(dish)
        }
    }

    fun deleteDish(dish: Dish){
        viewModelScope.launch {
            repository.deleteDish(dish)
        }
    }
}