package br.com.truckhospital.modules.util.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <reified T: FragmentActivity>Fragment.requireOwnerActivity(): T? {
    return if (requireActivity() is T) {
        activity as T
    } else {
        null
    }
}