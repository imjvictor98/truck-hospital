package br.com.truckhospital.modules.util.extension

import android.widget.EditText
import com.redmadrobot.inputmask.MaskedTextChangedListener

fun EditText.installMask(
    mask: String,
    onValueChanged: ((maskFilled: Boolean, extracted: String, formatted: String) -> Unit)? = null
) {
    MaskedTextChangedListener.installOn(
        this,
        mask,
        object : MaskedTextChangedListener.ValueListener {
            override fun onTextChanged(
                maskFilled: Boolean,
                extractedValue: String,
                formattedValue: String
            ) {
                onValueChanged?.invoke(maskFilled, extractedValue, formattedValue)
            }
        })
}

