package com.example.login001v.remote

import com.example.login001v.data.model.Post
import retrofit2.http.GET

interface ApiService {

    // Define la solicitud GET al endpoint "/posts"
    @GET(value="/posts")
    suspend fun getPosts(): List<Post>

}