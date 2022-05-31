package br.com.truckhospital.modules.ui.order

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class OrderContract: BaseActivityContract {
    interface View : BaseActivityContract.BaseView {

    }

    interface Presenter : BaseActivityContract.BasePresenter<View>{

    }
}