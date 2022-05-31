package br.com.truckhospital.modules.ui.confirmation

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class ConfirmationContract {

    interface View: BaseActivityContract.BaseView {
        fun showButton()
        fun hideButton()

        fun showLoading()
        fun hideLoading()

        fun showDialogError(message: String? = null)

        fun goToHome()
    }

    interface Presenter: BaseActivityContract.BasePresenter<View> {
        fun validateSms(verificationId: String, smsCode: String)
    }
}