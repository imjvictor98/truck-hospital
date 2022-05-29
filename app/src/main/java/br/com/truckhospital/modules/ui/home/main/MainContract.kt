package br.com.truckhospital.modules.ui.home.main

import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.BaseContract

class MainContract {
    interface View: BaseContract.BaseView {
        fun goToOrder()

        fun goToSplash()
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun onCardClicked()

        fun signOut()
    }
}