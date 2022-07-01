package br.com.truckhospital.modules.ui.main.home

import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract

class HomeContract {
    interface View: BaseFragmentContract.BaseView {
        fun goToSplash()

        fun setDescription(text: String)
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View> {
        fun getPhoneNumber()

        fun signOut()
    }
}