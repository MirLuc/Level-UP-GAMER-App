package com.example.login001v.ui.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.login001v.data.model.Post
import com.example.login001v.viewmodel.PostUiState
import com.example.login001v.viewmodel.PostViewModel
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakePostViewModel(fakePosts: List<Post>) : PostViewModel() {

    private val fakeMutableUiState = MutableStateFlow(
        PostUiState(posts = fakePosts, isLoading = false)
    )

    override val uiState: StateFlow<PostUiState> = fakeMutableUiState.asStateFlow()

    init {
        // Bloquea el init original
    }
}

class PostScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun el_titulo_de_post_debe_aparecer_en_pantalla() {
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Primer Post de Prueba", body = "Contenido 1"),
            Post(userId = 2, id = 2, title = "Segundo Post de Prueba", body = "Contenido 2")
        )

        val fakeViewModel = FakePostViewModel(fakePosts)

        // Todo el código que usa composeRule debe estar dentro de esta función:
        composeRule.setContent {
            PostScreen(viewModel = fakeViewModel)
        }

        try {
            composeRule.onNodeWithText("Título: Primer Post de Prueba", useUnmergedTree = true)
                .assertExists("No se encontró 'Título: Primer Post de Prueba'")
                .assertIsDisplayed()
        } catch (e: AssertionError) {
            composeRule.onNodeWithText("Primer Post de Prueba", useUnmergedTree = true)
                .assertExists()
                .assertIsDisplayed()
        }

        try {
            composeRule.onNodeWithText("Título: Segundo Post de Prueba", useUnmergedTree = true)
                .assertExists("No se encontró 'Título: Segundo Post de Prueba'")
                .assertIsDisplayed()
        } catch (e: AssertionError) {
            composeRule.onNodeWithText("Segundo Post de Prueba", useUnmergedTree = true)
                .assertExists()
                .assertIsDisplayed()
        }
    }
}