package com.example.login001v.data.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class AuthRepositoryTest : StringSpec({

    val repository = AuthRepository()

    "login() debe retornar el username en caso de credenciales válidas" {
        val username = repository.login("user", "pass")
        username shouldBe "user"
    }

    "login() debe lanzar una excepción en caso de credenciales inválidas" {
        assertThrows<IllegalArgumentException> {
            repository.login("user", "wrongpass")
        }
    }

    "login() debe lanzar una excepción si el username está vacío" {
        assertThrows<IllegalArgumentException> {
            repository.login("", "pass")
        }
    }
})