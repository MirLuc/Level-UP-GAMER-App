package com.example.login001v.data.repository

import com.example.login001v.data.model.Post
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Pruebas del repositorio de Posts usando MockK.
 *
 * Objetivo:
 * - Simular el repositorio sin llamadas reales de red.
 * - Validar que getPosts() retorna exactamente la lista esperada (stub).
 *
 * Nota:
 * - Se usa runTest del kotlinx-coroutines-test para ejecutar código suspend.
 */
class PostRepositoryMockTest {

    @Test
    fun `getPosts devuelve la lista simulada`() = runTest {
        // Arrange: Creamos una lista fake de posts y un mock del repositorio
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Titulo 1", body = "Cuerpo 1"),
            Post(userId = 2, id = 2, title = "Titulo 2", body = "Cuerpo 2")
        )

        val repo = mockk<PostRepository>()

        // Stub: Cuando se invoque getPosts(), el mock retorna la lista fake
        coEvery { repo.getPosts() } returns fakePosts

        // Act: Llamamos al método suspend del repositorio
        val result = repo.getPosts()

        // Assert: El resultado coincide exactamente con la lista fake
        assertEquals(fakePosts, result)
    }
}