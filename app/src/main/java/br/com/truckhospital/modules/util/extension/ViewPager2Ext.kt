package br.com.truckhospital.modules.util.extension

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.onPageSelected(onSelectedPage: (position: Int) -> Unit) {
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            onSelectedPage.invoke(position)
            super.onPageSelected(position)
        }
    })
}