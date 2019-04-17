package com.mohamadk.pagingfragment.base.pagelist

import androidx.lifecycle.Transformations
import com.mohamadk.pagingfragment.base.list.BaseListFragmentViewModel

abstract class BasePagingViewModel<LIST_MODEL>() : BaseListFragmentViewModel<LIST_MODEL>() {

    open val pagingState = Transformations.switchMap(repoResult) {
        it.pagingState
    }!!
}