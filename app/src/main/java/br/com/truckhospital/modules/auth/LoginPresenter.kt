package br.com.truckhospital.modules.auth

import androidx.appcompat.app.AppCompatActivity
import br.com.truckhospital.modules.util.PhoneMaskUtil
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LoginPresenter(override val view: LoginContract.View?) : LoginContract.Presenter {

    override fun validatePhone(number: String, activity: AppCompatActivity) {
        view?.showCircularLoading()

        if (PhoneMaskUtil.isPhoneValid(number)) {
            val phoneNumber = PhoneMaskUtil.unmask(number)

            val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
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

                    override fun onCodeSent(code: String, token: PhoneAuthProvider.ForceResendingToken) {
                        super.onCodeSent(code, token)
                        view?.showVerification()
                    }

                })
                view?.showVerification()

            //chamar o firebase auth
        } else {
            view?.hideCircularLoading()
            view?.showError()
        }
    }
}