package com.mohamadk.pagingfragment.core.intractors

import android.os.Bundle

abstract class DialogPageFactory(val fragmentClass: Class<*>) {
    fun build(): BaseDialogFragment {
        return (fragmentClass.newInstance() as BaseDialogFragment)
            .apply {
                this.arguments = args()
            }
    }

    open fun tag(): String? {
        return fragmentClass.name
    }

    open fun addToBackStack(): Boolean {
        return true
    }

    open fun singleInstance(): Boolean {
        return false
    }

    open fun args(): Bundle? {
        return null
    }
}