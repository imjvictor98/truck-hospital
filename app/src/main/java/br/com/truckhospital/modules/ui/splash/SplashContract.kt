package br.com.truckhospital.modules.ui.splash

import br.com.truckhospital.modules.ui.base.BaseContract

interface SplashContract {
    interface View : BaseContract.BaseView {
        fun onButtonBehavior()

        fun goToHome()

        fun goToLogin()
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun init()

        fun checkUser()
    }
}