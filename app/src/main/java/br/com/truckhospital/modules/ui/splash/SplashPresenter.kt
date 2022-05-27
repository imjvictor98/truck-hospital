package br.com.truckhospital.modules.ui.splash

import br.com.truckhospital.modules.util.FirebaseAuthHelper

class SplashPresenter(override val view: SplashContract.View?) : SplashContract.Presenter {
    override fun init() {
        view?.onButtonBehavior()
    }

    override fun checkUser() {
        FirebaseAuthHelper.userAuth.currentUser?.getIdToken(true)
            ?.addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    view?.goToHome()
                } else {
                    view?.goToLogin()
                }
            } ?: run {
                view?.goToLogin()
        }
    }
}