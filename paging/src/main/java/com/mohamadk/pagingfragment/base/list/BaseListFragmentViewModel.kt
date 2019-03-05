package com.mohamadk.pagingfragment.base.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mohamadk.pagingfragment.Listing

abstract class BaseListFragmentViewModel<LIST_MODEL> : ViewModel() {

    open val repoResult = MutableLiveData<Listing<LIST_MODEL>>()

    open val items = Transformations.switchMap(repoResult) {
        it.list
    }!!

    open val networkState = Transformations.switchMap(repoResult) {
        it.pagingState
    }!!

    open val refreshState = Transformations.switchMap(repoResult) {
        it.refreshState
    }!!

    open fun retry(vararg args: Any) {
        getListingPage(*args).retry()
    }

    open fun loadItems(vararg args: Any) {
        repoResult.postValue(getListingPage(*args))
    }

    open fun getListingPage(vararg args: Any): Listing<LIST_MODEL> {
        //this never going to happen
        return null as Listing<LIST_MODEL>
    }

}