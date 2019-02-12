package com.pagingsample.core.utils.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.*

class LocationManagerPlay110600(
    context: Context
    , private val locationRequestCompat: LocationRequestCompat
) : LocationManagerCompat {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private var locationListenerCompat: LocationManagerCompat.LocationListenerCompat? = null


    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            locationListenerCompat?.onLocationUpdated(locationResult.lastLocation)
        }
    }

    override fun removeLocationUpdates() {
        this.locationListenerCompat = null
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun requestLocationUpdates(locationListenerCompat: LocationManagerCompat.LocationListenerCompat) {
        this.locationListenerCompat = locationListenerCompat

        val locationRequest = LocationRequest().apply {
            interval = locationRequestCompat.interval
            fastestInterval = locationRequestCompat.fastestInterval
            priority = locationRequestCompat.priority
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }
}