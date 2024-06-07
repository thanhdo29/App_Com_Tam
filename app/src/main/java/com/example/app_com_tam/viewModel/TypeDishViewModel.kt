package com.example.app_com_tam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.repository.Repository
import kotlinx.coroutines.launch

class TypeDishViewModel (val repository: Repository):ViewModel(){
    fun addTypeDish(typeDish: TypeDish){
        viewModelScope.launch {
            repository.addTypeDishtoRoom(typeDish)
        }
    }

    val typeDishs=repository.getAllTypeDish()

    fun updateTypeDish(typeDish: TypeDish){
        viewModelScope.launch {
            repository.updateTypeDish(typeDish)
        }
    }

    fun deleteTypeDish(typeDish: TypeDish){
        viewModelScope.launch {
            repository.deleteTypeDish(typeDish)
        }
    }
}