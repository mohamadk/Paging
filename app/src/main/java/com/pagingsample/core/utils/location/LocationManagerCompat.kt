package com.pagingsample.core.utils.location

import android.location.Location

interface LocationManagerCompat {

    fun requestLocationUpdates(locationListenerCompat: LocationListenerCompat)

    fun removeLocationUpdates()

    interface LocationListenerCompat {
        fun onLocationUpdated(location: Location)
    }
}


