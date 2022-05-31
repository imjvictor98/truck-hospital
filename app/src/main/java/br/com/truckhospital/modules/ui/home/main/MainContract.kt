package br.com.truckhospital.modules.ui.home.main

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class MainContract {
    interface View: BaseActivityContract.BaseView {
        fun goToOrder()

        fun goToSplash()
    }

    interface Presenter: BaseActivityContract.BasePresenter<View> {
        fun onCardClicked()

        fun signOut()
    }
}