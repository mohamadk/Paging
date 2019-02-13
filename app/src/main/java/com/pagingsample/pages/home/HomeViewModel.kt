package com.pagingsample.pages.home

import android.location.Location
import androidx.paging.PagedList
import com.mohamadk.middleman.model.BaseModel
import com.mohamadk.pagingfragment.Listing
import com.mohamadk.pagingfragment.base.pagelist.BasePagingViewModel
import com.pagingsample.pages.home.repo.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : BasePagingViewModel<PagedList<BaseModel>>() {

    override fun getListingPage(vararg args: Any): Listing<PagedList<BaseModel>> {
        val location: Location = args[0] as Location
        return homeRepository.loadPage(location)
    }

}
