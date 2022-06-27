package br.com.truckhospital.modules.util.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showSnackBar(message: String, isLongDuration: Boolean = true) {
    Snackbar.make(this, message, if (isLongDuration) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT).show()
}