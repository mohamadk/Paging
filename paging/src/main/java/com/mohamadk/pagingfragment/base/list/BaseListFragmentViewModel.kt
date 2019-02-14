package com.mohamadk.pagingfragment.base.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mohamadk.pagingfragment.Listing

abstract class BaseListFragmentViewModel<LIST_MODEL> : ViewModel() {

    val repoResult = MutableLiveData<Listing<LIST_MODEL>>()

    val items = Transformations.switchMap(repoResult) {
        it.list
    }!!

    val networkState = Transformations.switchMap(repoResult) {
        it.pagingState
    }!!

    val refreshState = Transformations.switchMap(repoResult) {
        it.refreshState
    }!!

    fun retry(vararg args: Any) {
        getListingPage(*args).retry()
    }

    open fun loadItems(vararg args: Any) {
        repoResult.postValue(getListingPage(*args))
    }

    abstract fun getListingPage(vararg args: Any): Listing<LIST_MODEL>


}