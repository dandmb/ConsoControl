package com.dmb25.consoprotection.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun androidDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = context.getDatabasePath("app_database.db")
    return Room.databaseBuilder<AppDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}