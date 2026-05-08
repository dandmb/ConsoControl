package com.dmb25.consoprotection.di

import androidx.room.RoomDatabase
import com.dmb25.consoprotection.data.local.AppDatabase
import com.dmb25.consoprotection.data.local.iosDatabaseBuilder
import org.koin.dsl.module

val iosDatabaseModule = module {
    single<RoomDatabase.Builder<AppDatabase>> {
        iosDatabaseBuilder()
    }
}