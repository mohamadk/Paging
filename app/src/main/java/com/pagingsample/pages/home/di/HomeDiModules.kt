package com.pagingsample.pages.home.di

import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationRequest
import com.pagingsample.BuildConfig
import com.pagingsample.core.repository.db.StoresDao
import com.pagingsample.core.repository.db.StoresDb
import com.pagingsample.core.repository.settings.SettingsRepo
import com.pagingsample.core.repository.settings.SettingsRepoImp
import com.pagingsample.core.utils.location.LocationManagerBelowPlay110600
import com.pagingsample.core.utils.location.LocationManagerPlay110600
import com.pagingsample.core.utils.location.LocationRequestCompat
import com.pagingsample.pages.home.repo.HomeRepository
import com.pagingsample.pages.home.repo.HomeRepositoryImp
import com.pagingsample.pages.home.repo.api.StoresBoundaryCallback
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import java.util.concurrent.TimeUnit


val homeModules: Module = module {


    single<HomeRepository> {
        HomeRepositoryImp(storesItemKeyedDataSource = get(), storesDao = get(), settingsRepo = get())
    }

    single<StoresDao> {
        val db: StoresDb = get()
        db.storesDao()
    }

    single<SettingsRepo> {
        SettingsRepoImp()
    }

    single {
        StoresBoundaryCallback(homeApi = get(), settingsRepo = get())
    }

    single {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(androidContext()) == ConnectionResult.SUCCESS) {
            LocationManagerPlay110600(androidContext(), locationRequestCompat = get())
        } else {
            LocationManagerBelowPlay110600(androidContext(), locationRequestCompat = get())
        }
    }

    single {

        if (BuildConfig.DEBUG) {
            LocationRequestCompat(
                TimeUnit.SECONDS.toMillis(1)
                , TimeUnit.SECONDS.toMillis(1)
                , LocationRequest.PRIORITY_HIGH_ACCURACY
            )

        } else {
            LocationRequestCompat(
                TimeUnit.MINUTES.toMillis(2)
                , TimeUnit.MINUTES.toMillis(2)
                , LocationRequest.PRIORITY_HIGH_ACCURACY
            )
        }
    }

}