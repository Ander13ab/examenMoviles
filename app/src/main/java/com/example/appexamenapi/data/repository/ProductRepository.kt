package com.example.appexamenapi.data.repository

import com.example.appexamenapi.data.model.ProductDto
import com.example.appexamenapi.data.remote.ProductosApi


class ProductRepository(
    private val api: ProductosApi
) {
    suspend fun getProducts(page: Int = 1): List<ProductDto> =
        api.getProducts(page).results
}