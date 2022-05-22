package br.com.truckhospital.modules.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackBarUtil {
    fun View.showSnackBar(message: String, isLongDuration: Boolean = true) {
        Snackbar.make(this, message, if (isLongDuration) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT).show()
    }
}