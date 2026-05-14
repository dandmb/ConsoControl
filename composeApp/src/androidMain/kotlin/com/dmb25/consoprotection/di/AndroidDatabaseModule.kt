package com.dmb25.consoprotection.di

import androidx.room.RoomDatabase
import com.dmb25.consoprotection.data.local.AppDatabase
import com.dmb25.consoprotection.data.local.androidDatabaseBuilder
import com.dmb25.consoprotection.scanner.BarcodeScanner
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDatabaseModule = module {
    single<RoomDatabase.Builder<AppDatabase>> { androidDatabaseBuilder(androidContext()) }
    factory {
        BarcodeScanner()
    }
}