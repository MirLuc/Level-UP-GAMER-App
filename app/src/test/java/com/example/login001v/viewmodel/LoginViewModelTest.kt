package com.example.login001v.viewmodel

import com.example.login001v.data.repository.AuthRepository
import com.example.login001v.ui.login.LoginViewModel
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class LoginViewModelTest : FunSpec({

    val mockAuthRepository = mockk<AuthRepository>()

    test("onUsernameChange debe actualizar el estado y limpiar el error") {
        val viewModel = LoginViewModel(mockAuthRepository)
        viewModel.uiState = viewModel.uiState.copy(error = "Error previo")
        viewModel.onUsernameChange("newuser")
        viewModel.uiState.username shouldBe "newuser"
        viewModel.uiState.error shouldBe null
    }

    test("onPasswordChange debe actualizar el estado y limpiar el error") {
        val viewModel = LoginViewModel(mockAuthRepository)
        viewModel.uiState = viewModel.uiState.copy(error = "Error previo")
        viewModel.onPasswordChange("newpass")
        viewModel.uiState.password shouldBe "newpass"
        viewModel.uiState.error shouldBe null
    }

    test("submit debe llamar a repo.login con credenciales y manejar el éxito") {
        var successCalled = false
        every { mockAuthRepository.login(any(), any()) } returns "testuser"
        val viewModel = LoginViewModel(mockAuthRepository)

        viewModel.onUsernameChange("testuser")
        viewModel.onPasswordChange("testpass")

        viewModel.submit { user ->
            successCalled = true
            user shouldBe "testuser"
        }

        verify(exactly = 1) { mockAuthRepository.login("testuser", "testpass") }
        viewModel.uiState.isLoading shouldBe false
        successCalled shouldBe true
    }

    test("submit debe actualizar el estado de error en caso de fallo") {
        every { mockAuthRepository.login(any(), any()) } throws IllegalArgumentException("Credenciales inválidas")
        val viewModel = LoginViewModel(mockAuthRepository)

        viewModel.onUsernameChange("baduser")
        viewModel.submit { }

        viewModel.uiState.isLoading shouldBe false
        viewModel.uiState.error shouldBe "Credenciales inválidas"
    }
})