package com.example.login001v.viewmodel

import com.example.login001v.data.model.Producto
import com.example.login001v.data.repository.ProductoRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class ProductoViewModelTest : FunSpec({

    val testDispatcher = UnconfinedTestDispatcher()

    beforeSpec {
        Dispatchers.setMain(testDispatcher)
    }

    afterSpec {
        Dispatchers.resetMain()
    }

    test("uiState debe exponer productos del repositorio") {
        val fakeProducts = listOf(
            Producto(id = 1, nombre = "Mesa", precio = "1000", idImagen = 0),
            Producto(id = 2, nombre = "Silla", precio = "500", idImagen = 0)
        )

        val mockRepository = mockk<ProductoRepository> {
            coEvery { allProducts } returns flowOf(fakeProducts)
        }

        val viewModel = ProductoViewModel(mockRepository)

        // Se verifica que el StateFlow tenga los datos esperados
        viewModel.uiState.value.productoList shouldContainExactly fakeProducts
        viewModel.uiState.value.isLoading shouldBe false
    }

    test("insert debe llamar al método insert del repositorio") {
        val mutableFlow = MutableStateFlow(emptyList<Producto>())

        val mockRepository = mockk<ProductoRepository> {
            coEvery { allProducts } returns mutableFlow
            coEvery { insert(any()) } returns Unit
        }

        val viewModel = ProductoViewModel(mockRepository)
        val newProducto = Producto(nombre = "Teclado", precio = "80", idImagen = 0)

        runTest(testDispatcher) {
            viewModel.insert(newProducto)
            coVerify(exactly = 1) { mockRepository.insert(newProducto) }
        }
    }

    test("deleteAll debe llamar al método deleteAll del repositorio") {
        val mockRepository = mockk<ProductoRepository> {
            coEvery { allProducts } returns flowOf(emptyList())
            coEvery { deleteAll() } returns Unit
        }

        val viewModel = ProductoViewModel(mockRepository)

        runTest(testDispatcher) {
            viewModel.deleteAll()
            coVerify(exactly = 1) { mockRepository.deleteAll() }
        }
    }
})