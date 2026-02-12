package com.example.appexamenapi.data.repository

import com.example.appexamenapi.data.model.Post
import com.example.appexamenapi.data.remote.RetrofitInstance

class PostRepository {
    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }
}