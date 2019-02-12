package com.mohamadk.pagingfragment.core

import androidx.lifecycle.LiveData
import com.mohamadk.middleman.networkstate.NetworkState

data class Listing<T>(
    val list: LiveData<T>? = null,
    val networkState: LiveData<NetworkState>,
    //this value represent first fetching data or refreshing whole data
    val refreshState: LiveData<NetworkState>,
    val retry: () -> Unit
)