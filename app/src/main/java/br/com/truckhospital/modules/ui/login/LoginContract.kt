package br.com.truckhospital.modules.ui.login

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract
import com.google.firebase.auth.PhoneAuthOptions

class LoginContract {
    interface View : BaseActivityContract.BaseView {
        fun showCircularLoading()
        fun hideCircularLoading()
        fun showError()
        fun showVerification(verificationId: String)
    }

    interface Presenter : BaseActivityContract.BasePresenter<View> {
        fun validatePhone(countryCode: String,
                          number: String,
                          optionsBuilder: PhoneAuthOptions.Builder
        )
    }
}