package com.dmb25.consoprotection.di

import com.dmb25.consoprotection.data.local.AppDatabase
import com.dmb25.consoprotection.data.local.CreateDatabase
import org.koin.dsl.module

val sharedModule = module {
    single <AppDatabase> { CreateDatabase(get()).getDatabase() }
}