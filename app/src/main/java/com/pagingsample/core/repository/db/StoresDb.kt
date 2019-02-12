package com.pagingsample.core.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pagingsample.core.repository.db.StoresDb.Companion.DB_VERSION
import com.pagingsample.pages.home.items.StoreItem


@Database(version = DB_VERSION, entities = [StoreItem::class])
abstract class StoresDb : RoomDatabase() {

    abstract fun storesDao(): StoresDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "dbStores"

    }
}
