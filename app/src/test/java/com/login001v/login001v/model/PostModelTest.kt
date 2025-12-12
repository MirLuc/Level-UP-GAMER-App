package com.example.login001v.data.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

/**
 * Pruebas del modelo de datos Post.
 *
 * Objetivo:
 * - Verificar el comportamiento por defecto de data class (equals/copy).
 * - Asegurar que copiar y modificar campos funciona como se espera.
 */
class PostModelTest {

    @Test
    fun `equals y copy funcionan en Post`() {
        // Arrange: Creamos un Post base y dos variantes (una igual y otra con un título distinto)
        val p1 = Post(userId = 1, id = 10, title = "Titulo", body = "Cuerpo")
        val p2 = p1.copy()                 // Copia exacta: debe ser "igual" (equals true)
        val p3 = p1.copy(title = "Otro")   // Copia modificando un campo: debe ser "distinta"

        // Assert: p1 y p2 son iguales (todos los campos coinciden)
        assertEquals(p1, p2)

        // Assert: p1 y p3 son distintos (el título cambia)
        assertNotEquals(p1, p3)

        // Assert adicional: la copia modificada tiene el nuevo valor esperado
        assertEquals("Otro", p3.title)
    }
}