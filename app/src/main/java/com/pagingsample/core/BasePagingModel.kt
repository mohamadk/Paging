package com.pagingsample.core

import com.mohamadk.middleman.model.BaseModel

interface BasePagingModel : BaseModel {

    val totalItemsCount: Int
    var itemIdInPage: Int

}