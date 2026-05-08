package com.dmb25.consoprotection.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.dmb25.consoprotection.data.local.dao.ProductDao
import com.dmb25.consoprotection.data.local.dao.SyncMetadataDao
import com.dmb25.consoprotection.data.local.entity.ProductEntity
import com.dmb25.consoprotection.data.local.entity.SyncMetadataEntity

@Database(
    entities = [ProductEntity::class, SyncMetadataEntity::class],
    version = 1,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun syncMetadataDao(): SyncMetadataDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor: RoomDatabaseConstructor<AppDatabase>{
    override fun initialize() : AppDatabase
}