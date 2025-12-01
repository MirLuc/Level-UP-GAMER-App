package com.example.login001v.data.repository

import com.example.login001v.data.dao.ProductoDao
import com.example.login001v.data.model.Producto
import kotlinx.coroutines.flow.Flow

class ProductoRepository(private val dao: ProductoDao) {

    val allProducts: Flow<List<Producto>> = dao.getAll()

    suspend fun insert(producto: Producto) {
        dao.insert(producto)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}