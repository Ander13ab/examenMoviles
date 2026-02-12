package com.example.appexamenapi.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appexamenapi.data.model.ProductDto
import com.example.appexamenapi.data.remote.ProductNetwork
import com.example.appexamenapi.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository(ProductNetwork.api)

    var state by mutableStateOf<UiState<List<ProductDto>>>(UiState.Loading)
        private set

    init {
        loadProducts()
    }

    fun loadProducts(page: Int = 1) {
        viewModelScope.launch {
            state = UiState.Loading
            state = try {
                UiState.Success(repository.getProducts(page))
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error cargando productos")
            }
        }
    }
}