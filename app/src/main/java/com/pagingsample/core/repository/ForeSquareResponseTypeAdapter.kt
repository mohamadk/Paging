package com.pagingsample.core.repository

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.pagingsample.pages.home.items.StoreItem
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ForeSquareResponseTypeAdapter : JsonDeserializer<List<*>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<*> {
        val type = (typeOfT as ParameterizedType).actualTypeArguments[0]

        val result = mutableListOf<Any>()

        if (type == StoreItem::class.java) {
            with(json!!.asJsonObject) {

                val response =
                    getAsJsonObject("response")

                val totalResults = response.get("totalResults").asInt

                val items = response
                    .getAsJsonArray("groups")[0].asJsonObject
                    .getAsJsonArray("items")
                for (storeJson in items) {
                    val venue = storeJson.asJsonObject.get("venue").asJsonObject
                    result.add(
                        StoreItem(
                            item_id = venue.get("id").asString,
                            name = venue.get("name").asString,
                            distance = venue.get("location").asJsonObject.get("distance").asInt,
                            category = venue.get("categories").asJsonArray[0].asJsonObject.get("name").asString,
                            categoryIcon = venue.get("categories").asJsonArray[0].asJsonObject.get("icon").asJsonObject.let {
                                it.get("prefix").asString + "88" + it.get(
                                    "suffix"
                                ).asString
                            },
                            address = venue.get("location").asJsonObject.get("formattedAddress").asJsonArray.let {
                                var res = ""
                                for (jsonElement in it) {
                                    res += jsonElement.asString
                                }
                                res
                            },
                            itemIdInPage = result.size,
                            totalItemsCount = totalResults
                        )
                    )
                }

            }
        }

        return result.toList()
    }
}