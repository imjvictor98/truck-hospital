package br.com.truckhospital.modules.util.extension

import br.com.truckhospital.modules.MainApplication
import java.text.NumberFormat

object NumberUtil {
    fun getStringFormattedByCurrency(
        fractionDigits: Int,
        what: Any?
    ): String? {
        val numberFormat = NumberFormat.getCurrencyInstance(MainApplication.localeBRL).apply {
            maximumFractionDigits = fractionDigits
        }

        val format = try {
            numberFormat.format(what)
        } catch (e: Exception) {
            null
        }

        return format
    }
}