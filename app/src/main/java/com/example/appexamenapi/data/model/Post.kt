package com.example.appexamenapi.data.model

data class Post(
    val id: Int,
    val name: String,
    val description: String? = null,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean,
    @Json(name = "portrait_path") val portraitPath: String? = null
)
