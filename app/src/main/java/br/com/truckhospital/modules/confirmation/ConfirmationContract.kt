package br.com.truckhospital.modules.confirmation

import br.com.truckhospital.modules.base.BaseContract

class ConfirmationContract {

    interface View: BaseContract.BaseView {

    }

    interface Presenter: BaseContract.BasePresenter<View> {

    }
}