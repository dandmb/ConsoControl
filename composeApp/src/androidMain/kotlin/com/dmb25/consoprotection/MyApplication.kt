package com.dmb25.consoprotection

import android.app.Application
import com.dmb25.consoprotection.di.androidDatabaseModule
import com.dmb25.consoprotection.di.dataModule
import com.dmb25.consoprotection.di.domainModule
import com.dmb25.consoprotection.di.presentationModule
import com.dmb25.consoprotection.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(sharedModule, dataModule, presentationModule, domainModule, androidDatabaseModule)
        }
    }
}