package br.com.truckhospital.modules.ui.base.activity

interface BaseActivityContract {
    interface BaseView {}

    interface BasePresenter<T> {
        val view: T?
    }
}
