package br.com.truckhospital.modules.util.extension

import android.widget.EditText
import com.redmadrobot.inputmask.MaskedTextChangedListener

fun EditText.installMask(mask: String, valueListener: MaskedTextChangedListener.ValueListener? = null) {
    MaskedTextChangedListener.installOn(this, mask, valueListener)
}

