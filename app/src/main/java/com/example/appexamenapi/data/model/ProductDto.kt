package com.example.appexamenapi.data.model

import com.squareup.moshi.Json

data class ProductDto(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean,
    @Json(name = "portrait_path") val portraitPath: String? = null
)