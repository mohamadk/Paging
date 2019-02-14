package com.mohamadk.pagingfragment.base.factories

import android.os.Bundle
import com.mohamadk.pagingfragment.base.BaseFragment

abstract class PageFactory(val fragmentClass: Class<*>) {

    fun build(): BaseFragment {
        return (fragmentClass.newInstance() as BaseFragment)
            .apply {
                this.arguments = args()
            }
    }

    open fun tag(): String? {
        return null
    }

    open fun addToBackStack(): Boolean {
        return true
    }

    open fun replace(): Boolean {
        return true
    }

    open fun singleInstance(): Boolean {
        return false
    }

    open fun args(): Bundle? {
        return null
    }


}