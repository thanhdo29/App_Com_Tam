package com.example.app_com_tam.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_com_tam.model.Dish
import com.example.app_com_tam.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DishViewModel (val repository: Repository):ViewModel(){
    private val _selectedDish = MutableLiveData<Dish?>(null)
    val selectedDish: LiveData<Dish?> = _selectedDish
    fun addDish(dish: Dish){
        viewModelScope.launch {
            repository.addDishtoRoom(dish)
        }
    }


    private val _dishes = MutableStateFlow<List<Dish>>(emptyList())
    val dishes: StateFlow<List<Dish>> = _dishes

    init {
        viewModelScope.launch {
            repository.getAllDish().collect { dishList ->
                _dishes.value = dishList
            }
        }
    }

//    fun getDishById(idDishUpdate:Int){
//        viewModelScope.launch {
//            var dish=repository.getDishById(idDishUpdate)
//            _selectedDish.postValue(dish)
//        }
//    }

    fun getDishById(idDishUpdate: Int) {
        viewModelScope.launch {
            repository.getDish(idDishUpdate).collect { dish ->
                _selectedDish.value = dish
            }
        }
    }
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


    fun getDishById(dishId: Int, callback: (Dish?) -> Unit) {
        viewModelScope.launch {
            val dish = repository.getDishById(dishId)
            callback(dish)
        }
    }





}