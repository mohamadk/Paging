package com.pagingsample.core

import com.mohamadk.pagingfragment.core.intractors.DialogPageFactory
import com.mohamadk.pagingfragment.core.intractors.PageFactory
import junit.framework.TestCase.assertNotNull


class FragmentOpenerActivityRobot(private val setupActivity: FragmentOpenerActivityTestActivity) {

    fun loadPage(page: PageFactory): FragmentOpenerActivityRobot {
        setupActivity.open(page)
        return this
    }

    fun checkExistingPage(tag: String): FragmentOpenerActivityRobot {

        assertNotNull(
            "fragment with tag:$tag doesn't exist"
            , setupActivity.supportFragmentManager.findFragmentByTag(tag)
        )
        return this
    }

    fun loadPage(page: DialogPageFactory): FragmentOpenerActivityRobot {

        setupActivity.open(page)

        return this
    }

}
