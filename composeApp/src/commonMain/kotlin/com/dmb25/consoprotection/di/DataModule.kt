package com.dmb25.consoprotection.di

import com.dmb25.consoprotection.data.remote.ApiService
import com.dmb25.consoprotection.data.repository.RecallRepositoryImpl
import com.dmb25.consoprotection.domain.repository.RecallRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) { level = LogLevel.INFO }
            install(HttpTimeout) { requestTimeoutMillis = 15_000 }
            install(DefaultRequest) {
                url("https://data.economie.gouv.fr/api/explore/v2.1/catalog/datasets/rappelconso-v2-gtin-trie")
            }
            expectSuccess = true
        }
    }

    single<RecallRepository> { RecallRepositoryImpl(get()) }

    single { ApiService(get()) }

}