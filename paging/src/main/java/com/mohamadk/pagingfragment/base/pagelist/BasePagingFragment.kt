package com.mohamadk.pagingfragment.base.pagelist

import androidx.paging.PagedList
import com.mohamadk.middleman.AdapterProvider
import com.mohamadk.middleman.adapter.GeneralPagingViewAdapter
import com.mohamadk.middleman.model.BaseModel
import com.mohamadk.pagingfragment.base.list.BaseListFragment
import com.mohamadk.pagingfragment.base.list.BaseListFragmentViewModel

abstract class BasePagingFragment<VIEW_MODEL : BaseListFragmentViewModel<PagedList<BaseModel>>>
    : BaseListFragment<PagedList<BaseModel>, VIEW_MODEL>() {

    override fun provideAdapter(): AdapterProvider<PagedList<BaseModel>> {
        return GeneralPagingViewAdapter(intractor = this)
    }

    override fun submitList(it: PagedList<BaseModel>?) {
        adapterProvider.submitList(it)
    }
}