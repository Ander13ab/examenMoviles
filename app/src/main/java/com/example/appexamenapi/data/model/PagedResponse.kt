package com.example.appexamenapi.data.model

import com.squareup.moshi.Json
data class PagedResponse<T>(
    val page: Int?,
    val per_page: Int?,
    val total: Int?,
    val total_pages: Int?,
    @Json(name = "results") val results: List<T>
)

