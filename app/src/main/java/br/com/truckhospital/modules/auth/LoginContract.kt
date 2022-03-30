package br.com.truckhospital.modules.auth

import androidx.appcompat.app.AppCompatActivity
import br.com.truckhospital.modules.base.BaseContract

class LoginContract {
    interface View : BaseContract.BaseView {
        fun showCircularLoading()
        fun hideCircularLoading()
        fun showError()
        fun showVerification()
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun validatePhone(number: String, activity: AppCompatActivity)
    }
}