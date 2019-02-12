package com.pagingsample.core.repository.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pagingsample.pages.home.items.StoreItem


@Dao
interface StoresDao {
    @Query("select * from Stores where nearByLatitude between :latitudeMin and :latitudeMax and nearByLongitude between :longitudeMin and :longitudeMax order by itemIdInPage asc ")
    fun stores(
        latitudeMin: Double,
        latitudeMax: Double,
        longitudeMin: Double,
        longitudeMax: Double
    ): DataSource.Factory<Int, StoreItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(stores: List<StoreItem>): List<Long>
}



