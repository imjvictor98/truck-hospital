package br.com.truckhospital.modules.base

interface BaseContract {
    interface BaseView
    interface BasePresenter<T> {
        val view: T?
    }
}
