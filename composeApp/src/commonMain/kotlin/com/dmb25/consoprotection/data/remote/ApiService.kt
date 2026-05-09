package com.dmb25.consoprotection.data.remote

import com.dmb25.consoprotection.data.remote.dto.ProductDto
import com.dmb25.consoprotection.data.remote.dto.RecallResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodeURLParameter

class ApiService(
    private val httpClient: HttpClient
) {
    suspend fun getRecalls(
        limit: Int = 20,
        offset: Int = 0,
        orderBy: String = "date_publication DESC"
    ): RecallResponseDto {
        return httpClient.get("rappelconso-v2-gtin-trie/records") {
            url {
                encodedParameters.append("order_by", "date_publication DESC".encodeURLParameter())
                encodedParameters.append("limit", limit.toString())
                encodedParameters.append("offset", offset.toString())
            }
        }.body()
    }

    suspend fun getRecallsByCategorie(
        categorie: String,
        limit: Int = 20,
        offset: Int = 0
    ): List<ProductDto> {
        return httpClient.get("/records") {
            parameter("limit", limit)
            parameter("offset", offset)
            parameter("order_by", "date_publication DESC")
            parameter("where", "categorie_produit='$categorie'")
        }.body<RecallResponseDto>().results
    }

    suspend fun searchRecalls(
        query: String,
        limit: Int = 20
    ): RecallResponseDto {
        return httpClient.get("/records") {
            parameter("limit", limit)
            parameter("order_by", "date_publication DESC")
            parameter("where", "search(*, '$query')")
        }.body()
    }


}