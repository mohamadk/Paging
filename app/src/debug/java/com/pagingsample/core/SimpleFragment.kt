package com.pagingsample.core

import com.mohamadk.pagingfragment.base.BaseFragment
import com.mohamadk.pagingfragment.base.factories.PageFactory

class SimpleFragment : BaseFragment() {


    class SimplePage(val pageTag: String) :
        PageFactory(SimpleFragment::class.java) {
        override fun tag(): String? {
            return pageTag
        }
    }
}