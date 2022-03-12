package br.com.truckhospital.modules.splash

import br.com.truckhospital.modules.base.BaseContract

interface SplashContract {
    interface View : BaseContract.BaseView {
        fun onButtonBehavior()
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun init()
    }
}