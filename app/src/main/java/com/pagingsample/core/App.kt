package com.pagingsample.core

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.pagingsample.core.repository.di.storesApiModule
import com.pagingsample.core.repository.di.storesDbModule
import com.pagingsample.pages.home.di.homeModules
import com.pagingsample.pages.viewModelModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin(
            this, listOf(storesApiModule, storesDbModule, viewModelModules, homeModules)
        )

    }

}