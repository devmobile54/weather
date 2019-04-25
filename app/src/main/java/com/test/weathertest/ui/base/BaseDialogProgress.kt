package com.test.weathertest.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import com.test.weathertest.R

class BaseDialogProgress(context: Context) : Dialog(context) {

    init {
        setCancelable(false)
        setContentView(R.layout.dialog_progress)

        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setGravity(Gravity.CENTER)
            it.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }

}