package com.dmb25.consoprotection.domain.repository

import com.dmb25.consoprotection.data.model.Product
import kotlinx.coroutines.flow.Flow

interface RecallRepository {
    suspend fun getRecalls(
        limit: Int = 20,
        offset: Int = 0,
        orderBy: String = "date_publication DESC"
    ) : Flow<List<Product>>
}