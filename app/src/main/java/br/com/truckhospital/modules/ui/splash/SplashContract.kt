package br.com.truckhospital.modules.ui.splash

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

interface SplashContract {
    interface View : BaseActivityContract.BaseView {
        fun goToHome()

        fun goToLogin()

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter : BaseActivityContract.BasePresenter<View> {
        fun checkUser()
    }
}