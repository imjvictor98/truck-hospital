package br.com.truckhospital.modules.util

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText


object PhoneMaskUtil {
    private const val mask10 = "(##) ####-####"
    private const val mask11 = "(##) #####-####"

    fun unmask(s: String?): String {
        return if (s != null && !TextUtils.isEmpty(s)) {
            s.replace("[^0-9]*".toRegex(), "")
        } else ""
    }

    @JvmOverloads
    fun build(editText: EditText, watcher: TextWatcher? = null): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                watcher?.beforeTextChanged(s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mask(s)
                watcher?.onTextChanged(s, start, before, count)
            }

            override fun afterTextChanged(s: Editable) {
                watcher?.afterTextChanged(s)
            }

            fun mask(s: CharSequence) {
                val str = unmask(s.toString())
                val mask: String
                val defaultMask = getDefaultMask(str)
                mask = when (str.length) {
                    11 -> mask11
                    10 -> mask10
                    else -> defaultMask
                }
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length && str.length != i) {
                        mascara += m
                        continue
                    }
                    mascara += try {
                        str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }
        }
    }

    private fun getDefaultMask(str: String): String {
        var defaultMask = mask10
        if (str.length > 11) {
            defaultMask = mask11
        }
        return defaultMask
    }

    fun isPhoneValid(phone: String): Boolean {
        var valid = true
        if (phone.length < 14) {
            valid = false
        }
        return valid
    }
}