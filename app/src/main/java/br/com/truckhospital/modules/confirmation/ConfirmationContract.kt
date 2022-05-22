package br.com.truckhospital.modules.confirmation

import br.com.truckhospital.modules.base.BaseContract

class ConfirmationContract {

    interface View: BaseContract.BaseView {
        fun showButton()
        fun hideButton()

        fun showLoading()
        fun hideLoading()

        fun showDialogError(message: String? = null)
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun validateSms(verificationId: String, smsCode: String)
    }
}