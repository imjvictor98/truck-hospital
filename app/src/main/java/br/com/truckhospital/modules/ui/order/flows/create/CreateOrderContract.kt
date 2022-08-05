package br.com.truckhospital.modules.ui.order.flows.create

import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class CreateOrderContract: BaseActivityContract {
    interface View : BaseActivityContract.BaseView {
        fun showDialog()

        fun finishForm(order: Order)

        fun showSuccess(orderId: String)
    }

    interface Presenter : BaseActivityContract.BasePresenter<View>{
        fun createOrder(order: Order)
    }
}