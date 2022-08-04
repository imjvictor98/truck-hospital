package br.com.truckhospital.modules.util.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.truckhospital.R

inline fun <reified T: FragmentActivity>Fragment.getParentActivity(): T? {
    return if (requireActivity() is T) {
        activity as T
    } else {
        null
    }
}

fun FragmentActivity.getNavController(@IdRes id: Int): NavController? {
    val navHostFragment = this.supportFragmentManager.findFragmentById(id) as? NavHostFragment
    return navHostFragment?.navController
}