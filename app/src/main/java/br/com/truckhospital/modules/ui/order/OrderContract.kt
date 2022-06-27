package br.com.truckhospital.modules.ui.order

import br.com.truckhospital.modules.core.model.*
import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class OrderContract: BaseActivityContract {
    interface View : BaseActivityContract.BaseView {
        fun showDialog()

        fun finishForm(order: Order)

        fun showSuccess(orderId: String)
    }

    interface Presenter : BaseActivityContract.BasePresenter<View>{
        fun createOrder(order: Order)
    }
}