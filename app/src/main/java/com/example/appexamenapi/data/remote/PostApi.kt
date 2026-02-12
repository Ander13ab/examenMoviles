package com.example.appexamenapi.data.remote


import com.example.appexamenapi.data.model.Post
import retrofit2.http.GET

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}