package br.com.truckhospital.modules.util.extension

import android.text.InputFilter
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

fun EditText.addDecimalInputFilter(decimalDigits: Int) {
    val decimalInputFilter = InputFilter { source, _, _, dest, _, dEnd ->
        var dotPos = -1
        val len = dest.length
        for (i in 0 until len) {
            val c = dest[i]
            if (c == '.' || c == ',') {
                dotPos = i
                break
            }
        }
        if (dotPos >= 0) {

            // protects against many dots
            if (source == "." || source == ",") {
                return@InputFilter ""
            }
            // if the text is entered before the dot
            if (dEnd <= dotPos) {
                return@InputFilter null
            }
            if (len - dotPos > decimalDigits) {
                return@InputFilter ""
            }
        }
        return@InputFilter null
    }

    this.filters += decimalInputFilter
}
