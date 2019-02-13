package com.pagingsample.core.activity

import androidx.appcompat.app.AppCompatActivity
import com.mohamadk.pagingfragment.base.factories.DialogPageFactory
import com.mohamadk.pagingfragment.intractors.FragmentOpener
import com.mohamadk.pagingfragment.base.factories.PageFactory
import com.pagingsample.R

open class FragmentOpenerActivity : AppCompatActivity()
    , FragmentOpener {

    override fun open(page: PageFactory) {
        if (supportFragmentManager.findFragmentByTag(page.tag()) == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_contentLay, page.build(), page.tag()).also {
                    if (page.addToBackStack()) {
                        it.addToBackStack(page.tag())
                    }
                }
                .commit()
        }
    }

    override fun open(page: DialogPageFactory) {
        page.build().show(supportFragmentManager, page.tag())
    }

    override fun setTitle(title: String?) {
        this.title = title
    }


}