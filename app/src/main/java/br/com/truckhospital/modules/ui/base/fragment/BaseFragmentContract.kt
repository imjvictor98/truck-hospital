package br.com.truckhospital.modules.ui.base.fragment

interface BaseFragmentContract {
    interface BaseView {}

    interface BasePresenter<T> {
        val view: T?
    }
}