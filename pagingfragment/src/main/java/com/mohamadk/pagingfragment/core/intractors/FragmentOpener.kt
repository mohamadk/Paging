package com.mohamadk.pagingfragment.core.intractors

import com.mohamadk.middleman.intractors.BaseIntractor

interface FragmentOpener : BaseIntractor {
    fun open(page: PageFactory)
    fun open(page: DialogPageFactory)
    fun setTitle(title: String?)
}