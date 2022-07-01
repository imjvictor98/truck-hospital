package br.com.truckhospital.modules.ui.main

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class MainContract {
    interface View: BaseActivityContract.BaseView {
        fun createOrder()
    }

    interface Presenter: BaseActivityContract.BasePresenter<View> {

    }
}