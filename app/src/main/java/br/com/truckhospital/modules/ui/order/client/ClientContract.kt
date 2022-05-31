package br.com.truckhospital.modules.ui.order.client

import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract

class ClientContract {
    interface View: BaseFragmentContract.BaseView {

    }

    interface Presenter: BaseFragmentContract.BasePresenter<View> {
    }
}