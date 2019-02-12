package com.pagingsample.pages.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.mohamadk.pagingfragment.core.base.BaseFragmentOpenerFragment
import com.mohamadk.pagingfragment.core.intractors.PageFactory
import com.pagingsample.R
import com.pagingsample.core.imgloader.GlideApp
import com.pagingsample.pages.detail.DetailFragment.DetailPage.Companion.PARAM_STORE
import com.pagingsample.pages.home.items.StoreItem
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : BaseFragmentOpenerFragment() {
    lateinit var storeItem: StoreItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storeItem = arguments!!.getParcelable(PARAM_STORE)!!
    }

    override fun onResume() {
        super.onResume()
        setTitle(getString(R.string.detail))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_title.text = storeItem.name
        tv_distance.text = storeItem.distanceText()
        tv_address.text = storeItem.address

        GlideApp.with(this)
            .load(storeItem.categoryIcon)
            .placeholder(ColorDrawable(Color.parseColor("#ff0000")))
            .into(iv_catLogo)

    }

    class DetailPage(private val storeItem: StoreItem) : PageFactory(DetailFragment::class.java) {
        override fun args(): Bundle? {
            return bundleOf(Pair(PARAM_STORE, storeItem))
        }

        override fun tag(): String? {
            return this::class.java.name
        }

        companion object {
            const val PARAM_STORE = "st"
        }
    }


}