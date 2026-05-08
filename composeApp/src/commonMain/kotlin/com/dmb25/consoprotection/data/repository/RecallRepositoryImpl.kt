package com.dmb25.consoprotection.data.repository

import com.dmb25.consoprotection.data.model.Product
import com.dmb25.consoprotection.data.remote.ApiService
import com.dmb25.consoprotection.domain.repository.RecallRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecallRepositoryImpl(
    val apiService: ApiService
) : RecallRepository{
    override suspend fun getRecalls(
        limit: Int,
        offset: Int,
        orderBy: String
    ): Flow<List<Product>> = flow {
        val data = apiService.getRecalls()
        emit(data)
    }
}