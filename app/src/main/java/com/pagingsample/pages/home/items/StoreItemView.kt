package com.pagingsample.pages.home.items

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import com.mohamadk.middleman.Binder
import com.mohamadk.middleman.intractors.RequireInteractor
import com.mohamadk.pagingfragment.core.intractors.FragmentOpener
import com.pagingsample.R
import com.pagingsample.core.imgloader.GlideApp
import com.pagingsample.pages.detail.DetailFragment
import kotlinx.android.synthetic.main.item_store_view.view.*

class StoreItemView
@JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttribute: Int = 0) :
    MaterialCardView(context, attributeSet, defStyleAttribute)
    , Binder<StoreItem>
    , RequireInteractor<FragmentOpener> {

    lateinit var fragmentOpener: FragmentOpener
    val colorsList = resources.getIntArray(R.array.colorsList)

    override fun setInteractor(intractor: FragmentOpener) {
        this.fragmentOpener = intractor
    }

    init {
        View.inflate(context, R.layout.item_store_view, this)
        layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        useCompatPadding = true
    }

    override fun bind(item: StoreItem?) {
        tv_title.text = item!!.name
        tv_distance.text = item.distanceText()
        tv_address.text = item.address

        GlideApp.with(this)
            .load(item.categoryIcon)
            .into(iv_categoryLogo)

        setCardBackgroundColor(colorsList[item.itemIdInPage % colorsList.size])

        setOnClickListener {
            fragmentOpener.open(DetailFragment.DetailPage(item))
        }
    }


}