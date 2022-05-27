package br.com.truckhospital.modules.ui.confirmation

import br.com.truckhospital.modules.ui.base.BaseContract

class ConfirmationContract {

    interface View: BaseContract.BaseView {
        fun showButton()
        fun hideButton()

        fun showLoading()
        fun hideLoading()

        fun showDialogError(message: String? = null)

        fun goToHome()
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun validateSms(verificationId: String, smsCode: String)
    }
}