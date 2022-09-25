package br.com.truckhospital.modules.ui.confirmation


import br.com.truckhospital.modules.util.FirebaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import timber.log.Timber

class ConfirmationPresenter(override val view: ConfirmationContract.View?) :
    ConfirmationContract.Presenter {
    override fun validateSms(verificationId: String, smsCode: String) {
        val credential = FirebaseHelper.getCredentialSMS(verificationId, smsCode)

        view?.showLoading()
        view?.hideButton()

        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                view?.hideLoading()
                view?.showButton()

                it.result?.user?.run {
                    Timber.d("Confirmation SMS is successfull")
                    view?.goToHome()
                }

            } else {
                Timber.e(it.exception, "signInWithCredential Error")
                if (it.exception is FirebaseAuthInvalidCredentialsException) {
                    Timber.d("Confirmation SMS is errored")
                    view?.showDialogError("SMS informado é inválido")
                    view?.hideLoading()
                    view?.showButton()
                }
            }
        }
    }

}