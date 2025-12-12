package com.example.login001v.remote

import com.example.login001v.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
    @POST("/posts")
    suspend fun createPost(@Body post: Post): Post
}