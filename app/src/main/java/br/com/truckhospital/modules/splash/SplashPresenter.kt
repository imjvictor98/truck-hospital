package br.com.truckhospital.modules.splash

class SplashPresenter(override val view: SplashContract.View?) : SplashContract.Presenter {
    override fun init() {
        view?.onButtonBehavior()
    }
}