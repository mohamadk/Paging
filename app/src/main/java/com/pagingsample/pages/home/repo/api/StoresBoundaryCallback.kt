package com.pagingsample.pages.home.repo.api

import android.location.Location
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.mohamadk.middleman.networkstate.NetworkState
import com.pagingsample.core.repository.settings.SettingsRepo
import com.pagingsample.core.repository.settings.SettingsRepo.Companion.DISTANCE_OFFSET_FOR_NEW_REQUEST
import com.pagingsample.pages.home.items.StoreItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class StoresBoundaryCallback(
    private val homeApi: HomeApi
    , private val settingsRepo: SettingsRepo

) : PagedList.BoundaryCallback<StoreItem>() {

    lateinit var location: Location
    lateinit var handledResponse: (List<StoreItem>) -> Unit
    val networkState = MutableLiveData<NetworkState>()
    val refreshState = MutableLiveData<NetworkState>()

    private var failedRequest: Request? = null
    private var lastRequest: Request? = null
    private var queuedRequest: Request? = null
    private var lastOffset: Int = 0


    @MainThread
    override fun onZeroItemsLoaded() {
        if (!runningAlready()) {
            lastOffset = 0
            makeCall()
        } else {
            queueRequest()
        }
    }

    private fun queueRequest(itemAtEnd: StoreItem? = null) {
        if (location.distanceTo(lastRequest!!.location) > DISTANCE_OFFSET_FOR_NEW_REQUEST) {
            queuedRequest = Request(itemAtEnd, location = location)
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: StoreItem) {
        if (!runningAlready(itemAtEnd)) {
            if (isNextPage(itemAtEnd)) {
                makeCall(itemAtEnd)
            }
        } else {
            queueRequest(itemAtEnd)
        }
    }

    private fun isNextPage(itemAtEnd: StoreItem): Boolean {
        return (itemAtEnd.totalItemsCount > itemAtEnd.itemIdInPage + 1)
    }

    private fun nextOffset(itemAtEnd: StoreItem?): Int {
        return if (itemAtEnd == null) {
            0
        } else {
            lastOffset = itemAtEnd.itemIdInPage + 1
            lastOffset
        }
    }

    private fun runningAlready(itemAtEnd: StoreItem? = null): Boolean {
        return lastRequest?.model?.equals(itemAtEnd) == true
                || (lastRequest != null && lastRequest!!.model == null && itemAtEnd == null)
    }

    private fun makeCall(itemAtEnd: StoreItem? = null) {
        lastRequest = Request(itemAtEnd, location)

        setNetState(itemAtEnd, NetworkState.LOADING)

        makeWebservice(itemAtEnd)
            .doOnNext {
                handledResponse(prepareResponse(it))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    recordRequestResult()
                    setNetState(itemAtEnd, NetworkState.LOADED)
                    checkQueue()
                },
                onError = {
                    recordRequestResult(it)
                    setNetState(itemAtEnd, NetworkState.error(it.message))
                    it.printStackTrace()
                    checkQueue()
                }
            )
    }

    private fun setNetState(
        itemAtEnd: StoreItem?,
        netState: NetworkState
    ) {
        if (itemAtEnd == null) {
            refreshState.postValue(netState)
        } else {
            networkState.postValue(netState)
        }
    }

    private fun checkQueue() {
        if (queuedRequest != null) {
            location = queuedRequest!!.location
            makeCall(queuedRequest!!.model)
            queuedRequest = null
        }
    }

    private fun prepareResponse(list: List<StoreItem>): List<StoreItem> {
        return list.map { stormItem ->
            stormItem.apply {
                itemIdInPage += lastOffset
                nearByLatitude = lastRequest!!.location.latitude
                nearByLongitude = lastRequest!!.location.longitude
            }
        }
    }

    private fun makeWebservice(itemAtEnd: StoreItem?): Observable<List<StoreItem>> {
        return homeApi.getStores(
            latLng = "${location.latitude},${location.longitude}"
            , offset = nextOffset(itemAtEnd)
            , limit = settingsRepo.networkPageSize()
        )
    }

    private fun recordRequestResult(throwable: Throwable? = null) {
        failedRequest = if (throwable != null) {
            lastRequest
        } else {
            null
        }

        lastRequest = null
    }

    fun retry() {
        if (networkState.value != NetworkState.LOADING && failedRequest != null) {
            if (failedRequest!!.model == null) {
                onZeroItemsLoaded()
            } else {
                onItemAtEndLoaded(failedRequest!!.model!!)
            }
        }
    }

    class Request(var model: StoreItem? = null, val location: Location)
}