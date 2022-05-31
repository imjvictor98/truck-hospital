package br.com.truckhospital.modules.ui.splash

import br.com.truckhospital.modules.util.FirebaseAuthHelper

class SplashPresenter(override val view: SplashContract.View?) : SplashContract.Presenter {
    override fun checkUser() {
        view?.showLoading()
        FirebaseAuthHelper.userAuth.currentUser?.getIdToken(true)
            ?.addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    view?.hideLoading()
                    view?.goToHome()
                } else {
                    view?.hideLoading()
                    view?.goToLogin()
                }
            } ?: run {
                view?.hideLoading()
                view?.goToLogin()
        }
    }
}