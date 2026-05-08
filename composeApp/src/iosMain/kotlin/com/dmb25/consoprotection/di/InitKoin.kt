package com.dmb25.consoprotection.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(sharedModule, dataModule, presentationModule, domainModule, iosDatabaseModule)
    }
}