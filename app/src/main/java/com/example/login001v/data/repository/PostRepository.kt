package com.example.login001v.repository

import com.example.login001v.data.model.Post
import com.example.login001v.data.remote.RetrofitInstance

class PostRepository {

    // Función suspendida para obtener los post desde la API
    suspend fun getPosts(): List<Post>{
        return RetrofitInstance.api.getPosts()
    }

}