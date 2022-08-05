package br.com.truckhospital.modules.ui.order.flows.show

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

interface ShowOrderContract {
    interface View: BaseActivityContract.BaseView {

    }

    interface Presenter: BaseActivityContract.BasePresenter<View> {

    }
}