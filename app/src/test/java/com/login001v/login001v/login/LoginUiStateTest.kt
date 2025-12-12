package com.example.login001v.ui.login

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

/**
 * Pruebas del estado de UI para la pantalla de Login (LoginUiState).
 *
 * Objetivo:
 * - Verificar valores por defecto del estado (strings vacíos, no cargando, sin error).
 * - Asegurar que la función copy permite actualizar campos correctamente.
 */
class LoginUiStateTest {

    @Test
    fun `estado por defecto es vacio y sin error`() {
        // Arrange: Se crea el estado con constructor por defecto
        val state = LoginUiState()

        // Assert: Campos iniciales correctos
        assertEquals("", state.username)
        assertEquals("", state.password)
        assertEquals(false, state.isLoading)
        assertNull(state.error)
    }

    @Test
    fun `copy permite actualizar campos`() {
        // Arrange: Estado inicial con usuario y contraseña
        val state = LoginUiState(username = "u", password = "p")

        // Act: Se actualizan isLoading y error con copy
        val updated = state.copy(isLoading = true, error = "Oops")

        // Assert: Los campos actualizados se conservan y los anteriores permanecen iguales
        assertEquals("u", updated.username)
        assertEquals("p", updated.password)
        assertEquals(true, updated.isLoading)
        assertEquals("Oops", updated.error)
    }
}