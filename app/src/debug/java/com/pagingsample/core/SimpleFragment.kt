package com.pagingsample.core

import com.mohamadk.pagingfragment.core.base.BaseFragment
import com.mohamadk.pagingfragment.core.intractors.PageFactory

class SimpleFragment : BaseFragment() {


    class SimplePage(val pageTag: String) :
        PageFactory(SimpleFragment::class.java) {
        override fun tag(): String? {
            return pageTag
        }
    }
}