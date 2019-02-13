package com.pagingsample.pages.home.repo

import android.location.Location
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mohamadk.middleman.model.BaseModel
import com.mohamadk.pagingfragment.Listing
import com.pagingsample.core.repository.db.StoresDao
import com.pagingsample.core.repository.settings.SettingsRepo
import com.pagingsample.core.repository.settings.SettingsRepo.Companion.DISTANCE_OFFSET_FOR_NEW_REQUEST
import com.pagingsample.core.utils.location.latitudeOffset
import com.pagingsample.core.utils.location.longitudeOffset
import com.pagingsample.pages.home.items.StoreItem
import com.pagingsample.pages.home.repo.api.StoresBoundaryCallback

class HomeRepositoryImp(
    private val storesItemKeyedDataSource: StoresBoundaryCallback
    , private val storesDao: StoresDao
    , private val settingsRepo: SettingsRepo
) : HomeRepository {

    init {
        storesItemKeyedDataSource.handledResponse = ::insertStoresToDb
    }

    override fun loadPage(location: Location): Listing<PagedList<BaseModel>> {

        storesItemKeyedDataSource.location = location

        val dataSourceFactory = storesDao.stores(
            latitudeOffset(location.latitude, -DISTANCE_OFFSET_FOR_NEW_REQUEST)
            , latitudeOffset(location.latitude, DISTANCE_OFFSET_FOR_NEW_REQUEST)
            , longitudeOffset(location.latitude, location.longitude, -DISTANCE_OFFSET_FOR_NEW_REQUEST)
            , longitudeOffset(location.latitude, location.longitude, DISTANCE_OFFSET_FOR_NEW_REQUEST)
        )

        val pageListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
            .setPageSize(settingsRepo.networkPageSize())
            .build()

        val builder = LivePagedListBuilder(dataSourceFactory, pageListConfig)
            .setBoundaryCallback(storesItemKeyedDataSource)

        return Listing(
            list = builder.build(),
            pagingState = storesItemKeyedDataSource.networkState,
            refreshState = storesItemKeyedDataSource.refreshState,
            retry = {
                storesItemKeyedDataSource.retry()
            }
        ) as Listing<PagedList<BaseModel>>
    }

    private fun insertStoresToDb(list: List<BaseModel>) {
        storesDao.inserts(list as List<StoreItem>)
    }
}