package br.com.truckhospital.modules.ui.home.main

import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.BaseContract

class MainContract {
    interface View: BaseContract.BaseView {
        fun foo()
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun foo(list: List<Order>)
    }
}