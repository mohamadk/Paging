package com.pagingsample.pages.home.repo

import android.location.Location
import androidx.paging.PagedList
import com.mohamadk.middleman.model.BaseModel
import com.mohamadk.pagingfragment.core.Listing

interface HomeRepository {

    fun loadPage(location: Location): Listing<PagedList<BaseModel>>

}