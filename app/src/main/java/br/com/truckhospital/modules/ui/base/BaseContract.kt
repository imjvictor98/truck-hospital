package br.com.truckhospital.modules.ui.base

interface BaseContract {
    interface BaseView
    interface BasePresenter<T> {
        val view: T?
    }
}
