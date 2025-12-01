package com.example.login001v.viewmodel

import androidx.lifecycle.ViewModel
import com.example.login001v.data.dao.ProductoDao
import com.example.login001v.data.model.Producto
import com.example.login001v.data.repository.ProductoRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.assertThrows
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductoViewModelFactoryTest : StringSpec({

    val mockDao = object : ProductoDao {
        override fun getAll(): Flow<List<Producto>> = flowOf(emptyList())
        override suspend fun insert(producto: Producto) {}
        override suspend fun deleteAll() {}
    }

    val mockRepository = ProductoRepository(mockDao)
    val factory = ProductoViewModelFactory(mockRepository)

    "create debe instanciar correctamente ProductoViewModel" {
        val viewModel = factory.create(ProductoViewModel::class.java)
        viewModel.shouldBeInstanceOf<ProductoViewModel>()
    }

    "create debe lanzar excepción para clases ViewModel desconocidas" {
        class UnknownViewModel : ViewModel()
        assertThrows<IllegalArgumentException> {
            factory.create(UnknownViewModel::class.java)
        }
    }
})