package com.dmb25.consoprotection.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rappel(
    @SerialName("results")
    val results: List<Article>,
    @SerialName("total_count")
    val totalCount: Int
)