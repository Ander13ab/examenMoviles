package com.example.appexamenapi.data.remote

import com.example.appexamenapi.data.model.PagedResponse
import com.example.appexamenapi.data.model.ProductDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductosApi {

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 1
    ): PagedResponse<ProductDto>
}