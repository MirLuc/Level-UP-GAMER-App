package com.example.login001v.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.login001v.data.model.Producto
import com.example.login001v.data.repository.ProductoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ProductoUiState(
    val productoList: List<Producto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ProductoViewModel(private val repository: ProductoRepository) : ViewModel() {

    val uiState: StateFlow<ProductoUiState> = repository.allProducts
        .map { ProductoUiState(productoList = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProductoUiState(isLoading = true)
        )

    fun insert(producto: Producto) = viewModelScope.launch {
        repository.insert(producto)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class ProductoViewModelFactory(private val repository: ProductoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}