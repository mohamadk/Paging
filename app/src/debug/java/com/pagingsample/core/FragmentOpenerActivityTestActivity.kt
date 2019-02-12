package com.pagingsample.core

import android.os.Bundle
import com.pagingsample.R
import com.pagingsample.core.activity.FragmentOpenerActivity

class FragmentOpenerActivityTestActivity : FragmentOpenerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_opener_activity_test_activity)
    }

}