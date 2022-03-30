package br.com.truckhospital.modules.util

import android.content.Context
import android.content.DialogInterface
import android.view.View
import br.com.truckhospital.R
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView

object DialogUtil {
    @JvmOverloads
    @JvmStatic
    fun showDialog(
        context: Context,
        title: CharSequence? = null,
        message: CharSequence? = null,
        positiveText: CharSequence? = null,
        positiveCallback: DialogInterface.OnClickListener? = null,
        negativeText: CharSequence? = null,
        negativeCallback: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = true,
        view: View? = null,
        wrapInScrollView: Boolean = false
    ): MaterialDialog {

        return MaterialDialog(context).show {
            title?.run {
                title(text = this.toString())
            }
            message?.run {
                message(text = this)
            }
            view?.run {
                customView(view = view, scrollable = wrapInScrollView)
            }
            positiveText?.run {
                positiveButton(text = this, res = R.string.dialog_default_error_btn) {
                    positiveCallback?.onClick(it, DialogInterface.BUTTON_POSITIVE)
                }
            }
            negativeText?.run {
                negativeButton(text = negativeText) {
                    negativeCallback?.onClick(it, DialogInterface.BUTTON_NEGATIVE)
                }
            }
            cancelable(cancelable)
        }
    }
}
