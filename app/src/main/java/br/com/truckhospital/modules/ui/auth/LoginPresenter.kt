package br.com.truckhospital.modules.ui.auth

import br.com.truckhospital.modules.util.PhoneMaskUtil
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import timber.log.Timber

class LoginPresenter(override val view: LoginContract.View?) : LoginContract.Presenter {

    override fun validatePhone(
        countryCode: String,
        number: String,
        optionsBuilder: PhoneAuthOptions.Builder
    ) {
        view?.showCircularLoading()

        if (PhoneMaskUtil.isPhoneValid(number)) {
            val phoneNumber = countryCode.plus(PhoneMaskUtil.unmask(number))
            val options = optionsBuilder
                .setPhoneNumber(phoneNumber)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(authCredential: PhoneAuthCredential) {
                        Timber.d("onVerificationCompleted: %s", authCredential.smsCode)
                        view?.hideCircularLoading()
                    }

                    override fun onVerificationFailed(exception: FirebaseException) {
                        Timber.d("onVerificationFailed: %s", exception)
                        view?.hideCircularLoading()
                        view?.showError()
                    }

                    override fun onCodeSent(
                        code: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
                        Timber.d("onCodeSent: %s", code)
                        super.onCodeSent(code, token)
                        view?.hideCircularLoading()
                        view?.showVerification(code)
                    }
                }).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        } else {
            view?.hideCircularLoading()
            view?.showError()
        }
    }
}