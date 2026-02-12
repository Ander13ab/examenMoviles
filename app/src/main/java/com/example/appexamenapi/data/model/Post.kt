package com.example.appexamenapi.data.model

data class Post(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean,
)
