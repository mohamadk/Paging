package com.mohamadk.pagingfragment

import androidx.lifecycle.LiveData
import com.mohamadk.middleman.networkstate.NetworkState

data class Listing<T>(
    val list: LiveData<T>? = null,
    //this value represent paging state "Recyclerview loading state"
    val pagingState: LiveData<NetworkState>,
    //this value represent first fetching data or refreshing whole data
    val refreshState: LiveData<NetworkState>,
    val retry: () -> Unit
)