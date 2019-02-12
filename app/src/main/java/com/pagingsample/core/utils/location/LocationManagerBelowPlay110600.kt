package com.pagingsample.core.utils.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import com.pagingsample.core.repository.settings.SettingsRepo.Companion.DISTANCE_OFFSET_FOR_NEW_REQUEST
import com.pagingsample.core.utils.location.LocationRequestCompat.Companion.PRIORITY_BALANCED_POWER_ACCURACY
import com.pagingsample.core.utils.location.LocationRequestCompat.Companion.PRIORITY_HIGH_ACCURACY
import com.pagingsample.core.utils.location.LocationRequestCompat.Companion.PRIORITY_LOW_POWER
import com.pagingsample.core.utils.location.LocationRequestCompat.Companion.PRIORITY_NO_POWER

class LocationManagerBelowPlay110600(
    context: Context
    , private val locationRequestCompat: LocationRequestCompat
) : LocationManagerCompat
    , LocationListener {

    private var locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private var locationListenerCompat: LocationManagerCompat.LocationListenerCompat? = null


    @SuppressLint("MissingPermission")
    override fun requestLocationUpdates(locationListenerCompat: LocationManagerCompat.LocationListenerCompat) {
        this.locationListenerCompat = locationListenerCompat

        locationManager.requestLocationUpdates(
            locationRequestCompat.interval
            , DISTANCE_OFFSET_FOR_NEW_REQUEST.toFloat()
            , provideCriteria(locationRequestCompat.priority)
            , this
            , Looper.getMainLooper()
        )

    }

    private fun provideCriteria(priority: Int): Criteria {
        val criteria = Criteria()
        when (priority) {
            PRIORITY_HIGH_ACCURACY -> {
                criteria.accuracy = Criteria.ACCURACY_FINE
                criteria.horizontalAccuracy = Criteria.ACCURACY_HIGH
                criteria.verticalAccuracy = Criteria.ACCURACY_HIGH
                criteria.bearingAccuracy = Criteria.ACCURACY_HIGH
                criteria.speedAccuracy = Criteria.ACCURACY_HIGH
                criteria.powerRequirement = Criteria.POWER_HIGH
            }
            PRIORITY_BALANCED_POWER_ACCURACY -> {
                criteria.accuracy = Criteria.ACCURACY_COARSE
                criteria.horizontalAccuracy = Criteria.ACCURACY_MEDIUM
                criteria.verticalAccuracy = Criteria.ACCURACY_MEDIUM
                criteria.bearingAccuracy = Criteria.ACCURACY_MEDIUM
                criteria.speedAccuracy = Criteria.ACCURACY_MEDIUM
                criteria.powerRequirement = Criteria.POWER_MEDIUM
            }
            PRIORITY_LOW_POWER, PRIORITY_NO_POWER -> {
                criteria.accuracy = Criteria.ACCURACY_COARSE
                criteria.horizontalAccuracy = Criteria.ACCURACY_LOW
                criteria.verticalAccuracy = Criteria.ACCURACY_LOW
                criteria.bearingAccuracy = Criteria.ACCURACY_LOW
                criteria.speedAccuracy = Criteria.ACCURACY_LOW
                criteria.powerRequirement = Criteria.POWER_LOW
            }
            else -> {
                criteria.accuracy = Criteria.ACCURACY_COARSE
                criteria.horizontalAccuracy = Criteria.ACCURACY_LOW
                criteria.verticalAccuracy = Criteria.ACCURACY_LOW
                criteria.bearingAccuracy = Criteria.ACCURACY_LOW
                criteria.speedAccuracy = Criteria.ACCURACY_LOW
                criteria.powerRequirement = Criteria.POWER_LOW
            }
        }
        return criteria
    }

    override fun removeLocationUpdates() {
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        locationListenerCompat?.onLocationUpdated(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

}