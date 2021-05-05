package com.appdev.wordcheck

import android.app.Application
import com.appdev.wordcheck.di.DiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class WordCheckApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(DiModule)
        }
    }
}