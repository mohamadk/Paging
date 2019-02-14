package com.mohamadk.pagingfragment.intractors

import com.mohamadk.middleman.intractors.BaseIntractor
import com.mohamadk.pagingfragment.base.factories.DialogPageFactory
import com.mohamadk.pagingfragment.base.factories.PageFactory

interface FragmentOpener : BaseIntractor {
    fun open(page: PageFactory)
    fun open(page: DialogPageFactory)
    fun setTitle(title: String?)
}