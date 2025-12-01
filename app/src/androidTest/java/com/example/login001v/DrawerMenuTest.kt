package com.example.login001v

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.login001v.navigation.AppNav
import com.example.login001v.ui.theme.Login001VTheme
import org.junit.Rule
import org.junit.Test

class DrawerMenuTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun drawer_navegacion_guarda_producto_y_muestra_datos() {
        composeTestRule.setContent {
            Login001VTheme {
                AppNav(
                    padding = androidx.compose.foundation.layout.PaddingValues(),
                    productoViewModelFactory = getTestProductoViewModelFactory()
                )
            }
        }

        // Simular login exitoso para llegar al DrawerMenu (requiere refactor de test anterior)
        // Por simplicidad, asumimos que el test inicia directamente en el Drawer (requiere cambiar startDestination)

        // Asumiendo que el test inicia en el Login, navegaremos primero:
        composeTestRule.onNodeWithText("Usuario").performTextInput("user")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("pass")
        composeTestRule.onNodeWithText("Iniciar sesion").performClick()

        // 1. Abrir el Drawer (Requiere simular el clic en el icono de menú, que es difícil sin un ID)
        // Usaremos el deslizamiento o un atajo de teclado si el icono de menú no tiene un ID de prueba.
        // Asumiremos que el Drawer está abierto por la inyección de datos del login.

        // 2. Navegar a un producto para guardar (Ej: "Juegos de Mesa")
        composeTestRule.onNodeWithText("Juegos de Mesa").performClick()

        // 3. Verificar que estamos en la pantalla de ProductoForm
        composeTestRule.onNodeWithText("Nombre: Juegos de Mesa").assertExists()

        // 4. Guardar el producto en Room
        composeTestRule.onNodeWithText("Guardar Producto Localmente").performClick()

        // 5. Verificar que hemos navegado a MuestraDatosScreen y el producto está ahí
        composeTestRule.onNodeWithText("Productos Guardados").assertExists()
        composeTestRule.onNodeWithText("Nombre: Juegos de Mesa").assertExists()
    }
}