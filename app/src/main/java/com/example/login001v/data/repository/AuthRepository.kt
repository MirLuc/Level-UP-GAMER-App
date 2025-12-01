package com.example.login001v.data.repository

class AuthRepository {

    fun login(username: String, password: String): String {
        return if (username.trim() == "user" && password == "pass") {
            username.trim()
        } else {
            throw IllegalArgumentException("Credenciales inválidas")
        }
    }
}