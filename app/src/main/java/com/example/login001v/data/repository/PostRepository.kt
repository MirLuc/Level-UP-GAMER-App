package com.example.login001v.data.repository

import com.example.login001v.data.model.Post
import com.example.login001v.data.remote.RetrofitClient

class PostRepository {
    suspend fun getPosts(): List<Post> {
        return RetrofitClient.apiService.getPosts()
    }
}