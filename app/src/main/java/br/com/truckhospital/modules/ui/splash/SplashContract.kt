package br.com.truckhospital.modules.ui.splash

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

interface SplashContract {
    interface View : BaseActivityContract.BaseView {
        fun onButtonBehavior()

        fun goToHome()

        fun goToLogin()
    }

    interface Presenter : BaseActivityContract.BasePresenter<View> {
        fun init()

        fun checkUser()
    }
}