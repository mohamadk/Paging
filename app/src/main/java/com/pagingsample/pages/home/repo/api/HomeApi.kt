package com.pagingsample.pages.home.repo.api

import com.pagingsample.pages.home.items.StoreItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface HomeApi {

    @GET("/v2/venues/explore/")
    fun getStores(
        @Query("client_id")
        clientId: String = "MR3HJG1MDW4FZFVHCG0223RLH4LFTHJZCJ15IOPR5402EIBP",
        @Query("client_secret")
        clientSecret: String = "HZ0UNK0VXWOYBXPD1YF5X2CH103Z5YPARYSXJJKTY3UC4OH2",
        @Query("v")
        date: String = SimpleDateFormat("yyyyMMDD", Locale.ENGLISH).format(Date()),
        @Query("intent")
        intent: String = "checkin",
        @Query("ll")
        latLng: String,
        @Query("offset")
        offset: Int,
        @Query("limit")
        limit: Int

    ): Observable<List<StoreItem>>

    companion object {
        const val BASE_URL = "https://api.foursquare.com/"
    }
}
