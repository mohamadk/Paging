package com.pagingsample.core.utils.location

class LocationRequestCompat(
    val interval: Long
    , val fastestInterval: Long
    , val priority: Int
) {
    companion object {
        const val PRIORITY_HIGH_ACCURACY = 100
        const val PRIORITY_BALANCED_POWER_ACCURACY = 102
        const val PRIORITY_LOW_POWER = 104
        const val PRIORITY_NO_POWER = 105
    }
}