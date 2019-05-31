package com.pagingsample.pages.home

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.location.Location
import android.os.Build
import com.mohamadk.pagingfragment.base.pagelist.BasePagingFragment
import com.mohamadk.pagingfragment.base.factories.PageFactory
import com.pagingsample.MainActivity
import com.pagingsample.R
import com.pagingsample.core.repository.settings.SettingsRepo.Companion.DISTANCE_OFFSET_FOR_NEW_REQUEST
import com.pagingsample.core.utils.haveLocationPermission
import com.pagingsample.core.utils.isGpsOn
import com.pagingsample.core.utils.location.LocationManagerCompat
import com.pagingsample.pages.turnongps.TurnOnGpsDialogFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BasePagingFragment<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()
    private val locationManager: LocationManagerCompat by inject()
    private var lastLocation: Location? = null

    private val locationCallback: LocationManagerCompat.LocationListenerCompat =
        object : LocationManagerCompat.LocationListenerCompat {
            override fun onLocationUpdated(location: Location) {
                if (isNewLocation(location)) {
                    loadItems(location)
                }
            }
        }


    override fun onResume() {
        super.onResume()
        setTitle(getString(R.string.home_page))
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (haveLocationPermission(activity!!)) {
            if (isGpsOn(activity!!)) {
                locationManager.requestLocationUpdates(locationCallback)
            } else {
                open(TurnOnGpsDialogFragment.TurnOnGpsPage())
            }
        } else {
            grantLocationPermission()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun grantLocationPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.ACCESS_COARSE_LOCATION

            ),
            MainActivity.REQUEST_LOCATION_PERMISSION
        )
    }

    private fun stopLocationUpdates() {
        locationManager.removeLocationUpdates()
    }

    private fun isNewLocation(location: Location): Boolean {
        return if (lastLocation == null ||
            location.distanceTo(lastLocation) > DISTANCE_OFFSET_FOR_NEW_REQUEST
        ) {
            lastLocation = location
            true
        } else {
            false
        }

    }

    class HomePage : PageFactory(HomeFragment::class.java) {
        override fun addToBackStack(): Boolean {
            return false
        }

        override fun tag(): String? {
            return this::class.java.name
        }
    }
}
