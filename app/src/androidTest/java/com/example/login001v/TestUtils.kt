package com.example.login001v

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.login001v.data.dao.ProductoDao
import com.example.login001v.data.model.Producto
import com.example.login001v.data.repository.ProductoRepository
import com.example.login001v.viewmodel.ProductoViewModelFactory
import kotlinx.coroutines.flow.flowOf

// Simulación de DAO para evitar inicializar Room en tests instrumentados
fun getMockProductoDao() = object : ProductoDao {
    override fun getAll() = flowOf(emptyList<Producto>())
    override suspend fun insert(producto: Producto) {}
    override suspend fun deleteAll() {}
}

// Factoría de prueba que usa el Mock Dao
fun getTestProductoViewModelFactory(): ProductoViewModelFactory {
    val mockDao = getMockProductoDao()
    val mockRepository = ProductoRepository(mockDao)
    return ProductoViewModelFactory(mockRepository)
}