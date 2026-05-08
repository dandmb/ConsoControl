package com.dmb25.consoprotection.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmb25.consoprotection.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM products ORDER BY datePublication DESC")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("DELETE FROM products")
    suspend fun clearAll()
}