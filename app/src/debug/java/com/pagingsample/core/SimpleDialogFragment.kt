package com.pagingsample.core

import com.mohamadk.pagingfragment.core.intractors.BaseDialogFragment
import com.mohamadk.pagingfragment.core.intractors.DialogPageFactory

class SimpleDialogFragment : BaseDialogFragment() {

    class SimpleDialogPage(val pageTag: String) : DialogPageFactory(SimpleDialogFragment::class.java) {
        override fun tag(): String? {
            return pageTag
        }
    }

}