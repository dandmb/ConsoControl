package com.dmb25.consoprotection.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dmb25.consoprotection.data.local.entity.SyncMetadataEntity

@Dao
interface SyncMetadataDao {

    @Upsert
    suspend fun upsert(syncMetadata: SyncMetadataEntity)

    @Query("SELECT * FROM sync_metadata WHERE id = 1")
    suspend fun get(): SyncMetadataEntity?

    @Query("UPDATE sync_metadata SET totalLocalCount = totalLocalCount + :count WHERE id = 1")
    suspend fun incrementLocalCount(count: Int)
}