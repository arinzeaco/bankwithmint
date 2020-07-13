package com.bankwithmint.util

import android.content.Context
import com.shashank.sony.fancytoastlib.FancyToast

class AppUtils {
    fun showAlertMessage(context: Context, message: String, type: Int) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, type, false).show()
    }
}
