package com.pagingsample.core

import com.mohamadk.pagingfragment.base.BaseDialogFragment
import com.mohamadk.pagingfragment.base.factories.DialogPageFactory

class SimpleDialogFragment : BaseDialogFragment() {

    class SimpleDialogPage(val pageTag: String) : DialogPageFactory(SimpleDialogFragment::class.java) {
        override fun tag(): String? {
            return pageTag
        }
    }

}