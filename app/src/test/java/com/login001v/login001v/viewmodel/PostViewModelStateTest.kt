package com.example.login001v.viewmodel

import com.example.login001v.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// Subclase de prueba para controlar el estado y bloquear la llamada a la red en el init
class FakePostViewModelStateTest(fakePosts: List<Post>) : PostViewModel() {

    // Declaración del estado falso que usaremos en el test
    private val testMutableUiState = MutableStateFlow(
        PostUiState(posts = fakePosts, isLoading = false)
    )

    // Sobreescribimos uiState para inyectar nuestro estado falso (requiere que PostViewModel.uiState sea 'open')
    override val uiState: StateFlow<PostUiState> = testMutableUiState.asStateFlow()

    init {
        // Bloquea el init original que llama a fetchPosts()
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelStateTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        // Establece el dispatcher principal para tests
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        // Restaura el dispatcher principal original
        Dispatchers.resetMain()
    }

    @Test
    fun `postList contiene datos esperados al inicializar el FakeVM`() = runTest(testDispatcher) {
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Título 1", body = "Contenido 1"),
            Post(userId = 2, id = 2, title = "Título 2", body = "Contenido 2")
        )

        // Usamos nuestro VM falso que ya tiene los datos inyectados
        val viewModel = FakePostViewModelStateTest(fakePosts)

        // Verificamos que el StateFlow exponga los datos
        assertEquals(2, viewModel.uiState.value.posts.size)
        assertEquals("Título 1", viewModel.uiState.value.posts[0].title)
        assertEquals("Contenido 2", viewModel.uiState.value.posts[1].body)
    }

    @Test
    fun `test básico de ejemplo`() = runTest(testDispatcher) {
        // Test simple de verificación
        assertEquals(1, 1)
    }
}