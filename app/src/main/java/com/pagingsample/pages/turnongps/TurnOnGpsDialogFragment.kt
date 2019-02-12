package com.pagingsample.pages.turnongps

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.view.*
import com.mohamadk.pagingfragment.core.intractors.BaseDialogFragment
import com.mohamadk.pagingfragment.core.intractors.DialogPageFactory
import com.pagingsample.R
import com.pagingsample.core.utils.isGpsOn
import kotlinx.android.synthetic.main.turn_on_gps_dialog_fragment.*

class TurnOnGpsDialogFragment : BaseDialogFragment() {


    override fun onResume() {
        super.onResume()

        if (isGpsOn(activity!!)) {
            dismiss()
        }

        val window = dialog!!.window
        val size = Point()

        val display = window.windowManager.defaultDisplay
        display.getSize(size)
        window.setLayout((size.x * 0.75).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.turn_on_gps_dialog_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_ok.setOnClickListener {
            startActivity(Intent(ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    class TurnOnGpsPage : DialogPageFactory(TurnOnGpsDialogFragment::class.java)

}