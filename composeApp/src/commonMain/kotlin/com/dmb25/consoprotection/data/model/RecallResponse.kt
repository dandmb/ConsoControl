package com.dmb25.consoprotection.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecallResponse(
    @SerialName("results")
    val results: List<Product>,
    @SerialName("total_count")
    val totalCount: Int
)