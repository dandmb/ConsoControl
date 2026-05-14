package com.dmb25.consoprotection.data.repository

import com.dmb25.consoprotection.data.local.dao.ProductDao
import com.dmb25.consoprotection.data.local.dao.SyncMetadataDao
import com.dmb25.consoprotection.data.local.entity.SyncMetadataEntity
import com.dmb25.consoprotection.data.mapper.toEntity
import com.dmb25.consoprotection.data.mapper.toModel
import com.dmb25.consoprotection.data.remote.ApiService
import com.dmb25.consoprotection.data.remote.dto.RecallResponseDto
import com.dmb25.consoprotection.domain.model.Product
import com.dmb25.consoprotection.domain.repository.RecallRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class RecallRepositoryImpl(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val syncMetadataDao: SyncMetadataDao,
) : RecallRepository {

    override fun getRecalls(): Flow<List<Product>> {
        return productDao.getAll()
            .map { entities -> entities.map { it.toModel() } }
            .onStart { refresh() }
    }

    private suspend fun refresh() {
        try {
            val response = apiService.getRecalls(limit = 20, offset = 0)
            val entities = response.results.map { it.toEntity() }
            productDao.insertAll(entities)

            val metadata = syncMetadataDao.get()
            if (metadata == null) {
                syncMetadataDao.upsert(
                    SyncMetadataEntity(
                        totalRemoteCount = response.totalCount,
                        totalLocalCount = entities.size
                    )
                )
            } else {
                syncMetadataDao.upsert(
                    metadata.copy(
                        totalRemoteCount = response.totalCount,
                    )
                )
            }
        } catch (e: Exception) {
            // si erreur réseau, le Flow continue avec les données locales
        }
    }

    override suspend fun fetchAndSave(offset: Int) {
        val response = apiService.getRecalls(limit = 20, offset = offset)
        updateLocalDatabase(response)
    }

    override suspend fun canLoadMore(): Boolean {
        val metadata = syncMetadataDao.get() ?: return true
        return metadata.totalLocalCount < metadata.totalRemoteCount
    }

    override suspend fun getCurrentOffset(): Int {
        return syncMetadataDao.get()?.totalLocalCount ?: 0
    }

    override suspend fun searchRecalls(query: String): List<Product> {
        val response = apiService.searchRecalls(query = query)
        updateLocalDatabase(response)
        return response.results.map { it.toEntity().toModel() }
    }

    override suspend fun getProductById(id: Int): Product? {
        return productDao.getById(id)?.toModel()
    }

    override suspend fun getProductByGtin(gtin: Long): Product? {
        val response = apiService.getProductByGtin(gtin)
        updateLocalDatabase(response)
        return response.results.firstOrNull()?.toEntity()?.toModel()
    }

    private suspend fun updateLocalDatabase(response : RecallResponseDto){
        val entities = response.results.map { it.toEntity() }
        productDao.insertAll(entities)
        syncMetadataDao.incrementLocalCount(entities.size)
    }
}