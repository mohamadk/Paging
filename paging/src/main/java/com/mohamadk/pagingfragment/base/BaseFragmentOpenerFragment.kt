package com.mohamadk.pagingfragment.base

import android.content.Context
import com.mohamadk.pagingfragment.base.factories.DialogPageFactory
import com.mohamadk.pagingfragment.intractors.FragmentOpener
import com.mohamadk.pagingfragment.base.factories.PageFactory

abstract class BaseFragmentOpenerFragment :
    BaseFragment()
    , FragmentOpener {
    open var titlePage: String? = null
    var listener: FragmentOpener? = null

    override fun onResume() {
        super.onResume()
        listener!!.setTitle(titlePage)
    }

    override fun open(page: PageFactory) {
        listener!!.open(page)
    }

    override fun open(page: DialogPageFactory) {
        listener!!.open(page)
    }

    override fun setTitle(title: String?) {
        listener!!.setTitle(title)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentOpener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FragmentOpener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}