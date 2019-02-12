package com.pagingsample

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.pagingsample.core.activity.FragmentOpenerActivity
import com.pagingsample.core.utils.haveLocationPermission
import com.pagingsample.core.utils.isGpsOn
import com.pagingsample.pages.home.HomeFragment
import com.pagingsample.pages.turnongps.TurnOnGpsDialogFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : FragmentOpenerActivity() {


    override fun onResume() {
        super.onResume()
        start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tb_toolbar)
    }

    private fun start() {
        if (haveLocationPermission(this)) {
            if (isGpsOn(this)) {
                open(HomeFragment.HomePage())
            } else {
                open(TurnOnGpsDialogFragment.TurnOnGpsPage())
            }
        } else {
            grantLocationPermision()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun grantLocationPermision() {
        requestPermissions(
            arrayOf(
                ACCESS_FINE_LOCATION
                , ACCESS_COARSE_LOCATION
            )
            , REQUEST_LOCATION_PERMISSION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            start()
        }
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 2104
    }
}
