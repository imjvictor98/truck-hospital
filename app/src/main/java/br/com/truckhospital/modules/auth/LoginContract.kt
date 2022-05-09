package br.com.truckhospital.modules.auth

import br.com.truckhospital.modules.base.BaseContract
import com.google.firebase.auth.PhoneAuthOptions

class LoginContract {
    interface View : BaseContract.BaseView {
        fun showCircularLoading()
        fun hideCircularLoading()
        fun showError()
        fun showVerification()
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun validatePhone(countryCode: String,
                          number: String,
                          optionsBuilder: PhoneAuthOptions.Builder
        )

        fun validateSms(sms: String)
    }
}