package com.mohamadk.pagingfragment.core.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    /**
     * called when fragment in opened in single instance and already exist
     * an instance of the fragment
     */
    fun onNewIntent(args: Bundle?) {

    }


}