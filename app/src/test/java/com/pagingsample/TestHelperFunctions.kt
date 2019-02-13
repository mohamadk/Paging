package com.pagingsample

import com.mohamadk.pagingfragment.base.factories.DialogPageFactory
import com.mohamadk.pagingfragment.base.factories.PageFactory
import com.pagingsample.core.SimpleDialogFragment
import com.pagingsample.core.SimpleFragment


fun createNewPage(tag: String): PageFactory {
    return SimpleFragment.SimplePage(tag)
}


fun createNewDialogPage(tag: String): DialogPageFactory {
    return SimpleDialogFragment.SimpleDialogPage(tag)
}