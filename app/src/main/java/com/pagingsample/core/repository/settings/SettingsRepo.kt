package com.pagingsample.core.repository.settings

interface SettingsRepo {
    fun networkPageSize(): Int
    fun getClientId(): String
    fun getClientSecret(): String


    companion object {
        const val DISTANCE_OFFSET_FOR_NEW_REQUEST = 100
    }
}

