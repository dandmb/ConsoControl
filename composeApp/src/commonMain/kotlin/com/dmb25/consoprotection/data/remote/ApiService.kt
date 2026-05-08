package com.dmb25.consoprotection.data.remote

import com.dmb25.consoprotection.data.model.Product
import com.dmb25.consoprotection.data.model.RecallResponse
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
    ): List<Product> {
        return httpClient.get("rappelconso-v2-gtin-trie/records") {
            url {
                encodedParameters.append("order_by", "date_publication DESC".encodeURLParameter())
                encodedParameters.append("limit", limit.toString())
                encodedParameters.append("offset", offset.toString())
            }
        }.body<RecallResponse>().results
    }

    suspend fun getRecallsByCategorie(
        categorie: String,
        limit: Int = 20,
        offset: Int = 0
    ): List<Product> {
        return httpClient.get("/records") {
            parameter("limit", limit)
            parameter("offset", offset)
            parameter("order_by", "date_publication DESC")
            parameter("where", "categorie_produit='$categorie'")
        }.body<RecallResponse>().results
    }

    suspend fun searchRecalls(
        query: String,
        limit: Int = 20
    ): List<Product> {
        return httpClient.get("/records") {
            parameter("limit", limit)
            parameter("order_by", "date_publication DESC")
            parameter("where", "search(*, '$query')")
        }.body<RecallResponse>().results
    }


}