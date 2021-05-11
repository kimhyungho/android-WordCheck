package com.appdev.wordcheck

import android.app.Application
import com.appdev.wordcheck.di.DiModule
import com.appdev.wordcheck.util.PreferenceUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class WordCheckApplication : Application() {
    companion object{
        lateinit var preferences: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        preferences = PreferenceUtil(applicationContext)
        startKoin {
            androidContext(applicationContext)
            modules(DiModule)
        }
    }
}