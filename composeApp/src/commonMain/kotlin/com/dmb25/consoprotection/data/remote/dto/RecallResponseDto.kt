package com.dmb25.consoprotection.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecallResponseDto(
    @SerialName("results")
    val results: List<ProductDto>,
    @SerialName("total_count")
    val totalCount: Int
)