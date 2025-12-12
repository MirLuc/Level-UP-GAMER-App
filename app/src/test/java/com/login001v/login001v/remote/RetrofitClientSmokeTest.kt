package com.example.login001v.data.remote

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * Prueba "smoke" del cliente Retrofit.
 *
 * Objetivo:
 * - Confirmar que RetrofitClient.apiService se inicializa correctamente sin lanzar excepciones.
 * - No realiza llamadas de red; solo fuerza la inicialización perezosa y verifica que la instancia exista.
 */
class RetrofitClientSmokeTest {

    @Test
    fun `apiService se inicializa sin errores`() {
        // Assert: Al acceder a apiService no debe lanzarse ninguna excepción
        assertDoesNotThrow {
            val service = RetrofitClient.apiService

            // Verificación básica de que la instancia no es nula
            assertNotNull(service)

            // toString() para asegurar que la instancia se materializa y evitar warnings
            service.toString()
        }
    }
}