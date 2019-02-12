package com.pagingsample.pages.home.items

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohamadk.middleman.model.BaseModel
import com.pagingsample.core.BasePagingModel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Stores")
@Parcelize
data class StoreItem(
    @PrimaryKey
    val item_id: String
    , val name: String
    , val distance: Int
    , val category: String
    , val categoryIcon: String
    , val address: String

    , var nearByLatitude: Double = 0.0
    , var nearByLongitude: Double = 0.0
    , override val totalItemsCount: Int = 0
    , override var itemIdInPage: Int = 0


) : BasePagingModel, Parcelable {

    override fun areContentsTheSame(newItem: BaseModel): Boolean {
        return item_id == (newItem as StoreItem).item_id
    }

    override fun areItemsTheSame(newItem: BaseModel): Boolean {
        return item_id == (newItem as StoreItem).item_id
    }

    override fun defaultResLayout(position: Int): Int? {
        return null
    }

    override fun defaultViewClass(position: Int): Class<*>? {
        return StoreItemView::class.java
    }

    fun distanceText(): String {
        return "$distance m"
    }
}