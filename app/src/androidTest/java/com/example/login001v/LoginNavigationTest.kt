package com.example.login001v

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.login001v.navigation.AppNav
import com.example.login001v.ui.theme.Login001VTheme
import org.junit.Rule
import org.junit.Test
import androidx.compose.foundation.layout.PaddingValues

class LoginNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun login_exitoso_debe_navegar_a_DrawerMenu() {
        // Usamos activity.setContent para controlar el contexto de la actividad
        composeTestRule.activity.setContent {
            Login001VTheme {
                AppNav(
                    padding = PaddingValues(),
                    productoViewModelFactory = getTestProductoViewModelFactory()
                )
            }
        }

        composeTestRule.onNodeWithText("Usuario").performTextInput("user")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("pass")

        composeTestRule.onNodeWithText("Iniciar sesion").performClick()

        // Verificar la navegación
        composeTestRule.onNodeWithText("Tienda de items de : user").assertExists()
    }

    @Test
    fun login_fallido_debe_mostrar_mensaje_de_error() {
        composeTestRule.activity.setContent {
            Login001VTheme {
                AppNav(
                    padding = PaddingValues(),
                    productoViewModelFactory = getTestProductoViewModelFactory()
                )
            }
        }

        composeTestRule.onNodeWithText("Usuario").performTextInput("baduser")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("badpass")

        composeTestRule.onNodeWithText("Iniciar sesion").performClick()

        // Verificar el mensaje de error del ViewModel
        composeTestRule.onNodeWithText("Credenciales inválidas").assertExists()
    }
}