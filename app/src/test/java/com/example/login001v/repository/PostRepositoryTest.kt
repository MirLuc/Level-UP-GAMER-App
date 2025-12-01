package com.example.login001v.data.repository

import com.example.login001v.data.dao.ProductoDao
import com.example.login001v.data.model.Producto
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest

class ProductoRepositoryTest : StringSpec({

    val mockDatabase = MutableStateFlow(emptyList<Producto>())

    val fakeDao = object : ProductoDao {
        override fun getAll(): Flow<List<Producto>> = mockDatabase
        override suspend fun insert(producto: Producto) {
            mockDatabase.update { it + producto }
        }
        override suspend fun deleteAll() {
            mockDatabase.update { emptyList() }
        }
    }

    val repository = ProductoRepository(fakeDao)

    val fakeProducto1 = Producto(id = 1, nombre = "Test 1", precio = "100", idImagen = 0)
    val fakeProducto2 = Producto(id = 2, nombre = "Test 2", precio = "200", idImagen = 0)

    beforeTest {
        mockDatabase.value = emptyList()
    }

    "insert() debe agregar un producto a la lista" {
        runTest {
            repository.insert(fakeProducto1)
            val result = repository.allProducts.first()
            result shouldContainExactly listOf(fakeProducto1)
        }
    }

    "deleteAll() debe vaciar la lista de productos" {
        runTest {
            repository.insert(fakeProducto1)
            repository.insert(fakeProducto2)
            repository.deleteAll()
            val result = repository.allProducts.first()
            result shouldContainExactly emptyList()
        }
    }

    "allProducts debe emitir la lista completa de productos" {
        runTest {
            repository.insert(fakeProducto1)
            repository.insert(fakeProducto2)
            val result = repository.allProducts.first()
            result shouldContainExactly listOf(fakeProducto1, fakeProducto2)
        }
    }
})