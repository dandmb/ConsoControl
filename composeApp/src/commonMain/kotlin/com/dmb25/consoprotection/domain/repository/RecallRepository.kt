package com.dmb25.consoprotection.domain.repository

import com.dmb25.consoprotection.data.local.entity.ProductEntity
import com.dmb25.consoprotection.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface RecallRepository {
    fun getRecalls(): Flow<List<Product>>
    suspend fun fetchAndSave(offset: Int)
    suspend fun canLoadMore(): Boolean
    suspend fun getCurrentOffset(): Int
    suspend fun searchRecalls(query: String): List<Product>
    suspend fun getProductById(id: Int) : Product?
}