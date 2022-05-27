package br.com.truckhospital.modules.ui.auth

import br.com.truckhospital.modules.ui.base.BaseContract
import com.google.firebase.auth.PhoneAuthOptions

class LoginContract {
    interface View : BaseContract.BaseView {
        fun showCircularLoading()
        fun hideCircularLoading()
        fun showError()
        fun showVerification(verificationId: String)
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun validatePhone(countryCode: String,
                          number: String,
                          optionsBuilder: PhoneAuthOptions.Builder
        )
    }
}