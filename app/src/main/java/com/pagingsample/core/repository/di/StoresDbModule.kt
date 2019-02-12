package com.pagingsample.core.repository.di

import androidx.room.Room
import com.pagingsample.core.repository.db.StoresDb
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val storesDbModule: Module = module {
    single {
        Room.databaseBuilder(androidContext(), StoresDb::class.java, StoresDb.DB_NAME).build()
    }
}
